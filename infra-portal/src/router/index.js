import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

/* Layout 布局*/
import Layout from "@/layout";

/**
 * 路由配置指南：
 * hidden: true  //  当设置 true 的时候该路由不会在侧边栏出现 如401，login等页面
 *
 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * alwaysShow: true
 * name:'router-name'  // 使用<keep-alive>时必需设置name
 * meta : {
    title: 'title'              // 必需！路由名称
    icon: ''                    // 路由图标
    keepAlive： true  // 缓存视图组件
  }
 *
 */

/**
 * 静态路由
 */
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },
  {
    path: "/404",
    component: () => import("@/views/error/404/index"),
    hidden: true
  },
  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: () => import("@/views/dashboard/index"),
        meta: { title: "控制台", icon: "el-icon-help" }
      }
    ]
  },
  {
    path: "/profile",
    component: Layout,
    redirect: "/profile/index",
    hidden: true,
    children: [
      {
        path: "index",
        component: () => import("@/views/profile/index"),
        name: "Profile",
        meta: { title: "个人中心", icon: "el-icon-user" }
      }
    ]
  },
  {
    path: "/style",
    component: Layout,
    redirect: "/style/index",
    children: [
      {
        path: "index",
        component: () => import("@/views/style/index"),
        name: "Style",
        meta: { title: "样式库", icon: "el-icon-star-off" }
      }
    ]
  },
  {
    path: "/nested",
    component: Layout,
    redirect: "/nested/menu1",
    name: "Nested",
    meta: { title: "Nested", icon: "el-icon-menu" },
    children: [
      {
        path: "menu1",
        component: () => import("@/views/nested/menu1/index"), // Parent router-view
        name: "Menu1",
        meta: { title: "Menu1" },
        children: [
          {
            path: "menu1-1",
            component: () => import("@/views/nested/menu1/menu1-1"),
            name: "Menu1-1",
            meta: { title: "Menu1-1" }
          },
          {
            path: "menu1-2",
            component: () => import("@/views/nested/menu1/menu1-2"),
            name: "Menu1-2",
            meta: { title: "Menu1-2" },
            children: [
              {
                path: "menu1-2-1",
                component: () =>
                  import("@/views/nested/menu1/menu1-2/menu1-2-1"),
                name: "Menu1-2-1",
                meta: { title: "Menu1-2-1" }
              },
              {
                path: "menu1-2-2",
                component: () =>
                  import("@/views/nested/menu1/menu1-2/menu1-2-2"),
                name: "Menu1-2-2",
                meta: { title: "Menu1-2-2" }
              }
            ]
          },
          {
            path: "menu1-3",
            component: () => import("@/views/nested/menu1/menu1-3"),
            name: "Menu1-3",
            meta: { title: "Menu1-3" }
          }
        ]
      },
      {
        path: "menu2",
        component: () => import("@/views/nested/menu2/index"),
        meta: { title: "menu2" }
      }
    ]
  }
  // {
  //   path: "/login2",
  //   component: Layout,
  //   children: [
  //     {
  //       path: "index",
  //       component: () => import("views/login/index"),
  //       meta: { title: "登录页", icon: "el-icon-help" }
  //     }
  //   ]
  // },
  // {
  //   path: "/404",
  //   component: Layout,
  //   children: [
  //     {
  //       path: "index",
  //       name: "index",
  //       component: () => import("@/views/error/404/index"),
  //       meta: { title: "404", icon: "el-icon-warning-outline" }
  //     }
  //   ]
  // }
];

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
