<template>
  <div>
    <div class="ordercard ml-3">
      <br />
      <div class="container">
        <div class="row">
          <div class="col-3 mt-3">
            <img
              fluid
              :src="`${order.saleItemDto.itemDto.itemImgUrl}`"
              width="70"
              height="70"
              style="border-radius: 50%"
            />
          </div>
          <div class="col-6">
            <div style="text-align: start; align-items: start">
              <span style="color: black; font-size: 1.1rem">
                {{ order.saleItemDto.itemDto.itemName }}
              </span>
              <br />
              <span style="color: gray; font-size: 0.8rem">
                {{ order.saleItemDto.saleDto.storeDto.storeName }}</span
              ><br />
              <span style="color: gray; font-size: 0.8rem"
                >주문 날짜: {{ order.date.slice(0, 4) }}.{{
                  order.date.slice(4, 6)
                }}.{{ order.date.slice(6, 8) }}</span
              ><br />
              <span style="color: gray; font-size: 0.8rem"
                >수량: {{ order.count }} 개</span
              >
              <br />
              <span
                v-if="`${order.state}` === 'WAIT'"
                style="font-size: 0.75rem; color: gray"
                >[상태: 주문대기]</span
              >
              <span
                v-else-if="`${order.state}` === 'ORDER'"
                style="font-size: 0.75rem; color: blue"
                >[상태: 주문승인]</span
              >
              <span
                v-else-if="`${order.state}` === 'CANCEL'"
                style="font-size: 0.75rem; color: red"
                >[상태: 주문거절]</span
              >
              <span
                v-else-if="`${order.state}` === 'PICKUP'"
                style="font-size: 0.75rem; color: orange"
                >[상태: 수령완료]</span
              >
            </div>
          </div>
          <div class="col-3 mt-2">
            <button
              v-show="`${order.state}` === 'WAIT' && `${elapsedTime}` < 10"
              id="btn-order"
              type="button"
              @click="orderCancel(order)"
            >
              주문취소
            </button>

            <br /><br />
            <button
              id="btn-order"
              @click="goStore(order.saleItemDto.saleDto.storeDto.storeId)"
              type="button"
            >
              가게보기</button
            ><br />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapActions } from "vuex";
import axios from "axios";
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
          axios.defaults.headers["Authorization"] =
            "key=AAAAh0BP7KE:APA91bG7iSEIgwr2OAGSSxZveLwHi7eu7D_IHj_PGCB7BGOJp7BDHHdcqzb1ALmWCHAu6YKEMiIOABiED36j86onF__SUhcoDFk-V5fHtCqQUVD7HmhF_V7AltjIbHEToGvv7ULj0roP";
          axios.post("https://fcm.googleapis.com/fcm/send", {
            notification: {
              title: "온식고의 알림이 도착했습니다",
              body: "온식고를 확인해주세요!",
              click_action: "https://i7e201.p.ssafy.io/",
            },
            to: response.data,
          });
          this.$alert("주문이 취소되었습니다. 감사합니다.");
          this.$router.go();
        } else {
          this.$alert(
            "주문 취소가 실패되었습니다. 다시한번 확인해주세요. * 정확한 문의는 매장으로 부탁드립니다. * "
          );
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
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 95%;
  background-color: white;
  align-items: center;
  height: 130px;
  border-radius: 10px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
  margin: 0;
  background-color: rgba(0, 0, 0, 0.05);
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
