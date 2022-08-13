import Vue from "vue";
import VueRouter from "vue-router";

// 메인뷰
import MainView from "../views/home/MainView.vue";
// 기부
import DonationView from "../views/donation/DonationView.vue";
// 회원관리
import LoginView from "../views/accounts/LoginView.vue";
import LogoutView from "../views/accounts/LogoutView.vue";
import SignupView from "../views/accounts/SignupView.vue";
import UserView from "../views/accounts/UserView.vue";
import OwnerView from "../views/accounts/OwnerView.vue";
import CompleteView from "../views/accounts/CompleteView.vue";
import OwnerInfoChangeView from "../views/accounts/OwnerInfoChangeView.vue";
import UserInfoChangeView from "../views/accounts/UserInfoChangeView.vue";
import PasswordChangeView from "../views/accounts/PasswordChangeView.vue";
import storeAddView from "../views/accounts/storeAddView.vue";
// 관리자 페이지
import AdminView from "../views/admin/AdminView.vue";
// 알림+
import NoticeView from "../views/notice/NoticeView.vue";
import NoticeUserView from "../views/notice/NoticeUserView.vue";
// 메인기능
import ShopView from "../views/shopping/ShopView.vue";
import OrderView from "../views/shopping/OrderView.vue";
// 가게보기
import StoreView from "../views/shopping/StoreView.vue";
// 전체상품보기
import AllProdView from "../views/management/AllProdView.vue";
import ProdChangeView from "../views/management/ProdChangeView.vue";
import ProdRegisterView from "../views/management/ProdRegisterView.vue";
// 마이페이지(점주)
import MypageOwnerView from "../views/profile/MypageOwnerView.vue";
import DataAnalysisView from "../views/profile/DataAnalysisView.vue";
import StoreInfoChangeView from "../views/profile/StoreInfoChangeView.vue";
import closeCheck from "../views/profile/closeCheck.vue";
// 마이페이지(사용자)
import MypageUserView from "../views/profile/MypageUserView.vue";
import OrderHistoryView from "../views/profile/OrderHistoryView.vue";
import MyreviewView from "../views/profile/MyreviewView.vue";
// 오류
import NotFoundView from "../views/error/NotFoundView.vue";

Vue.use(VueRouter);

const routes = [
  // 메인
  {
    path: "/",
    name: "main",
    component: MainView,
    meta: {
      title: "온식고",
    },
  },
  // 기부
  {
    path: "/donation",
    name: "donation",
    component: DonationView,
    meta: {
      title: "기부 페이지",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  //  회원관리
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      title: "로그인",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 0) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/addstore",
    name: "addstore",
    component: storeAddView,
    meta: {
      title: "매장추가",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1 || isLogin == 3) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      title: "로그아웃",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin !== 0) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupView,
    meta: {
      title: "회원가입",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 0) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/signup/user",
    name: "userview",
    component: UserView,
    meta: {
      title: "회원가입",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 0) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/signup/owner",
    name: "ownerview",
    component: OwnerView,
    meta: {
      title: "회원가입",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 0) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/signup/complete",
    name: "complete",
    component: CompleteView,
    meta: {
      title: "가입성공",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 0) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/ownerinfochange",
    name: "ownerInfoChange",
    component: OwnerInfoChangeView,
    meta: {
      title: "회원정보변경",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1 || isLogin == 2) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/userinfochange",
    name: "userInfoChange",
    component: UserInfoChangeView,
    meta: {
      title: "회원정보변경",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1 || isLogin == 2) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/infochange/pwchange",
    name: "passwordChange",
    component: PasswordChangeView,
    meta: {
      title: "비밀번호변경",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1 || isLogin == 2) {
        return next();
      }
      next("/404");
    },
  },
  // 관리자 페이지
  {
    path: "/admin",
    name: "admin",
    component: AdminView,
    meta: {
      title: "관리자 페이지",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 3) {
        return next();
      }
      next("/404");
    },
  },
  // 알림
  {
    path: "/notice",
    name: "notice",
    component: NoticeView,
    meta: {
      title: "알림조회",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1 || isLogin == 3) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/notice/user",
    name: "noticeUser",
    component: NoticeUserView,
    meta: {
      title: "알림조회",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 2) {
        return next();
      }
      next("/404");
    },
  },
  // 메인기능
  {
    path: "/shop",
    name: "shopView",
    component: ShopView,
    meta: {
      title: "상품조회",
    },
  },
  {
    path: "/shop/order",
    name: "orderView",
    component: OrderView,
    meta: {
      title: "주문하기",
    },
    // beforeEnter: function (to, from, next) {
    //   const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
    //     .userCheck;
    //   if (isLogin == 2) {
    //     return next();
    //   }
    //   alert("업주께서는 이용하실수 없는 서비스입니다.");
    //   this.$alert("업주께서는 이용하실수 없는 서비스입니다.");
    // },
    props: true,
  },
  // 가게보기
  {
    path: "/store",
    name: "storeView",
    component: StoreView,
    meta: {
      title: "가게조회",
    },
    props: true,
  },
  // 전체상품보기
  {
    path: "/allprod",
    name: "allProduct",
    component: AllProdView,
    meta: {
      title: "전체상품조회",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/allprod/change",
    name: "prodChange",
    component: ProdChangeView,
    meta: {
      title: "상품변경",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/allprod/register",
    name: "prodRegister",
    component: ProdRegisterView,
    meta: {
      title: "상품등록",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  // 마이페이지(점주)
  {
    path: "/mypage/owner",
    name: "mypageOwner",
    component: MypageOwnerView,
    meta: {
      title: "마이페이지",
    },
    props: true,
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/mypage/owner/analysis",
    name: "dataAnalysis",
    component: DataAnalysisView,
    meta: {
      title: "재고분석",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/mypage/store/infochange",
    name: "storeInfoChange",
    component: StoreInfoChangeView,
    meta: {
      title: "가게정보변경",
    },
    props: true,
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/store/closeCheck",
    name: "closeCheck",
    component: closeCheck,
    meta: {
      title: "영업종료 확인",
    },
    props: true,
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        return next();
      }
      next("/404");
    },
  },
  // 마이페이지(사용자)
  {
    path: "/mypage/user",
    name: "mypageUser",
    component: MypageUserView,
    meta: {
      title: "마이페이지",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 2) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/mypage/user/history",
    name: "orderHistory",
    component: OrderHistoryView,
    meta: {
      title: "내주문조회",
    },
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 2) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/mypage/user/review",
    name: "myReview",
    component: MyreviewView,
    meta: {
      title: "내리뷰조회",
    },
    props: true,
    beforeEnter: function (to, from, next) {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 2) {
        return next();
      }
      next("/404");
    },
  },
  {
    path: "/404",
    name: "notfound",
    component: NotFoundView,
    meta: {
      title: "404NotFound",
    },
  },
  {
    path: "*",
    redirect: "/404",
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  document.title = to.meta.title;
  next();
});

export default router;
