const accounts = {
    state: {
        currentAddress:"",

    },
    getters: {

    },
    actions: {
        getAddress :({commit}, address) => {
            commit("GET_ADDRESS", address)
        },
    },
    mutations: {
        GET_ADDRESS: (state, address) => {
            state.currentAddress = address;
        }
    },
  };
  
  export default accounts;