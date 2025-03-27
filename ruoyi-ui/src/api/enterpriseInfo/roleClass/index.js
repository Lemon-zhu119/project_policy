export function roleList(query) {
    return request({
      url: '/enterpriseInfo/roleClass/list',
      method: 'get',
      params: query
    })
  }