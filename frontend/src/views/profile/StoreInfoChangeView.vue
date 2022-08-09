<template>
  <div>
    <!--이미지 변경 & 수정-->
    <br />
    <b-img :src="previewImg" height="150px" width="200px" />
    <div class="mt-5">
      <p class="d-flex justify-content-end">
        <input @change="fileSelect" type="file" />
      </p>
    </div>
    <br />
    <div>
      <b-form @submit="onSubmit" v-if="show">
        <!--매장명 -->
        <div class="ml-5 mr-4 mt-3">
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
        </div>
        <!--매장전화번호-->
        <div class="ml-5 mr-4 mt-3">
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
        </div>
        <br />

        <!--매장위치 -->
        <div class="ml-5 mr-4 mt-3">
          <b-form-group
            class="d-flex justify-content-between"
            id="input-group-3"
            label="매장위치"
            label-for="input-3"
          >
            <span style="color: black" class="text-start"
              >{{ this.storeDto.address }}<br />
              {{ this.storeDto.extraAddress }}</span
            >
            <br /><br />
            <span id="red-small"
              >가게 주소를 변경하기 위해서는 <br />아래의 "주소 검색" 버튼을
              눌러주세요</span
            >

            <div class="d-flex justify-content-end mt-3">
              <button
                class="border-m radius-m address-btn"
                @click="execDaumPostcode()"
              >
                주소 검색
              </button>
            </div>
            <!-- -----------가게 주소 입력-------------- -->
            <div class="position-box">
              <v-text-field
                v-model="this.address1"
                label="가게 주소를 입력해주세요."
                class="input-box"
                type="address"
                v-bind:disabled="true"
              ></v-text-field>
            </div>

            <v-text-field
              v-model="this.extraAddress1"
              label="상세 주소를 입력해주세요."
              class="input-box"
              type="address"
            ></v-text-field>
          </b-form-group>
        </div>
        <br />
        <!--사업자등록번호 -->
        <div class="ml-5 mr-4 mt-3">
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
              placeholder="사업자 등록번호"
              required
            ></b-form-input>
            <br />
          </b-form-group>
          <div class="container">
            <div class="row">
              <div class="col-6">
                <div v-if="ownercheckDuple" id="red-small">
                  사업자 번호가 확인 되었습니다.
                </div>
                <div v-if="ownerfailDuple" id="red-small">
                  다시 확인해주시길 바랍니다.
                </div>
              </div>
              <div class="col-6">
                <div class="d-flex justify-content-end">
                  <button
                    type="button"
                    @click="ownerNumcheck"
                    class="border-m radius-m address-btn"
                  >
                    사업자 등록번호 확인
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <br />
        <!--매장 종료시간-->
        <div class="ml-5 mr-4 mt-3">
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
        </div>
        <br />

        <!-- -------------휴무일 입력---------------- -->
        <div class="ml-5 mr-4 mt-3">
          <v-select
            :items="days"
            v-model="off"
            label="휴무일을 입력해주세요."
            multiple
            chips
            required
          ></v-select>
        </div>
        <!-- ------------카테고리셀렉트 박스----------- -->
        <div class="ml-5 mr-4 mt-3">
          <v-select
            :items="items"
            v-model="storeDto.category"
            label="카테고리를 선택해주세요."
            required
            color="black"
          ></v-select>
        </div>
        <br />
        <!--form 끝-->
        <div class="ml-5 mr-4 mt-3 mb-16">
          <div class="d-flex justify-content-end">
            <button
              type="submit"
              class="border-m radius-m address-btn"
              @click="modifyStore()"
            >
              수정완료
            </button>
          </div>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import http from "@/util/http-common";
