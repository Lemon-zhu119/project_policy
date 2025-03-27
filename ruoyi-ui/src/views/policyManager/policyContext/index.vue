<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="政策名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入政策名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="发布时间" prop="publishDate">
        <el-input v-model="queryParams.publishDate" placeholder="请输入发布时间" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handlePolicyAdd()"
          v-hasPermi="['system:policyContent:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdatePolicy"
          v-hasPermi="['system:policyClass:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
          @click="handleDeletePolicyBatch" v-hasPermi="['system:policContent:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:policyClass:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="el-icon-upload" size="mini" @click="openImportTable"
          v-hasPermi="['system:policyClass:import']">导入</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="policyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="政策名称" align="center" prop="name" />
      <el-table-column label="发布时间" align="center" prop="publishDate" />
      <el-table-column 
        label="简要说明" 
        align="center" 
        prop="description" 
        width="300">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.description" placement="top">
            <div class="multi-line-content">
              {{ scope.row.description }}
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column 
        label="详细内容" 
        align="center" 
        prop="content" 
        :show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="getPolicyDetailData(scope.row.id)"
            v-hasPermi="['system:policyContent:query']">查看</el-button>
          <el-button size="mini" type="text" @click="handleUpdatePolicy(scope.row)"
            v-hasPermi="['system:policyContent:edit']">修改</el-button>
          <el-button size="mini" type="text" @click="handleDeletePolicy(scope.row)"
            v-hasPermi="['system:policyClass:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改policy_category_data对话框 -->
    <el-dialog :title="title" :visible.sync="openPolicyDetailData" width="500px" append-to-body>
      <el-form ref="formPolicyDetailData" :model="formPolicyDetailData" :rules="rules" label-width="80px">
        <el-form-item label="分类属性" prop="name" v-if="isAdd">
          <el-autocomplete 
            v-model="policyAttr" 
            class="inline-input" 
            :fetch-suggestions="querySearchAsyncAttr"
            placeholder="请输入内容" 
            @select="handleSelectAttr">
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-autocomplete
            v-model="formPolicyDetailData.content"
            class="inline-input"
            :fetch-suggestions="querySearchAsyncContent"
            placeholder="请输入内容"
            @select="handleSelectContent">
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPolicyDetailDataForm">确 定</el-button>
        <el-button @click="cancel('policyDetailData')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改policy_category对话框 -->
    <el-dialog :title="title" :visible.sync="openPolicy" width="800px" append-to-body>
      <el-form ref="formPolicy" :model="formPolicy" :rules="rules" label-width="100px">
        <el-form-item label="政策名称" prop="name">
          <el-input v-model="formPolicy.name" placeholder="请输入政策名称" />
        </el-form-item>
        <el-form-item label="发布时间" prop="publishDate">
          <el-date-picker
            v-model="formPolicy.publishDate"
            type="date"
            placeholder="选择发布日期"
            value-format="yyyy-MM-dd"
            style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截止日期" prop="expireDate">
          <el-date-picker
            v-model="formPolicy.expireDate"
            type="date"
            placeholder="选择截止日期"
            value-format="yyyy-MM-dd"
            style="width: 100%">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="政策内容" prop="descrition">
          <el-input v-model="formPolicy.description" placeholder="请输入政策内容" />
        </el-form-item>
        <el-form-item label="政策原文" prop="content">
          <el-input v-model="formPolicy.content" placeholder="请输入政策原文" />
        </el-form-item>
        <el-form-item label="政策条件" prop="policyCondition">
          <el-input
            type="textarea"
            v-model="formPolicy.policyCondition"
            placeholder="请输入政策条件"
            :rows="4"
          />
        </el-form-item>
        <el-form-item label="申报材料" prop="policyDocDetail">
          <el-input
            type="textarea"
            v-model="formPolicy.policyDocDetail"
            placeholder="请输入申报材料"
            :rows="4"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPolicyForm">确 定</el-button>
        <el-button @click="cancel('policy')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 二级菜单 -->
    <el-dialog title="分类明细" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handlePolicyDetailDataAdd()"
          style="position: absolute; top: 1.8vh; left: 6vw" v-hasPermi="['system:policyClass:add']">新增</el-button>
      </el-col>
      <div class="table">
        <el-table :data="policyDetailData" height="250" border style="width: 100%">
          <el-table-column type="index" width="50" label="序号" align="center">
          </el-table-column>
          <el-table-column prop="name" label="政策属性" align="center">
          </el-table-column>
          <el-table-column prop="content" label="属性明细" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleUpdatePolicyDetailData(scope)"
                v-hasPermi="['system:policyContent:edit']">修改</el-button>
              <el-button size="mini" type="text" @click="handleDeletePolicyDetailData(scope.row)"
                v-hasPermi="['system:policyContent:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>

    <el-dialog title="导入政策表" :visible.sync="dialogVisibleImport" width="30%" :before-close="handleCloseImport">
      <div>
        <!-- 这是开发时测试路径
        action="http://localhost:8080/api/excel/uploadExcel" -->
        <!-- 这是服务器路径
        action="http://10.10.49.254:5388/api/excel/uploadExcel" -->
        <el-upload class="upload-demo" drag action="http://10.10.49.254:5388/api/excel/uploadExcel" multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">
            只能上传excel文件，且不超过500kb
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleImport = false">取 消</el-button>
        <el-button type="primary" @click="handleImportPolicy()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  listPolicyCategory,
  listPolicyCategoryDataByCategoryId,
  listPolicyCategoryDataById,
  delPolicyCategoryData,
  updatePolicyCategoryData,
  updatePolicyCategory,
  addPolicyCategoryData,
  listPolicyCategoryById,
  addPolicyCategory,
  delPolicyCategory,
  listPolicy,
  updatePolicy,
  addPolicy,
  listPolicyById,
  delPolicy,
  delPolicyBatch,
  listPolicyDetailById,
  updatePolicyDetailData,
  addPolicyDetailData,
  delPolicyDetail,
  listPolicyCategoryByPolicyId,
} from "@/api/system/policy";

