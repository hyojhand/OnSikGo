<template>
  <div>
    <orderList></orderList>
  </div>
</template>

<script>
import orderList from "@/components/profile/orderList.vue";
export default {
  name: "OrderHistoryView",
  components: {
    orderList,
  }
};
</script>

<style scoped></style>
