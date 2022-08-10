const accounts = {
    namespaced: true,
    state: {
      userCheck:0,
      myReviewList: [],
      ownerOrderList: [],
    },
    getters: {
      userCheck: state => state.userCheck,
      myReviewList: state => state.myReviewList,
      ownerOrderList: state => state.ownerOrderList,
    },
    actions: {
      getUserCheck:({commit}, userCheck) => {
        commit("GET_USERCHECK", userCheck)
      },
      getMyReviewList:({commit}, myReviewList) => {
        commit("GET_MYREVIEWLIST", myReviewList)
      },
      getOwnerOrderList:({commit}, ownerOrderList) => {
        commit("GET_OWNERORDER", ownerOrderList)
      },
    },
    mutations: {
      GET_USERCHECK: (state, userCheck) => {
        state.userCheck = userCheck;
      },
      GET_MYREVIEWLIST: (state, myReviewList) => {
        state.myReviewList = myReviewList;
      },
      GET_OWNERORDER: (state, ownerOrderList) => {
        state.ownerOrderList = ownerOrderList;
      },
    },
  };
  
  export default accounts;