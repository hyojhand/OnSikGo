<template>
  <div>
    <h1>전체리뷰</h1>

    <div
      v-for="(review, index) in reviewList"
      :key="index"
      class="row fs-5 tab-link current">
      <div class="text-start">
        <p class="text-primary">{{ review.nickname }} : {{ review.content }}</p>
        <p>작성한 가게명: {{ review.storeDto.storeName }}</p>
      </div>
        <v-btn @click="deleteReview(review)" color="error" samll depressed>삭제</v-btn>
        <hr>
      </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: 'AllReview',

  data: function () {
    return {
      reviewListL: [],
    }
  },

  methods: {
    allReview() {
      http
        .get('/review/total')
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
    this.allReview();
  }
};
</script>

<style>

</style>