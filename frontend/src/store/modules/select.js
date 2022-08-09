import http from "@/util/http-common";

const select = {
  namespaced: true,
  state: {
    saveStore: "",
    saveName: "",
  },
  getters: {
    saveStore: (state) => state.saveStore,
    saveName: (state) => state.saveName,
  },
  actions: {
    getSaveStore: ({ commit }, storeId) => {
      commit("GET_SAVE_STORE", storeId);
    },
  },
  mutations: {
    GET_SAVE_STORE: (state, storeId) => {
      state.saveStore = storeId;
      http.get(`/store/${storeId}`).then((response) => {
        state.saveName = response.data.storeName;
      });
    },
  },
};

export default select;
