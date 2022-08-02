const store = {
    namespaced: true,
    state: {
      currentAddress:"",
    },
    getters: {
      currentAddress: state => state.currentAddress,
    },
    actions: {
      getAddress :({commit}, address) => {
        commit("GET_ADDRESS", address)
        console.log("현재 주소 등록완료")
      },
    },
    mutations: {
      GET_ADDRESS: (state, address) => {
        state.currentAddress = address;
      }
    },
  };
  
  export default store;