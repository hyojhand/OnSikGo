<template>
  <div>
    <div
      v-for="(review, index) in reviewList"
      :key="index"
      class="row">
      <div class="text-start mt-3">
        <p>글쓴이: {{ review.nickname }}</p>
        <p>가게명: {{ review.storeDto.storeName }}</p>
        <p class="text-primary fs-5">작성내용: {{ review.content }}</p>
      </div>
        <v-btn @click="deleteReview(review)" color="error" style="width: 100px; left:300px;" depressed>삭제</v-btn>
        <hr class="mt-5">
      </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: 'AllReview',

  data() {
    return {
      reviewList: [],
    }
  },

  methods: {
    allReview() {
      http
        .get('/review/total')
        .then((response) => {
          this.reviewList = response.data;
        })
    },

    deleteReview(review) {
      http
        .delete(`/review/${review.reviewId}`)
        .then((response) => {
          if (response.status == 200) {
            this.allReview();
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