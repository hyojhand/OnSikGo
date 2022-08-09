<template>
  <div>
    <span style="font-size: 1.5rem">💌주문내역💌</span>
    <div v-if="this.orderList.length">
      <order-list
        v-for="(order, index) in orderList"
        :key="index"
        :order="order"
      ></order-list>
    </div>
    <div v-else class="non-msg">
      <div>아직 Onsikgo를 이용한</div>
      <div>흔적이 없어요 ㅠ</div>
    </div>
  </div>
</template>

<script>
import orderList from "@/components/profile/orderList.vue";
import http from "@/util/http-common.js";
import { mapActions } from "vuex";

export default {
  name: "OrderHistoryView",
  data() {
    return {
      orderList: [],
    };
  },
  created() {
    this.getOrderList();
  },
  components: {
    orderList,
  },
  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    getOrderList() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get("/order").then((response) => {
        console.log(response.data);
        this.orderList = response.data.reverse();
      });
    },
  },
};
</script>

<style scoped>
.hiscard {
  display: flex;
  flex-direction: row;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 10%);
  margin: 0;
}
.non-msg {
  width: 100%;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.non-msg > div {
  font-size: 30px;
  color: rgba(0, 0, 0, 0.2);
}
</style>
