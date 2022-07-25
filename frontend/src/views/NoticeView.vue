<template>
  <div>
    <notice-card class="notice-card"></notice-card>
    <notice-card class="notice-card"></notice-card>
    <notice-card class="notice-card"></notice-card>
  </div>
</template>

<script>
import NoticeCard from "../components/NoticeCard.vue";

export default {
  name: "NoticeView",
  components: {
    NoticeCard,
  },
};
</script>

<style scoped>
.notice-card {
  margin: 3% auto 6% auto;
}
</style>
