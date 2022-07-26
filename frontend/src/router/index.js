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
    }
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
  {
    path: "/quit",
    name: "memberQuit",
    component: MemberQuitView,
    meta: {
      title: "회원탈퇴",
    },
  },
  {
    path: "/quit/complete",
    name: "quitComplete",
    component: QuitCompleteView,
    meta: {
      title: "탈퇴성공",
    },
  },
  {
    path: "/storeinfodiscard",
    name: "storeInfoDiscard",
    component: StoreInfoDiscardView,
    meta: {
      title: "가게탈퇴",
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
    path: "/notice/cancel",
    name: "orderCancel",
    component: OrderCancelView,
    meta: {
      title: "주문취소",
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
  {
    path: "/allprod/delete",
    name: "prodDelete",
    component: ProdDeleteView,
    meta: {
      title: "상품삭제",
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
  {
    path: "/mypage/owner/close",
    name: "closedView",
    component: ClosedView,
    meta: {
      title: "영업종료",
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  console.log(to)
  document.title = to.meta.title
  next()
})

export default router;
