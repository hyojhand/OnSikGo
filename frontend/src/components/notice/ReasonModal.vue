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
        <v-card-title class="lighten-2 card-header">
          거절 기타 사유
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
import http from "@/util/http-common"
import { mapActions } from "vuex"

export default {
  name: "ReasonModal",
  methods: {
    ...mapActions("accounts", ["getOwnerOrderList"]),
    twoCheckIt: function () {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http
        .patch(`/order/refuse/${this.value.orderDto.orderId}`,{
          reason: this.reason
        })
        .then((response) =>{
          if (response.status === 200) {
            console.log(response)
            this.$router.push({
              name: "notice"
            })
          } else {
            console.log(response)
            alert("거절 실패")
          }
        })
      http
        .get("/notice").then((response) => {
          this.getOwnerOrderList(response.data.reverse())
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
  color: rgb(140, 184, 131);
}
.reason {
  width: 100%;
  color: white;
  background-color: rgb(31, 31, 31);
}
.box-reason {
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.input-reason {
  width: 100%;
}
.btn-send {
  margin: 2%;
  width: 150px;
  color: white;
  background-color: rgb(140, 184, 131);
}
</style>
