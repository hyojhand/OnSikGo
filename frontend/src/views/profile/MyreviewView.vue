<template>
  <div>
    <img src="@/assets/images/solo.jpg" width="40" height="40" />
    <br />

    <span> {{ reviewList[0].nickname }} 님의 리뷰</span>
    <hr />
    <h5>🙋‍♀️🙋‍♂️ 리뷰 내역</h5>
    <reviewList
      v-for="(review, index) in reviewList"
      :key="index"
      v-bind="review"
    />
  </div>
</template>

<script>
import reviewList from "@/components/profile/reviewList.vue";
import http from "@/util/http-common";
export default {
  name: "MyReviewView",
  components: {
    reviewList,
  },
  data() {
    return {
      reviewList: [],
    };
  },
  created() {
    http
      .post("/review/user", {
        nickname: this.$route.params.nickname,
      })
      .then((response) => {
        console.log(this.reviewList);
        if (response.status == 200) {
          if (response.data != null) {
            this.reviewList = response.data;
          } else {
            alert("리뷰가 없습니다.");
          }
        }
      });
  },
};
</script>

<style scoped></style>
