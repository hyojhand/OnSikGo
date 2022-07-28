<template>
  <div>
    <notice-user-card ref class="notice-card"></notice-user-card>
    <notice-user-card ref class="notice-card"></notice-user-card>
  </div>
</template>

<script>
import NoticeUserCard from "../components/NoticeUserCard.vue";

export default {
  name: "NoticeUserView",
  components: {
    NoticeUserCard,
  },
};
</script>

<style scoped>
.notice-card {
  margin: 3% auto 6% auto;
}
</style>
