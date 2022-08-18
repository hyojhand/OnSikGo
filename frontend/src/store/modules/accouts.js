const accounts = {
  namespaced: true,
  state: {
    userCheck: 0,
    myReviewList: [],
    ownerOrderList: [],
    reviewNickName: "",
  },
  getters: {
    userCheck: (state) => state.userCheck,
    myReviewList: (state) => state.myReviewList,
    ownerOrderList: (state) => state.ownerOrderList,
    reviewNickName: (state) => state.reviewNickName,
  },
  actions: {
    getUserCheck: ({ commit }, userCheck) => {
      commit("GET_USERCHECK", userCheck);
    },
    getMyReviewList: ({ commit }, myReviewList) => {
      commit("GET_MYREVIEWLIST", myReviewList);
    },
    getOwnerOrderList: ({ commit }, ownerOrderList) => {
      commit("GET_OWNERORDER", ownerOrderList);
    },
    getReviewNickName: ({ commit }, reviewNickName) => {
      commit("GET_REVIEWNICKNAME", reviewNickName);
    },
    resetAccounts: ({ commit }) => {
      commit("RESET_ACCOUNT");
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
    GET_REVIEWNICKNAME: (state, reviewNickName) => {
      state.reviewNickName = reviewNickName;
    },
    RESET_ACCOUNT: (state) => {
      state.userCheck = 0;
      state.myReviewList = [];
      state.ownerOrderList = [];
      state.reviewNickName = "";
    },
  },
};

export default accounts;
