import { getPermissionByRoles } from "../../api/role";

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
          const { asyncRoutes } = data;
          let aa = delete data.asyncRoutes.id
            console.log(aa)
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
