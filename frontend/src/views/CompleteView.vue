<template>
  <div class="container" width="100%">
    <img src="@/assets/logo.png" alt="" />
    <div class="text-h5 mt-5">가입이 완료되었어요</div>
    <div>
      <v-list-item-title class="mt-3 mb-3">
        이제 온식고와 함께
      </v-list-item-title>
      <v-list-item-title class="mt-3 mb-5">
        지구를 지켜나가봐요
      </v-list-item-title>
    </div>
    <v-btn color="success" width="80%" @click="gomain()">이용하러 가기</v-btn>
  </div>
</template>

<script>
export default {
  name: "CompleteView",
  methods: {
    gomain() {
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
img {
  width: 200px;
  height: 170px;
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 80vh;
}
</style>
