<template>
  <div class="container">
    <!--현재 내 날짜로부터 7일전날짜부터 ~ 현재 날짜 -->

    <div class="mt-5 mb-5">
      <span style="font-size: 1.3rem">"{{ this.storeName }}" 점주님은 </span
      ><br /><span style="font-size: 1.3rem; color: #8cb883">온식고</span>
      <span style="font-size: 1.3rem">를 통해</span>
      <br />
      <span style="font-size: 1.3rem; color: #8cb883">{{ this.total }}원 </span
      ><span style="font-size: 1.3rem">가치의 세상을 구하셨어요!</span>
    </div>
    <div v-if="!once" @click="opento" class="date font-m mt-5">
      📆 <br /><span style="font-size: 0.8rem"
        >* 달력을 클릭하여 날짜를 선택하세요 *</span
      >
    </div>
    <div v-else>
      <div class="calender-container">
        <div @click="open">
          <div v-if="this.dates[0]" class="date">
            시작일자: {{ this.dates[0] }}
          </div>
          <div v-if="this.dates[1]" class="date">
            끝 일자: {{ this.dates[1] }}
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
    <div class="ti-box row" v-if="this.wordCloud.length > 0">
      <!-- <div class="col-6 imo-box likes"> -->
      <!-- <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="30"
          fill="#8cb883"
          class="bi bi-emoji-smile-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zM4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM10 8c-.552 0-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5S10.552 8 10 8z"
          />
        </svg> -->
      <!-- </div> -->
      <!-- <div class="col-6 imo-box" @click="bad()">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="30"
          fill="tomato"
          class="bi bi-emoji-frown-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm-2.715 5.933a.5.5 0 0 1-.183-.683A4.498 4.498 0 0 1 8 9.5a4.5 4.5 0 0 1 3.898 2.25.5.5 0 0 1-.866.5A3.498 3.498 0 0 0 8 10.5a3.498 3.498 0 0 0-3.032 1.75.5.5 0 0 1-.683.183zM10 8c-.552 0-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5S10.552 8 10 8z"
          />
        </svg>
        <span
          >저희는 <br />
          관리가 필요해요..</span
        >
      </div> -->
    </div>

    <!-- 데이터 불러와서 상위 5개의 제품 보여줌 -->
    <div v-if="this.wordCloud.length > 0">
      <div v-if="this.total != 0">
        <span style="font-size: 1.3rem">😋 잘 팔린 상품들을 한눈에! 😋</span
        ><br /><br />
        <span style="font-size: 0.9rem; font-weight: 500"
          >글씨 크기가 클 수록 많이 팔린 상품입니다.</span
        >
        <word-cloud></word-cloud>
      </div>
      <div class="mt-5">
        <span style="font-size: 1.3rem">📊 팔린 개수와 전체 개수 비교 📊</span>
        <br />
        <span style="font-size: 0.9rem; font-weight: 500"
          >오른쪽으로 스크롤하여 데이터를 비교해보세요!</span
        ><br /><br />
        <bar-chart></bar-chart>
      </div>
    </div>
    <div v-else class="non-msg">
      <div>해당 기간동안</div>
      <div>등록된 제품이 없어요</div>
    </div>

    <!--판매한 금액의 총금액을 넣음-->
  </div>
</template>

<script>
import barChart from "@/components/profile/barChart.vue";
import wordCloud from "@/components/profile/wordCloud.vue";
import http from "@/util/http-common";
import { mapActions, mapGetters } from "vuex";

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
      dates: [],
      pick: true,
      once: false,
      storeValue: "",
    };
  },
  async created() {
    this.getBaseStart();
    this.getBaseEnd();
    // 가게 이름 받아오기
    this.id = this.$route.params.storeId;
    await http.get(`/store/${this.id}`).then((response) => {
      this.getStoreName(response.data.storeName);
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
        console.log(this.storeValue.good);
        this.getTotal(this.storeValue);
        this.getWordCloud(this.storeValue.good);
        // this.getWordBad(this.storeValue.good);
        this.getChart(this.storeValue.good);
      });
  },
  methods: {
    ...mapActions("analysis", [
      // "goodStatus",
      // "badStatus",
      "getBaseStart",
      "getBaseEnd",
      "getStoreName",
      "getTotal",
      "getDate",
      "getWordCloud",
      // "getWordBad",
      "getChart",
    ]),
    open() {
      this.pick = !this.pick;
    },
    search() {
      this.pick = !this.pick;
      this.getDate(this.dates);
      this.dates = this.date;
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
          this.getTotal(this.storeValue);
          this.getWordCloud(this.storeValue.good);
          // this.getWordBad(this.storeValue.good);
          this.getChart(this.storeValue.good);
        });
    },
    opento() {
      this.pick = !this.pick;
      this.once = !this.once;
    },
    // good() {
    //   this.goodStatus();
    //   console.log(this.status);
    // },
    // bad() {
    //   this.badStatus();
    //   console.log(this.status);
    // },
  },

  // 날짜구하기

  computed: {
    ...mapGetters("analysis", [
      // "status",
      "baseStart",
      "baseEnd",
      "date",
      "storeName",
      "total",
      "wordCloud",
    ]),
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
.ti-box {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-evenly;
  align-items: center;
  margin: 5% 0;
}
.imo-box {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 0;
  cursor: pointer;
}
.imo-box > span {
  text-align: start;
  margin-left: 7%;
  color: tomato;
}
.likes > span {
  color: black;
  font-size: 1.3rem;
}
.non-msg {
  width: 100%;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.non-msg > div {
  font-size: 30px;
  color: rgba(0, 0, 0, 0.2);
}
</style>
