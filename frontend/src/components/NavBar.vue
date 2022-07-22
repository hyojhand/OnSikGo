<template>
  <div class="navbar">
    <router-link :to="{ name: 'home' }">토글바</router-link>
    <router-link :to="{ name: 'home' }"
      ><img src="../assets/logo.jpg" alt="logo였던것.."
    /></router-link>
    <router-link :to="{ name: 'home' }">알림설정</router-link>
  </div>
</template>

<script>
export default {
  name: "NavBar",
};
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
}
img {
  width: 150px;
  height: 110px;
}
</style>
