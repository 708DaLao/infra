/*导航守卫*/
import router from "./router";
import store from "./store";
import NProgress from "nprogress"; // 进度条
import "nprogress/nprogress.css"; // 进度条样式
import { getToken } from "./utils/auth";
import { Message } from "element-ui";

const whiteList = ["/login"]; // no redirect whitelist

// 全局前置守卫
router.beforeEach(async (to, from, next) => {
  NProgress.start(); // 开启进度条

  // 判断是否登录
  const hasToken = getToken();

  if (hasToken) {
    if (to.path === "/login") {
      next({ path: "/" });
      NProgress.done();
    } else {
      const hasRoles = store.getters.roles && store.getters.roles.length > 0;
      if (hasRoles) {
        next();
      } else {
        try {
          // 获取当前用户角色，根据角色去获取路由
          const { roles } = await store.dispatch("user/getInfo");
          // 获取动态路由,即角色的路由
          const asyncRoutes = await store.dispatch(
            "role/getAsyncRoutes",
            roles
          );
          console.log(asyncRoutes);
          // 添加路由
          router.addRoutes(asyncRoutes);
          next({ ...to, replace: true });
        } catch (e) {
          await store.dispatch("user/resetToken");
          Message.error(e || "获取信息异常");
          next(`/login?redirect=${to.path}`);
          NProgress.done();
        }
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      next();
    } else {
      // 其他没有访问权限的页面被重定向到登录页面
      next(`/login?redirect=${to.path}`);
      NProgress.done();
    }
  }
});

// 全局后置钩子
router.afterEach(() => {
  NProgress.done();
});
