<template>
  <div class="text-center">
    <v-dialog v-model="parents" width="344" persistent>
      <template v-slot:activator="{ on, attrs }">
        <button
          class="border-m radius-m notice-btn"
          v-bind="attrs"
          v-on="on"
          v-bind:disabled="
            value.orderDto.state == 'ORDER' || value.orderDto.state == 'CANCEL'
          "
        >
          주문 확인
        </button>
      </template>

      <v-card>
        <div class="d-flex justify-content-spacebetween">
        <v-card-title class="text-h5 lighten-2 fw-bold" style="color: #66a32e">주문 상세보기</v-card-title>
        <button @click="off()" style="margin-left:120px;"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <v-card
          class="mx-auto my-auto pb-3 order-info"
          max-width="350"
          outlined
        >
          <div class="account-info">
            <img
              class="img-box col-6 mt-3 ml-2"
              :src="`${value.userDto.imgUrl}`"
              :alt="`${value.userDto.nickname}`"
            />
            <div class="col-7 mt-2 order-box">
              <v-list-item-content class="notice-box">
                <v-list-item-title class="msg-box">
                  <span class="text-l fw-bold" v-html="`${value.content}`"></span>
                </v-list-item-title>
              </v-list-item-content>
            </div>
          </div>
          <div class="mx-auto card-box" max-width="300" outlined>
            <div class="" style="margin-top:50px; margin-bottom: 50px;" >
              <img
                class="col-5 food-pic"
                :src="`${value.orderDto.saleItemDto.itemDto.itemImgUrl}`"
                alt="사진이었던것.."
              />
              <div class="mt-2">
                <div class="fs-5 fw-bold notice-msg">
                  {{ value.orderDto.saleItemDto.itemDto.itemName }}
                </div>
                <div class="text-m notice-msg">
                  {{ value.orderDto.count }} 개
                </div>
              </div>
            </div>
            <div class="btn-box mt-2">
            </div>
          </div>
        <v-card-actions>
          <v-spacer></v-spacer>
              <v-card-actions>
                <button
                  class="border-m radius-l text-m btn-accept"
                  @click="orderOk()"
                >
                  수락
                </button>
              </v-card-actions>
              <v-card-actions>
                <refuse-modal @check-it="checkIt" :value="value"></refuse-modal>
              </v-card-actions>
            </v-card-actions>

        </v-card>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import RefuseModal from "@/components/notice/RefuseModal.vue";
import http from "@/util/http-common.js";
import { mapActions } from "vuex";
export default {
  name: "NoticeModal",
  components: { RefuseModal },
  props: {
    value: null,
  },
  methods: {
    ...mapActions("accounts", ["getOwnerOrderList"]),
    getUser() {},
    accept() {
      this.parents = false;
    },
    checkIt: function () {
      this.parents = false;
    },
    async orderOk() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      await http
        .patch(`/order/sign/${this.value.orderDto.orderId}`)
        .then((response) => {
          if (response.status === 200) {
            console.log(response);
          } else {
            console.log(response);
            alert("주문 실패");
          }
        });
      await http.get("/notice").then((response) => {
        this.getOwnerOrderList(response.data.reverse());
      });
      this.parents = false;
    },
    off() {
      this.parents = false;
    }
  },
  data() {
    return {
      parents: false,
    };
  },
};
</script>

<style scoped>
.card-box {
  margin: 0;
  padding: 0;
}
.account-info {
  margin-top: 5px;
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-evenly;
}
.order-info {
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: center;
  align-items: center;
}
.notice-box {
  display: flex;
  justify-content: space-evenly;
  text-align: start;
  padding: 0;
}
.order-box {
  display: flex;
  justify-content: center;
  flex-direction: column;
  padding: 0;
  margin: 0;
}
.img-box {
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  padding: 0;
  margin: 0;
}
.btn-box {
  display: flex;
  justify-content: space-evenly;
}
.notice-btn {
  width: 110px;
  height: 28px;
}
.notice-btn:disabled {
  background-color: grey;
  opacity: 0.5;
  color: #000;
  width: 110px;
}
.food-pic {
  padding: 0;
  margin: 0;
  width: 400px;
  height: 200px;
}
.noti-title {
  text-align: start;
}
.notice-msg {
  color: black;
}
.btn-accept {
  height: 40px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #368f3d;
  color: #ffffff;
  width: 80px;
}
</style>
