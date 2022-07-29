<template>
  <v-card class="mx-auto card-box" max-width="344">
    <div class="row card-box">
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
            >주문이 도착했습니다.</v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="btn-box mb-1">
          <p class="time-text">10분전</p>
          <notice-modal></notice-modal>
        </v-card-actions>
      </div>
    </div>
  </v-card>
</template>

<script>
import NoticeModal from "@/components/NoticeModal.vue";

export default {
  name: "NoticeCard",
  components: { NoticeModal },
  methods: {
    goDetail() {
      this.$router.push("/notice/detail");
    },
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
</style>
