<template>
  <div
    :class="{
      active: `${notice.orderDto.state}` == 'WAIT',
    }"
    class="card-box"
  >
    <img
      v-if="notice.orderDto.state === 'ORDER' &&  notice.noticeState === 'ORDER'"
      class="siren"
      src="@/assets/images/siren.png"
      @click="reportOrder(notice)"
      alter="신고 버튼이었던것.."
    />
    <img
      class="img-box col-5"
      :src="`${notice.userDto.imgUrl}`"
      alt="유저 프로필"
    />
    <div class="col-7 order-box">
      <div class="mb-2 msg-box">
        <div class="date-box">
          <div class="mr-1">주문일 :</div>
          <div>
            {{ notice.orderDto.date.slice(0, 4) }}.{{
              notice.orderDto.date.slice(4, 6)
            }}.{{ notice.orderDto.date.slice(6, 8) }}
            {{ notice.orderDto.date.slice(8, 10) }}:{{
              notice.orderDto.date.slice(10, 12)
            }}
          </div>
        </div>
        <div v-html="`${notice.content}`"></div>
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
        <p
          v-else-if="`${notice.orderDto.state}` === 'PICKUP'"
          class="time-text pickup"
          style="color: orange"
        >
          수령 완료
        </p>
        <notice-modal :value="notice"></notice-modal>
      </v-card-actions>
    </div>
  </div>
</template>

<script>
import NoticeModal from "@/components/notice/NoticeModal.vue";
import http from '@/util/http-common';
import { mapActions } from "vuex";
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
    ...mapActions("accounts", ["getOwnerOrderList"]),
    goDetail() {
      this.$router.push("/notice/detail");
    },
    reportOrder(notice) {
      http
        .post(`/user/ban/${notice.noticeId}`).then((response) =>{
          if (response.status === 200){
            console.log("신고완료");
          }
      }).then(() => {
        http.defaults.headers["access-token"] = localStorage.getItem("access-token");
        http.get("/notice").then((response) => {
        this.getOwnerOrderList(response.data.reverse());
      });
      
    });
    }
  },
};
</script>

<style scoped>
.card-box {
  display: flex;
  position: relative;;
  margin: 0 auto 5px 0;
  /* width: 100%; */
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  background-color: rgb(240, 240, 240);
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

.siren{
  position: absolute;
  top: 8%;
  left: 3%;
  width: 20px;
  height: 20px;
  cursor: pointer;
}
</style>
