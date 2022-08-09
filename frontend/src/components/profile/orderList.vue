<template>
  <div>
    <div class="ordercard ml-5">
      <div class="container mt-5">
        <div class="row">
          <div class="col-3 ml-3 mt-2">
            <img
              fluid
              :src="`${order.saleItemDto.itemDto.itemImgUrl}`"
              width="70"
              height="70"
              style="border-radius: 50%"
            />
          </div>
          <div class="col-5 ml-3">
            <div style="text-align: start">
              <span style="color: black; font-size: 1.1rem">
                {{ order.saleItemDto.itemDto.itemName }} </span
              ><br />
              <span style="color: gray; font-size: 0.8rem"
                >매장이름:
                {{ order.saleItemDto.saleDto.storeDto.storeName }}</span
              ><br />
              <span style="color: gray; font-size: 0.8rem"
                >주문시각: {{ order.date.slice(0, 4) }}.{{
                  order.date.slice(5, 6)
                }}.{{ order.date.slice(7, 8) }}</span
              ><br />
              <span style="color: gray; font-size: 0.8rem"
                >수량: {{ order.count }} 개</span
              >
            </div>
          </div>
          <div class="col-2 ml-3 mt-2">
            <button
              v-show="`${order.state}` === 'WAIT' && `${elapsedTime}` < 10"
              id="btn-order"
              type="button"
              @click="orderCancel(order)"
            >
              주문취소</button
            ><br /><br />
            <button
              id="btn-order"
              @click="goStore(order.saleItemDto.saleDto.storeDto.storeId)"
              type="button"
            >
              가게보기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapActions } from "vuex";

export default {
  name: "orderList",
  data() {
    return {
      elapsedTime: "",
    };
  },
  props: {
    order: null,
  },
  created() {
    this.nowDate();
  },
  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    orderCancel(order) {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.patch(`/order/cancel/${order.orderId}`).then((response) => {
        if (response.status === 200) {
          console.log(response);
        } else {
          console.log(response);
          alert("취소 실패");
        }
      });
    },
    goStore(storeId) {
      this.getStoreId(storeId);
      this.$router.push("/store");
    },

    nowDate() {
      var today = new Date();

      var year = today.getFullYear();
      var month = ("0" + (today.getMonth() + 1)).slice(-2);
      var day = ("0" + today.getDate()).slice(-2);
      var hours = ("0" + today.getHours()).slice(-2);
      var minutes = ("0" + today.getMinutes()).slice(-2);

      var orderyear = this.order.date.slice(0, 4);
      var ordermonth = this.order.date.slice(4, 6);
      var orderday = this.order.date.slice(6, 8);
      var orderhours = this.order.date.slice(8, 10);
      var orderminutes = this.order.date.slice(10, 12);

      const nowdate = new Date(year, month, day, hours, minutes, 0);
      const orderdate = new Date(
        orderyear,
        ordermonth,
        orderday,
        orderhours,
        orderminutes,
        0
      );
      const elapsedTime = (nowdate.getTime() - orderdate.getTime()) / 60000;
      this.elapsedTime = elapsedTime;
    },
  },
};
</script>

<style scoped>
.ordercard {
  width: 370px;
  display: flex;
  flex-direction: row;
  align-items: center;
  border-bottom: 2px solid rgba(0, 0, 0, 10%);
  margin: 0;
}
#btn-order {
  height: 25px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  box-sizing: border-box;
  background-color: #de9712;
  color: #ffffff;
  width: 70px;
  font-size: 0.75rem;
}
</style>
