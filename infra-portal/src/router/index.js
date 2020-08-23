import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);

import Login from "../views/login.vue";
import NProgress from "nprogress";

/* Layout 布局*/
import Layout from '@/layout'

/**
 * 路由配置指南：
 * hidden: true  // 为true时侧边栏隐藏此路由
 * alwaysShow: true  // 为true时将始终显示根菜单，如果没有设置，当其有子路径时变成嵌套模式，否则不显示根路径
 * name:'router-name'  // 使用<keep-alive>时必需设置name
 * meta : {
    roles: ['admin','editor']   // 权限
    title: 'title'              // 必需！路由名称
    icon: ''                    // 路由图标
    keepAlive： true  // 缓存视图组件
  }
 *
 */
export const constantRoutes = [
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: { title: '登录页(路由还存在问题)', icon: 'el-icon-help' },
  },
  {
    path: "/",
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('views/dashboard/index'),
      meta: { title: '控制台', icon: 'el-icon-help' },
    }]
  },
  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {title: 'Nested', icon: 'el-icon-menu'},
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        meta: { title: 'menu2' }
      }
    ]
  },
  {
    path: "/404",
    component: Layout,
    children: [{
      path: 'index',
      name: 'index',
      component: () => import('views/404/index'),
      meta: { title: '404', icon: 'el-icon-warning-outline' }
    }]
  }
];

const createRouter = () => new VueRouter({
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
