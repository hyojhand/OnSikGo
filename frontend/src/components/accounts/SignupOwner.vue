<template>
  <v-stepper v-model="e1" style="top:25px;">
    <v-stepper-items>
      <v-stepper-content
        step="1"
        class="btn-box mt-3"
        black
        outlined
        min-height="450"
      >
        <form @submit.prevent="submit" class="mb-2 el-case">
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
            <button 
              class="border-m radius-m confirm-btn" 
              @click="isCheck()"
              type="button">
              {{ checkmsg }}
            </button>
          </div>
          <div
          v-if="emailDuple" 
          style="color: red; margin-top: 3px;">
          이미 가입된 메일 혹은 잘못된 이메일입니다.</div>
          <!-- ---------인증 메일 보내기------------ -->
          <div v-if="sendMail">
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
                  class="border-m radius-m mailconfirm-btn" 
                  @click="checkMail()"
                  type="button">
                  인증
                </button>
              </div>
            </div>
            <div v-if="emailfailDuple" style="color: red;">인증번호 확인에 실패했습니다.</div>
            <div v-if="mailconfirmDuple" style="color: green;">인증번호 확인이 되었습니다.</div>
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
          <button 
          class="border-m radius-m" 
          @click="e1 = 2"
          v-bind:disabled="check1 == false"
          >
          다음으로</button>
        </div>
      </v-stepper-content>

      <v-stepper-content
        step="2"
        class="btn-box mt-3"
        black
        outlined
        min-height="200"
      >
        <form @submit.prevent="submit" class="mb-2">
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
            <button 
              class="border-m radius-m ownernum-btn" 
              @click="checkOwner()"
              type="button">
              인증
            </button>
          <div v-if="ownercheckDuple" style="color: green;">사업자 번호가 확인 되었습니다.</div>
          <div v-if="ownerfailDuple" style="color: red;">다시 확인해주시길 바랍니다.</div>
          </div>
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

          <!-- -------------전화번호 입력----------- -->
          <v-text-field
            v-model="tel"
            :error-messages="telErrors"
            type="tel"
            label="가게 전화번호를 입력해주세요."
            required
            class="input-box"
            color="black"
            @input="$v.tel.$touch()"
            @blur="$v.tel.$touch()"
          ></v-text-field>

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
              v-bind:disabled="true" 
            ></v-text-field>
            <button 
              class="border-m radius-m address-btn" 
              @click="execDaumPostcode()"
              type="button">
              주소 검색
            </button>
          </div>
            <v-text-field
              v-model="extraAddress"
              label="상세 주소를 입력해주세요."
              required
              class="input-box"
              color="black"
              type="address"
              @input="$v.address.$touch()"
              @blur="$v.address.$touch()"
            ></v-text-field>
        </form>

        <div class="sign-btn">
          <button class="border-m radius-m" @click="e1 = 1">이전으로</button>
          <button 
          class="border-m radius-m" 
          @click="e1 = 3"
          v-bind:disabled="check2 == false"
          >다음으로</button>
        </div>
      </v-stepper-content>

      <v-stepper-content
        step="3"
        class="btn-box mt-3"
        black
        outlined
        min-height="200"
      >
        <form @submit.prevent="submit" class="mb-2">
          <!-- -----------마감시간 입력----------- -->
          <v-text-field
            v-model="end"
            :error-messages="endErrors"
            type="time"
            label="마감시간을 입력해주세요."
            required
            class="input-box"
            color="black"
            @input="$v.end.$touch()"
            @blur="$v.end.$touch()"
          ></v-text-field>

          <!-- -------------휴무일 입력---------------- -->
          <v-select
            :items="days"
            v-model="off"
            label="휴무일을 입력해주세요."
            multiple
            chips

          ></v-select>

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
          <!-- 가게 이미지 등록 -->
          <div style="margin-bottom: 5px;">
            <p style="margin-top:3px; color: rgb(140, 184, 131);">
              <i class="fa-solid fa-image"></i> 가게를 대표할 이미지 파일을 등록해주세요!</p>
            <input @change="fileSelect" type="file"/>
          </div>
        </form>

        <div class="sign-btn">
          <button class="border-m radius-m mt-5" @click="e1 = 2">이전으로</button>
          <button 
          v-if="category != false && imgFile != null"
          class="border-m radius-m" 
          @click="signup()">가입하기</button>
        </div>
        <div v-if="signupfailDuple" style="color:red;">😥 회원가입에 실패했습니다.</div>
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
        <v-stepper-step
          class="status-btn"
          color="success"
          :complete="e1 > 3"
          step="3"
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
import axios from 'axios';
import CountTimer from "@/components/accounts/Timer.vue";
export default {
    components: {
    CountTimer
  },
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
      extraAddress: "",
      tel: "",
      identify: "",
      end: "",
      category: "",
      off: "",
      checkmsg: "메일 인증",
      sendMail: false,
      authNum: "",
      imgFile: null,
      check1: false,
      check2: false,
      check3: false,
      emailDuple: false,
      mailconfirmDuple: false,
      emailfailDuple: false,
      ownercheckDuple: false,
      ownerfailDuple: false,
      signupfailDuple: false,
      items: [
        {value: 'KOREA', text: '한식'},
        {value: 'JAPAN', text: '일식'},
        {value: 'WESTERN', text: '양식'},
        {value: 'SNACK', text: '분식'},
        {value: 'DESSERT', text: '디저트'},
        {value: 'INGREDIENT', text: '식자재'},
      ],
      days: [
        {value: '월', text: '월요일'},
        {value: '화', text: '화요일'},
        {value: '수', text: '수요일'},
        {value: '목', text: '목요일'},
        {value: '금', text: '금요일'},
        {value: '토', text: '토요일'},
        {value: '일', text: '일요일'},
      ],
      time:false,
      rederKey:0,
      ownerDto: [],
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
      var pattern_name = /^[가-힣]{2,10}$/
      if (!this.$v.name.$dirty) return errors;
      this.name.search(/\s/) != -1 &&errors.push("이름은 빈 칸을 포함 할 수 없습니다.")
      !pattern_name.test(this.name)&&errors.push("2글자 이상의 한글 이름을 입력해주세요.");
      !this.$v.name.maxLength && errors.push(" ");
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

    telErrors() {
      const errors = [];
      if (!this.$v.tel.$dirty) return errors;
      var pattern_num = /[0-9]/;
      !(pattern_num.test(this.tel))&&errors.push("숫자만 입력해 주세요.");
      !this.$v.tel.required && errors.push(" ");
      return errors;
    },
    identifyErrors() {
      const errors = [];
      if (!this.$v.identify.$dirty) return errors;
      var pattern_num = /[0-9]/;
      !(pattern_num.test(this.identify))&&errors.push("숫자만 입력해 주세요.");
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
    // 이메일 중복 확인 및 인증 번호 전송
    isCheck() {
      this.emailDuple = false;
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
          this.emailDuple = !this.emailDuple;
        }
      });
    },
    // 인증번호 확인
    checkMail() {
      this.mailconfirmDuple = false;
      this.emailfailDuple = false;
      http
        .post("/user/emailAuthNumber", {
          email: this.email,
          authNum: this.authNum,
        })
        .then((response) => {
        if ((response.status) == 200) {
          this.mailconfirmDuple = !this.mailconfirmDuple;
          this.check1 = true;
          this.time=false;
        } else {
          this.emailfailDuple = !this.emailfailDuple;
        }
      });
    },

    // 사업자 등록번호 인증
    checkOwner() {
      this.ownercheckDuple = false;
      this.ownerfailDuple = false;
      axios.post('https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=%2BA5hdMZjFvEJER4a%2F4qYT0AD4oO2hJdzyUeFv99ZKQnpprgGdTATL6XcUvXcvv0StLZAgpe9CvB8gVD03bS72Q%3D%3D&returnType=JSON', {
        b_no: [this.identify]
      })
      .then((response) => {
        if (response.data.match_cnt == 1) {
          this.ownercheckDuple = !this.ownercheckDuple;
          this.check2 = true;
        } else {
          this.ownerfailDuple = !this.ownerfailDuple;
        }
      })
      .catch(err => {
        console.log(err);
      });
    },

    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }
  
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
        },
      }).open();
    },    

    // 이미지 파일 업로드
    fileSelect(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.previewImg = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImg = null;
      }
      this.imgFile = input.files[0];
    },

    signup() {
      this.signupfailDuple = false;
      this.ownerDto = {
        email: this.email,
        password: this.password,
        userName: this.name,
        role: this.role,
        storeName: this.store,
        address: this.address,
        extraAddress: this.extraAddress,
        tel: this.tel,
        storeNum: this.identify,
        closingTime: this.end,
        offDay: this.off ? this.off.join() : "연중무휴",
        category: this.category,
      };
      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "ownerDto",
        new Blob([JSON.stringify(this.ownerDto)], { type: "application/json" })
      );
      http
        .post("/user/signup/owner", formData)
        .then((response) => {
          if (response.status == 200) {
          this.$router.push("/signup/complete");
          } else {
            this.signupfailDuple = true;
          }
        })
        
      
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
  right: 0px;
  left: 183px;
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
.ownernum-btn{
  right: 0px;
  left: 200px;
  top: 10px;
  bottom: 3px;
  position: absolute;
  margin: 0px;
  padding: 1%;
  width: 60px;
  height: 30px;
  color: black;
  font-size: 13px;
}
.address-btn {
  right: 0px;
  left: 190px;
  top: 10px;
  bottom: 3px;
  position: absolute;
  margin: 0px;
  padding: 1%;
  width: 70px;
  height: 30px;
  color: black;
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
