<template>
  <v-card class="mx-auto card-box" max-width="344">
    <div
      :class="{
        active: `${notice.orderDto.state}` == 'WAIT',
      }"
      class="row card-box"
    >
      <img class="img-box col-5" :src="`${notice.userDto.imgUrl}`" alt="유저 프로필" />
      <div class="col-7 order-box">
        <v-list-item-content>
          <v-list-item-title class="text mb-3 msg-box">
            <span>{{ notice.orderDto.date }}</span>
            <span v-html="`${notice.content}`"></span>
            <!-- <span class="notice">님의</span> -->
          </v-list-item-title>
        </v-list-item-content>
        <v-card-actions class="btn-box mb-1">
          <p v-if="`${notice.orderDto.state}` === 'WAIT'" class="time-text">
            주문대기
          </p>
          <p v-else-if="`${notice.orderDto.state}` === 'CANCEL'" class="time-text">
            주문취소
          </p>
          <p v-else-if="`${notice.orderDto.state}` === 'ORDER'" class="time-text">
            주문완료
          </p>
          <notice-modal :value="notice"></notice-modal>
        </v-card-actions>
      </div>
    </div>
  </v-card>
</template>

<script>
import NoticeModal from "@/components/notice/NoticeModal.vue";

export default {
  name: "NoticeCard",
  components: { NoticeModal },
  data() {
    return {
      items: [],
    };
  },
  props: {
    notice: Object,
  },
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
.active {
  background-color: #fff;
  border-radius: 20px;
}
</style>
