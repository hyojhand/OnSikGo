<template>
  <v-card class="mx-auto card-box" max-width="344">
    <div class="row card-box" v-if="`${userDto.role}` == 'USER'">
      <img
        class="img-box col-5"
        :src="`${orderDto.saleItemDto.itemDto.itemImgUrl}`"
        :alt="`${orderDto.saleItemDto.itemDto.itemName}`"
      />
      <div class="col-7 order-box">
        <v-list-item-content>
          <!-- <v-list-item-title class="text mb-3 msg-box">
            <span>{{ userDto.userName }} </span>
            <span class="notice">님의</span>
          </v-list-item-title> -->
          <v-list-item-title class="text mb-3 msg-box notice"
            >{{ content }}</v-list-item-title
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
        :src="`${orderDto.saleItemDto.saleDto.storeDto.storeImgUrl}`"
        :alt="`${orderDto.saleItemDto.saleDto.storeDto.storeName}`"
      />
      <div class="col-7 order-box">
        <v-list-item-content>
          <v-list-item-title class="text mb-3 msg-box">
            <!-- <span> {{ userDto.nickname }} </span>
            <span class="notice">매장이</span> -->
          </v-list-item-title>
          <v-list-item-title class="text mb-3 msg-box notice">
            {{ content }}</v-list-item-title
          >
        </v-list-item-content>
        <v-card-actions class="btn-box mb-1">
          <p class="time-text">{{ createdDate }}</p>
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
