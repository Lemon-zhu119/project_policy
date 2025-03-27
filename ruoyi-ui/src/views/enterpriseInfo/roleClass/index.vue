<template>
    <div class="container">
      <div class="front">
        <div class="title">
          {{ companyName ? companyName : "公司名称" }}
          政策服务平台 {{ role ? role : "身份" }}
        </div>
        <hr />
        <div class="count">累计在线申报政策数量：{{ num ? num : 0 }}起</div>
      </div>
  
      <div class="table">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="搜索框">
            <el-input v-model="formInline.name" placeholder=""></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">搜索</el-button>
            <el-button type="primary" @click="dialogFormVisibleAdd = true"
              >添加数据</el-button
            >
          </el-form-item>
        </el-form>
  
        <!-- 表格 -->
        <el-table
          :indent="indents"
          v-loading="listLoading"
          :data="list.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
          element-loading-text="Loading"
          row-key="policyType"
          border
          fit
          highlight-current-row
          max-height="1000"
          lazy
          :load="load"
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
        >
          <!-- <el-table-column type="selection" width="55" /> -->
  
          <el-table-column align="center" label="序号" type="">
            <template slot-scope="scope">
              {{ scope.$index + 1 }}
            </template>
          </el-table-column>
  
          <el-table-column label="角色" align="center">
            <template slot-scope="scope">
              {{ scope.row.name }}
            </template>
          </el-table-column>
  
          <el-table-column label="是否有效" align="center" type="">
            <template slot-scope="scope">
              <el-checkbox :value="scope.row.submissionStatus"></el-checkbox>
            </template>
          </el-table-column>
  
          <el-table-column label="排序" align="center" type="">
            <template slot-scope="scope">
              {{ scope.row.rank }}
            </template>
          </el-table-column>
  
          <el-table-column align="center" type="">
            <template slot="header" slot-scope="scope">
              <!-- <el-input v-model="search" size="mini" placeholder="输入关键字搜索" /> -->
              操作
            </template>
            <template slot-scope="scope">
              <el-button size="mini" @click="initFormData(scope.$index)"
                >修改</el-button
              >
              <el-button size="mini" type="danger" @click="open(scope.$index)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
  
        <!-- 添加数据 -->
        <!-- <el-dialog :visible.sync="dialogFormVisibleAdd">
          <el-form :model="formAdd">
            <el-form-item label="角色" :label-width="formLabelWidth">
              <el-input
                v-model="formAdd.name"
                autocomplete="off"
                placeholder="角色"
              ></el-input>
            </el-form-item>
            <el-form-item label="发布部门" :label-width="formLabelWidth">
              <el-input
                v-model="formAdd.publishDep"
                autocomplete="off"
                placeholder="发布部门"
              ></el-input>
            </el-form-item>
            <el-form-item label="发布时间" :label-width="formLabelWidth">
              <el-input
                v-model="formAdd.policyDate"
                autocomplete="off"
                placeholder="发布时间"
              ></el-input>
            </el-form-item>
            <el-form-item label="政策期限" :label-width="formLabelWidth">
              <el-input
                v-model="formAdd.deadline"
                autocomplete="off"
                placeholder="政策期限"
              ></el-input>
            </el-form-item>
            <el-form-item label="政策标签" :label-width="formLabelWidth">
              <el-input
                v-model="formAdd.policyTab"
                autocomplete="off"
                placeholder="政策标签"
              ></el-input>
            </el-form-item>
            <el-form-item label="政策内容" :label-width="formLabelWidth">
              <el-link href="">优质中小企业梯度培育管理暂行办法.pdf</el-link>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
            <el-button type="primary" @click="handleAdd()">确 定</el-button>
          </div>
        </el-dialog>
   -->
        <!-- 分页器 -->
        <div class="block" style="margin-top: 10vh">
          <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="list.length"
          >
          </el-pagination>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        companyName: "",
        role: "",
        num: 0,
        indents: 0,
        list: [
          { 
            name: "admin",
            submissionStatus: true,
            rank: "一级",
              hasChildren: true,
            children: [
              {
                policyType: "科技局",
                submissionStatus: true,
                rank: "二级",
              },
            ],
          },
          {
            name: "管理者",
            submissionStatus: true,
            rank: "一级",
          },
          {
            name: "子公司",
            submissionStatus: true,
            rank: "一级",
          },
          {
            name: "运维者",
            submissionStatus: true,
            rank: "一级",
          }
        ],
        listLoading: true,
        search: "",
        dialogTableVisible: false,
        dialogFormVisibleAdd: false,
        dialogFormVisible: false,
        nowId: 0,
        form: {
          policyType: "",
          submissionStatus: 0,
          rank: "",
          role: "",
          id: "",
        },
        formAdd: {
          policyName: "",
          publishDep: "",
          publishDate: "",
          deadline: "",
          id: "",
          policyTab: "",
          policyInfo: "",
        },
        formInline: {
          name: "",
          sex: "",
          age: "",
          role: "",
        },
        formLabelWidth: "120px",
        currentPage: 1,
        currentIndex: "",
        pagesize: 10,
      };
    },
    created() {
      this.fetchData();
    },
    methods: {
      handleSizeChange(val) {
        this.pagesize = val;
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
      },
      async fetchData() {
        this.listLoading = true;
        //   await getList(this.formInline).then((response) => {
        //     this.list = response.data;
        //     this.listLoading = false;
        //   });
        this.listLoading = false;
      },
      load(tree, treeNode, resolve) {
        setTimeout(() => {
          resolve([
            {
              policyType: "科技局",
              submissionStatus: 1,
              rank: "二级",
            },
          ]);
        }, 1000);
      },
      onSubmit() {},
    },
  };
  </script>
  
  <style lang="scss" scoped>
  .container {
    overflow: hidden;
    width: 100%;
    height: 100vh;
    background-color: #f2f2f2;
    .front {
      margin: 40px;
      font-size: 20px;
      background-color: white;
      .title {
      }
      .count {
      }
    }
    .table {
      margin: 40px;
    }
  }
  </style>
  