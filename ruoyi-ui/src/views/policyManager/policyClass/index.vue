<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="属性名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入属性名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleCategoryAdd"
          v-hasPermi="['system:policyClass:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single"
          @click="handleUpdatePolicyCategory" v-hasPermi="['system:policyClass:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
          @click="handleDeletePolicyCategoryBatch" v-hasPermi="['system:policyClass:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:policyClass:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="policyCategoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="属性名" align="center" prop="name" />
      <el-table-column label="是否生效" align="center" prop="status">
        <template #default="scope">
          <el-checkbox v-model="scope.row.status" @change="updateStatus(scope.row)" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="getCategoryData(scope.row.id)"
            v-hasPermi="['system:policyClass:query']">查看</el-button>
          <el-button size="mini" type="text" @click="handleUpdatePolicyCategory(scope.row)"
            v-hasPermi="['system:policyClass:edit']">修改</el-button>
          <el-button size="mini" type="text" @click="handleDeletePolicyCategory(scope.row)"
            v-hasPermi="['system:policyClass:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改policy_category_data对话框 -->
    <el-dialog :title="title" :visible.sync="openCategoryData" width="500px" append-to-body>
      <el-form ref="formCategoryData" :model="formCategoryData" :rules="rules" label-width="80px">
        <el-form-item label="内容" prop="content">
          <el-input v-model="formCategoryData.content" placeholder="请输入属性明细" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitCategoryDataForm">确 定</el-button>
        <el-button @click="cancel('categoryData')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改policy_category对话框 -->
    <el-dialog :title="title" :visible.sync="openCategory" width="500px" append-to-body>
      <el-form ref="formCategory" :model="formCategory" :rules="rules" label-width="80px">
        <el-form-item label="属性名" prop="name">
          <el-input v-model="formCategory.name" placeholder="请输入属性名" />
        </el-form-item>
        <el-form-item label="生效状态" prop="status">
          <el-input v-model="formCategory.status" placeholder="请输入生效状态" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitCategoryForm">确 定</el-button>
        <el-button @click="cancel('category')">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 二级菜单 -->
    <el-dialog title="分类明细" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleCategoryDataAdd"
          style="position: absolute; top: 1.8vh; left: 6vw" v-hasPermi="['system:policyClass:add']">新增</el-button>
      </el-col>
      <div class="table">
        <el-table :data="categroyData" height="250" border style="width: 100%">
          <el-table-column type="index" width="50" label="序号" align="center">
          </el-table-column>
          <el-table-column prop="content" label="内容" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleUpdatePolicyCategoryData(scope.row)"
                v-hasPermi="['system:policyClass:edit']">修改</el-button>
              <el-button size="mini" type="text" @click="handleDeletePolicyCategoryData(scope.row)"
                v-hasPermi="['system:policyClass:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
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
  delPolicyCategoryBatch,
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
      policyClassList: [],
      policyCategoryList: [],
      filterPolicyClassList: [],
      categroyData: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      openCategoryData: false,
      openCategory: false,
      // 查询参数
      queryParams: {
        name: null,
        status: null,
      },
      // 表单参数
      formCategory: {},
      formCategoryData: {},
      // 表单校验
      rules: {},
      //categroy_id
      nowId: 0,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getCategoryData(id) {
      this.nowId = id;
      this.dialogVisible = true;
      listPolicyCategoryDataByCategoryId(id).then((response) => {
        this.categroyData = response.rows;
      });
      console.log(id);
    },
    handleClose(done) {
      this.dialogVisible = false;
    },
    /** 查询指标列表 */
    getList() {
      this.loading = true;
      listPolicyCategory(this.queryParams).then((response) => {
        this.policyCategoryList = this.filter(response.rows);
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel(target) {
      if (target === "categoryData") {
        this.openCategoryData = false;
      } else if (target === "category") {
        this.openCategory = false;
      }
      this.reset(target);
    },
    // 表单重置
    reset(target) {
      if (target === "categoryData") {
        this.formCategoryData = {
          id: null,
          content: null,
        };
      } else if (target === "category") {
        this.formCategory = {
          id: null,
          name: null,
          status: null,
          level: null,
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
    handleCategoryAdd() {
      this.reset("category");
      this.openCategory = true;
      this.title = "添加分类属性";
    },
    handleCategoryDataAdd() {
      this.reset("categoryData");
      this.openCategoryData = true;
      this.title = "添加分类明细";
    },
    /** 修改按钮操作 */
    handleUpdatePolicyCategory(row) {
      this.reset("category");
      const id = row.id || this.ids;
      listPolicyCategoryById(id).then((response) => {
        this.formCategory = response.rows[0];
        this.openCategory = true;
        this.title = "修改属性名";
      });
    },
    handleUpdatePolicyCategoryData(row) {
      this.reset("categoryData");
      const id = row.id;
      listPolicyCategoryDataById(id).then((response) => {
        this.formCategoryData = response.rows[0];
        this.openCategoryData = true;
        this.title = "修改分类明细";
        console.log(this.formCategoryData);
        console.log(this.nowId);
      });
    },
    /** 提交按钮 */
    submitCategoryDataForm() {
      console.log("---------------------");
      console.log(this.formCategoryData);
      this.$refs["formCategoryData"].validate((valid) => {
        if (valid) {
          this.formCategoryData.policyCategoryId = this.nowId;
          console.log(this.formCategoryData.content)
          console.log(this.categroyData)
          var result=this.categroyData.filter(item => item.content == this.formCategoryData.content)
          console.log(result)
          if (this.categroyData.filter(item => item.content == this.formCategoryData.content).length>0){
            this.$message.error("与现有内容冲突")
           }
          else {
            if (this.formCategoryData.id != null) {
              updatePolicyCategoryData(this.formCategoryData).then((response) => {
                this.$modal.msgSuccess("修改成功");
                this.openCategoryData = false;
                this.dialogVisible = false;
                this.getList();
              });
            } else {
              addPolicyCategoryData(this.formCategoryData).then((response) => {
                this.$modal.msgSuccess("新增成功");
                this.openCategoryData = false;
                this.dialogVisible = false;
                this.getList();
              });
            }
          }
        }
      });
    },
    submitCategoryForm() {
      console.log("---------------------");
      console.log(this.formCategory);
      this.$refs["formCategory"].validate((valid) => {
        if (valid) {
          if (this.formCategory.id != null) {
            updatePolicyCategory(this.formCategory).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.openCategory = false;
              this.dialogVisible = false;
              this.getList();
            });
          } else {
            addPolicyCategory(this.formCategory).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.openCategory = false;
              this.dialogVisible = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDeletePolicyCategoryBatch(rows){
      console.log(this.ids);
      const ids=this.ids
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          console.log(ids);
          return delPolicyCategoryBatch(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    handleDeletePolicyCategory(row) {
      const ids = row.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          return delPolicyCategory(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    handleDeletePolicyCategoryData(row) {
      const ids = row.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          console.log("测试");
          return delPolicyCategoryData(ids);
        })
        .then(() => {
          this.dialogVisible = false;
          this.getList();
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
  },
};
</script>
