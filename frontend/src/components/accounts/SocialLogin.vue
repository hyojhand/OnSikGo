<template>
  <div>
    <a id="kakao-login" @click="kakaoLogin()">
      <img id="kakao-login-img" src="@/assets/images/kakao.png" />
      <span>카카오 계정으로 로그인하기</span>
    </a>
    <a id="naver-login" @click="naverLogin()">
      <img id="naver-login-img" src="@/assets/images/naver.png" />
      <span>네이버 계정으로 로그인하기</span>
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
              console.log(res);
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
div {
  display: flex;
  margin-top: 5%;
  width: 100%;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
}
a {
  display: flex;
  flex-direction: column;
  align-items: center;
}
span {
  margin-top: 7%;
  color: black;
  font-size: 14px;
}
</style>
