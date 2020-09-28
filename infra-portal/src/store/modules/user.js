import { login, logout, getInfo } from "@/api/user";
import { getToken, setToken, removeToken } from "@/utils/auth";
import { resetRouter } from "@/router";
import { Message } from "element-ui";

const state = {
  token: getToken(),
  nickname: "",
  avatar: "",
  roles: []
};

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NICKNAME: (state, nickname) => {
    state.nickname = nickname;
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar;
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles;
  }
};

const actions = {
  // 用户登录
  login({ commit }, data) {
    return new Promise((resolve, reject) => {
      login(data)
        .then(response => {
          const { data } = response;
          commit("SET_TOKEN", data.access_token);
          setToken(data.access_token);
          resolve();
        })
        .catch(error => {
          reject(error);
        });
    });
  },

  // 获取用户信息
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token)
        .then(response => {
          const { data } = response;
          if (!data) {
            reject("验证失败，请重新登录！");
          }
          const { roles, info } = data;
          if (!roles || roles.length <= 0) {
            reject("角色不能为空，且为数组！");
          }

          commit("SET_ROLES", roles);
          commit("SET_NICKNAME", info.nickname);
          commit("SET_AVATAR", info.avatar);
          resolve(data);
        })
        .catch(error => {
          reject(error);
        });
    });
  },

  // 退出登录
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token)
        .then(res => {
          commit("SET_TOKEN", "");
          commit("SET_ROLES", []);
          removeToken();
          resetRouter();
          Message({
            message: res.message,
            type: "success",
            duration: 5 * 1000
          });
          resolve();
        })
        .catch(error => {
          reject(error);
        });
    });
  },

  // 移除 token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit("SET_TOKEN", "");
      commit("SET_ROLES", []);
      removeToken();
      resolve();
    });
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
