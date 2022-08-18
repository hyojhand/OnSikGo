import http from "@/util/http-common";

const select = {
  namespaced: true,
  state: {
    saveStore: "",
    saveName: "",
    myStore: "",
    saveMyStore: "",
    storeValue: {},
  },
  getters: {
    saveStore: (state) => state.saveStore,
    saveName: (state) => state.saveName,
    myStore: (state) => state.myStore,
    saveMyStore: (state) => state.saveMyStore,
    storeValue: (state) => state.storeValue,
  },
  actions: {
    getSaveStore: ({ commit }, storeId) => {
      commit("GET_SAVE_STORE", storeId);
    },
    getMyStore: ({ commit }, storeId) => {
      commit("GET_MY_STORE", storeId);
    },
    getStoreValue: ({ commit }, data) => {
      commit("GET_STORE_VALUE", data);
    },
    resetValue: ({ commit }) => {
      commit("RESET_VALUE");
    },
  },
  mutations: {
    GET_SAVE_STORE: (state, storeId) => {
      state.saveStore = storeId;
      http.get(`/store/${storeId}`).then((response) => {
        state.saveName = response.data.storeName;
      });
    },
    GET_MY_STORE: (state, storeId) => {
      state.myStore = storeId;
      http.get(`/store/${storeId}`).then((response) => {
        state.saveMyStore = response.data.storeName;
      });
    },
    GET_STORE_VALUE: (state, data) => {
      state.storeValue = data;
    },
    RESET_VALUE: (state) => {
      state.saveStore = "";
      state.saveName = "";
      state.Mystore = "";
      state.saveMyStore = "";
      state.storeValue = {};
    },
  },
};

export default select;
