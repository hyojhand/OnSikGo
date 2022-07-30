<template>
  <div>
    <img src="@/assets/images/solo.jpg" width="40" height="40" />
    <br />

    <span> {{ userDto.userName }} 님의 리뷰</span>
    <hr />
    <reviewList></reviewList>
  </div>
</template>

<script>
import reviewList from "@/components/reviewList.vue";
import http from "@/util/http-common";
export default {
  name: "MyReviewView",
  components: {
    reviewList,
  },
  data() {
    return {
      userDto: {},
    };
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http.get("/user").then((response) => {
      this.userDto = response.data;
    });
  },
};
</script>

<style scoped></style>
