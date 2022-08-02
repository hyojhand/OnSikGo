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
    <v-card class="btn-box" black outlined min-width="330">
      <form class="mb-2 el-case">
        <!-- -------메일 입력하기---------------------------- -->
        <div class="mail-input">
          <v-text-field
            class="input-box mt-5"
            v-model="email"
            :error-messages="emailErrors"
            label="이메일을 입력해주세요."
            required
            color="black"
            @input="$v.email.$touch()"
            @blur="$v.email.$touch()"
          ></v-text-field>

          <button class="border-m radius-m confrim-btn" @click="isCheck()">
            {{ checkmsg }}
          </button>
        </div>
        <!-- ------------인증 메일 보내기-------------------- -->
        <div v-if="sendMail">
          <div class="mailconfim-case">
            <input
              id="mail-confirm"
              class="mail-confirm"
              v-model="authNum"
              placeholder="인증번호를 입력하세요."
            />
            <button
              class="border-m radius-m mailconfirm-btn"
              @click="checkMail()"
            >
              확인하기
            </button>
          </div>
        </div>

        <!-- --------비밀번호 입력------------ -->
        <v-text-field
          class="input-box"
          v-model="password"
          :error-messages="passwordErrors"
          label="비밀번호를 입력해주세요."
          required
          color="black"
          type="password"
          @input="$v.password.$touch()"
          @blur="$v.password.$touch()"
        ></v-text-field>

        <!-- ----------비밀번호 재확인-------------- -->
        <v-text-field
          class="input-box"
          v-model="passwordConfirm"
          :error-messages="passwordConfirmErrors"
          label="비밀번호를 다시 입력해주세요."
          required
          color="black"
          type="password"
          @input="$v.passwordConfirm.$touch()"
          @blur="$v.passwordComfirm.$touch()"
        ></v-text-field>

        <!-- ----------사용자 이름 입력-------------- -->
        <v-text-field
          class="input-box"
          v-model="name"
          :error-messages="nameErrors"
          label="이름을 입력해주세요."
          required
          color="black"
          @input="$v.name.$touch()"
          @blur="$v.name.$touch()"
        ></v-text-field>

        <!-- --------닉네임 입력---------------        -->
        <div class="mail-input">
          <v-text-field
            class="input-box"
            v-model="nickname"
            :error-messages="nicknameErrors"
            label="닉네임을 입력해주세요."
            required
            color="black"
            @input="$v.nickname.$touch()"
            @blur="$v.nickname.$touch()"
          ></v-text-field>
          <!-- ------닉네임 중복확인------- -->
          <button
            class="border-m radius-m name-confrim-btn"
            @click="nicknameCheck()"
          >
            중복확인하기
          </button>
          <div v-if="nicknameDuple">사용가능한 닉네임입니다.</div>
        </div>

        <!-- ----------회원가입 동의 체크------------ -->
        <v-checkbox
          v-model="checkbox"
          :error-messages="checkboxErrors"
          label="회원 가입에 동의하십니까?"
          required
          color="black"
          @change="$v.checkbox.$touch()"
          @blur="$v.checkbox.$touch()"
        ></v-checkbox>

        <div class="btns mb-5">
          <button class="border-m radius-m notice-btn" @click="signup()">
            가입하기
          </button>
          <button @click="clear" class="border-m radius-m notice-btn clear">
            초기화
          </button>
        </div>
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
    passwordConfirm: { required, minLength: minLength(8) },
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
    passwordConfirm: "",
    nickname: "",
    role: "USER",
    checkbox: false,
    sendMail: false,
    checkmsg: "메일 인증하기",
    nicknameDuple: false,
    authNum: Number,
  }),

  computed: {
    checkboxErrors() {
      const errors = [];
      if (!this.$v.checkbox.$dirty) return errors;
      !this.$v.checkbox.checked && errors.push(" ");
      return errors;
    },
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.maxLength &&
        errors.push("이름은 10글자 이내로 입력해야합니다.");
      !this.$v.name.required && errors.push(" ");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
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
    nicknameErrors() {
      const errors = [];
      if (!this.$v.nickname.$dirty) return errors;
      !this.$v.nickname.maxLength &&
        errors.push("닉네임은 10글자 이내로 입력해야합니다.");
      !this.$v.nickname.required && errors.push(" ");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("이메일 형식이 아닙니다.");
      !this.$v.email.required && errors.push(" ");
      return errors;
    },
  },
  methods: {
    isCheck() {
      http
        .post("/user/email", {
          email: this.email
        })
        .then((response) => {
        if (response.status == 200) {
          alert("인증번호를 확인해주세요");
          this.sendMail = true;
          this.checkmsg = "재전송하기";
        } else {
          alert("이미 가입된 이메일입니다");
        }
      });
    },
    checkMail() {
      http
        .post("/user/emailAuthNumber", {
          email: this.email,
          authNum: this.authNum,
        })
        .then((response) => {
        if ((response.status) == 200) {
          console.log(response.data);
        } else {
          alert("인증번호 확인에 실패했습니다");
        }
      });
    },
    nicknameCheck() {
      http
        .post("/user/nickname", {
          nickname: this.nickname
        })
        .then((response) => {
        if (response.status == 200) {
          this.nicknameDuple = !this.nicknameDuple;
        } else {
          alert("중복된 닉네임이 있습니다");
        }
      });
    },
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
.el-case {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  height: 100%;
}
.mailconfim-case {
  margin: 3% 0;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  color: black;
}
.mailconfirm-btn {
  color: black;
  width: 70px;
}
.ment-box {
  text-align: start;
}
.signup-box {
  display: flex;
  flex-direction: column;
  height: 100vh;
  justify-content: start;
  align-items: center;
}
.btn-box {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
.select-btn {
  width: 100px;
  min-height: 100px;
  border-radius: 50%;
  opacity: 90%;
}
.btns {
  width: 100%;
  margin: 0;
  display: flex;
  justify-content: space-evenly;
}
.notice-btn {
  width: 80px;
}
.btn-case {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
.mail-input {
  position: relative;
}
.confrim-btn {
  right: 0px;
  top: 32px;
  position: absolute;
  width: 90px;
  font-size: 13px;
  color: black;
}
.name-confrim-btn {
  right: 0px;
  top: 15px;
  position: absolute;
  width: 90px;
  font-size: 13px;
  color: black;
}
.clear {
  color: rgb(255, 82, 82);
}
.mail-box {
  display: flex;
  flex-direction: row;
}
.mail-confirm {
  color: black;
  border-bottom: 1px solid rgba(0, 0, 0, 30%);
}
.input-box {
  min-width: 266px;
}
</style>
