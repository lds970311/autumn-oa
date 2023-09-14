/*
角色管理相关的API请求函数
*/
import request from '@/utils/request'

const api_name = '/admin/system/sysRole'

export function getPageList(page, limit, searchObj) {
  return request({
    url: `${api_name}/${page}/${limit}`,
    method: 'get',
    params: searchObj
  })
}

export function removeById(id) {
  return request({
    url: `${api_name}/remove/${id}`,
    method: 'delete'
  })
}

export function save(role) {
  return request({
    url: `${api_name}/save`,
    method: 'post',
    data: role
  })
}

export function update(role) {
  return request({
    url: `${api_name}/update`,
    method: 'put',
    data: role
  })
}

export function getById(id) {
  return request({
    url: `${api_name}/get/${id}`,
    method: 'get'
  })
}

export function updateById(role) {
  return request({
    url: `${api_name}/update`,
    method: 'put',
    data: role
  })
}

export function batchRemove(idList) {
  return request({
    url: `${api_name}/batchDelete`,
    method: `delete`,
    data: idList
  })
}

export function getRoles(adminId) {
  return request({
    url: `${api_name}/toAssign/${adminId}`,
    method: 'get'
  })
}

export function assignRoles(assginRoleVo) {
  return request({
    url: `${api_name}/doAssign`,
    method: 'post',
    data: assginRoleVo
  })
}
