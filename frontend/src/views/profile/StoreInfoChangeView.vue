<template>
  <div>
    <!--이미지 변경 & 수정-->
    <br />
    <img :src="`${storeDto.storeImgUrl}`" width="200" height="150" />
    <div class="mt-5">
      <p class="d-flex justify-content-end">
        <input v-on:change="fileSelect()" type="file" ref="imgFile" />
      </p>
    </div>
    <br />
    <div>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <!--매장명 -->
        <b-form-group
          class="d-flex justify-content-between"
          id="input-group-1"
          label="매장명"
          label-for="input-1"
        >
          <b-form-input
            id="input-1"
            v-model="storeDto.storeName"
            type="text"
            placeholder="매장명"
            required
          ></b-form-input>
        </b-form-group>
        <br />
        <!--매장전화번호-->
        <b-form-group
          class="d-flex justify-content-between"
          id="input-group-2"
          label="매장전화번호"
          label-for="input-2"
        >
          <b-form-input
            id="input-2"
            v-model="storeDto.tel"
            type="tel"
            placeholder="매장 전화번호"
            required
          ></b-form-input>
        </b-form-group>
        <br />

        <!--매장위치 -->
        <b-form-group
          class="d-flex justify-content-between"
          id="input-group-3"
          label="매장위치"
          label-for="input-3"
        >
          <b-form-input
            id="input-3"
            v-model="storeDto.location"
            type="text"
            placeholder="매장위치"
            required
          ></b-form-input>

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
          <button
            class="border-m radius-m address-btn"
            @click="execDaumPostcode()"
          >
            주소 검색
          </button>
        </b-form-group>
        <br />
        <!--사업자등록번호 -->
        <b-form-group
          class="d-flex justify-content-between"
          id="input-group-4"
          label="사업자등록번호"
          label-for="input-4"
        >
          <b-form-input
            id="input-4"
            v-model="storeDto.storeNum"
            type="text"
            readonly
            placeholder="사업자 등록번호"
            required
          ></b-form-input>
          <br />
        </b-form-group>
        <br />
        <!--매장 종료시간-->
        <b-form-group
          class="d-flex justify-content-between"
          id="input-group-5"
          label="매장 종료 시간"
          label-for="input-5"
        >
          <b-form-input
            id="input-5"
            v-model="storeDto.closingTime"
            type="time"
            placeholder="매장 종료시간"
            required
          ></b-form-input>
          <!-- <b-form-timepicker v-model="value" locale="ko"></b-form-timepicker> -->
        </b-form-group>
        <br />

        <!--매장 휴무일-->
        <!-- <b-form-group
          class="d-flex justify-content-between"
          id="input-group-5"
          label="매장 휴무일"
          label-for="input-5"
        >
          <b-form-input
            id="input-4"
            v-model="storeDto.offDay"
            type="text"
            placeholder="매장 휴무일"
            required
          ></b-form-input>
        </b-form-group> -->

        <!-- -------------휴무일 입력---------------- -->
        <v-select
          :items="days"
          v-model="storeDto.offDay"
          label="휴무일을 입력해주세요."
          multiple
          chips
          required
          @input="$v.storeDto.offDay.$touch()"
          @blur="$v.storeDto.offDay.$touch()"
        ></v-select>

        <!-- ------------카테고리셀렉트 박스----------- -->
        <v-select
          :items="items"
          v-model="storeDto.category"
          label="카테고리를 선택해주세요."
          required
          color="black"
          @input="$v.storeDto.category.$touch()"
          @blur="$v.storeDto.category.$touch()"
        ></v-select>

        <br />
        <!--form 끝-->
        <div class="d-flex justify-content-between">
          <b-button
            type="reset"
            pill
            variant="outline-danger"
            @click="resetStoreDto()"
            >초기화</b-button
          >
          <b-button
            type="submit"
            pill
            variant="outline-success"
            @click="modifyStore()"
            >수정완료</b-button
          >
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "StoreInfoChangeView",
  data() {
    return {
      imgFile: null,
      storeDto: {},
      form: {
        store: "",
        phone: "",
        location: "",
        registernum: "",
        value: "",
      },
      show: true,
      address: "",
      extraAddress: "",
      days: [
        { value: "월", text: "월요일" },
        { value: "화", text: "화요일" },
        { value: "수", text: "수요일" },
        { value: "목", text: "목요일" },
        { value: "금", text: "금요일" },
        { value: "토", text: "토요일" },
        { value: "일", text: "일요일" },
      ],
      items: [
        { value: "KOREA", text: "한식" },
        { value: "JAPAN", text: "일식" },
        { value: "WESTERN", text: "양식" },
        { value: "SNACK", text: "분식" },
        { value: "DESSERT", text: "디저트" },
        { value: "INGREDIENT", text: "식자재" },
      ],
    };
  },
  created() {
    http.get(`/store/${this.$route.params.storeId}`).then((response) => {
      this.storeDto = response.data;
      console.log(this.storeDto);
    });
  },
  methods: {
    fileSelect() {
      this.imgFile = this.$refs.imgFile.files[0];
    },
    resetStoreDto() {
      this.storeDto = {};
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

    modifyStore() {
      console.log(this.storeDto.offday);
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");

      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "storeDto",
        new Blob([JSON.stringify(this.storeDto)], { type: "application/json" })
      );
      http
        .put(`/store/${this.storeDto.storeId}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          if (response.status == 200) {
            alert("가게정보 수정완료");
            this.$router.push({ name: "mypageOwner" });
          }
        });
    },
    onSubmit(event) {
      event.preventDefault();
    },
    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.form.store = "";
      this.form.phone = "";
      this.form.location = "";
      this.form.registernum = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.value = "";
      this.holiday = "";
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>

<style scoped></style>
