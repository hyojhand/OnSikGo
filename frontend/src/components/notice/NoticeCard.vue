<template>
  <div
    :class="{
      active: `${notice.orderDto.state}` == 'WAIT',
    }"
    class="card-box"
  >
    <img
      class="img-box col-5"
      :src="`${notice.userDto.imgUrl}`"
      alt="유저 프로필"
    />
    <div class="col-7 order-box">
      <div class="mb-2">
        <div class="msg-box">
          <div class="date-box">
            <div class="mr-1">주문날짜 :</div>
            <div>{{ notice.orderDto.date.slice(0,4) }}
              .{{ notice.orderDto.date.slice(4,6) }}
              .{{ notice.orderDto.date.slice(6,8) }}
              {{ notice.orderDto.date.slice(8,10) }}
              :{{ notice.orderDto.date.slice(10,12) }}  
            </div>
          </div>
          <div v-html="`${notice.content}`"></div>
          <!-- <span class="notice">님의</span> -->
        </div>
      </div>
      <v-card-actions class="btn-box">
        <p v-if="`${notice.orderDto.state}` === 'WAIT'" class="time-text wait">
          주문 대기
        </p>
        <p
          v-else-if="`${notice.orderDto.state}` === 'CANCEL'"
          class="time-text cancel"
        >
          주문 취소
        </p>
        <p
          v-else-if="`${notice.orderDto.state}` === 'ORDER'"
          class="time-text order"
        >
          주문 완료
        </p>
        <notice-modal :value="notice"></notice-modal>
      </v-card-actions>
    </div>
  </div>
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
  margin: 0 auto;
  width: 100%;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
  border-bottom: 2px solid rgba(0, 0, 0, 0.2);
}
.img-box {
  margin: 0;
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
  text-align: start;
  padding: 0px;
  margin: 0px;
}
.notice {
  color: black;
}
.active {
  background-color: #fff;
  border-radius: 20px;
}
.date-box {
  display: flex;
  flex-direction: row;
}
.wait {
  color: rgb(140, 184, 131);
}
.cancel {
  color: red;
}
.order {
  color: blue;
}
</style>
