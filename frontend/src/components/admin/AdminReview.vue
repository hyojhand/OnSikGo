<template>
  <div>
    <h1 class="mb-5">AdminReview</h1>
    <div
      v-for="(review, index) in reviewList"
      :key="index"
      class="row fs-5">
      <div class="text-start">
        <p class="text-primary">{{ review.nickname }} : {{ review.content }}</p>
        <p>작성한 가게명: {{ review.storeDto.storeName }}</p>
      </div>
        <v-btn @click="deleteReview()" color="error" samll depressed>삭제</v-btn>
        <hr>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name : "AdminReview",

  data: function () {
    return {
      reviewList: [],
    }
  },

  methods : {
    allReview() {
      http
        .get('/review/total')
        .then((response) => {
          this.reviewList = response.data;
          console.log(response.data);
        })
    },

    deleteReview() {
      console.log(this.reviewList);
      console.log(this.reviewList.data.reviewId);
      http
        .delete('/review/{review_id}')
    }
  },

  created () {
    this.allReview();
  }
};
</script>

<style>

</style>