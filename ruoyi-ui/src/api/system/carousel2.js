import request from '@/utils/request'

// 查询公告列表
export function ListCarousel(query) {
  return request({
    url: '/api/carousel/CarouselList',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function listCarouselById(id) {
  return request({
    url: '/api/carousel/CarouselListById',
    method: 'get',
    params: {
      id: id,
    },
  })
}

// 新增公告
export function addCarousel(data) {
  return request({
    url: '/api/carousel/insert',
    method: 'post',
    data: data
  })
}

// 修改公告
export function UpdateCarousel(data) {
  return request({
    url: '/api/carousel/updateCarousel',
    method: 'put',
    data: data
  })
}

// 删除公告
export function DelCarousel(id) {
  return request({
    url: '/api/carousel/DelCarousel/' + id,
    method: 'delete'
  })
}