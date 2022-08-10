<template>
  <div></div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "LogoutView",
  methods: { ...mapActions("select", ["resetValue"]) },

  created() {
    localStorage.removeItem("access-token");
    this.resetValue();
    this.$router.push("/");
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
