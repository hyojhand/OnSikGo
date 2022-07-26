<template>
  <v-app id="app" class="margin">

    <v-app-bar absolute temporary dense color="#ffffff">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer">

      </v-app-bar-nav-icon>
      
      <v-spacer></v-spacer>
      <v-btn icon color="white">
        <v-icon>mdi-battery-charging</v-icon>
      </v-btn>
      <router-link 
        :to="{ name: 'main' }">
        <img 
          src="@/assets/logo.jpg" 
          alt="logo였던것.." 
          style="height: 20%;
                width: 20%;
                margin: 3% 0%;"/>
      </router-link>
      <router-link 
        :to="{ name: 'notice' }" 
        class="col-sm"
      >
      <i class="fa-solid fa-bell"></i>
      </router-link>
    </v-app-bar>
    <!-- 툴바 켰을때-->
    <v-navigation-drawer app v-model="drawer" absolute clipped temporary>
      <router-link 
        :to="{ name: 'main' }">
        <img 
          src="@/assets/logo.jpg" 
          alt="logo였던것.." 
          style="height: 6%;
                width: 20%;
                margin: 3% 0%;"/>
      </router-link>
      <v-list nav>
        <v-list-item v-for="item in items" :key="item.title" :to="item.router">
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-content class="content">
      <router-view />
    </v-content>

  </v-app>
</template>

<script>


export default {

  data () {
    return {
      drawer: false,
      items: [
        { title: '홈', router: '/'},
        { title: '마이페이지', router: '/mypage/owner'},
        { title: '온식고', router: '/shop'},
        { title: '전체상품', router: '/allprod'},
        { title: '기부', router: '/donation'},
      ],
    }
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
.content {
  margin-top: 15%;
}
.margin {
  margin: 5% 5% 5% 5%;

}


</style>