import axios from "axios";
export default {
  name: "StoreInfoChangeView",
  data() {
    return {
      imgFile: null,
      storeDto: {},
      off: [],
      address1: "",
      extraAddress1: "",
      previewImg: null,
      show: true,
      ownercheckDuple: false,
      ownerfailDuple: false,
      numCheck: true,
      offDaylist: [],
      realoffDayList: "",
      days: [
        { value: "월요일", text: "월요일" },
        { value: "화요일", text: "화요일" },
        { value: "수요일", text: "수요일" },
        { value: "목요일", text: "목요일" },
        { value: "금요일", text: "금요일" },
        { value: "토요일", text: "토요일" },
        { value: "일요일", text: "일요일" },
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
      this.previewImg = this.storeDto.storeImgUrl;
      console.log(this.storeDto);
      this.storeDto.offDay.split(",").map((day) => {
        var temp = {
          value: day,
          text: day,
        };
        this.off.push({ ...temp });
      });
    });
  },
  methods: {
    ...mapActions("offdayStore", ["storeOffday"]),
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
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress1 !== "") {
            this.extraAddress1 = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address1 = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address1 = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress1 += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress1 +=
                this.extraAddress1 !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress1 !== "") {
              this.extraAddress1 = `(${this.extraAddress1})`;
            }
          } else {
            this.extraAddress1 = "";
          }
        },
      }).open();
    },
    ownerNumcheck() {
      axios
        .post(
          "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=%2BA5hdMZjFvEJER4a%2F4qYT0AD4oO2hJdzyUeFv99ZKQnpprgGdTATL6XcUvXcvv0StLZAgpe9CvB8gVD03bS72Q%3D%3D&returnType=JSON",
          {
            b_no: [this.storeDto.storeNum],
          }
        )
        .then((response) => {
          if (response.data.match_cnt == 1) {
            this.ownercheckDuple = true;
            this.ownerfailDuple = false;
            this.numCheck = true;
          } else {
            this.ownercheckDuple = false;
            this.ownerfailDuple = true;
            this.numCheck = false;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    modifyStore() {
      if (this.address1 != "" && this.extraAddress1 != "") {
        this.storeDto.address = this.address1;
        this.storeDto.extraAddress = this.extraAddress1;
      }
      // console.log(this.storeDto.address);
      // console.log(this.storeDto.extraAddress);

      this.storeDto.offDay = this.off.join;
      // console.log(this.storeDto.offDay);
      if (this.numCheck == true) {
        http.defaults.headers["access-token"] =
          localStorage.getItem("access-token");

        var selected = [];
        if (this.off.length > 0 && this.off[0].value != undefined) {
          this.off.map((day) => {
            selected.push(day.value);
          });
          this.storeDto.offDay = selected.join(",");
        } else {
          this.storeDto.offDay = Array.from(this.off).join(",");
        }

        // 휴무일 정렬
        if (this.storeDto.offDay.length >= 5) {
          this.storeDto.offDay.split(",").map((day) => {
            this.offDaylist.push(day);
          });
          const daySorter = {
            월요일: 1,
            화요일: 2,
            수요일: 3,
            목요일: 4,
            금요일: 5,
            토요일: 6,
            일요일: 7,
          };
          this.offDaylist.sort(function sortBydaySorter(a, b) {
            return daySorter[a] - daySorter[b];
          });
          this.realoffDayList = this.offDaylist.join();
        } else {
          this.realoffDayList = this.storeDto.offDay;
        }
        console.log(this.realoffDayList);
        this.storeOffday(this.realoffDayList);

        this.storeOffday(this.storeDto.offDay);
        const formData = new FormData();
        formData.append("file", this.imgFile);
        formData.append(
          "storeDto",
          new Blob([JSON.stringify(this.storeDto)], {
            type: "application/json",
          })
        );
        console.log(formData);
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
      } else {
        alert("사업자 등록번호를 정확하게 입력해주세요");
      }
    },
    onSubmit(event) {
      event.preventDefault();
    },
  },
};
</script>

<style scoped>
#red-small {
  color: rgb(222, 124, 39);
  font-size: 0.75rem;
}
</style>
