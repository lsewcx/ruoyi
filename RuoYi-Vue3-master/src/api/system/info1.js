import request from '@/utils/request'

// 查询文件信息1列表
export function listInfo1(query) {
  return request({
    url: '/system/info1/list',
    method: 'get',
    timeout: 10000,
    params: query
  })
}

// 查询文件信息1详细
export function getInfo1(fileId) {
  return request({
    url: '/system/info1/' + fileId,
    method: 'get'
  })
}

// 新增文件信息1
export function addInfo1(data) {
  return request({
    url: '/system/info1',
    method: 'post',
    timeout: 100000,
    data: data
  })
}

// 修改文件信息1
export function updateInfo1(data) {
  return request({
    url: '/system/info1',
    method: 'put',
    data: data
  })
}

// 删除文件信息1
export function delInfo1(fileId) {
  return request({
    url: '/system/info1/' + fileId,
    method: 'delete'
  })
}
