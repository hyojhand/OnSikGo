<template>
  <div>
    <h3>주문내역</h3>
    <orderList></orderList>
  </div>
</template>

<script>
import orderList from "@/components/orderList.vue";
export default {
  name: "OrderHistoryView",
  components: {
    orderList,
  }
};
</script>

<style scoped></style>
