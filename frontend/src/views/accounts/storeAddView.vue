<template>
  <div>
    <form>
      <div class="mt-8">
        <b-img :src="previewImg" height="200px" width="200px" />
        <p class="d-flex justify-content-end">
          <input @change="fileSelect" type="file" />
        </p>
        <span style="color: rgb(222, 124, 39); font-size: 0.8rem"
          >* 이미지 파일은 매장 추가 시 필수입니다. *</span
        >
      </div>

      <!-- ------상호명 입력--------------- -->
      <div class="mt-8">
        <div class="row">
          <div class="col-3 ml-1 mt-5 d-flex justify-content-center">
            <span>매장명</span>
          </div>
          <div class="col-8 mr-2">
            <v-text-field
              v-model="store"
              label="상호명을 입력해주세요."
              required
              class="input-box"
              color="black"
            ></v-text-field>
          </div>
        </div>
      </div>

      <!-- -----------가게 주소 입력-------------- -->

      <div class="row">
        <div class="col-3 d-flex justify-content-end">
          <span>매장 주소</span>
        </div>
        <div class="col-9">
          <div class="d-flex justify-content-end mr-8">
            <button id="button-address" @click="execDaumPostcode()">
              주소 검색
            </button>
          </div>
          <br />
          <span style="color: rgb(222, 124, 39); font-size: 0.8rem"
            >* 주소 검색을 클릭하여 주소를 선택해주세요 *</span
          >
          <div class="mr-6">
            <v-text-field
              v-model="address"
              label="가게 주소를 입력해주세요."
              required
              class="input-box"
              color="black"
              type="address"
            ></v-text-field>
            <v-text-field
              v-model="extraAddress"
              label="상세 주소를 입력해주세요."
              required
              class="input-box"
              color="black"
              type="address"
            ></v-text-field>
          </div>
        </div>
      </div>

      <!-- --------------사업자 등록번호 입력------------ -->

      <div class="mt-8">
        <div class="row">
          <div class="col-3 ml-1 mt-5 d-flex justify-content-center">
            <span>사업자번호</span>
          </div>
          <div class="col-8 mr-2">
            <v-text-field
              v-model="identify"
              label="사업자번호를 입력해주세요."
              required
              class="input-box"
              color="black"
            ></v-text-field>
          </div>
          <div class="row">
            <div class="col-8 d-flex justify-content-end">
              <div
                style="color: #34a623; font-size: 0.8rem"
                v-if="ownercheckDuple"
              >
                사업자 번호가 확인 되었습니다.
              </div>
              <div
                style="color: rgb(222, 124, 39); font-size: 0.8rem"
                v-if="ownerfailDuple"
              >
                다시 확인해주시길 바랍니다.
              </div>
            </div>
            <div class="col-4">
              <div class="d-flex justify-content-end">
                <button
                  @click="ownerNumcheck"
                  class="mr-4 mb-3"
                  id="button-add"
                  type="button"
                >
                  번호인증
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- -------------전화번호 입력----------- -->
      <div class="row">
        <div class="col-3 ml-2 mt-5 d-flex justify-content-center">
          <span>전화번호</span>
        </div>
        <div class="col-8 mr-2">
          <v-text-field
            v-model="tel"
            type="tel"
            label="가게 전화번호를 입력해주세요."
            required
            class="input-box"
            color="black"
          ></v-text-field>
        </div>
      </div>

      <!-- -----------마감시간 입력----------- -->
      <div class="row">
        <div class="col-3 ml-2 mt-5 d-flex justify-content-center">
          <span>마감시간</span>
        </div>
        <div class="col-8 mr-2">
          <v-select
            :items="days"
            v-model="off"
            label="휴무일을 입력해주세요."
            multiple
            chips
          ></v-select>
        </div>
      </div>

      <!-- -------------휴무일 입력---------------- -->
      <div class="row">
        <div class="col-3 ml-2 mt-5 d-flex justify-content-center">
          <span>휴무일</span>
        </div>
        <div class="col-8">
          <v-select
            :items="days"
            v-model="off"
            label="휴무일을 입력해주세요."
            multiple
            chips
          ></v-select>
          <span style="color: rgb(222, 124, 39); font-size: 0.8rem"
            >* 연중휴무 매장이라면 선택하지 않으셔도 됩니다. *</span
          >
        </div>
      </div>

      <!-- ------------카테고리셀렉트 박스----------- -->
      <div class="row">
        <div class="col-3 mt-5 d-flex ml-2 justify-content-center">
          <span>카테고리</span>
        </div>
        <div class="col-8">
          <v-select
            :items="items"
            v-model="category"
            label="카테고리를 선택해주세요."
            required
            color="black"
          ></v-select>
        </div>
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
import axios from "axios";
export default {
  name: "storeAddView",
  data() {
    return {
      imgFile: null,
      previewImg: null,
      store: "",
      address: "",
      extraAddress: "",
      tel: "",
      identify: "",
      end: "",
      off: "",
      category: "",
      ownercheckDuple: false,
      ownerfailDuple: false,
      numCheck: true,
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
  methods: {
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
    ownerNumcheck() {
      axios
        .post(
          "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=%2BA5hdMZjFvEJER4a%2F4qYT0AD4oO2hJdzyUeFv99ZKQnpprgGdTATL6XcUvXcvv0StLZAgpe9CvB8gVD03bS72Q%3D%3D&returnType=JSON",
          {
            b_no: [this.identify],
          }
        )
        .then((response) => {
          console.log(response.data);
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
    registerStore() {
      this.storeDto = {
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
        "storeDto",
        new Blob([JSON.stringify(this.storeDto)], { type: "application/json" })
      );
      if (this.numCheck == true) {
        http.defaults.headers["access-token"] =
          localStorage.getItem("access-token");
        console.log(this.storeDto);
        http
          .post("/store/register", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((response) => {
            if (response.status == 200) {
              console.log(this.storeDto);
              alert("매장이 추가 완료되었습니다");
              this.$router.push("/mypage/owner");
            } else {
              alert("매장 추가가 완료되지 않았습니다.");
            }
          });
      } else {
        alert("사업자 등록번호를 다시 확인해주세요");
      }
    },
  },
};
</script>

<style>
#button-address {
  margin: 0px 0px;
  border: 2px solid black;
  width: 40%;
  border-radius: 12px;
  color: black;
}
#button-add {
  margin: 0px 0px;
  border: 2px solid black;
  width: 100%;
  border-radius: 12px;
  color: black;
}
</style>
