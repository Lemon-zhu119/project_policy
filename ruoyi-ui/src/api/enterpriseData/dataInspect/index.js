import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listData(query) {
  return request({
    url: '/enterpriseData/dataInspect/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getData(id) {
  return request({
    url: '/enterpriseData/dataInspect' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addData(data) {
  return request({
    url: '/enterpriseData/dataInspect',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateData(data) {
  return request({
    url: '/enterpriseData/dataInspect',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delData(id) {
  return request({
    url: '/enterpriseData/dataInspect' + id,
    method: 'delete'
  })
}
