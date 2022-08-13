<template>
  <div>
    <div class="mt-16">
      <span style="color: black; font-size: 1.3rem">✔ 비밀번호 수정 ✔</span>
    </div>
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
          <div class="col-4 d-flex justify-content-end mr-2">
            <button id="btn-change" @click="checkcurrentPw">
              비밀번호 확인
            </button>
          </div>
        </div>
      </div>

      <div class="ml-5 mr-5">
        <v-text-field
          v-model="password"
          :error-messages="passwordErrors"
          label="비밀번호를 입력해주세요."
          required
          class="input-box"
          color="black"
          type="password"
          @input="$v.password.$touch()"
          @blur="$v.password.$touch()"
        ></v-text-field>
      </div>
      <div class="ml-5 mr-5">
        <v-text-field
          class="input-box"
          v-model="passwordConfirm"
          :error-messages="passwordConfirmErrors"
          label="비밀번호를 다시 입력해주세요."
          required
          color="black"
          type="password"
          @input="$v.passwordConfirm.$touch()"
          @blur="$v.passwordConfirm.$touch()"
        ></v-text-field>
      </div>
    </div>
    <div class="d-flex justify-content-end mt-6 mr-10">
      <button id="btn-change-check" @click="changePw">변경하기</button>
    </div>
  </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
import minLength from "vuelidate/lib/validators/minLength";
import http from "@/util/http-common";
export default {
  mixins: [validationMixin],
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
  validations: {
    password: { required, minLength: minLength(8) },
    passwordConfirm: { required, minLength: minLength(8) },
  },
  computed: {
    passwordErrors() {
      const errors = [];
      const validatePassword =
        /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
      if (!this.$v.password.$dirty) return errors;
      !validatePassword.test(this.password) &&
        errors.push("영문+숫자+특수기호로 구성하여야 합니다.(8-16자)");
      !this.$v.password.minLength && errors.push("8자 이상 입력해야합니다.");
      !this.$v.password.required && errors.push(" ");
      return errors;
    },
    passwordConfirmErrors() {
      const errors = [];
      if (this.password == this.passwordConfirm) return errors;
      errors.push("비밀번호가 일치하지 않습니다.");
      return errors;
    },
  },
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
#btn-change {
  margin: 0px 0px;

  border: 2px solid black;

  width: 80%;

  border-radius: 12px;
}
#btn-change-check {
  margin: 0px 0px;

  border: 2px solid black;

  width: 50%;

  border-radius: 12px;
}
</style>
