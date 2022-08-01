import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import accounts from "@/store/modules/accouts.js"
import store from "@/store/modules/store.js"

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  modules: {
    accounts,
    store
  }
});
