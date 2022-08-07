const accounts = {
    namespaced: true,
    state: {
      userCheck:0,

    },
    getters: {
      userCheck: state => state.userCheck,
    },
    actions: {
      getUserCheck:({commit}, userCheck) => {
        commit("GET_USERCHECK", userCheck)
      },
    },
    mutations: {
      GET_USERCHECK: (state, userCheck) => {
        state.userCheck = userCheck;
      },
    },
  };
  
  export default accounts;