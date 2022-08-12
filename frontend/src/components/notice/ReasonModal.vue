<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="344">
      <template v-slot:activator="{ on, attrs }">
        <button
          v-bind="attrs"
          class="border-m radius-l text-m reason"
          v-on="on"
        >
          기타
        </button>
      </template>

      <v-card class="box-reason">
        <v-card-title class="text-h5 lighten-2 fw-bold" style="color: #66a32e">
          📌 거절 기타 사유
        </v-card-title>

        <div class="mx-auto my-auto input-reason">
          <v-text-field
            :rules="rules"
            placeholder="사유를 입력하세요."
            class="mx-5"
            v-model="reason"
          ></v-text-field>
        </div>

        <button @click="twoCheckIt()" class="border-m radius-l text-m btn-send">
          전송하기
        </button>

        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapActions } from "vuex";

export default {
  name: "ReasonModal",
  methods: {
    ...mapActions("accounts", ["getOwnerOrderList"]),
    async twoCheckIt() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      await http
        .patch(`/order/refuse/${this.value.orderDto.orderId}`, {
          reason: this.reason,
        })
        .then((response) => {
          if (response.status === 200) {
            // console.log(response);
            this.$router.push({
              name: "notice",
            });
          } else {
            // console.log(response);
            this.$alert("거절 처리에 실패하였습니다.");
          }
        });
      await http.get("/notice").then((response) => {
        this.getOwnerOrderList(response.data.reverse());
      });

      this.dialog = false;
      this.$emit("two-check-it");
    },
  },
  props: {
    value: null,
  },
  data() {
    return {
      reason: "",
      dialog: false,
    };
  },
};
</script>

<style scoped>
.card-header {
  border-bottom: 1px solid rgba(31, 31, 31, 10%);
  width: 100%;
}
.reason {
  width: 320px;
  height: 30px;
  color: white;
  background-color: rgb(31, 31, 31);
}
.box-reason {
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: black;
}
.input-reason {
  width: 100%;
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
</style>
