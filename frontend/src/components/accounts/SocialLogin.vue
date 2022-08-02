<template>
  <div>
    <a id="kakao-login" @click="kakaoLogin()">
      <img id="kakao-login-img" src="@/assets/images/kakao.png" />
    </a>
    <a id="naver-login" @click="naverLogin()">
      <img id="naver-login-img" src="@/assets/images/naver.png" />
    </a>
  </div>
</template>

<script>
import http from "@/util/http-common";
const { Kakao } = window;
export default {
  methods: {
    kakaoLogin() {
      Kakao.Auth.login({
        success: (authObj) => {
          console.log(authObj.access_token);
          http
            .post(
              "http://localhost:8080/user/kakao",
              {
                access_token: authObj.access_token,
              },
              {
                headers: {
                  "Content-Type": "application/json",
                },
              }
            )
            .then((res) => {
              if (res.status == 200) {
                localStorage.setItem("access-token", res.data.token);
                this.$router.push("/");
              }
            })
            .catch((err) => {
              console.log(err);
            });
        },
        fail: (err) => {
          alert("다시 입력해주세요.");
          console.log(err);
        },
      });
    },
    naverLogin() {
      const naver_id_login = new window.naver_id_login(
        "0rgjMkjviFPmCTRQSJf5",
        "http://localhost:3000/login"
      );
      const state = naver_id_login.getUniqState();
      naver_id_login.setState(state);
      naver_id_login.setPopup();
      naver_id_login.init_naver_id_login();
    },
  },
};
</script>

<style scoped>
#kakao-login-img {
  width: 45px;
  height: 45px;
  margin-right: 10%;
}
#naver-login-img {
  width: 45px;
  height: 45px;
}
</style>
