import request from '@/utils/request'

// 查询word上传列表
export function listWord(query) {
  return request({
    url: '/system/word/list',
    method: 'get',
    params: query
  })
}

// 查询word上传详细
export function getWord(fileId) {
  return request({
    url: '/system/word/' + fileId,
    method: 'get'
  })
}

// 新增word上传
export function addWord(data) {
  return request({
    url: '/system/word',
    method: 'post',
    data: data
  })
}

// 修改word上传
export function updateWord(data) {
  return request({
    url: '/system/word',
    method: 'put',
    data: data
  })
}

// 删除word上传
export function delWord(fileId) {
  return request({
    url: '/system/word/' + fileId,
    method: 'delete'
  })
}
