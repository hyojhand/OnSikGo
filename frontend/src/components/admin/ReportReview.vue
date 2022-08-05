<template>
  <div>
    <h2>신고된 리뷰</h2>
    <div
      v-for="(report, index) in reviewList"
      :key="index"
      class="row">
      <div class="text-start">
        <p class="text-primary">{{ report.nickname }} : {{ report.content }}</p>
        <p>작성한 가게명: {{ report.storeDto.storeName }}</p>
      </div>
        <v-btn @click="deleteReview(report)" color="error" samll depressed>삭제</v-btn>
      <hr>
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
          console.log(response.data);
        })
    },
    deleteReview(review) {
      console.log(review.reviewId);
      http
        .delete(`/review/${review.reviewId}`)
        .then((response) => {
          if (response.status == 200) {
            this.$router.go();
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