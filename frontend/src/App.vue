<template>
  <v-app id="app">
    <div class="margin">
    <nav-bar id="nav" />
    <router-view />
    </div>
  </v-app>
</template>

<script>
import NavBar from "@/components/NavBar.vue";

export default {
  components: {
    NavBar,
  },
};
</script>

<style lang="scss">
#app {
  align-items: center;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0 auto;
  width: 360px;
  min-height: 100%;
  box-shadow: 0px 0px 20px 2px rgb(200, 200, 200);
}
#nav {
  margin-bottom: 5%;
}
.margin {
  margin: 0 5% 5% 5%;

}
</style>
