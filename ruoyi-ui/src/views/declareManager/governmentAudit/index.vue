<template>
  <div class="container">
    <div class="table">
      <!-- <div style="margin-bottom: 20px">
        <el-button type="primary" plain icon="el-icon-plus" @click="handlePolicyGovernmentAuditAdd"
          v-hasPermi="['system:governmentAudit:add']">新增</el-button>
      </div> -->
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

        <el-table-column label="企业名称" align="center">
          <template slot-scope="scope">
            {{ scope.row.companyName }}
          </template>
        </el-table-column>

        <el-table-column label="政策名称" align="center">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>

        <!-- <el-table-column label="审核部门" align="center">
          <template slot-scope="scope">
            {{ scope.row.auditDepartment }}
          </template>
        </el-table-column> -->

        <el-table-column label="审核建议" align="center">
          <template slot-scope="scope">
            {{ scope.row.auditAdvice === '' ? '/' : scope.row.auditAdvice }}
          </template>
        </el-table-column>

        <el-table-column label="政策状态" align="center">
          <template slot-scope="scope">
            {{ scope.row.auditStatus == 1 ? "已完结" : scope.row.auditStatus == 2 ? "审核中" : "已驳回" }}
          </template>
        </el-table-column>

        <el-table-column label="开始审核日期" align="center">
          <template slot-scope="scope">
            {{ scope.row.auditDate }}
          </template>
        </el-table-column>
        <el-table-column label="结束审核日期" align="center">
          <template slot-scope="scope">
            {{ scope.row.auditExpireDate === null ? '/' : scope.row.auditExpireDate }}
          </template>
        </el-table-column>

        <el-table-column align="center">
          <template slot="header" slot-scope="scope">
            <!-- <el-input v-model="search" size="mini" placeholder="输入关键字搜索" /> -->
            操作
          </template>
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handlePolicyGovernmentAuditRecordList(scope)">查看</el-button>
            <!-- <el-button size="mini" @click="dialogVisible = true"
              >审核</el-button
            > -->
            <!-- <el-button size="mini" @click="handleDelete(scope.$index)"
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
    <!-- 三级目录的修改 -->
    <el-dialog title="修改审核记录" :visible.sync="dialogVisibleUpdate" width="40%" :before-close="handleClose">
      <div class="updateForm">
        <el-form ref="form" :model="form" label-width="100px">
          <!-- <el-form-item label="政策名称">
            <el-select v-model="policyName" filterable placeholder="请选择">
              <el-option v-for="item in policyList" :key="item.id" :label="item.name" :value="item.name">
              </el-option>
            </el-select>
          </el-form-item> -->
          <el-form-item label="审核部门" placeholder="请选择审核部门">
            <el-input v-model="form.auditDepartment"></el-input>
          </el-form-item>
          <el-form-item label="审核建议" placeholder="请填写审核建议">
            <el-input v-model="form.auditAdvice"></el-input>
          </el-form-item>
          <el-form-item label="政策状态">
            <el-select v-model="form.auditStatus" placeholder="请选择政策状态">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="审核日期">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.auditDate"
                style="width: 100%"></el-date-picker>
            </el-col>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleUpdate = false">取 消</el-button>
        <el-button type="primary" @click="update()">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 三级目录的添加 -->
    <el-dialog :visible.sync="dialogVisibleAdd" title="增加审核记录" width="40%">
      <div class="formAdd">
        <el-form ref="formAdd" :model="formAdd" label-width="80px">
          <!-- <el-form-item label="政策名称">
            <el-select v-model="policyNameAdd" filterable placeholder="请选择">
              <el-option v-for="item in policyList" :key="item.id" :label="item.name" :value="item.name">
              </el-option>
            </el-select>
          </el-form-item> -->
          <el-form-item label="审核建议">
            <el-input v-model="formAdd.auditAdvice" placeholder="请输入审核建议"></el-input>
          </el-form-item>
          <el-form-item label="审核部门">
            <el-input v-model="formAdd.auditDepartment" placeholder="请输入审核部门"></el-input>
          </el-form-item>
          <el-form-item label="政策状态">
            <el-select v-model="formAdd.auditStatus" placeholder="请选择政策状态" value-key="value">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="审核日期">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="formAdd.auditDate"
                style="width: 100%"></el-date-picker>
            </el-col>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleAdd = false">取 消</el-button>
        <el-button type="primary" @click="add()">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 二级目录 -->
    <el-dialog title="审核明细" :visible.sync="dialogTableVisible" width="60%" :before-close="handleClose">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd()"
          style="position: absolute; top: 1.8vh; left: 6vw" v-hasPermi="['system:policyClass:add']"
          v-if="isCanAdd">新增</el-button>
      </el-col>
      <div class="table">
        <el-table :data="GovernmentAuditRecordList" height="250" border style="width: 100%">
          <el-table-column type="index" width="50" label="序号" align="center">
          </el-table-column>
          <el-table-column prop="auditDepartment" label="审核部门" align="center">
          </el-table-column>
          <el-table-column prop="auditAdvice" label="审核建议" align="center">
          </el-table-column>
          <el-table-column prop="auditStatus" label="审核状态" align="center" :formatter="statusFormatter">
          </el-table-column>
          <el-table-column prop="auditDate" label="审核时间" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" @click="handleUpdateGovernmentAuditRecord(scope)"
                v-hasPermi="['system:policyContent:edit']">修改</el-button>
              <el-button size="mini" type="text" @click="handleDeleteGovernmentAudtiRecord(scope.row)"
                v-hasPermi="['system:policyContent:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
    <el-dialog>

    </el-dialog>
  </div>
