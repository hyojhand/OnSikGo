<template>
  <div class="container">
    <div class="title">
      <div class="font-l text-m">OnSikGo</div>
      <div class="font-m text-m">로그인을 통해</div>
      <div class="font-m text-m">함께 세상을 구해나가봐요</div>
    </div>
    <!--로그인버튼-->
    <form class="input-box">
      <input
        v-model="email"
        type="text"
        name="username"
        placeholder="아이디를 입력해주세요."
      />
      <input
        v-model="password"
        @keyup.enter="login()"
        type="password"
        name="username"
        placeholder="비밀번호를 입력해주세요."
      />
    </form>
    <div v-if="loginCheck">
      로그인에 실패하였습니다.
    </div>
    <div class="btn-box mb-5">
      <button class="radius-m primary" @click="login()" @keyup.enter="login()">
        로그인 하기
      </button>
      <button class="radius-m error" @click="signup()">회원 가입</button>
    </div>

    <div class="find-box">
      <div>비밀번호를 잊으셨나요?</div>
      <v-dialog 
      v-model="dialog"
      persistent>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs"
          v-on="on">
          비밀번호 찾기</v-btn>
      </template>
      <v-card>
        <v-card-title><span class="text-h5">비밀번호 찾기</span></v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <div>
              <v-text-field
                v-model="userName"
                label="이름을 입력해주세요."
                required
                ></v-text-field>
              </div>
              <v-text-field
                v-model="emailCheck"
                label="이메일을 입력해주세요."
                required
                @keyup.enter="checkName()"
                ></v-text-field>
                <loading-spinner v-if="isLoading"></loading-spinner>
                <div v-if="checkCheck === 1">임시비밀번호가 전송되었습니다.</div>
                <div v-if="checkCheck === 2">가입된 이름 혹은 이메일이 아닙니다.</div>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="find-button1" color="success" depressed @click="checkName()">
              임시비밀번호 전송</v-btn>
          <v-btn class="find-button2" color="error" depressed  @click="clear()">닫기</v-btn>
        </v-card-actions>
      </v-card>
      </v-dialog>
    </div>

    <!--소셜 로그인을 위한 아이콘 넣기-->
    <social-login></social-login>
  </div>
</template>

<script>
import SocialLogin from "@/components/accounts/SocialLogin.vue";
import LoadingSpinner from "@/components/home/LoadingSpinner.vue";
import http from "@/util/http-common";
export default {
  name: "LoginView",
  components: {
    SocialLogin,
    LoadingSpinner,
  },
  data: () => ({
    email: "",
    password: "",
    userName: "",
    emailCheck:"",
    dialog: false,
    loginCheck: false,
    checkCheck: 0,
    isLoading: false,
  }),

  methods: {
    signup() {
      this.$router.push("/signup");
    },
    login() {
      this.loginCheck = false,
      http
        .post("/user/login", {
          email: this.email,
          password: this.password,
        })
        .then((response) => {
          if (response.status == 200) {
            localStorage.setItem("access-token", response.data.token);
            this.$router.push("/");
          } else {
            this.loginCheck = true;
          }
        })
        .catch(() => {
          this.loginCheck = true;
        });
    },
    checkName() {
      this.checkCheck = 0;
      this.isLoading = true;
      http
        .post("/user/pw-find", {
          email: this.emailCheck,
          userName: this.userName,
        })
        .then((response) => {
          if (response.status == 200) {
            this.isLoading = false;
            this.checkCheck = 1;
          } else {
            this.isLoading = false;
            this.checkCheck = 2;
          }
        })
    },

    clear() {
      this.userName = "";
      this.emailCheck = "";
      this.dialog = false;
      this.checkCheck = 0;
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.input-box {
  margin-top: 5%;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
input {
  width: 80%;
  padding: 8px 10px;
  text-align: start;
  background-color: white;
  border: 2px solid rgba(0, 0, 0, 20%);
  border-radius: 20px;
  margin: 2%;
}
.btn-box {
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
}
button {
  width: 80%;
  margin: 1%;
  color: white;
  background-color: rgba(140, 184, 131, 50%);
}
.find-box {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 80%;
}
.find-box > div {
  color: black;
  font-weight: 500;
  font-size: 13px;
}
.find-box > button {
  color: black;
  width: 50%;
  text-align: end;
  background-color: rgb(240, 240, 240);
}

.find-button1 {
  display: flex;
  width: 50%;
}
.find-button2 {
  display: flex;
  width: 30%;
}

.find-button3 {
  display: flex;
  width: 30%;
}
</style>
