<template>
  <div>
    <div class="row">
      <!--프로필사진-->
      <div class="col-3" @click="moveUserReview()">
        <img :src="`${userImgUrl}`" style="height: 40px" />
        <p style="font-size: small">{{ nickname }}</p>
      </div>
      <!--리뷰 본문 부분-->
      <div class="col-9">
        <p>{{ content }}</p>
        <hr class="mt-3" />
      </div>
    </div>
  </div>
</template>

<script>
// import http from "@/util/http-common";
export default {
  name: "StoreReview",
  props: {
    content: String,
    createdDate: String,
    nickname: String,
    userImgUrl: String,
    storeDto: {},
  },

  created() {},
  methods: {
    moveUserReview() {
      this.$router.push({
        name: "myReview",
        params: { nickname: this.nickname },
      });
    },
  },
};
</script>

<style scoped>
img {
  margin-bottom: 5px;
}
</style>
