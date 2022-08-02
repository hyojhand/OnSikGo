<template>
  <v-app id="app">
    <!-- nav -->
    <v-app-bar v-if="pageType === true" absolute temporary flat class="nav-box">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"> </v-app-bar-nav-icon>
      <v-spacer></v-spacer>
      <img
        v-if="title === '온식고'"
        src="@/assets/logo.png"
        alt="logo였던것.."
        style="height: 100%; width: 20%"
      />
      <div v-else>
        {{ title }}
      </div>
      <v-spacer></v-spacer>
      <div class="icon-box">
        <router-link :to="{ name: 'notice' }">
          <i class="fa-solid fa-bell" width="24px" height="16"></i>
        </router-link>
        <v-spacer></v-spacer>
        <!-- 마이페이지 일 경우에 톱니바퀴도 보이기 -->
        <button v-if="title === '마이페이지'" @click.stop="setting = !setting">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            fill="currentColor"
            class="bi bi-gear ml-3"
            viewBox="0 0 16 16"
          >
            <path
              d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"
            />
            <path
              d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"
            />
          </svg>
        </button>
      </div>
    </v-app-bar>

    <!-- 토글바-->
    <v-navigation-drawer app v-model="drawer" absolute clipped temporary>
      <router-link :to="{ name: 'main' }">
        <img
          src="@/assets/logo.png"
          alt="logo였던것.."
          style="height: 6%; width: 20%; margin: 3% 0%"
        />
      </router-link>
      <!-- 로그인 안했을 경우 -->
      <v-list v-if="logincheck === false" nav>
        <v-list-item
          v-for="item in notlogins"
          :key="item.title"
          :to="item.router"
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="25"
          height="25"
          fill="currentColor"
          class="bi bi-box-arrow-in-right"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"
          />
          <path
            fill-rule="evenodd"
            d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"
          />
        </svg>
      </v-list>

      <!-- 로그인 했을 경우 -->
      <div v-else>
        <!-- 토글바 업주 로그인의 경우 -->
        <v-list v-if="userState === 1" nav>
          <v-list-item
            v-for="item in owners"
            :key="item.title"
            :to="item.router"
          >
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="25"
            height="25"
            fill="currentColor"
            class="bi bi-box-arrow-in-right"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"
            />
            <path
              fill-rule="evenodd"
              d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"
            />
          </svg>
        </v-list>
        <!-- 토글바 일반 유저 로그인 경우 -->
        <v-list v-else nav>
          <v-list-item
            v-for="item in users"
            :key="item.title"
            :to="item.router"
          >
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="25"
            height="25"
            fill="currentColor"
            class="bi bi-box-arrow-in-right"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"
            />
            <path
              fill-rule="evenodd"
              d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"
            />
          </svg>
        </v-list>
      </div>
    </v-navigation-drawer>

    <!-- 설정 토글바 -->
    <v-navigation-drawer app v-model="setting" absolute clipped temporary>
      <router-link :to="{ name: 'main' }">
        <img
          src="@/assets/logo.png"
          alt="logo였던것.."
          style="height: 6%; width: 20%; margin: 3% 0%"
        />
      </router-link>

      <!-- 설정 토글바 업주 버전 -->
      <v-list v-if="userState === 1" nav>
        <v-list-item
          v-for="item in settingOwners"
          :key="item.title"
          :to="item.router"
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <MemberQuitModal></MemberQuitModal>
        <StoreInfoDiscardModal :no="this.storeId"></StoreInfoDiscardModal>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="25"
          height="25"
          fill="currentColor"
          class="bi bi-box-arrow-in-right"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"
          />
          <path
            fill-rule="evenodd"
            d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"
          />
        </svg>
      </v-list>

      <!-- 설정 토글바 일반 유저 버전 -->
      <v-list v-else nav>
        <v-list-item
          v-for="item in settingUsers"
          :key="item.title"
          :to="item.router"
        >
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <MemberQuitModal></MemberQuitModal>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="25"
          height="25"
          fill="currentColor"
          class="bi bi-box-arrow-in-right"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"
          />
          <path
            fill-rule="evenodd"
            d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"
          />
        </svg>
      </v-list>
    </v-navigation-drawer>

    <v-main class="view">
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import http from "@/util/http-common";
import MemberQuitModal from "@/components/profile/MemberQuitModal.vue"
import StoreInfoDiscardModal from "@/components/profile/StoreInfoDiscardModal.vue"
export default {
  components :{
    MemberQuitModal,
    StoreInfoDiscardModal,
  },
  data() {
    return {
      drawer: false,
      setting: false,
      // 일반 유저 0, 업주 1
      userState: 0,
      title: document.title,
      pageType: true,
      logincheck: false,
      stores: [],
      store: {},
      storeId: "",
      saleItemList: [],

      notlogins: [
        { title: "홈", router: "/" },
        { title: "로그인", router: "/login" },
        { title: "회원가입", router: "/signup" },
        { title: "온식고", router: "/shop" },
      ],

      users: [
        { title: "홈", router: "/" },
        { title: "로그아웃", router: "/logout" },
        { title: "마이페이지", router: "/mypage/user" },
        { title: "온식고", router: "/shop" },
      ],
      owners: [
        { title: "홈", router: "/" },
        { title: "로그아웃", router: "/logout" },
        { title: "마이페이지", router: "/mypage/owner" },
        { title: "온식고", router: "/shop" },
        { title: "전체상품", router: "/allprod" },
        { title: "기부", router: "/donation" },
      ],
      settingUsers: [{ title: "회원정보수정", router: "/userinfochange" }],
      settingOwners: [{ title: "회원정보수정", router: "/ownerinfochange" }],
      pages: [
        "온식고",
        "기부 페이지",
        "회원정보변경",
        "알림조회",
        "상품조회",
        "주문하기",
        "가게조회",
        "전체상품조회",
        "상품변경",
        "상품등록",
        "마이페이지",
        "재고분석",
        "가게정보변경",
        "내주문조회",
        "내리뷰조회",
      ],
    };
  },
  
  created() {
    this.pageType = this.pages.includes(this.title);
  },
  updated() {
    this.title = document.title;
    // 판단
    this.pageType = this.pages.includes(this.title);

    if (localStorage.getItem("access-token") == null) {
      this.logincheck = false;
    } else {
      this.logincheck = true;
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");

      http.get("/user").then((response) => {
        if (response.data.role == "OWNER") {
          this.userState = 1;
        } else {
          this.userState = 0;
        }
      });
    }
  },
};
</script>

<style lang="scss">
.view {
  margin-top: 58px;
}

#app {
  background-color: rgb(240, 240, 240);
  align-items: center;
  text-align: center;
  margin: 0 auto;
  max-width: 420px;
  width: 100%;
  min-height: 100%;
}
.bi-box-arrow-in-right {
  position: fixed;
  bottom: 30px;
  right: 30px;
  color: red;
}
.icon-box {
  display: flex;
  flex-direction: row;
  align-items: center;
}
// mobile
@media screen and (max-width: 414px) {
  #app {
    width: 100%;
  }
}
</style>
