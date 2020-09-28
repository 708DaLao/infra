import { getPermissionByRoles } from "../../api/role";
import { filterAsyncRouter } from "../../utils/permission";
import { constantRoutes } from "../../router";

const state = {
  routes: [], // 路由表
  addRoutes: [] // 动态添加的路由
};

const mutations = {
  SET_ROUTES: (state, asyncRoutes) => {
    state.addRoutes = asyncRoutes;
    state.routes = constantRoutes.concat(asyncRoutes); // 合并路由
  }
};

const actions = {
  // 获取路由
  getAsyncRoutes({ commit }, roles) {
    return new Promise((resolve, reject) => {
      getPermissionByRoles(roles)
        .then(response => {
          const { data } = response;
          const asyncRoutes = filterAsyncRouter(data.asyncRoutes);
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
