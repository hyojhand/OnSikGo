<template>
  <div>
    <form>
      <div class="mt-5">
      <b-img :src="previewImg" height="200px" width="200px" />
        <p class="d-flex justify-content-end">
          <input @change="fileSelect" type="file" />        
        </p>
      </div>

      <!-- ------상호명 입력--------------- -->
      <div class="ml-3 mr-4 mt-10">
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
      </div>

      <!-- -----------가게 주소 입력-------------- -->
      <div class="container">
        <div class="row">
          <div class="col-9">
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
          </div>
          <div class="col-3">
            <button
              class="border-l radius-m address-btn"
              @click="execDaumPostcode()"
            >
              주소 검색
            </button>
          </div>
        </div>
      </div>
      <div class="ml-3 mr-4 mt-3">
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
      </div>

      <!-- --------------사업자 등록번호 입력------------ -->
      <div class="container">
        <div class="row">
          <div class="col-9">
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
          </div>
          <div class="col-3">
            <button class="border-l radius-m address-btn" @click="checkOwner()">
              번호 인증
            </button>
          </div>
        </div>
      </div>

      <!-- -------------전화번호 입력----------- -->
      <div class="ml-3 mr-4 mt-3">
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
      </div>

      <!-- -----------마감시간 입력----------- -->
      <div class="ml-3 mr-4 mt-3">
        <v-text-field
          v-model="end"
          type="time"
          label="마감시간을 입력해주세요."
          required
          class="input-box"
          color="black"
          @input="$v.end.$touch()"
          @blur="$v.end.$touch()"
        ></v-text-field>
      </div>

      <!-- -------------휴무일 입력---------------- -->
      <div class="ml-3 mr-4 mt-3">
        <v-select
          :items="days"
          v-model="off"
          label="휴무일을 입력해주세요."
          multiple
          chips
          @input="$v.off.$touch()"
          @blur="$v.off.$touch()"
        ></v-select>
      </div>

      <!-- ------------카테고리셀렉트 박스----------- -->
      <div class="ml-3 mr-4 mt-3">
        <v-select
          :items="items"
          v-model="category"
          label="카테고리를 선택해주세요."
          required
          color="black"
          @input="$v.category.$touch()"
          @blur="$v.category.$touch()"
        ></v-select>
      </div>
    </form>
    <div class="ml-3 mr-4 mt-3 mb-10">
      <div class="d-flex justify-content-end">
        <button class="border-m radius-m" @click="registerStore">
          매장 추가하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "storeAddView",
  data() {
    return {
      imgFile: null,
      previewImg:null,
      store: "",
      address: "",
      tel: "",
      identify: "",
      end: "",
      off: "",
      category: "",
      items: [
        { value: "KOREA", text: "한식" },
        { value: "JAPAN", text: "일식" },
        { value: "WESTERN", text: "양식" },
        { value: "SNACK", text: "분식" },
        { value: "DESSERT", text: "디저트" },
        { value: "INGREDIENT", text: "식자재" },
      ],
      days: [
        { value: "월요일", text: "월요일" },
        { value: "화요일", text: "화요일" },
        { value: "수요일", text: "수요일" },
        { value: "목요일", text: "목요일" },
        { value: "금요일", text: "금요일" },
        { value: "토요일", text: "토요일" },
        { value: "일요일", text: "일요일" },
      ],
      storeDto: [],
    };
  },

  // computed: {
  //   storeErrors() {
  //     const errors = [];
  //     if (!this.$v.store.$dirty) return errors;
  //     !this.$v.store.required && errors.push(" ");
  //     return errors;
  //   },

  //   telErrors() {
  //     const errors = [];
  //     if (!this.$v.tel.$dirty) return errors;
  //     !this.$v.tel.required && errors.push(" ");
  //     return errors;
  //   },
  //   identifyErrors() {
  //     const errors = [];
  //     if (!this.$v.identify.$dirty) return errors;
  //     !this.$v.identify.required && errors.push(" ");
  //     return errors;
  //   },
  // },

  methods: {
    fileSelect(event) {
      var input = event.target;

      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = e => {
          this.previewImg = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImg = null;
      }

      this.imgFile = input.files[0];

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

    checkOwner() {
      alert("사업자 번호 등록");
    },

    registerStore() {
      // 이미지 파일도 추가하게 넣어야함
      this.storeDto = {
        storeName: this.store,
        location: this.address,
        tel: this.tel,
        storeNum: this.identify,
        closingTime: this.end,
        offDay: this.off.join(),
        category: this.category,
      };
      console.log(this.storeDto);
      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "storeDto",
        new Blob([JSON.stringify(this.storeDto)], { type: "application/json" })
      );
      console.log(formData);
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http
        .post("/store/register", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          if (response.status == 200) {
            alert("매장이 추가 완료되었습니다");
            this.$router.push("/mypage/owner");
          } else {
            alert("매장 추가가 완료되지 않았습니다.");
          }
        });
    },
  },
};
</script>

<style></style>
