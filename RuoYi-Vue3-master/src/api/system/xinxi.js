import request from '@/utils/request'

// 查询文件详细信息列表
export function listXinxi(query) {
  return request({
    url: '/system/xinxi/list',
    method: 'get',
    params: query
  })
}

// 查询文件详细信息详细
export function getXinxi(fileId) {
  return request({
    url: '/system/xinxi/' + fileId,
    method: 'get'
  })
}

// 新增文件详细信息
export function addXinxi(data) {
  return request({
    url: '/system/xinxi',
    method: 'post',
    data: data
  })
}

// 修改文件详细信息
export function updateXinxi(data) {
  return request({
    url: '/system/xinxi',
    method: 'put',
    data: data
  })
}

// 删除文件详细信息
export function delXinxi(fileId) {
  return request({
    url: '/system/xinxi/' + fileId,
    method: 'delete'
  })
}
