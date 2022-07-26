import Vue from "vue";
import VueRouter from "vue-router";

// 메인뷰
import MainView from "../views/MainView.vue";
// 기부
import DonationView from "../views/DonationView.vue";
// 회원관리
import LoginView from "../views/LoginView.vue";
import SignupView from "../views/SignupView.vue";
import UserView from "../views/UserView.vue";
import OwnerView from "../views/OwnerView.vue";
import CompleteView from "../views/CompleteView.vue";
import OwnerInfoChangeView from "../views/OwnerInfoChangeView.vue";
import UserInfoChangeView from "../views/UserInfoChangeView.vue";
import PasswordChangeView from "../views/PasswordChangeView.vue";
import MemberQuitView from "../views/MemberQuitView.vue";
import QuitCompleteView from "../views/QuitCompleteView.vue";
import StoreInfoDiscardView from "../views/StoreInfoDiscardView.vue";
// 알림+
import NoticeView from "../views/NoticeView.vue";
import NoticeUserView from "../views/NoticeUserView.vue";
import OrderCancelView from "../views/OrderCancelView.vue";
// 메인기능
import ShopView from "../views/ShopView.vue";
import OrderView from "../views/OrderView.vue";
// 가게보기
import StoreView from "../views/StoreView.vue";
// 전체상품보기
import AllProdView from "../views/AllProdView.vue";
import ProdChangeView from "../views/ProdChangeView.vue";
import ProdRegisterView from "../views/ProdRegisterView.vue";
import ProdDeleteView from "../views/ProdDeleteView.vue";
// 마이페이지(점주)
import MypageOwnerView from "../views/MypageOwnerView.vue";
import DataAnalysisView from "../views/DataAnalysisView.vue";
import StoreInfoChangeView from "../views/StoreInfoChangeView.vue";
import ClosedView from "../views/ClosedView.vue";
// 마이페이지(사용자)
import MypageUserView from "../views/MypageUserView.vue";
import OrderHistoryView from "../views/OrderHistoryView.vue";
import MyreviewView from "../views/MyreviewView.vue";

Vue.use(VueRouter);

const routes = [
  // 메인
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  // 기부
  {
    path: "/donation",
    name: "donation",
    component: DonationView,
  },
  //  회원관리
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignupView,
  },
  {
    path: "/signup/user",
    name: "userview",
    component: UserView,
  },
  {
    path: "/signup/owner",
    name: "ownerview",
    component: OwnerView,
  },
  {
    path: "/signup/complete",
    name: "complete",
    component: CompleteView,
  },
  {
    path: "/ownerinfochange",
    name: "ownerInfoChange",
    component: OwnerInfoChangeView,
  },
  {
    path: "/userinfochange",
    name: "userInfoChange",
    component: UserInfoChangeView,
  },
  {
    path: "/infochange/pwchange",
    name: "passwordChange",
    component: PasswordChangeView,
  },
  {
    path: "/quit",
    name: "memberQuit",
    component: MemberQuitView,
  },
  {
    path: "/quit/complete",
    name: "quitComplete",
    component: QuitCompleteView,
  },
  {
    path: "/storeinfodiscard",
    name: "storeInfoDiscard",
    component: StoreInfoDiscardView,
  },

  // 알림
  {
    path: "/notice",
    name: "notice",
    component: NoticeView,
  },
  {
    path: "/notice/cancel",
    name: "orderCancel",
    component: OrderCancelView,
  },
  {
    path: "/notice/user",
    name: "noticeUser",
    component: NoticeUserView,
  },
  // 메인기능
  {
    path: "/shop",
    name: "shopView",
    component: ShopView,
  },
  {
    path: "/shop/order",
    name: "orderView",
    component: OrderView,
  },
  // 가게보기
  {
    path: "/store",
    name: "storeView",
    component: StoreView,
  },
  // 전체상품보기
  {
    path: "/allprod",
    name: "allProduct",
    component: AllProdView,
  },
  {
    path: "/allprod/change",
    name: "prodChange",
    component: ProdChangeView,
  },
  {
    path: "/allprod/register",
    name: "prodRegister",
    component: ProdRegisterView,
  },
  {
    path: "/allprod/delete",
    name: "prodDelete",
    component: ProdDeleteView,
  },
  // 마이페이지(점주)
  {
    path: "/mypage/owner",
    name: "mypageOwner",
    component: MypageOwnerView,
  },
  {
    path: "/mypage/owner/analysis",
    name: "dataAnalysis",
    component: DataAnalysisView,
  },
  {
    path: "/mypage/store/infochange",
    name: "storeInfoChange",
    component: StoreInfoChangeView,
  },
  {
    path: "/mypage/owner/close",
    name: "closedView",
    component: ClosedView,
  },
  // 마이페이지(사용자)
  {
    path: "/mypage/user",
    name: "mypageUser",
    component: MypageUserView,
  },
  {
    path: "/mypage/user/history",
    name: "orderHistory",
    component: OrderHistoryView,
  },
  {
    path: "/mypage/user/review",
    name: "myReview",
    component: MyreviewView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
