<template>
  <div class="container">
    <div class="table">
      <!-- 表格 -->
      <el-table :indent="indents" v-loading="listLoading"
        :data="list.slice((currentPage - 1) * pagesize, currentPage * pagesize)" element-loading-text="Loading" border
        fit highlight-current-row max-height="1000">
        <el-table-column type="selection" width="55" />

        <el-table-column align="center" label="序号">
          <template slot-scope="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>

        <el-table-column label="申报单位" align="center">
          <template slot-scope="scope">
            {{ scope.row.companyName }}
          </template>
        </el-table-column>

        <el-table-column label="申报政策" align="center">
          <template slot-scope="scope">
            {{ scope.row.policyName }}
          </template>
        </el-table-column>

        <el-table-column label="审核评价" align="center">
          <template slot-scope="scope">
            {{
              scope.row.progressDescription
                ? scope.row.progressDescription
                : "尚未审核"
            }}
          </template>
        </el-table-column>

        <el-table-column label="材料状态" align="center">
          <template slot-scope="scope">
            {{ scope.row.materialStatus==="1"?"已上交":"未上交" }}
          </template>
        </el-table-column>

        <el-table-column label="审核状态" align="center">
          <template slot-scope="scope">
            {{ scope.row.submissionStatus === "1" ? "已通过" : scope.row.submissionStatus === "0" ? "已驳回" : "未审核" }}
          </template>
        </el-table-column>

        <el-table-column align="center">
          <template slot="header" slot-scope="scope">
            <!-- <el-input v-model="search" size="mini" placeholder="输入关键字搜索" /> -->
            操作
          </template>
          <template slot-scope="scope">
            <!-- <el-button size="mini" @click="dialogTableVisible = true"
              >查看</el-button
            > -->
            <el-button size="mini" @click="audit(scope)" :disabled="scope.row.submissionStatus==='1'||(scope.row.submissionStatus==='2'&&scope.row.materialStatus==='0')">审核</el-button>
            <!-- <el-button
              size="mini"
              @click="handleDelete(scope.$index)"
              >删除</el-button
            > -->
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页器 -->
      <div class="block" style="margin-top: 10vh">
        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :current-page="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper" :total="list.length">
        </el-pagination>
      </div>
    </div>

    <el-dialog :visible.sync="dialogVisible" width="50%" :before-close="handleClose" class="submission_dialog">
      <div class="form" style="height: 70vh">
        <div class="title">政策材料审核</div>

        <div class="body">
          <div>
            申报单位：<span style="color: black; font-weight: 100">{{
              form.companyName
            }}</span>
          </div>
          <div>
            政策名称：<span style="color: black; font-weight: 100">{{
              form.policyName
            }}</span>
          </div>
          <div>材料详情</div>
          <div class="table-container">
            <el-table :data="tableData" border :max-height="300">
              <el-table-column align="center" label="序号">
                <template slot-scope="scope">
                  {{ scope.$index + 1 }}
                </template>
              </el-table-column>
              <el-table-column prop="name" label="材料名称" align="center">
              </el-table-column>
              <el-table-column prop="uploadDate" label="提交时间" align="center">
              </el-table-column>
              <el-table-column prop="filePath" label="文件路径" align="center">
                <template scope="scope">
                  <div @click="downloadFile(scope)" style="color: blue">
                    {{ scope.row.filePath }}
                  </div>
                </template>
              </el-table-column>
              <!-- <el-table-column prop="document" label="材料上传" align="center">
              </el-table-column> -->
            </el-table>
          </div>

          <div class="submission">
            材料审核：
            <el-radio-group v-model="auditForm.auditStatus">
              <el-radio label="通过"></el-radio>
              <el-radio label="驳回"></el-radio>
            </el-radio-group>
          </div>

          <div class="advice">
            审核意见：<el-input type="textarea" v-model="auditForm.auditAdvice"></el-input>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="getDeName()">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog :visible.sync="dialogTableVisible" title="申报进程" align="center">
      <el-table :data="processForm">
        <el-table-column property="submissionStatus" label="申报状态"></el-table-column>
        <el-table-column property="submissionDesc" label="申报意见"></el-table-column>
        <el-table-column property="submissionDesc" label="审核部门"></el-table-column>
        <el-table-column property="submissionDate" label="申报时间"></el-table-column>
        <el-table-column align="center">
          <template slot="header" slot-scope="scope">
            <!-- <el-input v-model="search" size="mini" placeholder="输入关键字搜索" /> -->
            操作
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="dialogTableVisible = true">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <el-dialog title="提示" :visible.sync="dNameDialogVisible" width="30%" center>
      <div>
        <el-input v-model="dName" placeholder="请输入您的部门"></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dNameDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  listCompanyPolicySubmission,
  delCompanyPolicySubmission,
  listPolicySubmissionDoc,
  updateCompanyPolicySubmission,
  addPolicyGovernmentAudit
} from "@/api/system/companyPolicySubmission";
export default {
  data() {
    return {
      role: "",
      num: 0,
      indents: 0,
      list: [],
      tableData: [],
      processForm: [],
      listLoading: true,
      search: "",
      dialogTableVisible: false,
      dialogFormVisibleAdd: false,
      dialogFormVisible: false,
      dialogVisible: false,
      dialogTableVisible: false,
      dNameDialogVisible: false,
      nowId: 0,
      form: {
        policyType: "",
        submissionStatus: 0,
        rank: "",
        role: "",
        id: "",
      },
      submissionForm: {
        id: null,
      },
      auditForm: {
        id: null
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
        companyProperty: "",
        policy: "",
        documentStatus: "",
        submissionStatus: "",
      },
      formLabelWidth: "120px",
      currentPage: 1,
      currentIndex: "",
      pagesize: 10,
      cpsId: 0,
      dName: ""
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
      await listCompanyPolicySubmission().then((response) => {
        console.log(response);
        // this.list = response.rows;
        this.list = response.rows.filter(rows => {
          return rows.materialStatus === '1';
        });
        this.list.forEach((item) => {
          // if (item.submissionStatus === "0") {
          //   item.submissionStatus = "被驳回";
          // } else if (item.submissionStatus === "1") {
          //   item.submissionStatus = "已通过";
          // } else {
          //   item.submissionStatus = "未知状态";
          // }

          // if (item.materialStatus === "0") {
          //   item.materialStatus = "未上交";
          // } else if (item.materialStatus === "1") {
          //   item.materialStatus = "已上交";
          // } else {
          //   item.materialStatus = "未知状态";
          // }
        });
        this.listLoading = false;
      });
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
    getDeName() {
      this.dNameDialogVisible = true;
    },
    onSubmit() {
      // this.submissionForm.id = this.cpsId;
      // if (this.submissionForm.submissionStatus === "通过") {
      //   this.submissionForm.submissionStatus = "yes";
      // } else if (this.submissionForm.submissionStatus === "驳回") {
      //   this.submissionForm.submissionStatus = "no";
      // } else {
      //   this.submissionForm.submissionStatus = "pending";
      // }
      // updateCompanyPolicySubmission(this.submissionForm).then((res) => {
      //   this.dialogVisible = false;
      //   this.dNameDialogVisible=false;
      //   this.fetchData();
      //   this.$modal.msgSuccess("审核成功");
      // });
      this.auditForm = Object.assign({}, this.auditForm, this.form)
      this.auditForm.auditDepartment = this.dName
      this.auditForm.submissionId = this.cpsId
      this.auditForm.id = this.cpsId
      this.auditForm.date = null
      if (this.auditForm.auditStatus === "通过") {
        this.auditForm.auditStatus = 1;
      } else {
        this.auditForm.auditStatus = 0;
      }
      console.log(this.auditForm);
      if (this.dName) {
        updateCompanyPolicySubmission(this.auditForm).then((res) => {
          console.log(res)
          this.dialogVisible = false;
          this.dNameDialogVisible = false;
          this.auditForm = {}
          this.fetchData();
          this.$modal.msgSuccess("审核成功");
        })
      } else {
        this.$message({
          showClose: true,
          message: "请填写您隶属部门",
          type: 'error'
        });
      }
    },
    handleClose(done) {
      this.dialogVisible = false;
    },
    getCurrentDate() {
      var now = new Date(); // 获取当前日期和时间
      var year = now.getFullYear(); // 获取年份
      var month = now.getMonth() + 1; // 获取月份，月份从0开始，所以需要+1
      var day = now.getDate(); // 获取日

      // 将月份和日期格式化为两位数，如果小于10，则前面补0
      month = month < 10 ? '0' + month : month;
      day = day < 10 ? '0' + day : day;

      var dateStr = year + '-' + month + '-' + day; // 拼接成字符串
      return dateStr; // 返回格式化后的日期字符串
    },
    handleDelete(index) {
      index += 1;
      this.$modal
        .confirm('是否确认删除指标编号为"' + index + '"的数据项？')
        .then(function () {
          return delCompanyPolicySubmission(index);
        })
        .then(() => {
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    audit(scope) {
      this.form = this.list[scope.$index];
      this.cpsId = this.list[scope.$index].id;
      listPolicySubmissionDoc(this.list[scope.$index].id).then((res) => {
        console.log(res);
        this.tableData = res.rows;
      });
      console.log(this.form);
      this.dialogVisible = true;
    },
    downloadFile(scope) {
      const link = document.createElement("a");
      link.href = scope.row.filePath;
      link.setAttribute("download", scope.row.name);
      link.click();
    },
  },
};
</script>

<style lang="scss">
.container {
  input[aria-hidden="true"] {
    display: none !important;
  }

  .el-radio:focus:not(.is-focus):not(:active):not(.is-disabled) .el-radio__inner {
    box-shadow: none;
  }

  overflow: hidden;
  width: 100%;
  height: 100vh;
  background-color: #f2f2f2;

  .submission_dialog {
    .el-table__header-wrapper {
      margin: 0px !important;
    }

    .form {
      .title {
        text-align: center;
        font-size: 20px;
        font-weight: bolder;
      }

      .body {
        div:nth-child(1) {
          margin: 20px 0px;
          font-size: 16px;
          font-weight: bolder;
        }

        div:nth-child(2) {
          margin: 20px 0px;
          font-size: 16px;
          font-weight: bolder;
        }

        div:nth-child(3) {
          font-size: 16px;
          font-weight: bolder;
        }

        .submission {
          font-size: 16px;
          font-weight: bolder;
        }

        .advice {
          font-size: 16px;
          font-weight: bolder;
        }
      }
    }

    .el-dialog__body {
      padding: 0px 40px;
    }
  }

  .front {
    margin: 40px;
    font-size: 20px;
    background-color: white;

    .title {}

    .count {}
  }

  .table {
    margin: 40px;
  }
  .table-container{
    overflow-y: auto;
  }
}
</style>
