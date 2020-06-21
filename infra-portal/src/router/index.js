import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);

import Login from "../views/login.vue";
import NProgress from "nprogress";

/* Layout 布局*/
import Layout from '@/layout'

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/",
    component: Layout,
  }
];

const router = new VueRouter({
  routes
});

export default router;
