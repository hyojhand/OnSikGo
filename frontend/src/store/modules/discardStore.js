const discardStore = {
  namespaced: true,
  state: {
    discardStoreId: "",
    discardStoreImg: "",
    discardStoreName: "",
  },
  getters: {
    discardStoreId: (state) => state.discardStoreId,
    discardStoreImg: (state) => state.discardStoreImg,
    discardStoreName: (state) => state.discardStoreName,
  },
  actions: {
    discardStoreId: ({ commit }, storeId) => {
      commit("DISCARD_STOREID", storeId);
    },
    discardStoreImg: ({ commit }, storeImg) => {
      commit("DISCARD_STOREIMG", storeImg);
    },
    discardStoreName: ({ commit }, storeName) => {
      commit("DISCARD_STORENAME", storeName);
    },
  },
  mutations: {
    DISCARD_STOREID: (state, storeId) => {
      state.discardStoreId = storeId;
    },
    DISCARD_STOREIMG: (state, storeImg) => {
      state.discardStoreImg = storeImg;
    },
    DISCARD_STORENAME: (state, storeName) => {
      state.discardStoreName = storeName;
    },
  },
};

export default discardStore;
