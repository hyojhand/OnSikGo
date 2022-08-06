const store = {
    namespaced: true,
    state: {
      // 현재 주소
      currentAddress:"",
      // 위도 경도
      currentX:"",
      currentY:"",
      // 주문 상품 PK
      currentItemId:"",
      // 주문 가게 PK
      orderStore:"",
      aroundSaleStore:[],
      myStoreId:"",

    },
    getters: {
      currentAddress: state => state.currentAddress,
      currentItemId: state => state.currentItemId,
      currentX: state => state.currentX,
      currentY: state => state.currentY,
      orderStore: state => state.orderStore,
      aroundSaleStore: state => state.aroundSaleStore,
      myStoreId: state => state.myStoreId,

    },
    actions: {
      getAddress :({commit}, address) => {
        commit("GET_ADDRESS", address)
        // console.log("현재 주소 등록완료")
      },
      getCurrentX :({commit}, currentX) => {
        commit("GET_CURRENTX", currentX)
        // console.log("현재 위도 등록완료")
      },
      getCurrentY :({commit}, currentY) => {
        commit("GET_CURRENTY", currentY)
        // console.log("현재 경도 등록완료")
      },  
      getItemId : ({commit}, itemId) => {
        commit("GET_ITEMID", itemId)
        // console.log("ItemId 등록 완료")
      },
      getOrderStore : ({commit}, storeId) => {
        commit("GET_ORDERSTORE", storeId)
        // console.log("storeId 등록 완료")
      },
      getAroundSaleStore : ({commit}, aroundSaleStore) => {
        commit("GET_AROUNDSALESTORE", aroundSaleStore)
        // console.log("근처 가게 등록 완료")
      },
      getMyStoreId : ({commit}, myStoreId) => {
        commit("GET_MYSTOREID", myStoreId)
      }
    },
    mutations: {
      GET_ADDRESS: (state, address) => {
        state.currentAddress = address;
      },
      GET_ITEMID: (state, itemId) => {
        state.currentItemId = itemId;
      },
      GET_CURRENTX: (state, currentX) => {
        state.currentX = currentX;
      },
      GET_CURRENTY: (state, currentY) => {
        state.currentY = currentY;
      },
      GET_ORDERSTORE: (state, storeId) => {
        state.orderStore = storeId;
      },
      GET_AROUNDSALESTORE: (state, aroundSaleStore) => {
        state.aroundSaleStore = aroundSaleStore;
      },
      GET_MYSTOREID: (state, myStoreId) => {
        state.myStoreId = myStoreId
      },
    },
  };
  
  export default store;