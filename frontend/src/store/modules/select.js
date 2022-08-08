const select = {
  namespaced: true,
  state: {
    saveStore: "",
  },
  getters: {
    saveStore: (state) => state.saveStore,
  },
  actions: {
    getSaveStore: ({ commit }, index) => {
      commit("GET_SAVE_STORE", index);
    },
  },
  mutations: {
    GET_SAVE_STORE: (state, index) => {
      state.saveStore = index;
    },
  },
};

export default select;
