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
export default {
  name: "ReasonModal",
  methods: {
    twoCheckIt: function () {
      this.dialog = false;
      this.$emit("two-check-it");
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
