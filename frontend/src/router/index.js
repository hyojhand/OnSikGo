import Vue from "vue";
import VueRouter from "vue-router";

// 메인뷰
import MainView from "../views/MainView.vue";
// 기부
import DonationView from "../views/DonationView.vue";
// 회원관리
import LoginView from "../views/LoginView.vue";
import LogoutView from "../views/LogoutView.vue";
import SignupView from "../views/SignupView.vue";
import UserView from "../views/UserView.vue";
import OwnerView from "../views/OwnerView.vue";
import CompleteView from "../views/CompleteView.vue";
import OwnerInfoChangeView from "../views/OwnerInfoChangeView.vue";
import UserInfoChangeView from "../views/UserInfoChangeView.vue";
import PasswordChangeView from "../views/PasswordChangeView.vue";
// 알림+
import NoticeView from "../views/NoticeView.vue";
import NoticeUserView from "../views/NoticeUserView.vue";
// 메인기능
import ShopView from "../views/ShopView.vue";
import OrderView from "../views/OrderView.vue";
// 가게보기
import StoreView from "../views/StoreView.vue";
// 전체상품보기
import AllProdView from "../views/AllProdView.vue";
import ProdChangeView from "../views/ProdChangeView.vue";
import ProdRegisterView from "../views/ProdRegisterView.vue";
// 마이페이지(점주)
import MypageOwnerView from "../views/MypageOwnerView.vue";
import DataAnalysisView from "../views/DataAnalysisView.vue";
import StoreInfoChangeView from "../views/StoreInfoChangeView.vue";
// 마이페이지(사용자)
import MypageUserView from "../views/MypageUserView.vue";
import OrderHistoryView from "../views/OrderHistoryView.vue";
import MyreviewView from "../views/MyreviewView.vue";
// 오류
import NotFoundView from "../views/NotFoundView.vue";

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
  },
  //  회원관리
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      title: "로그인",
    },
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      title: "로그아웃",
    },
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupView,
    meta: {
      title: "회원가입",
    },
  },
  {
    path: "/signup/user",
    name: "userview",
    component: UserView,
    meta: {
      title: "회원가입",
    },
  },
  {
    path: "/signup/owner",
    name: "ownerview",
    component: OwnerView,
    meta: {
      title: "회원가입",
    },
  },
  {
    path: "/signup/complete",
    name: "complete",
    component: CompleteView,
    meta: {
      title: "가입성공",
    },
  },
  {
    path: "/ownerinfochange",
    name: "ownerInfoChange",
    component: OwnerInfoChangeView,
    meta: {
      title: "회원정보변경",
    },
  },
  {
    path: "/userinfochange",
    name: "userInfoChange",
    component: UserInfoChangeView,
    meta: {
      title: "회원정보변경",
    },
  },
  {
    path: "/infochange/pwchange",
    name: "passwordChange",
    component: PasswordChangeView,
    meta: {
      title: "비밀번호변경",
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
  },
  {
    path: "/notice/user",
    name: "noticeUser",
    component: NoticeUserView,
    meta: {
      title: "알림조회",
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
  },
  // 가게보기
  {
    path: "/store",
    name: "storeView",
    component: StoreView,
    meta: {
      title: "가게조회",
    },
  },
  // 전체상품보기
  {
    path: "/allprod",
    name: "allProduct",
    component: AllProdView,
    meta: {
      title: "전상품조회",
    },
  },
  {
    path: "/allprod/change",
    name: "prodChange",
    component: ProdChangeView,
    meta: {
      title: "상품변경",
    },
  },
  {
    path: "/allprod/register",
    name: "prodRegister",
    component: ProdRegisterView,
    meta: {
      title: "상품등록",
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
  },
  {
    path: "/mypage/owner/analysis",
    name: "dataAnalysis",
    component: DataAnalysisView,
    meta: {
      title: "재고분석",
    },
  },
  {
    path: "/mypage/store/infochange",
    name: "storeInfoChange",
    component: StoreInfoChangeView,
    meta: {
      title: "가게정보변경",
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
  },
  {
    path: "/mypage/user/history",
    name: "orderHistory",
    component: OrderHistoryView,
    meta: {
      title: "내주문조회",
    },
  },
  {
    path: "/mypage/user/review",
    name: "myReview",
    component: MyreviewView,
    meta: {
      title: "내리뷰조회",
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
