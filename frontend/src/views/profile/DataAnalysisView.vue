<template>
  <div>
    <!--현재 내 날짜로부터 7일전날짜부터 ~ 현재 날짜 -->
    <p>지금 내 매장 이름</p>
    <!-- 데이터 불러와서 상위 5개의 제품 보여줌 -->
    <bar-chart></bar-chart>

    <!--판매한 금액의 총금액을 넣음-->
    <div>
      <span
        >점주님은, 이번주 온식고를 통해 얼마원 가치의 세상을 구하셨어요!</span
      >
    </div>
  </div>
</template>

<script>
import barChart from "@/components/profile/barChart.vue";

export default {
  name: "dataAnalysisView",
  components: {
    barChart,
  },
  data() {
    return {
      chartOptions: {
        hoverBorderWidth: 20,
      },
      chartData: {
        hoverBackgroundColor: "red",
        hoverBorderWidth: 10,
        labels: ["Green", "Red", "Blue"],
        datasets: [
          {
            label: "Data One",
            backgroundColor: ["#41B883", "#E46651", "#00D8FF"],
            data: [1, 10, 5],
          },
        ],
      },
    };
  },
};
</script>

<style scoped>
#date {
  text-align: right;
}

#green {
  color: green;
}
</style>
