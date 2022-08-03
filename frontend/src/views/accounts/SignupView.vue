<template>
  <div class="signup-box" width="100%">
    <div>
      <v-list-item-title class="text-h5 mt-3 mb-5">
        회원 가입하기
      </v-list-item-title>
      <v-list-item-subtitle class="mb-2"
        >회원가입하시는 고객님의</v-list-item-subtitle
      >
      <v-list-item-subtitle class="mb-5"
        >유형을 선택해주세요</v-list-item-subtitle
      >
    </div>
    <v-card class="btn-box" black outlined min-width="320" min-height="170">
      <v-btn class="select-btn" color="success" @click="goSignUser()"
        >고객</v-btn
      >
      <v-btn class="select-btn" color="success" @click="goSignOwner()"
        >업주</v-btn
      >
    </v-card>
  </div>
</template>

<script>
export default {
  name: "SignupView",
  methods: {
    goSignUser() {
      this.$router.push("/signup/user");
    },
    goSignOwner() {
      this.$router.push("/signup/owner");
    },
  },
};
</script>

<style scoped>
.signup-box {
  display: flex;
  flex-direction: column;
  height: 100vh;
  justify-content: start;
  align-items: center;
}
.btn-box {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  height: 40vh;
}
.select-btn {
  width: 100px;
  min-height: 100px;
  border-radius: 50%;
  opacity: 90%;
}
</style>
