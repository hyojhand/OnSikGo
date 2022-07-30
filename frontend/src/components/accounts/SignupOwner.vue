<template>
  <v-stepper v-model="e1">
    <v-stepper-items>
      <v-stepper-content
        step="1"
        class="btn-box mt-3"
        black
        outlined
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
            :counter="8"
            label="비밀번호를 입력해주세요."
            required
            type="password"
            @input="$v.password.$touch()"
            @blur="$v.password.$touch()"
          ></v-text-field>

          <v-text-field
            v-model="name"
            :error-messages="nameErrors"
            label="사용자명을 입력해주세요."
            required
            @input="$v.name.$touch()"
            @blur="$v.name.$touch()"
          ></v-text-field>
        </form>

        <v-btn color="success" @click="e1 = 2"> 다음으로 </v-btn>
      </v-stepper-content>

      <v-stepper-content
        step="2"
        class="btn-box mt-3"
        black
        outlined
        min-height="200"
      >
        <form class="mb-2">
          <v-text-field
            v-model="store"
            :error-messages="storeErrors"
            label="상호명을 입력해주세요."
            required
            @input="$v.store.$touch()"
            @blur="$v.store.$touch()"
          ></v-text-field>

          <v-text-field
            v-model="address"
            :error-messages="adressErrors"
            label="가게 주소를 입력해주세요."
            required
            type="address"
            @input="$v.address.$touch()"
            @blur="$v.address.$touch()"
          ></v-text-field>

          <v-text-field
            v-model="tel"
            :error-messages="telErrors"
            label="가게 전화번호를 입력해주세요."
            required
            @input="$v.tel.$touch()"
            @blur="$v.tel.$touch()"
          ></v-text-field>

          <v-text-field
            v-model="identify"
            :error-messages="identifyErrors"
            label="사업자 등록번호를 입력해주세요."
            required
            @input="$v.identify.$touch()"
            @blur="$v.identify.$touch()"
          ></v-text-field>
          <v-text-field
            v-model="end"
            :error-messages="endErrors"
            label="마감시간을 입력해주세요."
            required
            @input="$v.end.$touch()"
            @blur="$v.end.$touch()"
          ></v-text-field>
          <v-text-field
            v-model="off"
            label="휴무일을 입력해주세요."
          ></v-text-field>
          <v-text-field
            v-model="category"
            :error-messages="categoryErrors"
            label="카테고리를 선택해주세요."
            required
            @input="$v.category.$touch()"
            @blur="$v.category.$touch()"
          ></v-text-field>
        </form>

        <v-btn color="success" class="mx-5" @click="signup()"> 다음으로 </v-btn>
        <v-btn text @click="e1 = 1"> 이전으로 </v-btn>
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
      role: "OWNER",
      store: "",
      address: "",
      tel: "",
      identify: "",
      end: "",
      off: "",
      category: "",
    };
  },

  validations: {
    email: { required, email },
    password: { required, minLength: minLength(8) },
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
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("이메일 형식이 아닙니다.");
      !this.$v.email.required && errors.push("이메일을 입력해주세요.");
      return errors;
    },
    storeErrors() {
      const errors = [];
      if (!this.$v.store.$dirty) return errors;
      !this.$v.store.required && errors.push("상호명을 입력해주세요.");
      return errors;
    },
    addressErrors() {
      const errors = [];
      if (!this.$v.address.$dirty) return errors;
      !this.$v.address.required && errors.push("가게주소를 입력해주세요.");
      return errors;
    },
    telErrors() {
      const errors = [];
      if (!this.$v.tel.$dirty) return errors;
      !this.$v.tel.required && errors.push("전화번호를 입력해주세요.");
      return errors;
    },
    identifyErrors() {
      const errors = [];
      if (!this.$v.identify.$dirty) return errors;
      !this.$v.identify.required && errors.push("사업장 번호를 입력해주세요.");
      return errors;
    },
    endErrors() {
      const errors = [];
      if (!this.$v.end.$dirty) return errors;
      !this.$v.end.required && errors.push("마감시간을 입력해주세요.");
      return errors;
    },
    categoryErrors() {
      const errors = [];
      if (!this.$v.category.$dirty) return errors;
      !this.$v.category.required && errors.push("카테고리를 선택해주세요.");
      return errors;
    },
  },
  methods: {
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
.btn-box {
  min-width: 344px;
}
.status-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.status-btn {
  padding: 0;
}
</style>
