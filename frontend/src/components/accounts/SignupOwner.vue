<template>
  <v-stepper v-model="e1">
    <v-stepper-items>
      <v-stepper-content
        step="1"
        class="btn-box mt-3"
        black
        outlined
        min-height="450"
      >
        <form class="mb-2 el-case">
          <!-- 메일 입력하기 -->
          <div class="position-box">
            <v-text-field
              v-model="email"
              :error-messages="emailErrors"
              label="이메일을 입력해주세요."
              required
              class="input-box mt-5"
              color="black"
              @input="$v.email.$touch()"
              @blur="$v.email.$touch()"
            ></v-text-field>
            <button class="border-m radius-m confirm-btn" @click="isCheck()">
              {{ checkmsg }}
            </button>
          </div>
          <!-- ---------인증 메일 보내기------------ -->
          <div v-if="sendMail">
            <div class="mailconfim-case">
              <input
                id="mail-confirm"
                class="mail-confirm"
                v-model="authNum"
                placeholder="인증번호를 입력하세요."
              />
              <button class="border-m radius-m mailconfirm-btn">
                확인하기
              </button>
            </div>
          </div>
          <!-- -------------비밀번호 입력------------------------------------ -->
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
            @blur="$v.passwordConfirm.$touch()"
          ></v-text-field>

          <!-- ----------사용자 이름 입력----------- -->
          <v-text-field
            v-model="name"
            :error-messages="nameErrors"
            label="이름을 입력해주세요."
            required
            class="input-box"
            color="black"
            @input="$v.name.$touch()"
            @blur="$v.name.$touch()"
          ></v-text-field>
        </form>
        <div class="next-btn">
          <button class="border-m radius-m" @click="e1 = 2">다음으로</button>
        </div>
      </v-stepper-content>

      <v-stepper-content
        step="2"
        class="btn-box mt-3"
        black
        outlined
        min-height="200"
      >
        <form class="mb-2">
          <!-- ------상호명 입력--------------- -->
          <v-text-field
            v-model="store"
            :error-messages="storeErrors"
            label="상호명을 입력해주세요."
            required
            class="input-box"
            color="black"
            @input="$v.store.$touch()"
            @blur="$v.store.$touch()"
          ></v-text-field>
          <!-- -----------가게 주소 에러 생략----------------- -->
          <!-- <v-text-field
            v-model="address"
            :error-messages="adressErrors"
            label="가게 주소를 입력해주세요."
            required
            class="input-box"
            color="black"
            type="address"
            @input="$v.address.$touch()"
            @blur="$v.address.$touch()"
          ></v-text-field> -->

          <!-- -----------가게 주소 입력-------------- -->
          <div class="position-box">
            <v-text-field
              v-model="address"
              label="가게 주소를 입력해주세요."
              required
              class="input-box"
              color="black"
              type="address"
              @input="$v.address.$touch()"
              @blur="$v.address.$touch()"
            ></v-text-field>
            <button class="border-m radius-m address-btn" @click="tempAlert">
              주소 검색하기
            </button>
          </div>

          <!-- -------------전화번호 입력----------- -->
          <v-text-field
            v-model="tel"
            :error-messages="telErrors"
            label="가게 전화번호를 입력해주세요."
            required
            class="input-box"
            color="black"
            @input="$v.tel.$touch()"
            @blur="$v.tel.$touch()"
          ></v-text-field>

          <!-- --------------사업자 등록번호 입력------------ -->
          <div class="position-box">
            <v-text-field
              v-model="identify"
              :error-messages="identifyErrors"
              label="사업자번호를 입력해주세요."
              required
              class="input-box"
              color="black"
              @input="$v.identify.$touch()"
              @blur="$v.identify.$touch()"
            ></v-text-field>
            <button class="border-m radius-m address-btn" @click="tempAlert">
              등록하기
            </button>
          </div>
          <!-- -----------마감시간 입력----------- -->
          <v-text-field
            v-model="end"
            :error-messages="endErrors"
            label="마감시간을 입력해주세요."
            required
            class="input-box"
            color="black"
            @input="$v.end.$touch()"
            @blur="$v.end.$touch()"
          ></v-text-field>

          <!-- -------------휴무일 입력---------------- -->
          <v-text-field
            v-model="off"
            label="휴무일을 입력해주세요."
            color="black"
          ></v-text-field>

          <!-- ------------카테고리셀렉트 박스----------- -->
          <v-select
            :items="items"
            v-model = "category"
            label="카테고리를 선택해주세요."
            required
            color="black"
            @input = "$v.category.$touch()"
            @blur= "$v.category.$touch()"
          ></v-select>
        </form>

        <div class="sign-btn">
          <button class="border-m radius-m" @click="e1 = 1">이전으로</button>
          <button class="border-m radius-m" @click="signup()">가입하기</button>
        </div>
      </v-stepper-content>
      <v-stepper-header class="status-box">
        <v-stepper-step
          class="status-btn"
          color="success"
          :complete="e1 > 1"
          step="1"
        >
        </v-stepper-step>
        <v-stepper-step
          class="status-btn"
          color="success"
          :complete="e1 > 2"
          step="2"
        >
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, maxLength, email } from "vuelidate/lib/validators";
import minLength from "vuelidate/lib/validators/minLength";
import http from "@/util/http-common";

