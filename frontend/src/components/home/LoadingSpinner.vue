<template>
  <div class="spinner-container">
    <div class="spinner" />
  </div>
</template>

<script>
export default {
  name: "LoadingSpinner",
  
};
</script>

<style scoped>
.spinner-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 13px;
}
.spinner {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: 5px solid #e0e0e0;
  border-bottom: 5px solid #fe9616;
  animation: spin 1s linear infinite;
  position: relative;
}
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>