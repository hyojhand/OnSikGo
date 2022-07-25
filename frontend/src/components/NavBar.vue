<template>
  <div>
      <router-link :to="{ name: 'main' }"><img src="../assets/logo.jpg" alt="logo였던것.." /></router-link>
      <router-link :to="{ name: 'notice' }" class="col-sm"
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
.navbar {
  padding: 0;
}
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