export default {
  mixins: [validationMixin],
  name: "SignupOwner",
  data() {
    return {
      e1: 1,
      name: "",
      email: "",
      password: "",
      passwordConfirm: "",
      role: "OWNER",
      store: "",
      address: "",
      tel: "",
      identify: "",
      end: "",
      off: "",
      category: "",
      checkmsg: "메일 인증하기",
      sendMail: false,
      authNum: Number,
      items: [
        {value: 'KOREA', text: '한식'},
        {value: 'JAPAN', text: '일식'},
        {value: 'WESTERN', text: '양식'},
        {value: 'SNACK', text: '분식'},
        {value: 'DESSERT', text: '디저트'},
        {value: 'INGREDIENT', text: '식자재'},
      ]
    };
  },

  validations: {
    email: { required, email },
    password: { required, minLength: minLength(8) },
    passwordConfirm: { required, minLength: minLength(8) },
    name: { required, maxLength: maxLength(10) },
    number: { required },
    store: { required },
    address: { required },
    tel: { required },
    identify: { required },
    end: { required },
    category: { required },
  },

  computed: {
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.maxLength && errors.push(" ");
      !this.$v.name.required && errors.push(" ");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.minLength && errors.push(" ");
      !this.$v.password.required && errors.push(" ");
      return errors;
    },
    passwordConfirmErrors() {
      const errors = [];
      if (this.password == this.passwordConfirm) return errors;
      errors.push("비밀번호가 일치하지 않습니다.");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push(" ");
      !this.$v.email.required && errors.push(" ");
      return errors;
    },
    storeErrors() {
      const errors = [];
      if (!this.$v.store.$dirty) return errors;
      !this.$v.store.required && errors.push(" ");
      return errors;
    },
    // addressErrors() {
    //   const errors = [];
    //   if (!this.$v.address.$dirty) return errors;
    //   !this.$v.address.required && errors.push(" ");
    //   return errors;
    // },
    telErrors() {
      const errors = [];
      if (!this.$v.tel.$dirty) return errors;
      !this.$v.tel.required && errors.push(" ");
      return errors;
    },
    identifyErrors() {
      const errors = [];
      if (!this.$v.identify.$dirty) return errors;
      !this.$v.identify.required && errors.push(" ");
      return errors;
    },
    endErrors() {
      const errors = [];
      if (!this.$v.end.$dirty) return errors;
      !this.$v.end.required && errors.push(" ");
      return errors;
    },
    categoryErrors() {
      const errors = [];
      if (!this.$v.category.$dirty) return errors;
      !this.$v.category.required && errors.push(" ");
      return errors;
    },
  },
  methods: {
    tempAlert() {
      alert("뭐가 뜰겁니다");
    },
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
    signup() {
      http.post("/user/signup/owner", {
        email: this.email,
        password: this.password,
        userName: this.name,
        role: this.role,
        storeName: this.store,
        location: this.address,
        tel: this.tel,
        storeNum: this.identify,
        closingTime: this.end,
        offDay: this.off,
        category: this.category,
      });
      this.$router.push("/signup/complete");
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
.btn-box {
  min-width: 344px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.status-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.status-btn {
  padding: 0;
}
.next-btn {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.sign-btn {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.position-box {
  position: relative;
}
.confirm-btn {
  position: absolute;
  color: black;
  right: 0;
  top: 32px;
  font-size: 13px;
}
.address-btn {
  position: absolute;
  color: black;
  right: 0;
  top: 12px;
  font-size: 13px;
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
.mail-confirm {
  color: black;
  border-bottom: 1px solid rgba(0, 0, 0, 30%);
}
.input-box {
  min-width: 266px;
}
</style>
