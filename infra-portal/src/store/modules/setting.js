import defaultSettings from "@/settings";
const { grantType, clientId, clientSecret } = defaultSettings;

const state = {
  grantType: grantType, // oauth认证模式
  clientId: clientId, // 客户端id
  clientSecret: clientSecret // 客户端密钥
};

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    // eslint-disable-next-line no-prototype-builtins
    if (state.hasOwnProperty(key)) {
      state[key] = value;
    }
  }
};

const actions = {
  changeSetting({ commit }, data) {
    commit("CHANGE_SETTING", data);
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
