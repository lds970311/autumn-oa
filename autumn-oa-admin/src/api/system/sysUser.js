import request from '@/utils/request'

const api_name = '/admin/system/sysUser'

export function getPageList(page, limit, searchObj) {
  return request({
    url: `${api_name}/${page}/${limit}`,
    method: 'get',
    params: searchObj // url查询字符串或表单键值对
  })
}

export function getById(id) {
  return request({
    url: `${api_name}/get/${id}`,
    method: 'get'
  })
}

export function save(role) {
  return request({
    url: `${api_name}/save`,
    method: 'post',
    data: role
  })
}

export function updateById(role) {
  return request({
    url: `${api_name}/update`,
    method: 'put',
    data: role
  })
}

export function removeById(id) {
  return request({
    url: `${api_name}/remove/${id}`,
    method: 'delete'
  })
}

export function updateStatus(id, status) {
  return request({
    url: `${api_name}/updateStatus/${id}/${status}`,
    method: 'get'
  })
}
