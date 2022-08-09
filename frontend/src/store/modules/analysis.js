const analysis = {
  namespaced: true,
  state: {
    status: true,
    storeName: "",
    baseStart: "",
    baseEnd: "",
    total: "",
    wordCloud: "",
    wordBad: "",
    date: [],
    chart: {},
    badChart: [],
  },
  getters: {
    status: (state) => state.status,
    storeName: (state) => state.storeName,
    baseStart: (state) => state.baseStart,
    baseEnd: (state) => state.baseEnd,
    total: (state) => state.total,
    wordCloud: (state) => state.wordCloud,
    wordBad: (state) => state.wordBad,
    date: (state) => state.date,
    chart: (state) => state.chart,
    badChart: (state) => state.badChart,
  },
  actions: {
    goodStatus: ({ commit }) => {
      commit("GOOD_STATUS");
    },
    badStatus: ({ commit }) => {
      commit("BAD_STATUS");
    },
    getStoreName: ({ commit }, name) => {
      commit("STORE_NAME", name);
    },
    getBaseStart: ({ commit }) => {
      commit("BASE_START");
    },
    getBaseEnd: ({ commit }) => {
      commit("BASE_END");
    },
    getTotal: ({ commit }, storeValue) => {
      commit("GET_TOTAL", storeValue);
    },
    getWordCloud: ({ commit }, storeValue) => {
      commit("WORD_CLOUD", storeValue);
    },
    getWordBad: ({ commit }, storeValue) => {
      commit("WORD_BAD", storeValue);
    },
    getDate: ({ commit }, dates) => {
      commit("DATE", dates);
    },
    getChart: ({ commit }, value) => {
      commit("CHART", value);
    },
    getBadChart: ({ commit }, value) => {
      commit("BAD_CHART", value);
    },
  },
  mutations: {
    GOOD_STATUS: (state) => {
      state.status = true;
    },
    BAD_STATUS: (state) => {
      state.status = false;
    },
    STORE_NAME: (state, name) => {
      state.storeName = name;
    },
    BASE_START: (state) => {
      let nowDate = new Date();
      let weekDate = nowDate.getTime() - 7 * 24 * 60 * 60 * 1000;
      nowDate.setTime(weekDate);

      let weekYear = nowDate.getFullYear();
      let weekMonth = nowDate.getMonth() + 1;
      let weekDay = nowDate.getDate();

      if (weekMonth < 10) {
        weekMonth = "0" + weekMonth;
      }
      if (weekDay < 10) {
        weekDay = "0" + weekDay;
      }
      let result = weekYear + "-" + weekMonth + "-" + weekDay;
      state.baseStart = result;
    },
    BASE_END: (state) => {
      let nowDate = new Date();
      let weekDate = nowDate.getTime() - 1 * 24 * 60 * 60 * 1000;
      nowDate.setTime(weekDate);

      let weekYear = nowDate.getFullYear();
      let weekMonth = nowDate.getMonth() + 1;
      let weekDay = nowDate.getDate();

      if (weekMonth < 10) {
        weekMonth = "0" + weekMonth;
      }
      if (weekDay < 10) {
        weekDay = "0" + weekDay;
      }
      let result = weekYear + "-" + weekMonth + "-" + weekDay;
      state.baseEnd = result;
    },
    GET_TOTAL: (state, storeValue) => {
      state.total = storeValue.total;
    },
    WORD_CLOUD: (state, storeValue) => {
      storeValue.sort(function (a, b) {
        return b.value - a.value;
      });
      state.wordCloud = storeValue;
    },
    WORD_BAD: (state, storeValue) => {
      storeValue.sort(function (a, b) {
        return a.value - b.value;
      });
      state.wordBad = storeValue;
    },
    DATE: (state, dates) => {
      if (dates[0] > dates[1]) {
        let temp = dates[0];
        dates[0] = dates[1];
        dates[1] = temp;
      }
      state.date = dates;
    },
    CHART: (state, value) => {
      let chartD = {};
      let labels = new Array();
      let data = [
        { label: "팔린 개수", backgroundColor: "#347C72" },
        { label: "전체 개수", backgroundColor: "#C68353" },
      ];
      let dd = new Array();
      let ss = new Array();
      for (let i = 0; i <= value.length - 1; i++) {
        labels.push(value[i].name);
        dd.push(value[i].sold);
        ss.push(value[i].total);
      }
      chartD.labels = labels;
      data[0].data = dd;
      data[1].data = ss;
      chartD.datasets = data;
      state.chart = chartD;
    },
    BAD_CHART: (state, value) => {
      let chartD = {};
      let labels = new Array();
      let data = [
        { label: "남은 갯수", backgroundColor: "#8cb883" },
        { label: "전체 갯수", backgroundColor: "#c49c68" },
      ];
      let dd = new Array();
      let ss = new Array();
      for (let i = 0; i <= value.length - 1; i++) {
        labels.push(value[i].name);
        dd.push(value[i].left);
        ss.push(value[i].total);
      }
      chartD.labels = labels;
      data[0].data = dd;
      data[1].data = ss;
      chartD.datasets = data;
      state.badChart = chartD;
    },
  },
};

export default analysis;
