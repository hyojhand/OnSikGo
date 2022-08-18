<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="344">
      <template v-slot:activator="{ on, attrs }">
        <button
          class="border-m radius-l text-m btn-reject"
          v-bind="attrs"
          v-on="on"
        >
          거절
        </button>
      </template>

      <div class="card-refuse">
        <v-card-title class="text-h5 lighten-2 fw-bold" style="color: #66a32e"
          >📌 주문 거절 사유
        </v-card-title>

        <div class="mx-auto my-auto option-box">
          <v-list-item-content class="btn-box">
            <button
              class="reason mb-2 radius-l text-m"
              :class="{ select: id1 }"
              @click="reason1()"
            >
              상품 품절
            </button>
            <button
              class="reason mb-2 radius-l text-m"
              :class="{ select: id2 }"
              @click="reason2()"
            >
              마감
            </button>
            <button
              class="reason mb-2 radius-l text-m"
              :class="{ select: id3 }"
              @click="reason3()"
            >
              고객 요청
            </button>
            <reason-modal
              @two-check-it="twoCheckIt"
              :value="value"
            ></reason-modal>
          </v-list-item-content>
        </div>
        <button @click="checkIt()" class="radius-l text-m btn-send">
          전송하기
        </button>
      </div>
    </v-dialog>
  </div>
</template>

<script>
import ReasonModal from "@/components/notice/ReasonModal.vue";
import http from "@/util/http-common";
import { mapActions } from "vuex";
import axios from "axios";

export default {
  name: "RefuseModal",
  components: { ReasonModal },
  props: {
    value: null,
  },
  methods: {
    ...mapActions("accounts", ["getOwnerOrderList"]),
    reason1() {
      this.reason = "상품 품절";
      this.id1 = true;
      this.id2 = false;
      this.id3 = false;
    },
    reason2() {
      this.reason = "마감";
      this.id1 = false;
      this.id2 = true;
      this.id3 = false;
    },
    reason3() {
      this.reason = "고객 요청";
      this.id1 = false;
      this.id2 = false;
      this.id3 = true;
    },
    async checkIt() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      await http
        .patch(`/order/refuse/${this.value.orderDto.orderId}`, {
          reason: this.reason,
        })
        .then((response) => {
          if (response.status === 200) {
            axios.defaults.headers["Authorization"] =
              "key=AAAAh0BP7KE:APA91bG7iSEIgwr2OAGSSxZveLwHi7eu7D_IHj_PGCB7BGOJp7BDHHdcqzb1ALmWCHAu6YKEMiIOABiED36j86onF__SUhcoDFk-V5fHtCqQUVD7HmhF_V7AltjIbHEToGvv7ULj0roP";
            axios.post("https://fcm.googleapis.com/fcm/send", {
              notification: {
                title: "온식고의 알림이 도착했습니다",
                body: "주문하신 상품이 거절되었습니다.",
                click_action: "https://i7e201.p.ssafy.io/",
                icon: "https://i7e201.p.ssafy.io/img/real_logo.136f0457.png",
              },
              to: response.data,
            });
            this.$router.push({
              name: "notice",
            });
          } else {
            this.$alert("거절 사유 처리에 실패하였습니다.");
          }
        });
      await http.get("/notice").then((response) => {
        this.getOwnerOrderList(response.data.reverse());
      });
      this.dialog = false;
      this.$emit("check-it");
    },
    twoCheckIt: function () {
      this.$emit("check-it");
      this.dialog = false;
    },
  },
  data() {
    return {
      dialog: false,
      reason: "상품 품절",
      id1: false,
      id2: false,
      id3: false,
    };
  },
};
</script>

<style scoped>
.card-refuse {
  background-color: white;
  height: 380px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.btn-reject {
  height: 40px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: tomato;
  color: #ffffff;
  width: 80px;
}
.btn-send {
  height: 40px;
  border: 2px solid tomato;
  display: inline-block;
  border-radius: 5px;
  margin-top: 5px;
  margin-bottom: 5px;
  padding: 0px;
  box-sizing: border-box;
  background-color: #fff;
  color: tomato;
  width: 150px;
}
.option-box {
  padding: 0 3%;
  background-color: rgb(240, 240, 240);
  width: 100%;
}
.btn-box {
  justify-content: center;
}
.etc-box {
  width: 100%;
  max-width: 344px;
}
.select {
  color: white;
  background-color: rgb(255, 82, 82);
}
.send {
  color: white;
  background-color: rgb(140, 184, 131);
}
.reason {
  width: 320px;
  height: 30px;
  border: 2px solid rgba(0, 0, 0, 0.2);
}
</style>
