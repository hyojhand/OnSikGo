<template>
  <v-card 
    v-if="`${elapsedTime}` < 1440"
    class="mx-auto card-box" 
    max-width="344">
    <div class="row card-box" v-if="`${noticeState}` == 'ORDER'">
      <img
        class="img-box col-5"
        :src="`${orderDto.saleItemDto.itemDto.itemImgUrl}`"
        :alt="`${orderDto.saleItemDto.itemDto.itemName}`"
      />
      <div class="col-7 order-box">
        <v-list-item-content>
          <v-list-item-title class="text mb-3 msg-box notice"  v-html="`${content}`"
            ></v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="btn-box mb-1">
          <p 
            class="time-text"
            v-if="elapsedTime < 60"
          >{{ elapsedTime }}분 전
          </p>
          <p
            class="time-text"
            v-else
          >{{ (elapsedTime / 60).toFixed(0) }}시간 전
          </p>
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
        :src="`${orderDto.saleItemDto.saleDto.storeDto.storeImgUrl}`"
        :alt="`${orderDto.saleItemDto.saleDto.storeDto.storeName}`"
      />
      <div class="col-7 order-box">
        <v-list-item-content>
          <v-list-item-title class="text mb-3 msg-box">
          </v-list-item-title>
          <v-list-item-title class="text mb-3 msg-box notice"  v-html="`${content}`">
            </v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="btn-box mb-1">
          <p 
            class="time-text"
            v-if="elapsedTime < 60"
          >{{ elapsedTime }}분 전
          </p>
          <p
            class="time-text"
            v-else
          >{{ (elapsedTime / 60).toFixed(0) }}시간 전
          </p>
          <button class="border-m radius-m notice-btn" @click="gostore()">
            정보 보러가기
          </button>
        </v-card-actions>
      </div>
    </div>
  </v-card>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "NoticeCard",
  props: {
    content: String,
    location: String,
    orderDto: [],
    receivedId: Number,
    state: Boolean,
    userDto: [],
    createdDate: String,
    noticeState: String,
  },
  created() {
    this.nowDate()
  },

  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    gohistory() {
      this.$router.push("/mypage/user/history");
    },
    gostore() {
      this.getStoreId(this.orderDto.saleItemDto.saleDto.storeDto.storeId);
      this.$router.push({
        name: "storeView",
      });
    },
    nowDate() {
      var today = new Date()

      var year = today.getFullYear();
      var month = ('0' + (today.getMonth() + 1)).slice(-2);
      var day = ('0' + today.getDate()).slice(-2);
      var hours = ('0' + today.getHours()).slice(-2); 
      var minutes = ('0' + today.getMinutes()).slice(-2); 

      var orderyear= this.orderDto.date.slice(0,4)
      var ordermonth = this.orderDto.date.slice(4,6)
      var orderday = this.orderDto.date.slice(6,8)
      var orderhours = this.orderDto.date.slice(8,10)
      var orderminutes = this.orderDto.date.slice(10,12)

      const nowdate = new Date(year, month, day, hours, minutes,0)
      const orderdate = new Date(orderyear, ordermonth, orderday, orderhours, orderminutes,0)

      const elapsedTime = (nowdate.getTime() - orderdate.getTime()) / 60000
      this.nowTime = year + month + day + hours + minutes
      this.elapsedTime = elapsedTime
    },
  },
  data() {
    return {
      user: false,
      nowTime: "",
      elapsedTime: "",
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
  margin: 0;
}
.notice-btn:hover {
  width: 110px;
  background-color: gray;
  opacity: 0.7;
  color: #fff;
  margin: 0;
  transition: 0.7s;
}
</style>
