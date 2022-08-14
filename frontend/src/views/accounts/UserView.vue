<template>
  <div class="signup-box" width="100%">
    <div class="mb-5">
      <v-list-item-title class="mt-3 mb-5 fw-bold" style="font-size:28px;">
        <strong style="color: #368f3d;">일반 고객</strong>  회원가입
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
      <form @submit.prevent="submit" class="mb-2 el-case">
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
          <button type="button" class="border-m radius-m confrim-btn" @click="isCheck()" >
            {{ checkmsg }}
          </button>
          <div v-if="emailfailDuple" style="color: red; margin-top: 3px;">이미 가입된 메일 혹은 잘못된 이메일입니다.</div>
        </div>
        <!-- ------------인증 메일 보내기-------------------- -->
        <div class="base-mailifirm" v-if="sendMail">
          <div class="mailconfim-case">
            <input
              id="mail-confirm"
              class="mail-confirm"
              v-model="authNum"
              placeholder="인증번호를 입력하세요."
            />
            <div>
              <CountTimer v-if="time" :time="time" :key="rederKey"/>
              <button
                class="border-m radius-m mailconfirm-btn mb-5 "
                @click="checkMail()"
                type="button"
                style="margin-left:20px;"
              >
                인증
              </button>
            </div>
          </div>
          <div v-if="mailconfirmDuple" style="color: green;">인증번호 확인이 되었습니다.</div>
          <div v-if="mailfailDuple" style="color: red;">인증번호 확인에 실패했습니다.</div>
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
            class="border-m radius-m name-confrim-btn mb-5"
            @click="nicknameCheck()"
            type="button"
          >
            중복확인
          </button>
          <div v-if="nicknameDuple" style="color: green;">사용가능한 닉네임입니다.</div>
          <div v-if="nicknamefailDuple" style="color: red;">사용 불가능한 닉네임입니다.</div>
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
          class = "checkbox"
        ></v-checkbox>
        <!-- 가입하기 버튼 -->
        <div class="btns mb-5">
          <button 
          class="border-m radius-m notice-btn" 
          @click="signup()"
          style="margin-top: 10px; width:150px; height:40px; background-color: #368f3d; border-color: #368f3d; color: white;"
>
          <!-- v-if="check1 && check2 && checkbox" -->
            가입하기
          </button>
        </div>
        <div v-if="signupfailDuple" style="color:red;">😥 회원가입에 실패했습니다.</div>
      </form>
    </v-card>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { validationMixin } from "vuelidate";
import { required, maxLength, email } from "vuelidate/lib/validators";
import minLength from "vuelidate/lib/validators/minLength";
import CountTimer from "@/components/accounts/Timer.vue";

export default {
  components: {
    CountTimer
  },
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
    checkmsg: "메일 인증",
    nicknameDuple: false,
    authNum: "",
    emailfailDuple: false,
    mailconfirmDuple: false,
    mialfailDuple: false,
    nicknamefailDuple: false,
    signupfailDuple: false,
    check1: false,
    check2: false,
    time: false,
    rederKey:0,
  }),

  computed: {
    checkboxErrors() {
      const errors = [];
      if (!this.$v.checkbox.$dirty) return errors;
      !this.$v.checkbox.checked && errors.push(" ");
      return errors;
    },
    nameErrors() {
      var pattern_name = /^[가-힣]{2,10}$/
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      this.name.search(/\s/) != -1 &&errors.push("이름은 빈 칸을 포함 할 수 없습니다.")
      !pattern_name.test(this.name)&&errors.push("2글자 이상의 한글 이름을 입력해주세요.");
      !this.$v.name.maxLength &&
        errors.push("이름은 10글자 이내로 입력해야합니다.");
      !this.$v.name.required && errors.push(" ");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
      if (!this.$v.password.$dirty) return errors; 
      !validatePassword.test(this.password) && errors.push("영문+숫자+특수기호로 구성하여야 합니다.(8-16자)");
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
      this.nickname.search(/\s/) != -1 &&errors.push("닉네임은 빈 칸을 포함 할 수 없습니다.")
      !this.$v.nickname.maxLength &&
        errors.push("닉네임은 10글자 이내로 입력해야합니다.");
      !this.$v.nickname.required && errors.push(" ");
      return errors;
    },
    emailErrors() {
      const errors = [];
      let reg_required = /.{1,10}/;
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("이메일 형식이 아닙니다.");
      !reg_required.test(this.email) && errors.push("이메일을 입력해주세요.")
      !this.$v.email.required && errors.push(" ");
      return errors;
    },
  },
  methods: {
    // 이메일 중복 확인 및 인증번호 전송
    isCheck() {
      this.emailfailDuple = false;
      http
        .post("/user/email", {
          email: this.email
        })
        .then((response) => {
        if (response.status == 200) {
          this.sendMail = true;
          this.checkmsg = "재전송";
          this.time=300;
          this.rederKey+=1;
        } else {
          this.emailfailDuple = true;
        }
      });
    },
    // 인증번호 확인
    checkMail() {
      this.mailconfirmDuple = false;
      this.mailfailDuple = false;
      http
        .post("/user/emailAuthNumber", {
          email: this.email,
          authNum: this.authNum,
        })
        .then((response) => {
        if ((response.status) == 200) {
          this.mailconfirmDuple = !this.mailconfirmDuple;
          this.check1 = true;
          this.time = false;
        } else {
          this.mailfailDuple = !this.mailfailDuple;
        }
      });
    },
    // 닉네임 중복 확인
    nicknameCheck() {
      this.nicknameDuple = false;
      this.nicknamefailDuple = false;
      http
        .post("/user/nickname", {
          nickname: this.nickname
        })
        .then((response) => {
        if (response.status == 200 && this.nickname != "") {
          this.nicknameDuple = true;
          this.check2 =true;
        } else {
          this.nicknamefailDuple = true;
        }
      });
    },
    tempgo() {
      this.$router.push("/signup/complete");
    },
    submit() {
      this.$v.$touch();
    },
    signup() {
      this.signupfailDuple = false;
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
          } else {
            this.signupfailDuple = true;
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
.base-mailifirm {
  height: 60px;
}
.mailconfim-case {
  margin: 3% 0;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  color: black;
  width: 266px;
  height: 60px;
  margin: 0;
  padding: 0;
}
.mailconfirm-btn {
  color: black;
  width: 60px;
  margin: 0;
  padding: 0;
}
.ment-box {
  text-align: start;
}
.signup-box {
  display: flex;
  flex-direction: column;
  height: 100vh;
  justify-content: flex-start;
  align-items: center;
  
}
.btn-box {
  top: 5px;
  display: flex;
  justify-content: space-evenly;
  align-items: flex-start;
  width: 90%;
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
  left: 190px;
  top: 30px;
  bottom: 3px;
  position: absolute;
  margin: 0px;
  padding: 1%;
  width: 80px;
  height: 30px;
  color: black;
  font-size: 13px;
}
.name-confrim-btn {
  right: 0px;
  left: 190px;
  top: 15px;
  bottom: 3px;
  position: absolute;
  margin: 0px;
  padding: 1%;
  width: 80px;
  height: 30px;
  color: black;
  font-size: 13px;
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
  width: 200px;
  height: 30px;
}
.input-box {
  min-width: 266px;
}
.checkbox {
  margin-left: 10%;
}
</style>
