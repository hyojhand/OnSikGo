<template>
  <div>
    <div
      v-for="(report, index) in reviewList"
      :key="index"
      class="row">
      <div class="text-start mt-3">
        <li>작성자명: {{ report.nickname }}</li>
        <li>가게명: {{ report.storeDto.storeName }}</li>
        <p class="text-primary fs-5">내용: {{ report.content }}</p>
      </div>
        <v-btn @click="deleteReview(report)" color="error" style="width: 100px; left:300px;" depressed>삭제</v-btn>
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

</style>