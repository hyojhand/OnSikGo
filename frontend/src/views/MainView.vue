<template>
  <div class="home">
    <RecoPage></RecoPage>
  </div>
</template>

<script>
import RecoPage from "@/components/RecoPage.vue";

export default {
  name: "MainView",
  components: {
    RecoPage,
  },
};
</script>

<style scoped>
img {
  height: 20%;
  width: 20%;
  margin: 3% 0%;
}
</style>
