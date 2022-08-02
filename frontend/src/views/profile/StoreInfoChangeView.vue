<template>
  <div>
    <!--이미지 변경 & 수정-->
    <br />
    <img :src="`${storeDto.storeImgUrl}`" />
    <p>이미지 변경하는 아이콘</p>
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
            v-model="this.storeDto.storeName"
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
            v-model="this.storeDto.tel"
            type="text"
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
            v-model="this.storeDto.location"
            type="text"
            placeholder="매장위치"
            required
          ></b-form-input>
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
            v-model="this.storeDto.storeNum"
            type="text"
            placeholder="사업자 등록번호"
            required
          ></b-form-input>
          <br />
          <div class="d-flex justify-content-end">
            <button class="btn btn-success">인증</button>
          </div>
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
            id="input-4"
            v-model="this.storeDto.closingTime"
            type="text"
            placeholder="매장 종료시간"
            required
          ></b-form-input>
          <!-- <b-form-timepicker v-model="value" locale="ko"></b-form-timepicker> -->
        </b-form-group>
        <br />

        <!--매장 휴무일-->
        <b-form-group
          class="d-flex justify-content-between"
          id="input-group-5"
          label="매장 휴무일"
          label-for="input-5"
        >
          <b-form-input
            id="input-4"
            v-model="this.storeDto.offDay"
            type="text"
            placeholder="매장 휴무일"
            required
          ></b-form-input>
        </b-form-group>

        <!--매장 휴무일 INPUT BOX -->
        <!-- <v-app id="inspire">
          <v-card>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-select
                    v-model="this.storeDto.offDay"
                    :items="items"
                    attach
                    chips
                    label="매장 휴무일"
                    multiple
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>
          </v-card>
        </v-app>  -->
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
      storeDto: {},
      form: {
        store: "",
        phone: "",
        location: "",
        registernum: "",
        value: "",
      },
      show: true,
      items: [
        "월요일",
        "화요일",
        "수요일",
        "목요일",
        "금요일",
        "토요일",
        "일요일",
      ],
      values: [
        "월요일",
        "화요일",
        "수요일",
        "목요일",
        "금요일",
        "토요일",
        "일요일",
      ],
    };
  },
  created() {
    http.get(`/store/${this.$route.params.storeId}`).then((response) => {
      console.log(this.$route.params.storeId)
      this.storeDto = response.data;
      console.log(this.storeDto);
    });
  },
  methods: {
    resetStoreDto() {
      this.storeDto = {};
    },
    modifyStore() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");

      const formData = new FormData();
      formData.append("file", this.storeDto.storeImgUrl);
      formData.append(
        "storeDto",
        new Blob([JSON.stringify(this.storeDto), { type: "application/json" }])
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
            this.$router.push("mypageOwner");
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
