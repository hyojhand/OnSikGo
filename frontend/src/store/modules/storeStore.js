const storeStore = {
  namespaced: true,
  state: {
    getStoreId: "",
  },
  getters: {
    getStoreId: (state) => state.getStoreId,
  },
  actions: {
    getStoreId: ({ commit }, storeId) => {
      commit("GET_STOREID", storeId);
    },
  },
  mutations: {
    GET_STOREID: (state, storeId) => {
      state.getStoreId = storeId;
    },
  },
};

export default storeStore;
