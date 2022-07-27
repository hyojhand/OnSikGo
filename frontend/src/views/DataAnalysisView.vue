<template>
  <div>
    <br />
    <p>지금 내 매장 이름</p>
    <br />
    <h3>이번 주 매장 남은 재고량</h3>
    <h5>상위 5개의 제품</h5>

    <!--현재 내 날짜로부터 7일전날짜부터 ~ 현재 날짜 -->
    <div id="date">
      <p>22.07.18-22.07.24</p>
    </div>
    <doughnut-chart></doughnut-chart>
    <br />
    <br />
    <!-- 데이터 불러와서 상위 5개의 제품 보여줌 -->
    <h1>상위 5개의 제품</h1>
    <div class="d-flex justify-content-center">
      <ul>
        <li>첫번째 많이 버려진 상품</li>
        <li>두번째 많이 버려진 상품</li>
        <li>세번째 많이 버려진 상품</li>
        <li>네번째 많이 버려진 상품</li>
        <li>다섯번째 많이 버려진 상품</li>
      </ul>
    </div>
    <br />
    <br />
    <br />
    <br />
    <!--판매한 금액의 총금액을 넣음-->
    <div>
      <span
        >점주님은, 이번주 <strong id="green">온식고</strong>를 통해
        <br /><strong id="green">얼마원</strong> 가치의 세상을 구하셨어요!</span
      >
    </div>
  </div>
</template>

<script>
import doughnutChart from "@/components/doughnutChart.vue";

export default {
  name: "dataAnalysisView",
  components: {
    doughnutChart,
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
