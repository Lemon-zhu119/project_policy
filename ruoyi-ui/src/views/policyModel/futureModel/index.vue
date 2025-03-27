<template>
  <div class="container">
    <div class="table">
      <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="政策名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入政策名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
      <el-form :inline="true" :model="formAdd" class="demo-form-inline">
        <el-form-item>
          <el-button type="primary" @click="modelAdd">新增模型</el-button>
        </el-form-item>
      </el-form>

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

        <el-table-column label="政策模型名称" align="center">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>

        <el-table-column label="政策状态" align="center">
          <template slot-scope="scope">
            {{ !!scope.row.status ? "本年度已发布" : "本年度未发布" }}
          </template>
        </el-table-column>

        <el-table-column label="模型材料数量" align="center">
          <template slot-scope="scope">
            {{ scope.row.docNumber }}
            <el-button size="mini" type="text" @click="handleDocNumber(scope)"
              v-hasPermi="['system:policyContent:edit']">查看信息</el-button>
          </template>
        </el-table-column>


        <el-table-column align="center">
          <template slot="header" slot-scope="scope">
            <!-- <el-input v-model="search" size="mini" placeholder="输入关键字搜索" /> -->
            操作
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="lookup(scope.row)">查看数据</el-button>
            <el-button size="mini" @click="check(scope.$index)">匹配企业</el-button>
          </template>
        </el-table-column>
        <el-table-column align="center">
          <template slot="header" slot-scope="scope">
            <!-- <el-input v-model="search" size="mini" placeholder="输入关键字搜索" /> -->
            操作
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="handleUpdateModel(scope.row)">修改基本属性</el-button>
            <el-button size="mini" type="danger" @click="delModel(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 添加数据 -->
      <el-dialog :visible.sync="dialogFormVisibleAdd">
        <el-form :model="formAdd">
          <el-form-item label="政策模型名称" :label-width="formLabelWidth">
            <el-input v-model="formAdd.name" autocomplete="off" placeholder="请输入政策模型名称"></el-input>
          </el-form-item>
          <el-form-item label="发布时间" :label-width="formLabelWidth">
            <el-input type="date" v-model="formAdd.publishDate" autocomplete="off" placeholder="请输入发布时间"></el-input>
          </el-form-item>
          <el-form-item label="截止时间" :label-width="formLabelWidth">
            <el-input type="date" v-model="formAdd.expireDate" autocomplete="off" placeholder="请输入发布时间"></el-input>
          </el-form-item>
          <el-form-item label="描述" :label-width="formLabelWidth">
            <el-input v-model="formAdd.description" autocomplete="off" placeholder="请输入模型描述"></el-input>
          </el-form-item>
          <el-form-item label="内容" :label-width="formLabelWidth">
            <el-input v-model="formAdd.content" autocomplete="off" placeholder="请输入模型内容"></el-input>
          </el-form-item>
          <el-form-item label="资金(单位:万)" :label-width="formLabelWidth">
            <el-input v-model="formAdd.money" autocomplete="off" placeholder="请输入模型资金"></el-input>
          </el-form-item>
          <el-form-item label="发布部门" :label-width="formLabelWidth">
            <el-input v-model="formAdd.source" autocomplete="off" placeholder="请输入发布部门"></el-input>
          </el-form-item>
          <!-- <el-form-item label="材料数量" :label-width="formLabelWidth">
            <el-input v-model="formAdd.docNumber" autocomplete="off" placeholder="请输入材料数量"></el-input>
          </el-form-item>  -->
          <!-- <el-form-item label="材料清单" :label-width="formLabelWidth">
            <el-input v-model="formAdd.docDetail" autocomplete="off" placeholder="请输入材料清单"></el-input>
          </el-form-item> -->
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisibleAdd = false">取 消</el-button>
          <el-button type="primary" @click="modelRecordAdd()">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 分页器 -->
      <div class="block" style="margin-top: 10vh">
        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :current-page="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper" :total="list.length">
        </el-pagination>
      </div>

      <el-dialog title="模型展示" :visible.sync="dialogVisible" width="80%" :before-close="handleClose">
        <div class="btn">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd()"
              v-hasPermi="['system:companyProperty:add']">新增</el-button>
          </el-col>
        </div>
        <div class="policy_model">
          <el-table :data="policyModelList" height="400" border style="width: 100%">
            <el-table-column align="center" label="序号">
              <template slot-scope="scope">
                {{ scope.$index + 1 }}
              </template>
            </el-table-column>
            <el-table-column prop="id" label="指标序号" align="center"></el-table-column>
            <el-table-column prop="name" label="数据项" align="center">
            </el-table-column>
            <el-table-column prop="operator" label="符号" align="center">
            </el-table-column>
            <el-table-column prop="threshold" label="阈值" align="center">
            </el-table-column>
            <el-table-column prop="complexFormula" label="复杂公式" align="center">
              <template slot-scope="scope">
                <!-- 使用 v-if 检查 complexFormulaList 是否为 null，如果是，则显示 el-dropdown -->
                <div v-if="
                  scope.row.complexFormulaList === null ||
                  scope.row.complexFormulaList.length === 0
                ">
                  <el-dropdown @command="handleDropdownCommand" key="item">
                    <el-button circle icon="el-icon-plus" size="mini">
                    </el-button>
                    <el-dropdown-menu slot="dropdown" :items="complexIndicatorList">
                      <template v-for="item in complexIndicatorList">
                        <el-dropdown-item :command="[item, scope]">{{
                          item
                        }}</el-dropdown-item>
                      </template>
                    </el-dropdown-menu>
                  </el-dropdown>
                </div>

                <!-- 使用 v-else 来显示 complexFormulaList 中的项 -->
                <div v-else>
                  <div v-for="(item, index) in scope.row.complexFormulaList" :key="index">
                    {{ item }}
                    <el-dropdown @command="handleDropdownCommand" key="item" v-if="index === 0">
                      <el-button circle icon="el-icon-plus" size="mini">
                      </el-button>
                      <el-dropdown-menu slot="dropdown" :items="complexIndicatorList">
                        <template v-for="item in complexIndicatorList">
                          <el-dropdown-item :command="[item, scope]">{{
                            item
                          }}</el-dropdown-item>
                        </template>
                      </el-dropdown-menu>
                    </el-dropdown>
                    <el-button circle icon="el-icon-minus" size="mini" @click="delIndicator(item, index, scope)" v-else>
                    </el-button>
                  </div>
                </div>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="handleDeletePolicyModel(scope.row)">删除</el-button>
                <el-button size="mini" type="primary" @click="handleUpdatePolicyModel(scope.$index)">修改</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <div class="formula" style="margin-top: 20px">
          <div style="margin-bottom: 10px">
            公式编辑器
            <el-button type="primary" plain size="mini" @click="handleUpdateFormula"
              v-hasPermi="['system:companyProperty:add']">更新</el-button>
            <el-button type="primary" @click="open" size="mini">提示</el-button>
          </div>
          <el-input v-model="formula" placeholder="请输入内容"></el-input>
        </div>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleSubmit()">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog title="新增" :visible.sync="dialogVisibleAdd" width="30%" :before-close="handleCloseAdd">
        <div class="info">
          <el-form ref="form" :model="formAdd" label-width="100px">
            <el-form-item label="数据项">
              <el-autocomplete v-model="state" class="inline-input" :fetch-suggestions="querySearchAsync"
                placeholder="请输入内容" @select="handleSelect">
                <template slot-scope="{ item }">
                  <div class="value">{{ item.value }}</div>
                </template></el-autocomplete>
            </el-form-item>
            <el-form-item label="符号">
              <el-select v-model="formAdd.operation" placeholder="请选择操作符">
                <el-option label=">" value=">"></el-option>
                <el-option label="<" value="<"></el-option>
                <el-option label="=" value="=="></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="阈值">
              <el-input v-model="formAdd.threshold"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">立即创建</el-button>
              <el-button @click="handleCloseAdd">取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>

      <el-dialog title="同年企业详情" :visible.sync="dialogVisibleCompany" width="30%" :before-close="handleCloseCompanyDetail">
        <div class="info">
          <el-table :data="companyDetailData" style="width: 100%" height="250" v-loading="loading">
            <el-table-column type="index" label="序号" align="center">
            </el-table-column>
            <el-table-column prop="companyName" label="企业公司" align="center">
            </el-table-column>
          </el-table>
        </div>
      </el-dialog>
      <el-dialog title="材料名称详情" :visible.sync="dialogVisibleDocDetailName" width="20%"
        :before-close="handleCloseDocDetailName">
        <div class="info">
          <el-input v-model="docDetailName"></el-input>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisibleDocDetailName = false">取 消</el-button>
          <el-button type="primary" @click="onSubmitDocDetailUpdate()">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog title="材料详情" :visible.sync="dialogVisibleDocDetail" width="30%" :before-close="handleCloseDocDetail">
        <el-col :span="1.5">
          <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleDocDetailAdd()"
            style="position: absolute; top: 1.8vh; left: 6vw" v-hasPermi="['system:policyClass:edit']">新增</el-button>
        </el-col>
        <div class="info">
          <el-table :data="docDetail" style="width: 100%" height="250">
            <el-table-column type="index" label="序号" align="center">
            </el-table-column>
            <el-table-column prop="name" label="材料名称" align="center">
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini" type="text" @click="handleUpdateDocDetail(scope)"
                  v-hasPermi="['system:DocDetail:edit']">修改</el-button>
                <el-button size="mini" type="text" @click="handleDeleteDocDetail(scope)"
                  v-hasPermi="['system:DocDetail:remove']">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-dialog>

      <!-- 修改单项模型指标 -->
      <el-dialog title="单项模型修改" :visible.sync="dialogVisibleUpdate" width="30%" :before-close="handleCloseUpdate">
        <div class="updateForm">
          <el-form :inline="true" :model="formInline" label-position="left" label-width="60px">
            <el-form-item label="阈值">
              <el-input v-model="formInline.threshold" placeholder="阈值"></el-input>
            </el-form-item>
            <el-form-item label="操作符">
              <el-select v-model="formInline.operator" placeholder="操作符">
                <el-option label=">" value=">"></el-option>
                <el-option label="<" value="<"></el-option>
                <el-option label="=" value="=="></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="onSubmitUpdate()">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {
  updatePolicy,
  listPolicy,
  listFuturePolicyModel,
  addPolicyModelRecord,
  addPolicyModel,
  listPolicyModel,
  listPolicyModelByPolicyId,
  updatePolicyFormulaData,
  listDataItem,
  delPolicyModel,
  updatePolicyModelData,
  matchCompanyList,
  updateComplexFormula,
  docDetailUpdate,
  delModelRecord
} from "@/api/system/policy";
export default {
  data() {
    return {
      yearName:'',
      companyName: "",
      showSearch: true,
      role: "",
      num: 0,
      indents: 0,
      dialogVisible: false,
      options: [],
      value: [],
      list: [],
      policyModelList: [],
      complexIndicatorList: [],
      loading: false,
      listLoading: true,
      search: "",
      dialogTableVisible: false,
      dialogFormVisibleAdd: false,
      dialogFormVisible: false,
      dialogVisibleCompany: false,
      dialogVisibleAdd: false,
      dialogVisibleDocDetail: false,
      dialogVisibleDocDetailName: false,
      formula: "",
      form: {
        id: "",
        name: "",
        item: "",
        threshold: "",
        operation: "",
      },
      formAdd: {
        id: null,
        name: "",
        item: "",
        threshold: "",
        operation: "",
      },
      queryParams: {
        name: null,
        publishDate: null,
        status: null,
      },
      tableData: [],
      companyDetailData: [],
      dataItemList: [],
      formLabelWidth: "120px",
      currentPage: 1,
      currentIndex: "",
      pagesize: 10,
      state: "",
      timeout: null,
      dialogVisibleUpdate: false,
      formInline: {
        id: 0,
        operator: "",
        threshold: 0,
      },
      loading: true,
      nowPolicyId: 0,
      complexFormulaList: [],
      docDetail: [],
      docDetailName: "",
      docDetailNameId: 0,
      policyId: 0,
      modelId: 0,
      docDetailAdd: 0
    };
  },
  watch: {
    tableData(val) {
      this.doLayout();
    },
  },
  mounted() {
    // this.list = this.states.map((item) => {
    //   return { value: `value:${item}`, label: `label:${item}` };
    // });
  },
  created() {
    this.fetchData();
  },
  methods: {
    doLayout() {
      let that = this;
      this.$nextTick(() => {
        that.$refs.table.doLayout();
      });
    },
    handleDeletePolicyModel(row) {
      const ids = row.id;
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(function () {
          return delPolicyModel(ids);
        })
        .then(() => {
          listPolicyModelByPolicyId(this.policyModelList[0].policyId).then(
            (res) => {
              // console.log(res);
              this.policyModelList = res.rows;
              if (
                res.rows &&
                res.rows[0] &&
                res.rows[0].formula !== undefined
              ) {
                this.formula = res.rows[0].formula;
              } else {
                this.formula = "";
              }
            }
          );
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    handleSizeChange(val) {
      this.pagesize = val;
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    onSubmitDocDetailUpdate() {
      if (this.docDetailName === '') {
        this.$message.error("材料不能为空")
      } else {
        if (this.docDetailAdd === 1) {
          this.docDetail.push({
            name: this.docDetailName
          });
        } else {
          this.docDetail[this.docDetailNameId].name = this.docDetailName;
        }
        let docDetailNameList = this.docDetail.map(item => item.name).join(';');
        docDetailUpdate(this.modelId, docDetailNameList).then((res => {
          this.$message.success("更新成功")
          this.dialogVisibleDocDetailName = false
        }))
      }
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      if(this.queryParams.name!==null&&this.queryParams!==' '){
        const name=this.queryParams.name
        this.list=this.list.filter(item=>item.name.includes(name))
      }
    },
    resetQuery() {
      this.fetchData();
    },
    handleDeleteDocDetail(scope) {
      console.log(scope.$index)
      const ids = scope.$index
      this.$modal
        .confirm('是否确认删除指标编号为"' + ids + '"的数据项？')
        .then(() => {
          this.docDetail.splice(scope.$index, 1);
          let docDetailNameList = this.docDetail.map(item => item.name).join(';');
          docDetailUpdate(this.modelId, docDetailNameList).then((res => {
            this.$message.success("删除成功")
          }))
        })
    },
    delModel(row) {
      console.log(row.id)
      this.$modal
        .confirm('是否确认删除指标编号为"' + row.id + '"的数据项？')
        .then(() => {
          delModelRecord(row.id).then((res => {
            this.$message.success("删除成功")
          }))
        })

    },
    handleCloseDocDetail() {
      this.dialogVisibleDocDetail = false
    },
    handleCloseDocDetailName() {
      this.dialogVisibleDocDetailName = false
    },
    handleDocNumber(scope) {
      console.log(scope.row)
      this.modelId = scope.row.id;
      console.log(this.modelId)
      if (scope.row.docDetail !== null) {
        // 去除末尾的空格和分号
        scope.row.docDetail = scope.row.docDetail
          .trimEnd() // 去除末尾的空格
          .replace(/;$/, ''); // 去除末尾的分号（如果存在）
        const items = scope.row.docDetail.split(';');
        this.docDetail = items.map(item => {
          return { name: item.trim() };
        });
      } else {
        const items = "";
        this.docDetail = []
      }

      this.dialogVisibleDocDetail = true
    },
    handleUpdateDocDetail(scope) {
      this.docDetailAdd = 0;
      console.log(scope.$index)
      this.docDetailName = scope.row.name
      this.docDetailNameId = scope.$index;
      this.dialogVisibleDocDetailName = true
    },
    async fetchData() {
      this.listLoading = true;
      await listFuturePolicyModel(this.queryParams).then((res) => {
        this.list = res.rows;
        console.log(this.list)
      });
      this.listLoading = false;
    },
    handleClose(done) {
      this.dialogVisible = false;
    },
    handleAdd() {
      this.state=""
      this.formAdd.name=""
      this.formAdd.threshold=""
      this.formAdd.operation=""
      // console.log(this.nowPolicyId);
      listDataItem(this.nowPolicyId).then((res) => {
        this.dataItemList = res.rows;
        // console.log(this.dataItemList);
      });
      this.dialogVisibleAdd = true;
    },
    handleCloseAdd() {
      this.dialogVisibleAdd = false;
    },
    handleCloseCompanyDetail() {
      this.dialogVisibleCompany = false;
      this.loading = true;
    },
    handleDocDetailAdd() {
      this.docDetailAdd = 1
      this.dialogVisibleDocDetailName = true;
      this.docDetailName = ''
    },
    onSubmit() {
      // console.log(this.formAdd);
      this.formAdd.name = this.state;
      const matchingItem = this.dataItemList.find(
        (item) => item.name === this.state
      );
      // console.log(matchingItem);
      if (!matchingItem) {
        this.$message.error("请按照指标名标准选择指标名");
      } else {
        addPolicyModelRecord(
          this.nowPolicyId,
          matchingItem.id,
          this.formAdd.operation,
          this.formAdd.threshold
        ).then((res) => {
          listPolicyModelByPolicyId(this.nowPolicyId).then((res) => {
            // console.log(res);
            this.policyModelList = res.rows;
            if (res.rows && res.rows[0] && res.rows[0].formula !== undefined) {
              this.formula = res.rows[0].formula;
            } else {
              this.formula = "";
            }
          });
          this.dialogVisibleAdd = false;
          this.formAdd = {
            id: null,
            name: "",
            item: "",
            threshold: "",
            operation: "",
          };
          this.$modal.msgSuccess("添加成功");
        });
      }
    },
    lookup(row) {
      // console.log(row.id);
      this.nowPolicyId = row.id;
      listPolicyModelByPolicyId(row.id).then((res) => {
        console.log(res);
        this.policyModelList = res.rows;
        this.complexIndicatorList = this.policyModelList.map(
          (item) => item.name
        );
        // console.log(this.complexIndicatorList);
        if (res.rows && res.rows[0] && res.rows[0].formula !== undefined) {
          this.formula = res.rows[0].formula;
        } else {
          this.formula = "";
        }
      });
      this.dialogVisible = true;
    },
    check(index) {
      if (!this.list[index].formula) {
        this.$confirm(
          "您并未指定该政策模型的匹配法则，这将导致匹配到所有企业，点击确定继续匹配",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          }
        )
          .then(() => {
            console.log(this.list[index]);
            // 用户点击确定后的逻辑
            setInterval(() => {
              this.loading = false;
            }, 8000);
            matchCompanyList(
              this.list[index].id,
              this.list[index].formula
            ).then((res) => {
              console.log(res);
              this.companyDetailData = res.rows;
            });
            clearInterval();
            this.dialogVisibleCompany = true;
            this.loading = true;
          })
          .catch(() => {
            // 用户点击取消后的逻辑
          });
      } else {
        setInterval(() => {
          this.loading = false;
        }, 8000);
        matchCompanyList(this.list[index].id, this.list[index].formula).then(
          (res) => {
            console.log(res);
            this.companyDetailData = res.rows;
          }
        );
        clearInterval();
        this.dialogVisibleCompany = true;
        this.loading = true;
      }
    },
    handleUpdateFormula() {
      const policy = {
        id: this.policyModelList[0].policyId,
        formula: this.formula,
      };
      updatePolicyFormulaData(policy).then((res) => {
        this.fetchData();
        this.dialogVisible = false;
        this.$modal.msgSuccess("修改成功");
      });
      // console.log(this.policyModelList[0].policyId);
    },
    querySearchAsync(queryString, cb) {
      var dataItemList = this.dataItemList;
      var results = queryString
        ? dataItemList.filter(this.createStateFilter(queryString))
        : dataItemList;
      results = results.map((data) => {
        return {
          value: data.name,
          name: data.name,
        };
      });
      cb(results);
    },
    createStateFilter(queryString) {
      return (state) => {
        return (
          state.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.name.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
    handleSelect(item) {
      // console.log(item);
    },
    handleUpdatePolicyModel(index) {
      this.formInline = {
        id: this.policyModelList[index].id,
        operator: this.policyModelList[index].operator,
        threshold: this.policyModelList[index].threshold,
      };
      this.dialogVisibleUpdate = true;
    },
    handleCloseUpdate() {
      this.dialogVisibleUpdate = false;
    },
    onSubmitUpdate() {
      updatePolicyModelData(this.formInline).then((res) => {
        listPolicyModelByPolicyId(this.policyModelList[0].policyId).then(
          (res) => {
            // console.log(res);
            this.policyModelList = res.rows;
            if (res.rows && res.rows[0] && res.rows[0].formula !== undefined) {
              this.formula = res.rows[0].formula;
            } else {
              this.formula = "";
            }
          }
        );
        this.dialogVisibleUpdate = false;
        this.$modal.msgSuccess("修改成功");
      });
      // console.log(this.formInline);
    },
    open() {
      this.$alert("数字表示指标序号，语法支持()、&、|", "使用说明", {
        confirmButtonText: "确定",
        // callback: (action) => {
        //   this.$message({
        //     type: "info",
        //     message: `action: ${action}`,
        //   });
        // },
      });
    },
    handleDropdownCommand(payload) {
      console.log(payload);
      const [command, scope] = payload;
      this.complexFormulaList.push(payload[0]);
      this.complexFormulaList.push.apply(
        this.complexFormulaList,
        payload[1].row.complexFormulaList
      );
      console.log(this.complexFormulaList);
      const data = {
        id: scope.row.id,
        complexFormulaList: this.complexFormulaList,
      };
      updateComplexFormula(data).then((res) => {
        listPolicyModelByPolicyId(this.nowPolicyId).then((res) => {
          console.log(res);
          this.policyModelList = res.rows;
          this.complexIndicatorList = this.policyModelList.map(
            (item) => item.name
          );
          // console.log(this.complexIndicatorList);
          if (res.rows && res.rows[0] && res.rows[0].formula !== undefined) {
            this.formula = res.rows[0].formula;
          } else {
            this.formula = "";
          }
        });
      });
      this.complexFormulaList = [];
    },
    delIndicator(item, index, scope) {
      let remainingElements = scope.row.complexFormulaList.filter(
        (str) => str !== item
      );
      const data = {
        id: scope.row.id,
        complexFormulaList: remainingElements,
      };

      updateComplexFormula(data).then((res) => {
        listPolicyModelByPolicyId(this.nowPolicyId).then((res) => {
          this.policyModelList = res.rows;
          this.complexIndicatorList = this.policyModelList.map(
            (item) => item.name
          );
          // console.log(this.complexIndicatorList);
          if (res.rows && res.rows[0] && res.rows[0].formula !== undefined) {
            this.formula = res.rows[0].formula;
          } else {
            this.formula = "";
          }
        });
      });
    },
    handleSubmit() {
      this.fetchData();
      this.dialogVisible = false;
    },
    modelAdd() {
      this.formAdd={id:null}
      this.dialogFormVisibleAdd = true;
    },
    //修改基本属性
    handleUpdateModel(row) {
      this.formAdd = row;
      this.dialogFormVisibleAdd = true;
    },
    modelRecordAdd() {
      if (this.formAdd.id !== null) {
        updatePolicy(this.formAdd).then(res=>{
          this.dialogFormVisibleAdd = false;
          this.fetchData();
          this.$modal.msgSuccess("更新成功");
        })
      } else {
        addPolicyModel(this.formAdd).then(res => {
          this.dialogFormVisibleAdd = false;
          this.fetchData();
          this.$modal.msgSuccess("添加成功");
        })
      }
    },
  },
};
</script>

<style lang="scss">
.container {
  .inline-input {
    width: 100%;
  }

  .btn {
    position: absolute;
    top: 5vh;
  }

  .policy_model {
    .el-input__inner {
      border: none;
      text-align: center;
      // border-bottom: 1px solid #ebebeb;
      // height: 4vh;
    }
  }

  overflow: hidden;
  width: 100%;
  height: 100vh;
  background-color: #f2f2f2;

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
