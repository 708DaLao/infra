import { getPermissionByRoles } from "../../api/role";
import { filterAsyncRouter } from "../../utils/permission";

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
      getPermissionByRoles(roles)
        .then(response => {
          const { data } = response;
          const asyncRoutes = filterAsyncRouter(data.asyncRoutes);
          commit("SET_ASYNCROUTES", asyncRoutes);
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
