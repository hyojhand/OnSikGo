<template>
  <div>
    <h1 class="mt-16" style="color: black">✔ 비밀번호 수정 ✔</h1>
    <div>
      <div class="container">
        <div class="row">
          <div class="col-7">
            <input
              v-model="currentPassword"
              :type="passwordType"
              id="input-box"
              placeholder="현재 비밀번호"
            />
          </div>
          <div class="col-5">
            <button @click="checkcurrentPw">현재 비밀번호 확인</button>
          </div>
        </div>
      </div>

      <div class="d-flex justify-content-start ml-6">
        <input
          v-model="password"
          :type="passwordType"
          :error-messages="passwordErrors"
          id="input-box-all"
          placeholder="새 비밀번호"
        />
      </div>
      <div class="d-flex justify-content-start ml-6 mt-3">
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          id="input-box-all"
          placeholder="새 비밀번호 재입력"
        />
      </div>
    </div>
    <div class="d-flex justify-content-end mt-6 mr-10">
      <button @click="changePw">변경하기</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "PasswordChangeView",
  data: () => {
    return {
      email: "",
      currentPassword: "",
      password: "",
      passwordConfirm: "",
      isLoading: false,
      error: {
        email: false,
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
      currentPwcheck: false,
    };
  },

  // computed: {
    // passwordErrors() {
      // const errors = [];
      // const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
      // if (!this.$v.password.$dirty) return errors;
      // !validatePassword.test(this.password) && errors.push("영문+숫자+특수기호로 구성하여야 합니다.(8-16자)");
  //     !this.$v.password.minLength && errors.push("8자 이상 입력해야합니다.");
  //     !this.$v.password.required && errors.push(" ");
      // return errors;
    // },
  // },

  methods: {
    checkcurrentPw() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");

      http
        .post("/user/pw-check", { password: this.currentPassword })
        .then((response) => {
          if (response.status == 200) {
            console.log(this.currentPassword);
            alert("현재 비밀번호와 같습니다!");
            this.currentPwcheck = true;
          } else {
            alert("현재 비밀번호 다릅니다.!");
            if (this.currentPwcheck == true) {
              this.currentPwcheck = false;
            }
          }
        });
    },
    changePw() {
      if (
        this.password == this.passwordConfirm &&
        this.currentPwcheck == true &&
        this.password.length >= 8
      ) {
        http.defaults.headers["access-token"] =
          localStorage.getItem("access-token");

        http
          .patch("/user/pw", { password: this.passwordConfirm })
          .then((response) => {
            if (response.status == 200) {
              alert("비밀번호 수정이 완료되었습니다");
              this.$router.push("/userinfochange");
            } else {
              alert("비밀번호 수정이 되지 않았습니다.");
            }
          });
      } else if (this.password != this.passwordConfirm) {
        alert("비밀번호가 재입력이 다릅니다");
      } else if (this.currentPwcheck == false) {
        alert("현재 비밀번호 확인 먼저 부탁드립니다.");
      } else if (this.password.length < 1) {
        alert("새로운 비밀번호를 입력해주세요!");
      }
    },
  },
};
</script>
<style scoped>
#input-box {
  width: 200px;
  height: 30px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
#input-box-all {
  width: 350px;
  height: 30px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
</style>
