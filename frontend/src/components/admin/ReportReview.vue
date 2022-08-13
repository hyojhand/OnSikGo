<template>
  <div>
    <div
      v-for="(report, index) in reviewList"
      :key="index"
      class="row">
      <div class="content">
      <div class="detail text-start">
        <li>작성자: {{ report.nickname }}</li>
        <li>가게명: {{ report.storeDto.storeName }}</li>
        <p class="text-primary mt-2">작성내용: {{ report.content }}</p>
      </div>
        <img
          class="siren"
          src="@/assets/images/trash.png"
          @click="deleteReview(report)"
          alt="삭제버튼이었던것.."
        />
      </div>
      <hr class="mt-5">
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: 'ReportReview',

  data() {
    return {
      reviewList: [],
    }
  },

  methods: {
    reportReview() {
      http
        .get('/admin/reportedReviews')
        .then((response) => {
          this.reviewList = response.data;
        })
    },
    deleteReview(review) {
      http
        .delete(`/review/${review.reviewId}`)
        .then((response) => {
          if (response.status == 200) {
            this.reportReview();
          }
        });
    },
  },

  created () {
    this.reportReview();
  }
};
</script>

<style>
.content{
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 5%;
  margin-right: 5%;
}
.detail{
  width: 320px;
}
.siren {
  width: 30px;
  height: 30px;
}
</style>