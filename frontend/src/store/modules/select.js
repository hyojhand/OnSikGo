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
      console.log("스토어아이디");
      console.log(state.saveStore);
    },
  },
};

export default select;
