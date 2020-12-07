// this.$store.state.xxx
const state = {
  isCollapse: false // 是否折叠侧边栏
};

// this.$store.commit()
const mutations = {
  changeCollapse: state => {
    state.isCollapse = !state.isCollapse;
  }
};

// this.$store.dispatch()
const actions = {};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
