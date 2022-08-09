import http from "@/util/http-common";

const select = {
  namespaced: true,
  state: {
    saveStore: "",
    saveName: "",
    myStore: "",
    saveMyStore: "",
  },
  getters: {
    saveStore: (state) => state.saveStore,
    saveName: (state) => state.saveName,
    myStore: (state) => state.myStore,
    saveMyStore: (state) => state.saveMyStore,
  },
  actions: {
    getSaveStore: ({ commit }, storeId) => {
      commit("GET_SAVE_STORE", storeId);
    },
    getMyStore: ({ commit }, storeId) => {
      commit("GET_MY_STORE", storeId);
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
  },
};

export default select;
