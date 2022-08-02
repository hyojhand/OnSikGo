const store = {
    namespaced: true,
    state: {
      currentAddress:"",
      currentItemId:"",
      orderStore:"",
      
    },
    getters: {
      currentAddress: state => state.currentAddress,
      currentItemId: state => state.currentItemId,
      orderStore: state => state.orderStore,
    },
    actions: {
      getAddress :({commit}, address) => {
        commit("GET_ADDRESS", address)
        console.log("현재 주소 등록완료")
      },
      getItemId : ({commit}, itemId) => {
        commit("GET_ITEMID", itemId)
        console.log("ItemId 등록 완료")
      },
      getOrderStore : ({commit}, storeId) => {
        commit("GET_ORDERSTORE", storeId)
        console.log("storeId 등록 완료")
      },
    },
    mutations: {
      GET_ADDRESS: (state, address) => {
        state.currentAddress = address;
      },
      GET_ITEMID: (state, itemId) => {
        state.currentItemId = itemId;
      },
      GET_ORDERSTORE: (state, storeId) => {
        state.orderStore = storeId;
      },


    },
  };
  
  export default store;