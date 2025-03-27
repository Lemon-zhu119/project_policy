import request from "@/utils/request";

// 查询指标列表
export function listEnterpriseIndex(query) {
  return request({
    url: "/system/enterpriseIndex/list",
    method: "get",
    params: query,
  });
}

// 查询指标详细
export function getEnterpriseIndex(id) {
  return request({
    url: "/system/enterpriseIndex/" + id,
    method: "get",
  });
}

// 新增指标
export function addEnterpriseIndex(data) {
  return request({
    url: "/system/enterpriseIndex",
    method: "post",
    data: data,
  });
}

// 修改指标
export function updateEnterpriseIndex(data) {
  return request({
    url: "/system/enterpriseIndex",
    method: "put",
    data: data,
  });
}

// 删除指标
export function delEnterpriseIndex(id) {
  return request({
    url: "/system/enterpriseIndex/" + id,
    method: "delete",
  });
}
