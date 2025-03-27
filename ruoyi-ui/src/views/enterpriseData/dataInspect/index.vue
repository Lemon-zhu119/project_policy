<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业名称" prop="companyName">
        <el-input v-model="queryParams.companyName" placeholder="请输入企业名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="法人" prop="legalPerson">
        <el-input v-model="queryParams.legalPerson" placeholder="请输入法人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="queryParams.address" placeholder="请输入地址" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleCompanyAdd"
          v-hasPermi="['system:companyProperty:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdateCompany"
          v-hasPermi="['system:companyProperty:edit']">修改</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDeleteCompanyBatch"
          v-hasPermi="['system:companyProperty:remove']"
          >删除</el-button
        >
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:companyProperty:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="法人" align="center" prop="legalPerson" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="getCompanyDetail(scope.row.id)"
            v-hasPermi="['system:companyProperty:query']">查看</el-button>
          <el-button size="mini" type="text" @click="handleUpdateCompany(scope.row)"
            v-hasPermi="['system:companyProperty:edit']">修改</el-button>
          <el-button size="mini" type="text" @click="handleDeleteCompany(scope.row)"
            v-hasPermi="['system:companyProperty:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改company_property对话框 -->
    <el-dialog :title="title" :visible.sync="openPropertyData" width="500px" append-to-body>
      <el-form ref="formPropertyData" :model="formPropertyData" :rules="rules" label-width="80px">
        <el-form-item label="分类属性" prop="name" v-if="isAdd">
          <el-autocomplete v-model="property" class="inline-input" :fetch-suggestions="querySearchAsyncProperty"
            placeholder="请输入内容" @select="handleSelectProperty">
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-autocomplete v-model="formPropertyData.content" class="inline-input"
            :fetch-suggestions="querySearchAsyncContent" placeholder="请输入内容" @select="handleSelectContent">
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPropertyDataForm">确 定</el-button>
        <el-button @click="cancel('property')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改company_indicator对话框 -->
    <el-dialog :title="title" :visible.sync="openIndicatorData" width="500px" append-to-body>
      <el-form ref="formIndicatorData" :model="formIndicatorData" :rules="rules" label-width="80px">
        <!-- <el-form-item label="指标名称" prop="name">
          <el-input
            v-model="formIndicatorData.name"
            placeholder="请输入指标名称"
          />
        </el-form-item> -->
        <!-- <el-form-item label="英译名称" prop="key">
          <el-input
            v-model="formIndicatorData.key"
            placeholder="请输入英译名称"
          />
        </el-form-item> -->

        <el-form-item label="指标名称" prop="name">
          <el-autocomplete v-model="indicator" class="inline-input" :fetch-suggestions="querySearchAsyncIndicator"
            placeholder="请输入内容" @select="handleSelectIndicator">
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template></el-autocomplete>
        </el-form-item>
        <el-form-item label="指标阈值" prop="threshold">
          <el-input v-model="formIndicatorData.threshold" placeholder="请输入指标阈值" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitIndicatorDataForm">确 定</el-button>
        <el-button @click="cancel('indicator')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改company对话框 -->
    <el-dialog :title="title" :visible.sync="openCompany" width="500px" append-to-body>
      <el-form ref="formCompany" :model="formCompany" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="formCompany.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司法人" prop="legalPerson">
          <el-input v-model="formCompany.legalPerson" placeholder="请输入公司法人名称" />
        </el-form-item>
        <el-form-item label="公司地址" prop="address">
          <el-input v-model="formCompany.address" placeholder="请输入公司地址" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitCompanyForm">确 定</el-button>
        <el-button @click="cancel('company')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 二级菜单 -->
    <el-dialog title="详细企业数据" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handlePropertyDataAdd"
          style="position: absolute; top: 1.8vh; left: 10vw"
          v-hasPermi="['system:companyProperty:add']">新增属性</el-button>
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleIndicatorDataAdd"
          style="position: absolute; top: 1.8vh; left: 15vw"
          v-hasPermi="['system:companyIndicator:add']">新增指标</el-button>
      </el-col>
      <div class="propertyTable">
        <el-table :data="companyPropertyList" height="250" border style="width: 100%">
          <el-table-column type="index" width="50" label="序号" align="center">
          </el-table-column>
          <el-table-column prop="name" label="属性" align="center">
          </el-table-column>
          <el-table-column prop="content" label="内容" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleUpdateCompanyPropertyData(scope.row)"
                v-hasPermi="['system:companyProperty:edit']">修改</el-button>
              <el-button size="mini" type="text" @click="handleDeletePropertyData(scope.row)"
                v-hasPermi="['system:companyProperty:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <div class="IndicatorTable">
        <el-table :data="companyIndicatorList" height="500" border style="width: 100%">
          <el-table-column type="index" label="序号" align="center">
          </el-table-column>
          <el-table-column prop="name" label="指标名称" align="center">
          </el-table-column>
          <el-table-column prop="key" label="英译" align="center">
          </el-table-column>
          <el-table-column prop="threshold" label="阈值" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleUpdateCompanyIndicatorData(scope.row)"
                v-hasPermi="['system:companyIndicator:edit']">修改</el-button>
              <el-button size="mini" type="text" @click="handleDeleteCompanyIndicator(scope.row)"
                v-hasPermi="['system:companyIndicator:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listCompany,
  updateCompany,
  addCompany,
  delCompany,
  listCompanyById,
  listCompanyCategoryDataByCategoryId,
  listCompanyIndicatorDataList,
  listCompanyPropertyDataById,
  listCompanyPropertyDataByDtoId,
  updateCompanyPropertyData,
  addCompanyProperty,
  delCompanyProperty,
  listCompanyIndicatorDataByDtoId,
  listCompanyIndicatorDataById,
  updateCompanyIndicatorData,
  addCompanyIndicator,
  delCompanyIndicator,
  listCompanyCategoryByCompanyId,
  listCompanyIndicatorDictionaryByCompanyId,
} from "@/api/system/company";
import { delCompanyBatch } from "../../../api/system/company";

