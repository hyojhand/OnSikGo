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
        <v-card-title class="text-h5 lighten-2"> 주문 거절 사유 </v-card-title>

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
          사유전송하기
        </button>
      </div>
    </v-dialog>
  </div>
</template>

<script>
import ReasonModal from "@/components/notice/ReasonModal.vue";
import http from "@/util/http-common";
import { mapActions } from "vuex";

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
            console.log(response);
            this.$router.push({
              name: "notice",
            });
          } else {
            console.log(response);
            alert("거절 실패");
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
  height: 280px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.btn-reject {
  color: rgb(255, 82, 82);
  width: 64px;
  height: 35px;
}
.btn-send {
  margin: 2%;
  width: 250px;
  height: 30px;
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