</template>

<script>
import {
  listGovernmentAuditRecord,
  listCompany,
  governmentAuditRecord,
  delCompanyPolicySubmission,
  listPolicyGovernmentAuditList,
  listPolicy,
  updatePolicyGovernmentAudit,
  addPolicyGovernmentAudit,
  addPolicyGovernmentAuditRecord,
  delGovernmentAudtiRecord,
  updatePolicyGovernmentAuditRecord,
} from "@/api/system/companyPolicySubmission";
export default {
  data() {
    return {
      role: "",
      num: 0,
      indents: 0,
      auditId: 0,
      list: [],
      tableData: [],
      processForm: [],
      companyList: [],
      policyList: [],
      listLoading: true,
      search: "",
      dialogVisibleUpdate: false,
      dialogFormVisible: false,
      dialogVisible: false,
      dialogVisibleAdd: false,
      dialogTableVisible: false,
      dialogVisibleAuditAdd: false,
      nowId: 0,
      form: {},
      GovernmentAuditRecordList: [],
      formAdd: {
        policyId: null,
        auditDepartment: "",
        auditStatus: null,
        auditAdvice: "",
        id: "",
        auditDate: "",
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
      policyName: "",
      companyName: "",
      policyNameAdd: "",
      isCanAdd: false,
      options: [
        { label: '已驳回', value: '0' },
        { label: '已完结', value: '1' },
        { label: '已通过', value: '2' }
      ]
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    handleSizeChange(val) {
      this.pagesize = val;
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    async fetchData() {
      this.listLoading = true;
      await listPolicyGovernmentAuditList().then((response) => {
        console.log(response);
        this.list = response.rows;
        this.listLoading = false;
      });
    },
    statusFormatter(row, column, cellValue, index) {
      switch (row.auditStatus) {
        case 1:
          return '已完结';
        case 0:
          return '已驳回';
        case 2:
          return '已通过';
        default:
          return '';
      }
    },
    onSubmit() { },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
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
    handleAdd(scope) {
      listPolicy().then((res) => {
        // console.log(res);
        this.policyList = res.rows;
      });
      // this.form = this.list[scope.$index];
      // this.policyName = this.form.name;
      this.dialogVisibleAdd = true;
    },
    handleUpdateGovernmentAuditRecord(scope) {
      console.log(scope.row.id)

      governmentAuditRecord(scope.row.id).then((res) => {
        console.log(res)
        this.form = res.data
        this.form.auditStatus = (this.form.auditStatus === 1 ? "已完结" : this.form.auditStatus === 0 ? "已驳回" : "已通过")
        console.log(res.data)
      })
      // this.form = this.list[scope.$index];
      // this.policyName = this.form.name;
      this.dialogVisibleUpdate = true;
    },
    update() {
      // this.form.name = this.policyName;
      // const foundPolicy = this.policyList.find(
      //   (policy) => policy.name === this.policyName
      // );
      // this.form.policyId = foundPolicy.id;
      // console.log(this.form);
      if (typeof (this.form.auditStatus) === String) {
        this.form.auditStatus === "已完结" ? this.form.auditStatus = 1 : this.form.auditStatus === '已驳回' ? this.form.auditStatus = 0 : this.form.auditStatus = 2
      }
      console.log(this.form.auditStatus)
      updatePolicyGovernmentAuditRecord(this.form).then((res) => {
        // console.log(res);
        this.fetchData();
        listGovernmentAuditRecord(this.auditId).then((res) => {
          console.log(res);
          this.GovernmentAuditRecordList = res.rows;
          this.updateCanAddStatus();
        });
        this.dialogVisibleUpdate = false;
        this.$modal.msgSuccess("修改成功");
      });
    },
    handlePolicyGovernmentAuditRecordList(scope) {
      console.log(scope.row.id)
      this.auditId = scope.row.id;
      listGovernmentAuditRecord(scope.row.id).then((res) => {
        console.log(res);
        this.GovernmentAuditRecordList = res.rows;
        this.updateCanAddStatus();
      });
      this.dialogTableVisible = true;
    },
    updateCanAddStatus() {
      this.isCanAdd = !this.GovernmentAuditRecordList.some(
        record => record.auditStatus === 1
      );
    },
    handleDeleteGovernmentAudtiRecord(obj) {
      console.log(obj.id);
      const id = obj.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + id + '"的数据项？')
        .then(function () {
          return delGovernmentAudtiRecord(id);
        })
        .then(() => {
          this.fetchData();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    add() {
      this.formAdd.auditId = this.auditId
      addPolicyGovernmentAuditRecord(this.formAdd).then((res) => {
        this.fetchData();
        this.dialogVisibleAdd = false;
        this.$modal.msgSuccess("添加成功");
      });
      console.log(this.formAdd);
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
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
          margin: 40px 20px 20px 20px;
          font-size: 16px;
          font-weight: bolder;
        }

        .submission {
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
}
</style>
