const accounts = {
    namespaced: true,
    state: {
      userCheck:0,
      myReviewList: [],

    },
    getters: {
      userCheck: state => state.userCheck,
      myReviewList: state => state.myReviewList,
    },
    actions: {
      getUserCheck:({commit}, userCheck) => {
        commit("GET_USERCHECK", userCheck)
      },
      getMyReviewList:({commit}, myReviewList) => {
        commit("GET_MYREVIEWLIST", myReviewList)
      },
    },
    mutations: {
      GET_USERCHECK: (state, userCheck) => {
        state.userCheck = userCheck;
      },
      GET_MYREVIEWLIST: (state, myReviewList) => {
        state.myReviewList = myReviewList;
      },
    },
  };
  
  export default accounts;