<template>
  <div>
    <user-flow></user-flow>
    <owner-flow></owner-flow>
  </div>
</template>

<script>
import UserFlow from "@/components/home/UserFlow.vue"
import OwnerFlow from "@/components/home/OwnerFlow.vue"
export default {
  name: "OnsikgoHowTo",
  components: {
    UserFlow,
    OwnerFlow,
  }
};
</script>

<style scoped></style>
