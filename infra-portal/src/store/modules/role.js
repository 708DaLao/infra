import { getRoutesByRoles } from "../../api/role";

const state = {
  asyncRoutes: [] // 动态路由
};

const mutations = {
  SET_ASYNCROUTES: (state, asyncRoutes) => {
    state.asyncRoutes = asyncRoutes;
  }
};

const actions = {
  // 获取路由
  getAsyncRoutes({ commit }, roles) {
    return new Promise((resolve, reject) => {
      getRoutesByRoles(roles)
        .then(response => {
          const { asyncRoutes } = response;
          commit("SET_ASYNCROUTES", asyncRoutes);
          resolve();
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