<template>
  <div>
    <form name="form">
      <div class="form-group">
        <input
          v-model="email"
          type="text"
          class="form-control input_login"
          name="username"
          placeholder="아이디를 입력해주세요"
        />
      </div>
      
      <div class="form-group">
        <input
          v-model="password"
          @keyup.enter="login()"
          type="password"
          class="form-control input_login"
          name="username"
          placeholder="비밀번호를 입력해주세요"
        />
      </div>
      
      <b-button
        @click="login()"
        @keyup.enter="login()"
        type="button"
        id="btn_login"
        squared
        variant="primary"
        >로그인하기</b-button
      >
    </form>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "LoginComponent",

  data: () => ({
    email: "",
    password: "",
  }),

  methods: {
    login() {
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
            alert("로그인에 실패했습니다");
          }
        });
    },
  },
};
</script>

<style scoped>
#btn_login {
  width: 100%;
  margin-bottom: 2%;
}
.input_login {
  margin-bottom: 2%;
}
</style>
