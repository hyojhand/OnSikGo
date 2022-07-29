<template>
  <div class="home">
    <slogan-page></slogan-page>

    <main-store></main-store>
    <p class="reco-name">OnSikgo
      <span class="sub-name">의</span> 
      <span class="last-name"> 추천</span>
    </p>
    <reco-page></reco-page>
  </div>
</template>

<script>
import RecoPage from "@/components/RecoPage.vue";
import SloganPage from "@/components/SloganPage.vue";
import MainStore from '@/components/MainStore.vue'
export default {
  name: "MainView",
  components: {
    MainStore,
    RecoPage,
    SloganPage,
  },

};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Mochiy+Pop+One&display=swap');
.home .reco-name{
  font-family: 'Mochiy Pop One', sans-serif;
  text-align: left;
  font-size: 20px;
  vertical-align: bottom ;
  margin-left: 1rem;
}
.home .reco-name .sub-name{
  font-size: 16px;
}

.home .reco-name .last-name{
  color: #68B7E7;
  
}
</style>
