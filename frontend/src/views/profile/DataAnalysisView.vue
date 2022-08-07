<template>
  <div class="container">
    <!--현재 내 날짜로부터 7일전날짜부터 ~ 현재 날짜 -->
    <div class="store font-l">{{ this.name }}</div>

    <div class="mt-5 mb-5">
      <h3>점주님은, 온식고를 통해</h3>
      <br />
      <h4>{{ this.storeValue.total }}원치의 세상을 구하셨어요!</h4>
    </div>
    <div v-if="!once" @click="opento" class="date font-m">
      날짜를 선택하세요
    </div>
    <div v-else>
      <div class="calender-container">
        <div @click="open">
          <div v-if="this.dates[0]" class="date">{{ this.dates[0] }}일부터</div>
          <div v-if="this.dates[1]" class="date">
            {{ this.dates[1] }}일까지 선택하셨습니다.
          </div>
        </div>
        <button
          v-if="this.dates[0] && this.dates[1]"
          class="index-btn border-m radius-m text-s"
          @click="search"
        >
          검색하기
        </button>
      </div>
      <v-date-picker
        v-if="!pick"
        v-model="dates"
        range
        color="#FFCC80"
        no-title
        class="calender"
      ></v-date-picker>
    </div>
    <!-- 데이터 불러와서 상위 5개의 제품 보여줌 -->
    <word-cloud :to="this.storeValue"></word-cloud>
    <bar-chart :to="this.storeValue"></bar-chart>

    <!--판매한 금액의 총금액을 넣음-->
  </div>
</template>

<script>
import barChart from "@/components/profile/barChart.vue";
import wordCloud from "@/components/profile/wordCloud.vue";
import http from "@/util/http-common";

export default {
  name: "dataAnalysisView",
  components: {
    barChart,
    wordCloud,
  },
  props: ["storeId"],
  data() {
    return {
      id: "",
      name: "",
      baseStart: "",
      baseEnd: "",
      dates: [],
      pick: true,
      once: false,
      storeValue: "",
    };
  },
  async created() {
    // 날짜 계산
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
    let nowDate2 = new Date();
    let result = weekYear + "-" + weekMonth + "-" + weekDay;
    this.baseStart = result;

    weekDate = nowDate2.getTime() - 1 * 24 * 60 * 60 * 1000;
    nowDate2.setTime(weekDate);

    weekYear = nowDate2.getFullYear();
    weekMonth = nowDate2.getMonth() + 1;
    weekDay = nowDate2.getDate();

    if (weekMonth < 10) {
      weekMonth = "0" + weekMonth;
    }
    if (weekDay < 10) {
      weekDay = "0" + weekDay;
    }

    result = weekYear + "-" + weekMonth + "-" + weekDay;
    this.baseEnd = result;
    // 가게 이름 받아오기
    this.id = this.$route.params.storeId;
    await http.get(`/store/${this.id}`).then((response) => {
      this.name = response.data.storeName;
    });
    // 정보 가져오기
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http
      .post(`/analysis/sale-history`, {
        storeId: this.id,
        startDate: this.baseStart,
        endDate: this.baseEnd,
      })
      .then((response) => {
        this.storeValue = response.data;
        console.log("나오나?");
        let temp = this.storeValue;

        console.log(temp.slice(1, 0));
      });
  },
  methods: {
    open() {
      this.pick = !this.pick;
    },
    search() {
      this.pick = !this.pick;
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http
        .post(`/analysis/sale-history`, {
          storeId: this.id,
          startDate: this.dates[0],
          endDate: this.dates[1],
        })
        .then((response) => {
          this.storeValue = response.data;
          console.log(this.storeValue);
        });
    },
    opento() {
      this.pick = !this.pick;
      this.once = !this.once;
    },

    // 날짜구하기
  },
  computed: {
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: center;
}
.store {
  font-size: 20px;
  border-bottom: 2px solid rgba(0, 0, 0, 10%);
  width: 100%;
  padding: 3% 0;
  margin: 2% 0;
}
.calender {
  color: white;
  background-color: white;
}
.date {
  color: black;
  margin-bottom: 3%;
}
.calender-container {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  margin: 5% 0;
}
.index-btn {
  color: white;
  background-color: rgba(0, 0, 0, 60%);
}
</style>
