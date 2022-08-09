const offdayStore = {
  namespaced: true,
  state: {
    storeOffday: "",
  },
  getters: {
    storeOffday: (state) => state.storeOffday,
  },
  actions: {
    storeOffday: ({ commit }, storeOffday) => {
      commit("STORE_OFF_DAY", storeOffday);
    },
  },
  mutations: {
    STORE_OFF_DAY: (state, storeOffday) => {
      state.storeOffday = storeOffday;
    },
  },
};

export default offdayStore;
