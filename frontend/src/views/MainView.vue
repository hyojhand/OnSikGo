<template>
  <div class="home">
    <slogan-page></slogan-page>
    <h3>추천 매장</h3>
    <reco-page></reco-page>
  </div>
</template>

<script>
import RecoPage from "@/components/RecoPage.vue";
import SloganPage from "@/components/SloganPage.vue";

export default {
  name: "MainView",
  components: {
    RecoPage,
    SloganPage,
  },
};
</script>

<style scoped></style>
