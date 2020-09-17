import Vue from "vue";
import Vuex from "vuex";
import getters from "./getters";
import app from "./modules/app";
import user from "./modules/user";
import setting from "./modules/setting";
import role from "./modules/role";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    app,
    setting,
    user,
    role
  },
  getters
});

export default store;
