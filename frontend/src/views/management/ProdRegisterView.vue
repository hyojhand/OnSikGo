<template>
  <div>
    <!--매장선택-->
    <div class="mt-5">
      <select id="dropdown1" class="store-name" @change="selectStore($event)">
        <option
          class="option-store"
          :key="index"
          :value="store.storeId"
          v-for="(store, index) in stores"
        >
          {{ store.storeName }}
        </option>
      </select>
    </div>

    <!--상품 이미지 업로드-->
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

    <!--입력항목-->
    <div class="d-flex justify-content-center mt-5">
      <!--정보입력칸-->
      <div class="col-8 info-box mt-5">
        <form class="mb-2 form-box">
          <v-text-field
            v-model="itemName"
            label="상품명을 입력해주세요."
            color="black"
          ></v-text-field>
          <v-text-field
            v-model="itemPrice"
            label="정상가를 입력해주세요."
            color="black"
          ></v-text-field>
          <v-text-field
            v-model="itemComment"
            label="특이사항을 적어주세요."
            color="black"
          ></v-text-field>
        </form>

        <button @click="register" class="border-m radius-m notice-btn">
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name: "ProdRegisterView",

  data() {
    return {
      imgFile: "",
      stores: [],
      storeId: "",
      itemName: "",
      itemPrice: "",
      itemComment: "",
      itemDto: [],
    };
  },

  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http.get("/store/list").then((response) => {
      this.stores = response.data;
      this.storeId = response.data[0].storeId;
    });
  },

  methods: {
    fileSelect() {
      console.log(this.$refs);
      this.imgFile = this.$refs.imgFile.files[0];
    },
    register() {
      this.itemDto = {
        itemName: this.itemName,
        price: this.itemPrice,
        comment: this.itemComment,
      };
      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "itemDto",
        new Blob([JSON.stringify(this.itemDto)], { type: "application/json" })
      );
      http
        .post(`/item/register/${this.storeId}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          if (response.status == 200) {
            this.$router.push("/allprod");
          } else {
            alert("상품 등록에 실패했습니다.");
          }
        });
    },
    selectStore(event) {
      this.storeId = event.target.value;
      this.selectPage(1);
    },
  },
};
</script>

<style scoped>
.store-name {
  width: 40%;
  font-size: 30px;
  text-align: center;
  padding: 2% 0;
}
.option-store {
  width: 100%;
}
.notice-btn {
  width: 110px;
}
.info-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.form-box {
  width: 100%;
}
</style>
