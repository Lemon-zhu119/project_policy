import request from "@/utils/request";

// 获取company_category表的list数据（带模糊查询）
export function listCompanyCategory(query) {
  return request({
    url: "/system/companyClass/categoryList",
    method: "get",
    params: query,
  });
}

// 用category_id获取company_category_data表的list数据
export function listCompanyCategoryDataByCategoryId(id) {
  return request({
    url: "/system/companyClass/categoryDataListByCategoryId",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 用id获取company_category_data表的list数据（实际只会得到一个对象）
export function listCompanyCategoryDataById(id) {
  return request({
    url: "/system/companyClass/categoryDataListById",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 用id获取company_category表的list数据（实际只会得到一个对象）
export function listCompanyCategoryById(id) {
  return request({
    url: "/system/companyClass/categoryListById",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 根据id删除company_category_data表的某一对象
export function delCompanyCategoryData(id) {
  return request({
    url: "/system/companyClass/del/categoryData/" + id,
    method: "delete",
  });
}

export function delCompanyCategoryBatch(ids) {
  return request({
    url: "/system/companyClass/delBatch/companyCategory",
    method: "delete",
    data:ids
  });
}
export function delCompanyBatch(ids) {
  return request({
    url: "/system/companyDetail/delBatch/company",
    method: "delete",
    data:ids
  });
}
// 为company_category_data表添加新数据
export function addCompanyCategoryData(data) {
  return request({
    url: "/system/companyClass/add/categoryData",
    method: "post",
    data: data,
  });
}

// 为company_category表添加新数据
export function addCompanyCategory(data) {
  return request({
    url: "/system/companyClass/add/category",
    method: "post",
    data: data,
  });
}

// 修改company_category_data表的某一条数据
export function updateCompanyCategoryData(data) {
  return request({
    url: "/system/companyClass/edit/categoryData",
    method: "put",
    data: data,
  });
}

// 修改company_category表的某一条数据
export function updateCompanyCategory(data) {
  return request({
    url: "/system/companyClass/edit/category",
    method: "put",
    data: data,
  });
}

// 根据id删除company_category表的某一对象和其下的所有属性明细
export function delCompanyCategory(id) {
  return request({
    url: "/system/companyClass/del/category/" + id,
    method: "delete",
  });
}

// 获取company表的list数据（带模糊查询）
export function listCompany(query) {
  return request({
    url: "/system/companyDetail/list",
    method: "get",
    params: query,
  });
}

// 修改company表的某一条数据
export function updateCompany(data) {
  return request({
    url: "/system/companyDetail/edit/company",
    method: "put",
    data: data,
  });
}

// 为company表添加新数据
export function addCompany(data) {
  return request({
    url: "/system/companyDetail/add/company",
    method: "post",
    data: data,
  });
}

// 根据id删除company表的某一对象
export function delCompany(id) {
  return request({
    url: "/system/companyDetail/del/company/" + id,
    method: "delete",
  });
}

// 用id获取company表的list数据（实际只会得到一个对象）
export function listCompanyById(id) {
  return request({
    url: "/system/companyDetail/companyListById",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 废弃
export function listCompanyPropertyDataList(id) {
  return request({
    url: "/system/companyDetailData/propertyList",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 通过companyId获取company_category_data的值
export function listCompanyPropertyDataById(id) {
  return request({
    url: "/system/companyDetailData/propertyListById",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 通过company_category_record id来检索单行company_category_data数据
export function listCompanyPropertyDataByDtoId(id) {
  return request({
    url: "/system/companyDetailData/propertyListByDtoId",
    method: "get",
    params: {
      id: id,
    },
  });
}

export function listCompanyIndicatorDataList(id) {
  return request({
    url: "/system/companyDetailData/indicatorList",
    method: "get",
    params: {
      id: id,
    },
  });
}

//listCompanyPropertyDataList
export function updateCompanyPropertyData(data) {
  return request({
    url: "/system/companyDetailData/edit/property",
    method: "put",
    data: data,
  });
}

//修改company_indicator_ditionary某行值
export function updateCompanyIndicatorData(data) {
  return request({
    url: "/system/companyDetailData/edit/indicator",
    method: "put",
    data: data,
  });
}

// 为company_category_data表和company_category表和company_category_record添加新数据
export function addCompanyProperty(data) {
  return request({
    url: "/system/companyDetailData/add/property",
    method: "post",
    data: data,
  });
}

// 根据id删除company_category_data表的某一对象
export function delCompanyProperty(id) {
  return request({
    url: "/system/companyDetailData/del/property/" + id,
    method: "delete",
  });
}

// 通过company_indicator_record id来检索单行company_indicator_dictionary数据
export function listCompanyIndicatorDataByDtoId(id) {
  return request({
    url: "/system/companyDetailData/indicatorListByDtoId",
    method: "get",
    params: {
      id: id,
    },
  });
}

// 通过companyId获取company_category_data的值
export function listCompanyIndicatorDataById(id) {
  return request({
    url: "/system/companyDetailData/indicatorListById",
    method: "get",
    params: {
      id: id,
    },
  });
}
export function addCompanyIndicator(data) {
  return request({
    url: "/system/companyDetailData/add/indicator",
    method: "post",
    data: data,
  });
}

export function delCompanyIndicator(id) {
  return request({
    url: "/system/companyDetailData/del/indicator/" + id,
    method: "delete",
  });
}

export function listCompanyCategoryByCompanyId(id) {
  return request({
    url: "/system/companyDetail/companyCategoryByCompanyId",
    method: "get",
    params: {
      id: id,
    },
  });
}

export function listCompanyIndicatorDictionaryByCompanyId(id) {
  return request({
    url: "/system/companyDetail/companyIndicatorDictionaryByCompanyId",
    method: "get",
    params: {
      id: id,
    },
  });
}