export default {
  name: "PolicyClass",
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
      policyList: [],
      policyCategoryList: [],
      filterPolicyClassList: [],
      policyDetailData: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      openPolicyDetailData: false,
      openPolicy: false,
      // 查询参数
      queryParams: {
        name: null,
        publishDate: null,
        status: null,
      },
      // 表单参数
      formPolicy: {},
      formPolicyDetailData: {},
      // 表单校验
      rules: {},
      nowId: 0,
      nowPolicyId: 0,
      isAdd: false,
      policyAttr: "",
      categoryItemList: [],
      dialogVisibleImport: false,
      contentItemList: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getPolicyDetailData(id) {
      console.log(id);
      this.nowPolicyId = id; //PolicyId
      this.dialogVisible = true;
      listPolicyDetailById(id).then((response) => {
        console.log(response);
        this.policyDetailData = response.rows;
      });
      listPolicyCategoryByPolicyId(id).then((res) => {
        console.log(res);
        this.categoryItemList = res.rows;
      });
      console.log(id);
    },
    handleClose(done) {
      this.dialogVisible = false;
    },
    /** 查询指标列表 */
    getList() {
      this.loading = true;
      listPolicy(this.queryParams).then((response) => {
        this.policyList = this.filter(response.rows);
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel(target) {
      if (target === "policyDetailData") {
        this.openPolicyDetailData = false;
      } else if (target === "policy") {
        this.openPolicy = false;
      }
      this.reset(target);
    },
    // 表单重置
    reset(target) {
      if (target === "policy") {
        this.formPolicy = {
          id: null,
          name: null,
          publishDate: null,
          expireDate: null,
          description: null,
          status: null,
          content: null,
        };
      } else if (target === "policyDetailData") {
        this.formPolicyDetailData = {
          id: null,
          name: null,
          content: null,
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
      // this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handlePolicyAdd() {
      this.reset("policy");
      this.openPolicy = true;
      this.title = "添加政策";
    },
    handlePolicyDetailDataAdd() {
      listPolicyCategoryByPolicyId(this.nowPolicyId).then((res) => {
        console.log(res);
        this.categoryItemList = res.rows;
      });
      this.isAdd = true;
      this.reset("policyDetailData");
      this.openPolicyDetailData = true;
      this.title = "添加分类明细";
    },
    /** 修改按钮操作 */
    handleUpdatePolicy(row) {
      this.reset("policy");
      const id = row.id || this.ids;
      listPolicyById(id).then((response) => {
        this.formPolicy = response.rows[0];
        this.openPolicy = true;
        this.title = "修改政策信息";
      });
    },
    handleUpdatePolicyDetailData(scope) {
      this.isAdd = false;
      this.nowId = scope.row.id; //此处nowId是categoryId
      this.reset("policyDetailData");
      listPolicyDetailById(this.nowPolicyId).then((response) => {
        console.log(response);
        this.formPolicyDetailData = response.rows[scope.$index];
        this.openPolicyDetailData = true;
        this.title = "修改分类明细";
        console.log(this.formPolicyDetailData);
        console.log(this.nowId);
      });
    },
    /** 提交按钮 */
    submitPolicyDetailDataForm() {
      console.log("---------------------");
      console.log(this.formPolicyDetailData);
      console.log(this.nowId);
      this.$refs["formPolicyDetailData"].validate((valid) => {
        if (valid) {
          if (!this.isAdd) {
            updatePolicyDetailData(this.formPolicyDetailData).then(
              (response) => {
                this.$modal.msgSuccess("修改成功");
                this.openPolicyDetailData = false;
                this.dialogVisible = false;
                this.getList();
              }
            );
          } else {
            this.formPolicyDetailData.policyId = this.nowPolicyId;
            this.formPolicyDetailData.id = this.nowId;
            this.formPolicyDetailData.name = this.policyAttr;
            console.log(this.formPolicyDetailData);
            const matchingItem = this.categoryItemList.find(
              (item) => item.name === this.policyAttr
            );
            if (!matchingItem) {
              this.$message.error("请按照属性名标准选择属性");
            } else {
              addPolicyDetailData(this.formPolicyDetailData).then(
                (response) => {
                  this.openPolicyDetailData = false;
                  listPolicyDetailById(this.nowPolicyId).then((response) => {
                    this.policyDetailData = response.rows;
                  });
                  this.policyAttr = "";
                  this.$modal.msgSuccess("新增成功");
                }
              );
            }
          }
        }
      });
    },
    submitPolicyForm() {
      console.log("---------------------");
      console.log(this.formPolicy);
      this.$refs["formPolicy"].validate((valid) => {
        if (valid) {
          if (this.formPolicy.id != null) {
            updatePolicy(this.formPolicy).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.openPolicy = false;
              this.dialogVisible = false;
              this.getList();
            });
          } else {
            addPolicy(this.formPolicy).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.openPolicy = false;
              this.dialogVisible = false;
              this.getList();
            });
          }
        }
      });
    },
    handleDeletePolicyBatch(rows) {
      console.log(this.ids);
      const ids = this.ids
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          console.log("shanbi");
          console.log(ids);
          return delPolicyBatch(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    /** 删除按钮操作 */
    handleDeletePolicy(row) {
      const ids = row.id;
      console.log(row)
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          return delPolicy(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    handleDeletePolicyDetailData(row) {
      console.log(row)
      const ids = row.policyPropertiesDataId;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          console.log("测试");
          return delPolicyDetail(ids);
        })
        .then(() => {
          listPolicyDetailById(this.nowPolicyId).then((response) => {
            console.log(response);
            this.policyDetailData = response.rows;
          });
          listPolicyCategoryByPolicyId(this.nowPolicyId).then((res) => {
            console.log(res);
            this.categoryItemList = res.rows;
          });
          this.policyAttr = "";
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/policyClass/export",
        {
          ...this.queryParams,
        },
        `policyClass_${new Date().getTime()}.xlsx`
      );
    },
    filter(objects) {
      // console.log(objects);
      const uniqueNames = new Set();
      const uniqueObjects = objects.filter((obj) => {
        obj.status = obj.status ? true : false;
        if (!uniqueNames.has(obj.name)) {
          uniqueNames.add(obj.name);
          return true;
        }
        return false;
      });
      console.log(uniqueObjects);
      return uniqueObjects;
    },
    updateStatus(data) {
      if (data.status) {
        data.status = 1;
      } else if (!data.status) {
        data.status = 0;
      }
      updatePolicyCategory(data).then((response) => {
        this.getList();
      });
    },
    querySearchAsyncAttr(queryString, cb) {
      var dataItemList = this.categoryItemList;
      var results = queryString
        ? dataItemList.filter(this.createAttrFilter(queryString))
        : dataItemList;
      results = results.map((data) => {
        return {
          id:data.id,
          value: data.name,
          name: data.name,
        };
      });
      cb(results);
    },
    createAttrFilter(queryString) {
      return (state) => {
        return (
          state.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.name.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
    handleSelectAttr(item) {
      // 假设你有一个 API 方法来获取对应的内容选项
      console.log(item)
      listPolicyCategoryDataByCategoryId(item.id).then(response => {
        this.contentItemList = response.rows;
      });
    },
    openImportTable() {
      this.dialogVisibleImport = true;
    },
    handleCloseImport() {
      this.dialogVisibleImport = false;
    },
    handleImportPolicy() {
      this.getList();
      this.dialogVisibleImport = false;
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
      this.formPolicyDetailData.content = item.content;
    },
  },
};
</script>

<style lang="scss">
.el-tooltip__popper {
  max-width: 50%;
}

.upload-demo {
  text-align: center;
}

.el-table .cell {
  line-height: 1.5;
  white-space: normal;
}

.el-table .cell {
  line-height: 1.5 !important;
  white-space: normal !important;
}

.multi-line-content {
  text-align: left;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
  padding: 0 10px;
  line-height: 1.5;
  min-height: 66px;
  word-break: break-all;
  max-height: none !important;
}

.el-table__row {
  height: auto !important;
}

.el-table__cell {
  padding: 8px 0 !important;
}
</style>
