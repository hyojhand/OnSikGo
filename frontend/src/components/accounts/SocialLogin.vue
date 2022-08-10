<template>
  <div>
    <div id="kakao-login" @click="kakaoLogin()">
      <a>
        <img id="kakao-login-img" src="@/assets/images/kakao.png" />
      </a>
    </div>
    <div id="naver_id_login" style="margin-top: 25px;"></div>
  </div>
</template>

<script>
import http from "@/util/http-common";
const { Kakao } = window;

export default {
  data() {
    return {
      lastChange: null,
      timer: null,
    };
  },
  created() {
    let curVal = localStorage.getItem("access-token");
    this.lastChange = new Date();
    this.timer = setInterval(() => {
      const newVal = localStorage.getItem("access-token");
      if (newVal !== curVal) {
        curVal = newVal;
        this.$router.push("/");
      }
    }, 1000);
  },
  beforeDestroy() {
    this.timer = null;
  },
  mounted() {
    const naver_id_login = new window.naver_id_login(
      "0rgjMkjviFPmCTRQSJf5",
      "http://localhost:3000/login"
    );
    const state = naver_id_login.getUniqState();
    naver_id_login.setState(state);
    naver_id_login.setButton("green", 3, 40);
    naver_id_login.setPopup();
    naver_id_login.init_naver_id_login();

    window.location.href.includes("access_token") && this.naverLogin();
  },
  methods: {
    kakaoLogin() {
      Kakao.Auth.login({
        success: (authObj) => {
          console.log(authObj.access_token);
          http
            .post(
              "/user/kakao",
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
              } else {
                console.log("로그인 실패");
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
      const location = window.location.href.split("=")[1];
      const token = location.split("&")[0];
      http
        .post(
          "/user/naver",
          {
            access_token: token,
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
            window.close();
          } else {
            console.log("로그인 실패");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
#kakao-login-img {
  width: 185px;
  height: 40px;
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
