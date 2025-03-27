<template>
  <div class="app-container">
    <h3>杭钢集团后台管理系统</h3>
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-input v-model="queryParams.status" placeholder="请输入状态" clearable @keyup.enter.native="handleQuery" />
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
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
          @click="handleDeleteCompany" v-hasPermi="['system:companyProperty:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:companyProperty:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图片名称" align="center" prop="name" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="图片" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.url" alt="" style="width: 60px;height: 80px;" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="handleshowCompany(scope.row.id)"
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

  
    <el-dialog :title="title" :visible.sync="openCompany" width="500px" append-to-body>
      <el-form ref="formCompany" :model="formCompany" :rules="rules" label-width="80px">
        <el-form-item label="图片名称" prop="name">
          <el-input v-model="formCompany.name" placeholder="请输入图片名称" />
        </el-form-item>
        <el-form-item label="图片状态" prop="status">
          <el-input v-model="formCompany.status" placeholder="请输入图片状态" />
        </el-form-item>
        <el-form-item label="图片地址" prop="url">
          <el-upload class="avatar-uploader" action="http://localhost:8080/api/policy/cailiaoupload" :show-file-list="false"
            :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" v-model="formCompany.url">
            <img v-if="formCompany.url" :src="formCompany.url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitCompanyForm">确 定</el-button>
        <el-button @click="cancel('company')">取 消</el-button>
      </div>
    </el-dialog> -->



  </div>
</template>

<script>
import {
  addCarousel,
  UpdateCarousel,
  ListCarousel,
  listCarouselById,
  DelCarousel


} from "@/api/system/carousel";

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
      // 指标表格数据
      companyList: [],

      dataItemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      openCompany: false,
      // 查询参数
      queryParams: {
        name: null,
        status: null,
        url: null,
      },
      // 表单参数
      formCompany: {},
      formCategory: {},
      formPropertyData: {},
      companyDetailData: {},
      // 表单校验
      rules: {},
      //categroy_id
      nowId: 0,
      nowCompanyId: 0,
      isAdd: false,

    };
  },
  created() {
    // this.getList();
  },
  methods: {
    beforeAvatarUpload(file) {
    // 可以在这里检查文件类型、大小等
    // 例如，只允许上传图片
    const isImage = file.type.startsWith('image/');
    if (!isImage) {
      this.$message.error('只能上传图片文件');
    }
    return isImage;
  },
    handleAvatarSuccess(response, file) {
    // 假设后端返回的文件 URL 在 response.data.url
    this.formCompany.url = response.data.url;  // 更新表单中的图片URL
  },

    /** 查询指标列表 */
    getList() {
      this.loading = true;
      ListCarousel(this.queryParams).then((response) => {
        console.log("xiaomao")
        console.log(response.row)
        this.companyList = response.rows;
        console.log("xiaomao2")
        console.log(this.companyList)
        console.log(response.total)
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel(target) {
      if (target === "company") {
        this.openCompany = false;
      }
      this.reset(target);
    },
    // 表单重置
    reset(target) {
      if (target === "company") {
        this.formCompany = {
          id: null,
          name: null,
          status: null,
          url: null,
        };
      }
      // this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleshowCompany(id) {
      this.openCompany = true;
      listCarouselById(id).then((response) => {
        this.formCompany = response.rows[0];
        this.openCompany = true
      })
    },
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

    /** 修改按钮操作 */
    handleUpdateCompany(row) {
      this.reset("company");
      const id = row.id;
      listCarouselById(id).then((response) => {
        console.log("小猫")
        console.log(response.rows[0])
        this.formCompany = response.rows[0];
        this.openCompany = true;
        this.title = "修改企业信息";
      });
    },
    /** 提交按钮 */

    submitCompanyForm() {
      this.$refs["formCompany"].validate((valid) => {
        if (valid) {
          if (this.formCompany.id != null) {
            UpdateCarousel(this.formCompany).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.openCompany = false;
              this.getList();
            });
          } else {
            addCarousel(this.formCompany).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.openCompany = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDeleteCompany(row) {
      const ids = row.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          return DelCarousel(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
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
    createPropertyFilter(queryString) {
      return (state) => {
        return (
          state.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.name.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
    createIndicatorFilter(queryString) {
      return (state) => {
        return (
          state.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.name.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
  },
};
</script>
