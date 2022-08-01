<template>
  <div class="signup-box" width="100%">
    <div>
      <div class="font-l mb-5">점주 회원 가입하기</div>
      <div class="ment-box mb-5">
        <v-list-item-subtitle>어서오세요!</v-list-item-subtitle>
        <v-list-item-subtitle>아래의 칸을 모두 채운다면</v-list-item-subtitle>
        <v-list-item-subtitle
          >저희와 함께 지구를 지킬 수 있어요.</v-list-item-subtitle
        >
      </div>
    </div>
    <signup-owner></signup-owner>
  </div>
</template>

<script>
import SignupOwner from "@/components/accounts/SignupOwner.vue";

export default {
  name: "OwnerView",
  components: { SignupOwner },
  methods: {
    tempgo() {
      this.$router.push("/signup/complete");
    },
  },
};
</script>

<style scoped>
.ment-box {
  text-align: start;
  width: 100%;
  display: flex;
  flex-direction: column;
}
.signup-box {
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: start;
  align-items: center;
}
</style>
