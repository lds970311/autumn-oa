import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: 'Dashboard', icon: 'dashboard' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    meta: {
      title: '系统管理',
      icon: 'el-icon-s-tools'
    },
    alwaysShow: true,
    children: [
      {
        path: 'sysRole',
        component: () => import('@/views/system/SysRole.vue'),
        meta: {
          title: '角色管理',
          icon: 'el-icon-s-help'
        }
      },
      {
        name: 'sysUser',
        path: 'sysUser',
        component: () => import('@/views/system/SysUser'),
        meta: {
          title: '用户管理',
          icon: 'el-icon-s-custom'
        }
      },
      {
        name: 'sysMenu',
        path: 'sysMenu',
        component: () => import('@/views/system/SysMenu.vue'),
        meta: {
          title: '菜单管理',
          icon: 'el-icon-s-unfold'
        }
      },
      {
        path: 'assignAuth',
        component: () => import('@/views/system/AssignAuth'),
        meta: {
          activeMenu: '/system/sysRole',
          title: '角色授权'
        },
        hidden: true
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  })

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
