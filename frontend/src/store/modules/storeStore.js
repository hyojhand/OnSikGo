const storeStore = {
  namespaced: true,
  state: {
    getStoreId: Number,
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
