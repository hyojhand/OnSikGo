const itemStore = {
  namespaced: true,
  state: {
    getItemId: "",
  },
  getters: {
    getItemId: (state) => state.getItemId,
  },
  actions: {
    getItemId: ({ commit }, itemId) => {
      commit("GET_ITEMID", itemId);
    },
  },
  mutations: {
    GET_ITEMID: (state, itemId) => {
      state.getItemId = itemId;
    },
  },
};

export default itemStore;
