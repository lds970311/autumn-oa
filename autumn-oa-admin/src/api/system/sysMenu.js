import request from '@/utils/request'

/*
菜单管理相关的API请求函数
*/
const api_name = '/admin/system/sysMenu'

/*
获取权限(菜单/功能)列表
*/
export function findNodes() {
  return request({
    url: `${api_name}/findNodes`,
    method: 'get'
  })
}

export function removeById(id) {
  return request({
    url: `${api_name}/remove/${id}`,
    method: 'delete'
  })
}

export function save(sysMenu) {
  return request({
    url: `${api_name}/save`,
    method: 'post',
    data: sysMenu
  })
}

export function updateById(sysMenu) {
  return request({
    url: `${api_name}/update`,
    method: 'put',
    data: sysMenu
  })
}

/*
查看某个角色的权限列表
*/
export function toAssign(roleId) {
  return request({
    url: `${api_name}/toAssign/${roleId}`,
    method: 'get'
  })
}

/*
给某个角色授权
*/
export function doAssign(assginMenuVo) {
  return request({
    url: `${api_name}/doAssign`,
    method: 'post',
    data: assginMenuVo
  })
}
