import request from '@/utils/request'

// 查询最终价格计算列表
export function listJiage(query) {
  return request({
    url: '/system/jiage/list',
    method: 'get',
    params: query
  })
}

// 查询最终价格计算详细
export function getJiage(fileId) {
  return request({
    url: '/system/jiage/' + fileId,
    method: 'get'
  })
}

// 新增最终价格计算
export function addJiage(data) {
  return request({
    url: '/system/jiage',
    method: 'post',
    data: data
  })
}

// 修改最终价格计算
export function updateJiage(data) {
  return request({
    url: '/system/jiage',
    method: 'put',
    data: data
  })
}

// 删除最终价格计算
export function delJiage(fileId) {
  return request({
    url: '/system/jiage/' + fileId,
    method: 'delete'
  })
}
