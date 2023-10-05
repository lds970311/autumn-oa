import request from '@/utils/request'

/*
菜单管理相关的API请求函数
*/
const api_name = '/admin/system/sysOperLog'

export default {
  getAllLogs() {
    return request({
      url: `${api_name}/allLogs`,
      method: 'get'
    })
  }
}
