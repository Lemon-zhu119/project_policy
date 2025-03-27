import request from "@/utils/request";

export function listCompanyPolicySubmission() {
  return request({
    url: "/system/policySubmission/list",
    method: "get",
  });
}

export function delCompanyPolicySubmission(id) {
  return request({
    url: "/system/policySubmission/del/policySubmission/" + id,
    method: "delete",
  });
}

export function listPolicyGovernmentAuditList(query) {
  return request({
    url: "/system/policyGovernmentAudit/policyGovernmentAuditList",
    method: "get",
    params: query,
  });
}

export function listPolicy(query) {
  return request({
    url: "/system/policyGovernmentAudit/policyList",
    method: "get",
    params: query,
  });
}

export function updatePolicyGovernmentAudit(data) {
  return request({
    url: "/system/policyGovernmentAudit/edit/policyGovernmentAudit",
    method: "put",
    data: data,
  });
}

export function addPolicyGovernmentAudit(data) {
  return request({
    url: "/system/policyGovernmentAudit/add/policyGovernmentAudit",
    method: "post",
    data: data,
  });
}

export function listPolicySubmissionDoc(id) {
  return request({
    url: "/system/policyGovernmentAudit/documentList",
    method: "get",
    params: {
      id: id,
    },
  });
}

export function updateCompanyPolicySubmission(data) {
  return request({
    url: "/system/policyGovernmentAudit/edit/companyPolicySubmission",
    method: "put",
    data: data,
  });
}
export function listGovernmentAuditRecord(auditId) {
  return request({
    url: "/system/governmentAuditRecord/selectGovernmentAuditRecordByAuditId",
    method: "get",
    params: {auditId:auditId}
  });
}
export function addPolicyGovernmentAuditRecord(data) {
  return request({
    url: "/system/governmentAuditRecord/add/governmentAuditRecord",
    method: "post",
    data: data,
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
export function governmentAuditRecord(id){
  return request({
    url: "/system/governmentAuditRecord/selectGovernmentAuditRecordById",
    method: "get",
    params: {
      id: id,
    },
  });
}

export function delGovernmentAudtiRecord(id) {
  return request({
    url: "/system/governmentAuditRecord/del/governmentAuditRecord/" + id,
    method: "delete",
  });
}

export function updatePolicyGovernmentAuditRecord(data) {
  return request({
    url: "/system/governmentAuditRecord/edit/governmentAuditRecord",
    method: "put",
    data: data,
  });
}