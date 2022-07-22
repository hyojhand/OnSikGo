import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// 뷰티파이
import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";

// 부트스트랩
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// 폰트어썸
import "@fortawesome/fontawesome-free/js/all.js";

Vue.use(Vuetify);
Vue.use(BootstrapVue);
Vue.config.productionTip = false;

new Vue({
  vuetify: new Vuetify(),
  router,
  store,
  render: (h) => h(App),
<<<<<<< HEAD
  data: () => ({
    drawer: false,
    group: null,
  }),

  watch: {
    group() {
      this.drawer = false;
    },
  },
}).$mount("#app");
=======
}).$mount("#app");

new Vue({
  el: '#navBar',
  vuetify: new Vuetify(),
  data () {
    return {
      drawer: null,
      items: [
        { title: 'Home', icon: 'mdi-view-dashboard' },
        { title: 'About', icon: 'mdi-forum' },
      ],
    }
  },
})
>>>>>>> 8c34ba02ca2b04c33ef6ce20ae50d70e5a59be8b
