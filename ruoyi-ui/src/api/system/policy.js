import request from "@/utils/request";

// 获取policy_category表的list数据（带模糊查询）
export function listPolicyCategory(query) {
  return request({
    url: "/system/policyClass/categoryList",
    method: "get",
    params: query,
  });
}

// 用category_id获取policy_category_data表的list数据
export function listPolicyCategoryDataByCategoryId(id) {
  return request({
    url: "/system/policyClass/categoryDataListByCategoryId",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 用id获取policy_category_data表的list数据（实际只会得到一个对象）
export function listPolicyCategoryDataById(id) {
  return request({
    url: "/system/policyClass/categoryDataListById",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 用id获取policy_category表的list数据（实际只会得到一个对象）
export function listPolicyCategoryById(id) {
  return request({
    url: "/system/policyClass/categoryListById",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 根据id删除policy_category_data表的某一对象
export function delPolicyCategoryData(id) {
  return request({
    url: "/system/policyClass/del/categoryData/" + id,
    method: "delete",
  });
}

// 为policy_category_data表添加新数据
export function addPolicyCategoryData(data) {
  return request({
    url: "/system/policyClass/add/categoryData",
    method: "post",
    data: data,
  });
}

// 为policy_category表添加新数据
export function addPolicyCategory(data) {
  return request({
    url: "/system/policyClass/add/category",
    method: "post",
    data: data,
  });
}

// 修改policy_category_data表的某一条数据
export function updatePolicyCategoryData(data) {
  return request({
    url: "/system/policyClass/edit/categoryData",
    method: "put",
    data: data,
  });
}

// 修改policy_category表的某一条数据
export function updatePolicyCategory(data) {
  return request({
    url: "/system/policyClass/edit/category",
    method: "put",
    data: data,
  });
}

// 根据id删除policy_category表的某一对象和其下的所有属性明细
export function delPolicyCategory(id) {
  return request({
    url: "/system/policyClass/del/category/" + id,
    method: "delete",
  });
}
export function delPolicyCategoryBatch(ids) {
  return request({
    url: "/system/policyClass/delBatch/policyCategory",
    method: "delete",
    data:ids
  });
}
// 获取policy表的list数据（带模糊查询）
export function listPolicy(query) {
  return request({
    url: "/system/policyContent/policyList",
    method: "get",
    params: query,
  });
}
// 获取policyModel表的list数据（带模糊查询）
export function listPolicyModel(query) {
  return request({
    url: "/system/policyContent/customModelList",
    method: "get",
    params: query,
  });
}
export function listFuturePolicyModel(query) {
  return request({
    url: "/system/policyContent/customModelFutureList",
    method: "get",
    params: query,
  });
}

// 修改policy表的某一条数据
export function updatePolicy(data) {
  return request({
    url: "/system/policyContent/edit/policy",
    method: "put",
    data: data,
  });
}

// 为policy表添加新数据
export function addPolicy(data) {
  return request({
    url: "/system/policyContent/add/policy",
    method: "post",
    data: data,
  });
}
//为policymodel添加新数据
export function addPolicyModel(data) {
  return request({
    url: "/system/policyContent/add/policyModelRecord",
    method: "post",
    data: data,
  });
}

// 根据id删除policy表的某一对象
export function delPolicy(id) {
  return request({
    url: "/system/policyContent/del/policy/" + id,
    method: "delete",
  });
}


export function delPolicyBatch(ids) {
  return request({
    url: "/system/policyContent/delBatch/policy",
    method: "delete",
    data:ids
  });
}


// 用id获取policy表的list数据（实际只会得到一个对象）
export function listPolicyById(id) {
  return request({
    url: "/system/policyContent/policyListById",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 获取policy_category和policy_category_data表联合后的list数据（带模糊查询）
export function listPolicyDetailById(id) {
  return request({
    url: "/system/policyContent/detailList",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 修改policy_category_data表的某一条数据
export function updatePolicyDetailData(data) {
  return request({
    url: "/system/policyContent/edit/policyDetail",
    method: "put",
    data: data,
  });
}

// 为policy_category_data表添加新数据
export function addPolicyDetailData(data) {
  return request({
    url: "/system/policyContent/add/policyDetail",
    method: "post",
    data: data,
  });
}

// 根据id删除policy_category_data表的某一对象
export function delPolicyDetail(id) {
  return request({
    url: "/system/policyContent/del/policyDetail/" + id,
    method: "delete",
  });
}

export function addPolicyModelRecord(
  policyId,
  dictionaryId,
  operator,
  threshold
) {
  return request({
    url: "/system/policyContent/add/policyModel",
    method: "post",
    params: {
      policyId: policyId,
      dictionaryId: dictionaryId,
      operator: operator,
      threshold: threshold,
    },
  });
}

export function listPolicyModelByPolicyId(id) {
  return request({
    url: "/system/policyContent/modelDetailList",
    method: "get",
    params: {
      id: id,
    },
  });
}

export function updatePolicyFormulaData(data) {
  return request({
    url: "/system/policyContent/edit/policyFormula",
    method: "put",
    data: data,
  });
}

export function listDataItem(id) {
  return request({
    url: "/system/policyContent/dataItemList",
    method: "get",
    params: {
      policyId: id,
    },
  });
}

export function delPolicyModel(id) {
  return request({
    url: "/system/policyContent/del/model/" + id,
    method: "delete",
  });
}

export function updatePolicyModelData(data) {
  return request({
    url: "/system/policyContent/edit/policyModel",
    method: "put",
    data: data,
  });
}

export function matchCompanyList(policyId, formula) {
  return request({
    url: "/system/policyContent/matchCompanyList",
    method: "get",
    params: {
      policyId: policyId,
      formula: formula,
    },
  });
}

export function listPolicyCategoryByPolicyId(id) {
  return request({
    url: "/system/policyContent/policyCategoryList",
    method: "get",
    params: {
      id: id,
    },
  });
}

export function updateComplexFormula(data) {
  return request({
    url: "/system/policyContent/edit/complexFormula",
    method: "put",
    data: data,
  });
}
export function docDetailUpdate(policyId,docDetail) {
  return request({
    url: "/system/policyContent/edit/policyModelDocDetail",
    method: "get",
    params: {
      policyId:policyId,
      docDetail:docDetail
    }
  });
}
export function delModelRecord(id) {
  return request({
    url: "/system/policyContent/del/policyModel/"+id,
    method: "delete",
  });
}
