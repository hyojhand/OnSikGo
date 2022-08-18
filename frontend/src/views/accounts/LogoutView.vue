<template>
  <div></div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "LogoutView",
  methods: { 
    ...mapActions("select", ["resetValue"]),
    ...mapActions("store", ["resetStore"]),
    ...mapActions("discardStore", ["resetDiscardStore"]),
    ...mapActions("analysis", ["resetAnalysis"]),
    ...mapActions("accounts", ["resetAccounts"]),
  },

  async created() {
    localStorage.removeItem("access-token");
    // localStorage.removeItem("vuex");
    await this.resetValue();
    await this.resetValue();
    await this.resetStore();
    await this.resetDiscardStore();
    await this.resetAnalysis();
    await this.resetAccounts();
    await this.$router.push("/").catch(() => {});
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
