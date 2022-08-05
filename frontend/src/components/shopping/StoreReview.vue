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
          <v-btn @click="reportReview(reviewId)" class="error" small>신고
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-exclamation-octagon" viewBox="0 0 16 16">
              <path d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353L4.54.146zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1H5.1z"/>
              <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>
            </svg>
          </v-btn>
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
    reviewId: String,
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
        .patch(`/review/report/${reviewId}`)
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
