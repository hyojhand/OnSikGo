<template>
  <v-app id="app" class="margin">
    <v-app-bar absolute temporary dense color="#ffffff">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer">

      </v-app-bar-nav-icon>
      
      <v-spacer></v-spacer>
      <v-btn icon color="white">
        <v-icon>mdi-battery-charging</v-icon>
      </v-btn>
      
        <img v-if="title === '온식고'"
          src="@/assets/logo.jpg" 
          alt="logo였던것.." 
          style="height: 60%;
                width: 15%;
                margin: 3% 0%;"/>
      
      <p class="title" v-else>{{ title }}</p>
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
      
      <v-list v-if="userState === 1" nav>
        <v-list-item v-for="item in owners" :key="item.title" :to="item.router">
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
          <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
        </svg>
      </v-list>
      <v-list v-else nav>
        <v-list-item v-for="item in consumers" :key="item.title" :to="item.router">
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
          <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
        </svg>
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
      // 일반 유저 0, 업주 1
      userState : 0,
      title : document.title,
      consumers: [
        { title: '홈', router: '/'},
        { title: '마이페이지', router: '/mypage/user'},
        { title: '온식고', router: '/shop'},
      ],
      owners: [
        { title: '홈', router: '/'},
        { title: '마이페이지', router: '/mypage/owner'},
        { title: '온식고', router: '/shop'},
        { title: '전체상품', router: '/allprod'},
        { title: '기부', router: '/donation'},
      ],
    }
  },
  updated() {
      this.title = document.title
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
  margin: 15% 5% 5% 5%;
}
.margin {
  margin: 5% 5% 5% 5%;

}
.title {
  margin-top: 15px;
  margin-right: 60px;
}
.bi-box-arrow-in-right {
  position: fixed;
  bottom:5px;
  margin-left: 90px;
  color: red;
}

</style>
