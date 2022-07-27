<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn rounded color="error" dark v-bind="attrs" v-on="on">
          거절
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="text-h5 lighten-2"> 주문 거절 사유 </v-card-title>

        <div class="mx-auto my-auto option-box">
          <v-list-item-content class="btn-box">
            <v-btn
              rounded
              class="reason mb-2"
              :class="{ error: id1 }"
              @click="reason1()"
            >
              상품 품절</v-btn
            >
            <v-btn
              rounded
              class="reason mb-2"
              :class="{ error: id2 }"
              @click="reason2()"
            >
              마감</v-btn
            >
            <v-btn
              rounded
              class="reason mb-2"
              :class="{ error: id3 }"
              @click="reason3()"
            >
              고객 요청</v-btn
            >
            <reason-modal @two-check-it="twoCheckIt"></reason-modal>
          </v-list-item-content>
        </div>

        <v-btn rounded color="success" @click="checkIt()" class="mb-3">
          사유전송하기
        </v-btn>

        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import ReasonModal from "@/components/ReasonModal.vue";
export default {
  name: "RefuseModal",
  components: { ReasonModal },
  methods: {
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
    checkIt: function () {
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
      reason: "",
      id1: false,
      id2: false,
      id3: false,
    };
  },
};
</script>

<style scoped>
.option-box {
  padding: 0 3%;
}
.btn-box {
  justify-content: center;
}
.etc-box {
  width: 100%;
  max-width: 344px;
}
.error {
  color: error;
}
</style>
