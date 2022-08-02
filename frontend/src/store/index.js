import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";


Vue.use(Vuex);

import accounts from "@/store/modules/accouts.js"
import store from "@/store/modules/store.js"

export default new Vuex.Store({
  plugins: [createPersistedState()],
  modules: {
    accounts,
    store,
  }
});
