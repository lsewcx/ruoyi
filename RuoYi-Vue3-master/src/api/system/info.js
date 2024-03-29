import request from '@/utils/request'

// 查询word上传列表
export function listInfo(query) {
  return request({
    url: '/system/info/list',
    method: 'get',
    params: query
  })
}

// 查询word上传详细
export function getInfo(fileId) {
  return request({
    url: '/system/info/' + fileId,
    method: 'get'
  })
}

// 新增word上传
export function addInfo(data) {
  return request({
    url: '/system/info',
    method: 'post',
    data: data
  })
}

// 修改word上传
export function updateInfo(data) {
  return request({
    url: '/system/info',
    method: 'put',
    data: data
  })
}

// 删除word上传
export function delInfo(fileId) {
  return request({
    url: '/system/info/' + fileId,
    method: 'delete'
  })
}
