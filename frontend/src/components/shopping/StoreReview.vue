<template>
  <div class="review-container">
    <!--프로필사진-->
    <div @click="moveUserReview()" class="col-3">
      <img :src="`${userImgUrl}`" class="profile-img" />
      <div class="name-font">{{ nickname }}</div>
    </div>
    <!--리뷰 본문 부분-->
    <div class="col-6">
      <div class="content">{{ content }}</div>
    </div>

    <div v-if="userCheck" class="col-3">
      <!-- 리뷰 작성 유저일때 -->
      <div v-if="userDto.nickname == nickname">
        <img
          class="siren"
          src="@/assets/images/trash.png"
          @click="deleteReview(reviewId)"
          alt="삭제버튼이었던것.."
        />
        <div v-if="deleteDuple">삭제가 완료되었습니다.</div>
      </div>

      <!-- 이외의 유저일때 -->
      <div v-else>
        <img
          class="siren"
          src="@/assets/images/siren.png"
          @click="reportReview(reviewId)"
          alter="신고 버튼이었던것.."
        />
        <div v-if="reportDuple" class="report mt-1">
          <div>해당 리뷰를</div>
          <div>신고하였습니다.</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "StoreReview",

  data() {
    return {
      reportDuple: false,
      deleteDuple: false,
      userDto: {},
    };
  },

  props: {
    content: String,
    createdDate: String,
    nickname: String,
    userImgUrl: String,
    storeDto: {},
    reviewId: null,
  },

  computed: {
    ...mapGetters("storeStore", ["getStoreId"]),
    ...mapGetters("accounts", ["userCheck"]),
  },

  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    http.get("/user").then((response) => {
      this.userDto = response.data;
    });
    console.log(this.reviewDto);
  },

  methods: {
    ...mapActions("accounts", ["getReviewNickName"]),
    ...mapActions("store", ["getStoreReviewList"]),
    moveUserReview() {
      this.$router.push({
        name: "myReview",
      });
      this.getReviewNickName(this.nickname);
    },

    reportReview(reviewId) {
      http.patch(`/review/${reviewId}`).then((response) => {
        if (response.status == 200) {
          console.log(response);
          this.reportDuple = true;
        }
      });
    },

    async deleteReview(reviewId) {
      this.deleteDuple = false;
      await http.delete(`/review/${reviewId}`).then((response) => {
        if (response.status == 200) {
          console.log(response);
          // this.$router.go();
        }
      });
      await http.get(`/review/store/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.getStoreReviewList(response.data.reverse());
        }
      });
    },
  },
};
</script>

<style scoped>
div {
  padding: 0;
}
.profile-img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}
.review-container {
  padding-top: 7px;
  padding-bottom: 7px;
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 0;
  width: 95%;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}
.content {
  text-align: start;
  margin: 0;
  margin-left: 17px;
}
.name-font {
  font-size: 12px;
}
button {
  background-color: tomato;
  width: 40px;
  height: 30px;
  border-radius: 5px;
}
.siren {
  width: 20px;
  height: 20px;
}
.report {
  font-size: 8px;
}
</style>
