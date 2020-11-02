import { getAsyncRoutersByRoles } from "../../api/role";
import { filterAsyncRouter } from "../../utils/permission";
import { constantRoutes } from "../../router";

const state = {
  routes: [] // 路由表
};

const mutations = {
  SET_ROUTES: (state, asyncRoutes) => {
    state.routes = constantRoutes.concat(asyncRoutes); // 合并路由
  }
};

const actions = {
  // 获取路由
  getAsyncRoutes({ commit }, roles) {
    return new Promise((resolve, reject) => {
      getAsyncRoutersByRoles(roles)
        .then(response => {
          const { data } = response;
          // 404 page must be placed at the end !!!
          const notFound = [
            {
              path: "*",
              redirect: "/404",
              hidden: true
            }
          ];
          const asyncRoutes = filterAsyncRouter(data.asyncRoutes).concat(
            notFound
          );
          commit("SET_ROUTES", asyncRoutes);
          resolve(asyncRoutes);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
