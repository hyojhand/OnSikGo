<template>
  <v-card class="mx-auto card-box" max-width="344" outlined shaped>
    <div class="row" v-if="user">
      <img
        class="img-box col-5"
        src="https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/4gqX/image/wIXZfUhOPGGGZxaZ0Nsmigd1paU.jpeg"
        alt="사진이었던것.."
      />
      <div class="col-7 order-box">
        <v-list-item-content class="list-box">
          <v-list-item-title class="text mb-3 msg-box">
            최지은 님의
          </v-list-item-title>
          <v-list-item-title class="text mb-3 msg-box"
            >주문이 준비되었습니다.</v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="row btn-box mb-1 mt-1">
          <p class="time-text col-4">10분전</p>
          <v-btn color="success" class="col-7" @click="gohistory()">
            주문 보러가기
          </v-btn>
        </v-card-actions>
      </div>
    </div>
    <!-- 가게 할인정보 게시시 -->
    <div class="row" v-else>
      <img
        class="img-box col-5"
        src="@/assets/images/이마트24.png"
        alt="사진이었던것...jpg"
      />
      <div class="col-7 order-box">
        <v-list-item-content class="list-box">
          <v-list-item-title class="text mb-3 msg-box">
            E mart 매장이
          </v-list-item-title>
          <v-list-item-title class="text mb-3 msg-box"
            >할인정보를 게시했습니다.</v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="row btn-box mb-1 mt-1">
          <p class="time-text col-4">10분전</p>
          <v-btn color="success" class="col-7" @click="gostore()">
            정보 보러가기
          </v-btn>
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
.img-box {
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
.list-box {
  padding-top: 10px;
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
  max-height: 50px;
  padding-left: 12px;
}
.time-text {
  opacity: 40%;
  text-align: start;
  padding: 0px;
  margin: 0px;
}
</style>
