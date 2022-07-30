<template>
  <v-card class="mx-auto card-box" max-width="344">
    <div class="row card-box" v-if="user">
      <img
        class="img-box col-5"
        src="https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/4gqX/image/wIXZfUhOPGGGZxaZ0Nsmigd1paU.jpeg"
        alt="사진이었던것.."
      />
      <div class="col-7 order-box">
        <v-list-item-content>
          <v-list-item-title class="text mb-3 msg-box">
            <span>{ 최지은 } </span>
            <span class="notice">님의</span>
          </v-list-item-title>
          <v-list-item-title class="text mb-3 msg-box notice"
            >주문이 준비되었습니다.</v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="btn-box mb-1">
          <p class="time-text">10분전</p>
          <button class="border-m radius-m notice-btn" @click="gohistory()">
            주문 보러가기
          </button>
        </v-card-actions>
      </div>
    </div>
    <!-- 가게 할인정보 게시시 -->
    <div class="row card-box" v-else>
      <img
        class="img-box col-5"
        src="@/assets/images/이마트24.png"
        alt="사진이었던것...jpg"
      />
      <div class="col-7 order-box">
        <v-list-item-content>
          <v-list-item-title class="text mb-3 msg-box">
            <span>{ E mart } </span>
            <span class="notice">매장이</span>
          </v-list-item-title>
          <v-list-item-title class="text mb-3 msg-box notice"
            >할인정보를 게시했습니다.</v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="btn-box mb-1">
          <p class="time-text">10분전</p>
          <button class="border-m radius-m notice-btn" @click="gostore()">
            정보 보러가기
          </button>
        </v-card-actions>
      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  name: "NoticeCard",
  methods: {
    gohistory() {
      this.$router.push("/mypage/user/history");
    },
    gostore() {
      this.$router.push("/store");
    },
  },
  data() {
    return {
      user: false,
    };
  },
};
</script>

<style scoped>
.card-box {
  display: flex;
  flex-direction: row;
  align-items: center;
  background-color: rgb(240, 240, 240);
  border-bottom: 2px solid rgba(0, 0, 0, 10%);
}
.img-box {
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
.msg-box {
  text-align: start;
}
.card-box {
  display: flex;
  flex-direction: row;
}
.order-box {
  display: flex;
  flex-direction: column;
}
.btn-box {
  max-height: 75px;
  padding: 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.time-text {
  opacity: 40%;
  text-align: start;
  padding: 0px;
  margin: 0px;
  color: black;
}
.notice {
  color: black;
}
.notice-btn {
  width: 110px;
}
</style>
