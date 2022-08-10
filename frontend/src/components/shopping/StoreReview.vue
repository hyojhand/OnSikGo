<template>
  <div>
    <div class="row">
      <!--프로필사진-->
      <div class="col-2" @click="moveUserReview()">
        <img :src="`${userImgUrl}`" style="height: 40px" />
        <p style="font-size: small">{{ nickname }}</p>
      </div>
      <!--리뷰 본문 부분-->
      <div class="col-8">
        <p>{{ content }}</p>
      </div>
      <div class="reviewicon col-3" v-if="userCheck" style="margin-left: 330px;">
        <!-- 리뷰 작성 유저일때 -->
        <div v-if="userDto.nickname == nickname">
          <v-btn @click="deleteReview(reviewId)" color="error" style="width: 35px">삭제</v-btn>
          <div v-if="deleteDuple">삭제가 완료되었습니다.</div>
        </div>
        <!-- 이외의 유저일때 -->
        <div v-else class="report">
            <img src="@/assets/images/siren.png" @click="reportReview(reviewId)" style="width: 35px;">
        <div v-if="reportDuple">신고가 완료되었습니다.</div>
        </div>
      </div>
      <hr class="mt-3" />
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
import { mapGetters, mapActions } from 'vuex';
export default {
  name: "StoreReview",
  
  data () {
    return {
      reportDuple: false,
      deleteDuple: false,
      userDto: {},
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

  computed : {
    ...mapGetters("accounts", [
      "userCheck",
    ]),
  },

  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    
    http
      .get("/user")
      .then((response) => {
        this.userDto = response.data;
      });
  },

  methods: {
    ...mapActions("accounts", ["getReviewNickName"]),
    moveUserReview() {
      this.$router.push({
        name: "myReview",
      });
      this.getReviewNickName(this.nickname)
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
    },
    
    deleteReview(reviewId){
      this.deleteDuple = false;
      http
        .delete(`/review/${reviewId}`)
        .then((response) => {
          if (response.status == 200) {
            console.log(response);
            // this.$router.go();
          } 
        })
    },
  },
};
</script>

<style scoped>
img {
  margin-bottom: 0px;
}


</style>
