<template>
  <div class="signup-box" width="100%">
    <div>
      <div class="mb-2" style="font-size:28px;"><strong style="color: #368f3d; font-size:40px;">Onsikgo</strong> 회원 가입</div>
      <v-list-item-subtitle style="font-size:13px;"
        >본인이 해당하는 회원가입 유형을 선택해주세요.</v-list-item-subtitle>
    </div>

    <v-card class="btn-box" style="margin-top: 25px;" black outlined width="350" height="300">
      <div class="type-box" style="margin-top:15px;">
        <img src="@/assets/images/buyer.png" alt="유저티콘" width="80" height="80">
        <div class="mt-5 fw-bold fs-5">일반고객</div>
        <div class="mt-2">맛있는 음식을 픽업으로 즐기고 싶으시다면</div> 
        <div class="mb-5">일반고객으로 회원가입해주세요!</div>
        <div class="d-flex justify-content-center">
          <v-btn class="select-btn fw-bold" color="success" @click="goSignUser()">일반고객 회원가입</v-btn></div>
      </div>
    </v-card>
    <v-card class="btn-box" black outlined width="350" height="300">
      <div class="type-box" style="margin-top:15px;">
        <img src="@/assets/images/seller.png" alt="유저티콘" width="80" height="80">
        <div class="mt-5 fw-bold fs-5">업주</div>
        <div class="mt-2">맛있는 음식을 제공하고 싶으시다면</div> 
        <div class="mb-5">업주로 회원가입해주세요!</div>
        <div class="d-flex justify-content-center">
          <v-btn class="select-btn fw-bold" color="success" @click="goSignOwner()">업주 회원가입</v-btn></div>
      </div>
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
  /* height: 250vh; */
  justify-content: flex-start;
  align-items: center;
}
.btn-box {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  height: 40vh;
  margin-top: 10px;
}
.select-btn {
  width: 150px;
  min-height: 50px;
  align-content: center;
  border-radius: 5px;
}
.type-box {
  justify-content: center;
  align-content: center;
  text-align: center;
  }
</style>
