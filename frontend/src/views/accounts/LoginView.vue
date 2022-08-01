<template>
  <div>
    <br><br><br>
    <div class="onsikgotext">
    <h3>OnSikGo</h3>
    <h5>로그인을 통해 <br> 함께 세상을 구해나가봐요</h5></div>
    <br><br>
    <LoginComponent></LoginComponent>
    <br>
    <b-button id="btn_signup" squared  variant="danger">회원가입</b-button>
    <br>
    <br>
    <div class="container">
      <div class="row">
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
  </div>
</template>

<script>
import LoginComponent from "@/components/accounts/LoginComponent.vue"
export default {
  name: "LoginView",
  components: {
    LoginComponent
  }
};
</script>

<style scoped>
.onsikgotext {
  text-align: start;
}
#btn_signup {
  width: 100%;
}
#bold {
  font-weight: bold;
  font-size: 0.75rem;
}
</style>
