<template>
  <div class="nav-box">
    <i class="fa-solid fa-bars"></i>
    <router-link :to="{ name: 'main' }"
      ><img src="../assets/logo.jpg" alt="logo였던것.."
    /></router-link>
    <router-link :to="{ name: 'notice' }"
      ><i class="fa-solid fa-bell"></i
    ></router-link>
  </div>
</template>

<script>
export default {
  name: "NavBar",
};
</script>

<style scoped>
.nav-box {
  display: flex;
  align-items: center;
}
img {
  height: 20%;
  width: 20%;
  margin: 3% 0%;
}
</style>
