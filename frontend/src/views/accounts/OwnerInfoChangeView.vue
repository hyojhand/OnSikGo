<template>
  <div class="container">
    <div class="mail-box">
      <div class="text-h5">이메일</div>
      <input type="text" placeholder="ganadara@mabasa.com" />
    </div>
    <div class="password-box">
      <div class="text-h5">비밀번호</div>
      <v-btn @click="gochange()">수정 </v-btn>
    </div>
    <v-divider></v-divider>
    <v-btn class="btn" justify="end" @click="gomypage()">수정 완료</v-btn>
  </div>
</template>

<script>
export default {
  name: "OwnerInfoChangeView",
  methods: {
    gochange() {
      this.$router.push("infochange/pwchange");
    },
    gomypage() {
      this.$router.push("userinfochange");
    },
  },
};
</script>

<style scoped>
.container {
  min-width: 310px;
  padding: 10%;
}
.mail-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.password-box {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  box-sizing: inherit;
}
</style>
