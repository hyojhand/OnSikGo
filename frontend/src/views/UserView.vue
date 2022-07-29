<template>
  <div class="signup-box" width="100%">
    <div class="mb-5">
      <v-list-item-title class="text-h5 mt-3 mb-5">
        고객 회원 가입하기
      </v-list-item-title>
      <div class="ment-box">
        <v-list-item-subtitle>어서오세요!</v-list-item-subtitle>
        <v-list-item-subtitle>아래의 칸을 모두 채운다면</v-list-item-subtitle>
        <v-list-item-subtitle class="mb-5"
          >저희와 함께 지구를 지킬 수 있어요.</v-list-item-subtitle
        >
      </div>
    </div>
    <v-card
      class="btn-box mt-5"
      black
      outlined
      min-width="330"
      min-height="200"
    >
      <form class="mb-2">
        <v-text-field
          v-model="email"
          :error-messages="emailErrors"
          label="이메일을 입력해주세요."
          required
          @input="$v.email.$touch()"
          @blur="$v.email.$touch()"
        ></v-text-field>

        <v-text-field
          v-model="password"
          :error-messages="passwordErrors"
          label="비밀번호를 입력해주세요."
          required
          type="password"
          @input="$v.password.$touch()"
          @blur="$v.password.$touch()"
        ></v-text-field>

        <v-text-field
          v-model="name"
          :error-messages="nameErrors"
          :counter="10"
          label="이름을 입력해주세요."
          required
          @input="$v.name.$touch()"
          @blur="$v.name.$touch()"
        ></v-text-field>
        <v-text-field
          v-model="nickname"
          :error-messages="nicknameErrors"
          :counter="10"
          label="닉네임을 입력해주세요."
          required
          @input="$v.nickname.$touch()"
          @blur="$v.nickname.$touch()"
        ></v-text-field>

        <v-checkbox
          v-model="checkbox"
          :error-messages="checkboxErrors"
          label="회원 가입에 동의하십니까?"
          required
          @change="$v.checkbox.$touch()"
          @blur="$v.checkbox.$touch()"
        ></v-checkbox>

        <v-btn class="mr-4" @click="signup()" color="success"> 가입하기 </v-btn>
        <v-btn @click="clear" color="error"> 초기화 </v-btn>
        <v-btn @click="tempgo()"> 임시 넘어가기</v-btn>
      </form>
    </v-card>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { validationMixin } from "vuelidate";
import { required, maxLength, email } from "vuelidate/lib/validators";
import minLength from "vuelidate/lib/validators/minLength";

export default {
  mixins: [validationMixin],
  name: "UserView",
  validations: {
    email: { required, email },
    password: { required, minLength: minLength(8) },
    name: { required, maxLength: maxLength(10) },
    nickname: { required, maxLength: maxLength(10) },
    checkbox: {
      checked(val) {
        return val;
      },
    },
  },

  data: () => ({
    name: "",
    email: "",
    password: "",
    nickname: "",
    role: "USER",
    checkbox: false,
  }),

  computed: {
    checkboxErrors() {
      const errors = [];
      if (!this.$v.checkbox.$dirty) return errors;
      !this.$v.checkbox.checked && errors.push("'동의합니다'를 눌러주세요");
      return errors;
    },
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.maxLength &&
        errors.push("이름은 10글자 이내로 입력해야합니다.");
      !this.$v.name.required && errors.push("이름을 입력해주세요.");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.minLength && errors.push("8자 이상 입력해야합니다.");
      !this.$v.password.required && errors.push("비밀번호를 입력해주세요.");
      return errors;
    },
    nicknameErrors() {
      const errors = [];
      if (!this.$v.nickname.$dirty) return errors;
      !this.$v.nickname.maxLength &&
        errors.push("닉네임은 10글자 이내로 입력해야합니다.");
      !this.$v.nickname.required && errors.push("닉네임을 입력해주세요.");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("이메일 형식이 아닙니다.");
      !this.$v.email.required && errors.push("이메일을 입력해주세요.");
      return errors;
    },
  },
  methods: {
    tempgo() {
      this.$router.push("/signup/complete");
    },
    submit() {
      this.$v.$touch();
    },
    clear() {
      this.$v.$reset();
      this.name = "";
      this.email = "";
      this.password = "";
      this.nickname = "";
      this.checkbox = false;
    },
    signup() {
      http
        .post("/user/signup", {
          email: this.email,
          password: this.password,
          nickname: this.nickname,
          userName: this.name,
          role: this.role,
        })
        .then((response) => {
          if (response.status == 200) {
            this.$router.push("/signup/complete");
            console.log(response.data);
          } else {
            alert("회원가입에 실패했습니다");
          }
        });
    },
  },
};
</script>

<style scoped>
.ment-box {
  text-align: start;
}
.signup-box {
  display: flex;
  flex-direction: column;
  height: 80vh;
  justify-content: center;
  align-items: center;
}
.btn-box {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  height: 70vh;
}
.select-btn {
  width: 100px;
  min-height: 100px;
  border-radius: 50%;
  opacity: 90%;
}
</style>
