<template>
  <div></div>
</template>

<script>
export default {
  name: "LogoutView",

  created() {
    localStorage.removeItem("access-token");
    this.$router.push("/");
    this.$router.go();
  },
};
</script>

<style scoped>
.onsikgotext {
  text-align: start;
}
#btn_signup {
  width: 100%;
}
#bold {
  font-weight: bold;
  font-size: 0.75rem;
}
</style>
