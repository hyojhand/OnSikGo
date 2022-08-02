<template>
  <div>
    <form>
    <div class="mt-5">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="250"
        height="250"
        fill="currentColor"
        class="bi bi-image"
        viewBox="0 0 16 16"
      >
        <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
        <path
          d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"
        />
      </svg>
      <p class="d-flex justify-content-end">
        <input v-on:change="fileSelect()" type="file" ref="imgFile" />
      </p>
    </div>
    
      <v-text-field
            v-model="store"
            label="상호명을 입력해주세요."
            required
            class="input-box"
            color="black"
          ></v-text-field>


            <v-text-field
              v-model="address"
              label="가게 주소를 입력해주세요."
              required
              class="input-box"
              color="black"
              type="address"
            ></v-text-field>
            <button class="border-m radius-m address-btn">
              주소 검색하기
            </button>

          <!-- -------------전화번호 입력----------- -->
          <v-text-field
            v-model="tel"
            label="가게 전화번호를 입력해주세요."
            required
            class="input-box"
            color="black"
          ></v-text-field>

          <!-- --------------사업자 등록번호 입력------------ -->
       
            <v-text-field
              v-model="identify"
              label="사업자번호를 입력해주세요."
              required
              class="input-box"
              color="black"
            ></v-text-field>
            <button class="border-m radius-m address-btn">
              등록하기
            </button>
          <!-- -----------마감시간 입력----------- -->
          <v-text-field
            v-model="end"
            label="마감시간을 입력해주세요."
            required
            class="input-box"
            color="black"
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
          ></v-select>
        </form>

        <button class="border-m radius-m" @click="registerStore">매장 추가하기</button>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "storeAddView",
  data () {
    return {
      imgFile: "",
      store: "",
      address: "",
      tel: "",
      identify: "",
      end: "",
      off: "",
      category: "",
      items: [
        {value: 'KOREA', text: '한식'},
        {value: 'JAPAN', text: '일식'},
        {value: 'WESTERN', text: '양식'},
        {value: 'SNACK', text: '분식'},
        {value: 'DESSERT', text: '디저트'},
        {value: 'INGREDIENT', text: '식자재'},
      ],
      storeDto: [],
    }
  },
  methods: {
    fileSelect() {
      console.log(this.$refs);
      this.imgFile = this.$refs.imgFile.files[0];
      console.log(this.imgFile)
    },
    registerStore() {
    
      
      // 이미지 파일도 추가하게 넣어야함
      this.storeDto = {
        storeName: this.store,
        location: this.address,
        tel: this.tel,
        storeNum: this.identify,
        closingTime: this.end,
        offDay: this.off,
      };
      console.log(this.storeDto)
      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "storeDto",
        new Blob([JSON.stringify(this.storeDto)], { type: "application/json" })
      );
      console.log(formData)
      http.defaults.headers["access-token"]
      = localStorage.getItem("access-token");
      http
        .post("/store/register", formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        })
      .then((response) => {
          if (response.status == 200) {
            alert("매장이 추가 완료되었습니다");
          } else {
            alert("매장 추가가 완료되지 않았습니다.");
          }
        });
    }
  }
}
</script>

<style>

</style>