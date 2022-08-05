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
        <div class="report">
            <img src="@/assets/images/siren.png" @click="reportReview(reviewId)" style="width: 30%">
          <div v-if="reportDuple">신고가 완료되었습니다.</div>
        </div>
        <hr class="mt-3" />
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
export default {
  name: "StoreReview",
  
  data () {
    return {
      reportDuple: false,
    }
  },

  props: {
    content: String,
    createdDate: String,
    nickname: String,
    userImgUrl: String,
    storeDto: {},
    reviewId: null,
  },

  created() {},
  methods: {
    moveUserReview() {
      this.$router.push({
        name: "myReview",
        params: { nickname: this.nickname },
      });
    },

    reportReview(reviewId) {
      http
        .patch(`/review/${reviewId}`)
        .then((response) => {
          if (response.status == 200) {
            console.log(response);
            this.reportDuple = true;
          }
        })
    }
  },
};
</script>

<style scoped>
img {
  margin-bottom: 5px;
}

.report {
  margin-top: 15%;
  margin-left: 70%;
}
</style>
