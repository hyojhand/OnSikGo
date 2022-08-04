<template>
  <div class="container">
    <!--현재 내 날짜로부터 7일전날짜부터 ~ 현재 날짜 -->
    <div class="store font-l">지금 내 매장 이름</div>

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
          @click="open"
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
    <word-cloud></word-cloud>
    <bar-chart></bar-chart>

    <!--판매한 금액의 총금액을 넣음-->
    <div>
      <span>점주님은, 이번주 온식고를 통해 </span><br />
      <span>얼마원 가치의 세상을 구하셨어요!</span>
    </div>
  </div>
</template>

<script>
import barChart from "@/components/profile/barChart.vue";
import wordCloud from "@/components/profile/wordCloud.vue";

export default {
  name: "dataAnalysisView",
  components: {
    barChart,
    wordCloud,
  },
  data() {
    return {
      dates: [],
      pick: true,
      once: false,
    };
  },
  methods: {
    open() {
      this.pick = !this.pick;
    },
    opento() {
      this.pick = !this.pick;
      this.once = !this.once;
    },
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
