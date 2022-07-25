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
            <v-btn rounded color="error" class="reason mb-2" @click="accept">
              상품 품절</v-btn
            >
            <v-btn rounded color="error" class="reason mb-2" @click="accept">
              마감</v-btn
            >
            <v-btn rounded color="error" class="reason mb-2" @click="accept">
              고객 요청</v-btn
            >
            <v-btn rounded color="error" class="reason mb-2" @click="accept">
              기타
            </v-btn>
          </v-list-item-content>
        </div>

        <v-btn rounded color="success" @click="accept" class="mb-3">
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
export default {
  name: "RefuseModal",
  methods: {
    accept() {
      this.dialog = false;
    },
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style scoped>
.btn-box {
  justify-content: center;
}
.reason {
  max-width: 344px;
}
</style>
