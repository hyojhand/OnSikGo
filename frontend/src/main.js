import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

//css reset
import "@/assets/css/reset.css";

import "@/assets/css/style.css";

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

// vuetify 사용
new Vue({
  vuetify: new Vuetify(),
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");

window.Kakao.init("dbd3360c2a3a37fb0ad6c06395351c2f");