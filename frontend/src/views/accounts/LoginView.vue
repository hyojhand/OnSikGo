<template>
  <div>
    <div class="onsikgotext">
      <h3>OnSikGo</h3>
      <h5>로그인을 통해</h5>
      <h5>함께 세상을 구해나가봐요</h5>
    </div>
    <!--로그인버튼-->
    <LoginComponent></LoginComponent>
    <b-button @click="signup()" id="btn_signup" squared variant="danger"
      >회원가입</b-button
    >

    <div class="find container">
      <div class="row mt-5">
        <div class="col-6">
          <span id="bold">아이디를 잊었다면?</span>
        </div>
        <div class="col-6">
          <b-button pill variant="outline-success">아이디 찾기</b-button>
        </div>
      </div>
    </div>
    <!--제발 커밋,, -->
    <div class="container">
      <div class="row">
        <div class="col-6">
          <span id="bold">비밀번호를 잊었다면?</span>
        </div>
        <div class="col-6">
          <b-button pill variant="outline-success">비밀번호 찾기</b-button>
        </div>
      </div>
    </div>

    <!--소셜 로그인을 위한 아이콘 넣기-->
    <SocialLogin />
  </div>
</template>

<script>
import LoginComponent from "@/components/accounts/LoginComponent.vue";
import SocialLogin from "@/components/accounts/SocialLogin.vue";
export default {
  name: "LoginView",
  components: {
    LoginComponent,
    SocialLogin,
  },
  methods: {
    signup() {
      this.$router.push("/signup");
    },
  },
};
</script>

<style scoped>
.onsikgotext {
  text-align: start;
  margin-bottom: 15%;
}
#btn_signup {
  width: 100%;
}
#bold {
  font-weight: bold;
  font-size: 0.75rem;
}

.find {
  margin-top: 130px;
}
</style>
