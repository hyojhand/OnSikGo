<template>
  <div>
    <div class="mt-7">
      <h3>🙋‍♀️🙋‍♂️리뷰</h3>
    </div>
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
      nickname: "",
    };
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http
      .post("/review/user", {
        nickname: this.$route.params.nickname,
      })
      .then((response) => {
        console.log(this.reviewList);
        if (response.status == 200) {
          if (response.data != null) {
            this.reviewList = response.data;
            console.log(this.reviewList);
          } else {
            alert("리뷰가 없습니다.");
          }
        }
      });
  },
};
</script>

<style scoped></style>
