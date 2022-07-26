<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          rounded
          color="error"
          dark
          v-bind="attrs"
          v-on="on"
          class="reason"
        >
          기타
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="text-h5 lighten-2"> 거절 기타 사유 </v-card-title>

        <div class="mx-auto my-auto">
          <v-text-field
            :rules="rules"
            placeholder="사유를 입력하세요."
            class="mx-5"
          ></v-text-field>
        </div>

        <v-btn rounded color="success" @click="checkIt()" class="mb-3">
          전송하기
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
  name: "ReasonModal",
  methods: {
    checkIt: function () {
      this.dialog = false;
      this.$emit("check-it");
    },
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style scoped></style>
