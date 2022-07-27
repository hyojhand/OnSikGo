<template>
  <div>
    <img src="@/assets/images/solo.jpg" width="40" height="40" />
    <br />
    <span>지은님의 리뷰</span>
    <hr />
    <reviewList></reviewList>
  </div>
</template>

<script>
import reviewList from "@/components/reviewList.vue";
export default {
  name: "MyReviewView",
  components: {
    reviewList,
  },
};
</script>

<style scoped></style>