export default {
  name: "Company",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      dialogVisible: false,
      // 指标表格数据
      companyList: [],
      companyPropertyList: [],
      companyIndicatorList: [],
      formPropertyDataList: [],
      dataItemList: [],
      indicatorItemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      openPropertyData: false,
      openIndicatorData: false,
      openCompany: false,
      // 查询参数
      queryParams: {
        name: null,
        person: null,
        address: null,
      },
      // 表单参数
      formCompany: {},
      formCategory: {},
      formPropertyData: {},
      formIndicatorData: {},
      companyDetailData: {},
      // 表单校验
      rules: {},
      //categroy_id
      nowId: 0,
      nowCompanyId: 0,
      isAdd: false,
      property: "",
      indicator: "",
      contentItemList: [], // 用于存储内容选项的列表
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getCompanyDetail(id) {
      listCompanyCategoryByCompanyId(id).then((res) => {
        console.log(res);
        this.dataItemList = res.rows;
      });
      listCompanyIndicatorDictionaryByCompanyId(id).then((res) => {
        console.log(res);
        this.indicatorItemList = res.rows;
      });
      this.nowCompanyId = id;
      this.dialogVisible = true;
      listCompanyPropertyDataById(id).then((response) => {
        this.companyPropertyList = response.rows;
      });
      listCompanyIndicatorDataById(id).then((response) => {
        this.companyIndicatorList = response.rows;
      });
    },
    handleClose(done) {
      this.dialogVisible = false;
    },
    /** 查询指标列表 */
    getList() {
      this.loading = true;
      listCompany(this.queryParams).then((response) => {
        this.companyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel(target) {
      if (target === "company") {
        this.openCompany = false;
      } else if (target === "property") {
        this.openPropertyData = false;
      } else if (traget === "indicator") {
        this.openIndicatorData = false;
      }
      this.reset(target);
    },
    // 表单重置
    reset(target) {
      if (target === "company") {
        this.formCompany = {
          id: null,
          companyName: null,
          legalPerson: null,
          address: null,
        };
      } else if (target === "property") {
        this.formPropertyData = {
          id: null,
          content: null,
        };
      } else if (target === "indicator") {
        this.formIndicatorData = {
          id: null,
          name: null,
          threshold: null,
          key: null,
        };
      }
      // this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleCompanyAdd() {
      this.reset("company");
      this.openCompany = true;
      this.title = "添加分类属性";
    },
    handlePropertyDataAdd() {
      listCompanyCategoryByCompanyId(this.nowCompanyId).then((res) => {
        console.log(res);
        this.dataItemList = res.rows;
      });
      this.isAdd = true;
      this.reset("property");
      this.openPropertyData = true;
      this.title = "添加分类明细";
    },
    handleIndicatorDataAdd() {
      listCompanyIndicatorDictionaryByCompanyId(this.nowCompanyId).then(
        (res) => {
          console.log(res);
          this.indicatorItemList = res.rows;
        }
      );
      this.isAdd = true;
      this.reset("indicator");
      this.openIndicatorData = true;
      this.title = "添加指标明细";
    },
    /** 修改按钮操作 */
    handleUpdateCompany(row) {
      this.reset("company");
      const id = row.id;
      listCompanyById(id).then((response) => {
        this.formCompany = response.rows[0];
        this.openCompany = true;
        this.title = "修改企业信息";
      });
    },
    handleUpdateCompanyPropertyData(row) {
      this.isAdd = false;
      this.nowId = row.id;
      this.reset("property");
      const id = row.id;
      listCompanyPropertyDataByDtoId(id).then((response) => {
        console.log(response);
        this.formPropertyData = response.rows[0];
        this.openPropertyData = true;
        this.title = "修改分类明细";
        listCompanyCategoryDataByCategoryId(this.formPropertyData.categoryId).then((response) => {
          this.contentItemList=response.rows
        })
      });

    },
    handleUpdateCompanyIndicatorData(row) {
      this.isAdd = false;
      this.nowId = row.id;
      this.reset("indicator");
      const id = row.id;
      listCompanyIndicatorDataByDtoId(id).then((response) => {
        console.log(response);
        this.formIndicatorData = response.rows[0];
        this.indicator = this.formIndicatorData.name;
        this.openIndicatorData = true;
        this.title = "修改分类明细";
      });
    },
    /** 提交按钮 */
    submitPropertyDataForm() {
      this.formPropertyData.companyId = this.nowCompanyId;
      this.$refs["formPropertyData"].validate((valid) => {
        if (valid) {
          if (!this.isAdd) {
            updateCompanyPropertyData(this.formPropertyData).then(
              (response) => {
                this.$modal.msgSuccess("修改成功");
                this.openPropertyData = false;
                this.dialogVisible = false;
                this.property = "";
                this.getList();
              }
            );
          } else {
            this.formPropertyData.id = this.nowId;
            this.formPropertyData.companyId = this.nowCompanyId;
            this.formPropertyData.name = this.property;
            console.log(this.formPropertyData);
            const matchingItem = this.dataItemList.find(
              (item) => item.name === this.property
            );
            if (!matchingItem) {
              this.$message.error("请按照指标名标准选择指标名");
            } else {
              addCompanyProperty(this.formPropertyData).then((response) => {
                listCompanyPropertyDataById(this.nowCompanyId).then(
                  (response) => {
                    this.companyPropertyList = response.rows;
                  }
                );
                listCompanyPropertyDataById(this.nowCompanyId).then(
                  (response) => {
                    this.companyPropertyList = response.rows;
                  }
                );
                this.$modal.msgSuccess("新增成功");
                this.property = "";
                this.openPropertyData = false;
              });
            }
          }
        }
      });
    },
    submitIndicatorDataForm() {
      this.$refs["formIndicatorData"].validate((valid) => {
        console.log(this.formIndicatorData);
        if (valid) {
          if (this.formIndicatorData.id != null) {
            updateCompanyIndicatorData(this.formIndicatorData).then(
              (response) => {
                this.$modal.msgSuccess("修改成功");
                this.openIndicatorData = false;
                this.dialogVisible = false;
                this.indicator = "";
                this.getList();
              }
            );
          } else {
            this.formIndicatorData.id = this.nowId;
            this.formIndicatorData.companyId = this.nowCompanyId;
            this.formIndicatorData.name = this.indicator;
            console.log(this.formIndicatorData);
            const matchingItem = this.indicatorItemList.find(
              (item) => item.name === this.indicator
            );
            this.formIndicatorData.key = matchingItem.key;
            if (!matchingItem) {
              this.$message.error("请按照指标名标准选择指标名");
            } else {
              addCompanyIndicator(this.formIndicatorData).then((response) => {
                listCompanyIndicatorDataById(this.nowCompanyId).then(
                  (response) => {
                    this.companyIndicatorList = response.rows;
                  }
                );
                listCompanyIndicatorDataById(this.nowCompanyId).then(
                  (response) => {
                    this.companyIndicatorList = response.rows;
                  }
                );
                this.$modal.msgSuccess("新增成功");
                this.indicator = "";
                this.openIndicatorData = false;
              });
            }
          }
        }
      });
    },
    submitCompanyForm() {
      this.$refs["formCompany"].validate((valid) => {
        if (valid) {
          if (this.formCompany.id != null) {
            updateCompany(this.formCompany).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.openCompany = false;
              this.dialogVisible = false;
              this.getList();
            });
          } else {
            addCompany(this.formCompany).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.openCompany = false;
              this.dialogVisible = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDeleteCompanyBatch(rows) {
      console.log(this.ids);
      const ids = this.ids
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          console.log(ids);
          return delCompanyBatch(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    handleDeleteCompany(row) {
      const ids = row.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCompany(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    handleDeleteCompanyIndicator(row) {
      const ids = row.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCompanyIndicator(ids);
        })
        .then(() => {
          this.$modal.msgSuccess("删除成功");
          listCompanyIndicatorDataById(this.nowCompanyId).then((response) => {
            this.companyIndicatorList = response.rows;
          });
          listCompanyIndicatorDictionaryByCompanyId(id).then((res) => {
            console.log(res);
            this.indicatorItemList = res.rows;
          });
        })
        .catch(() => { });
    },
    handleDeletePropertyData(row) {
      const ids = row.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCompanyProperty(ids);
        })
        .then(() => {
          this.$modal.msgSuccess("删除成功");
          listCompanyPropertyDataById(this.nowCompanyId).then((response) => {
            this.companyPropertyList = response.rows;
          });
          listCompanyCategoryByCompanyId(this.nowCompanyId).then((res) => {
            console.log(res);
            this.dataItemList = res.rows;
          });
        })
        .catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/companyProperty/export",
        {
          ...this.queryParams,
        },
        `companyClass_${new Date().getTime()}.xlsx`
      );
    },
    updateStatus(data) {
      if (data.status) {
        data.status = 1;
      } else if (!data.status) {
        data.status = 0;
      }
      updateCompanyCategory(data).then((response) => {
        this.getList();
      });
    },
    querySearchAsyncProperty(queryString, cb) {
      var dataItemList = this.dataItemList;
      var results = queryString
        ? dataItemList.filter(this.createPropertyFilter(queryString))
        : dataItemList;
      results = results.map((data) => {
        return {
          id: data.id,
          value: data.name,
          name: data.name,
        };
      });
      cb(results);
    },
    createPropertyFilter(queryString) {
      return (state) => {
        return (
          state.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.name.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
    handleSelectProperty(item) {
      // 这里需要调用后端API获取对应的内容选项
      // 示例代码，需要根据实际API进行修改
      console.log(item)
      listCompanyCategoryDataByCategoryId(item.id).then(response => {
        console.log(response)
        this.contentItemList = response.rows;
        console.log(this.contentItemList)
      });
    },
    querySearchAsyncIndicator(queryString, cb) {
      var dataItemList = this.indicatorItemList;
      var results = queryString
        ? dataItemList.filter(this.createIndicatorFilter(queryString))
        : dataItemList;
      results = results.map((data) => {
        return {
          value: data.name,
          name: data.name,
        };
      });
      cb(results);
    },
    createIndicatorFilter(queryString) {
      return (state) => {
        return (
          state.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.name.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
    handleSelectIndicator(item) {
      // console.log(item);
    },
    querySearchAsyncContent(queryString, cb) {
      var contentItemList = this.contentItemList;
      var results = queryString
        ? contentItemList.filter(this.createContentFilter(queryString))
        : contentItemList;
      results = results.map((data) => {
        return {
          value: data.content,
          content: data.content,
        };
      });
      cb(results);
    },
    createContentFilter(queryString) {
      return (state) => {
        return (
          state.content.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.content.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
    handleSelectContent(item) {
      this.formPropertyData.content = item.content;
    },
  },
};
</script>
