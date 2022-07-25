<template>
  <div>
    <div id="navbar">
  <v-app id="inspire">
    <v-sheet
      height="400"
      class="overflow-hidden"
      style="position: relative;"
    >
      <v-container class="fill-height">
        <v-row
          align="center"
          justify="center"
        >
          <v-btn
            color="pink"
            dark
            @click.stop="drawer = !drawer"
          >
            Toggle
          </v-btn>
        </v-row>
      </v-container>
  
      <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
      >
        <v-list-item>
          <v-list-item-avatar>
            <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
          </v-list-item-avatar>
  
          <v-list-item-content>
            <v-list-item-title>John Leider</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
  
        <v-divider></v-divider>
  
        <v-list dense>
          <v-list-item
            v-for="item in items"
            :key="item.title"
            link
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
  
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    </v-sheet>
  </v-app>
</div>
      <router-link :to="{ name: 'main' }"><img src="../assets/logo.jpg" alt="logo였던것.." /></router-link>
      <router-link :to="{ name: 'notice' }" class="col-sm"
        ><i class="fa-solid fa-bell"></i
      ></router-link>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  data () {
    return {
      drawer: null,
      items: [
        { title: 'Home', icon: 'mdi-view-dashboard' },
        { title: 'About', icon: 'mdi-forum' },
      ],
    }
  },
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
