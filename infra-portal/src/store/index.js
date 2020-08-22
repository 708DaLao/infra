import Vue from "vue";
import Vuex from "vuex";
import getters from './getters'
import app from './modules/app'
import user from './modules/user'
import setting from  './modules/setting'

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    app,
    setting,
    user
  },
  getters
})

export default store
