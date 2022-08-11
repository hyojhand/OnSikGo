const discardStore = {
  namespaced: true,
  state: {
    discardStoreId: "",
    discardStoreImg: "",
    discardStoreName: "",
    discardStoreClose: "",
    discardStoreList: [],
    discardStoreCnt: "",
  },
  getters: {
    discardStoreId: (state) => state.discardStoreId,
    discardStoreImg: (state) => state.discardStoreImg,
    discardStoreName: (state) => state.discardStoreName,
    discardStoreClose: (state) => state.discardStoreClose,
    discardStoreList: (state) => state.discardStoreList,
    discardStoreCnt: (state) => state.discardStoreCnt,
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
    getDiscardStoreClose: ({ commit }, storeClose) => {
      commit("GET_DISCARD_STORECLOSE", storeClose);
    },
    getDsicardStoreList: ({ commit }, storeList) => {
      commit("GET_DISCARD_STORELIST", storeList);
    },
    discardStoreCnt: ({ commit }, storeCnt) => {
      commit("DISCARD_STORE_CNT", storeCnt);
    },
    resetDiscardStore: ({commit}) => {
      commit("RESET_DISCARDSTORE")
    }
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
    GET_DISCARD_STORECLOSE: (state, storeClose) => {
      state.discardStoreClose = storeClose;
      // console.log(storeClose);
    },
    GET_DISCARD_STORELIST: (state, storeList) => {
      state.discardStoreList = storeList;
    },
    DISCARD_STORE_CNT: (state, storeCnt) => {
      state.discardStoreCnt = storeCnt;
    },
    RESET_DISCARDSTORE: (state) => {
      state.discardStoreId = "";
      state.discardStoreImg = "";
      state.discardStoreName = "";
      state.discardStoreClose = "";
      state.discardStoreList = [];
      state.discardStoreCnt = "";
    }
  },
};

export default discardStore;
