import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import accounts from "@/store/modules/accouts.js";
import select from "@/store/modules/select.js";
import store from "@/store/modules/store.js";
import itemStore from "@/store/modules/itemStore";
import storeStore from "@/store/modules/storeStore";
import discardStore from "@/store/modules/discardStore";
import analysis from "./modules/analysis";
import offdayStore from "@/store/modules/offdayStore";
export default new Vuex.Store({
  plugins: [createPersistedState()],
  modules: {
    analysis,
    select,
    accounts,
    store,
    itemStore,
    storeStore,
    discardStore,
    offdayStore,
  },
});
