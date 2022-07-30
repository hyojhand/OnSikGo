<template>
  <div>
    <!--매장선택-->
    <div class="d-flex justify-content-center mt-5">
      <select
        id="dropdown1"
        style="border-color: #63bf68"
        @change="selectStore($event)"
      >
        <option
          :key="store.storeName"
          :value="store.storeId"
          v-for="store in stores"
        >
          {{ store.storeName }}
        </option>
      </select>
    </div>
    <!--back 버튼-->
    <a href="" class="back-button"
      ><svg
        xmlns="http://www.w3.org/2000/svg"
        width="16"
        height="16"
        fill="currentColor"
        class="bi bi-backspace"
        viewBox="0 0 16 16"
      >
        <path
          d="M5.83 5.146a.5.5 0 0 0 0 .708L7.975 8l-2.147 2.146a.5.5 0 0 0 .707.708l2.147-2.147 2.146 2.147a.5.5 0 0 0 .707-.708L9.39 8l2.146-2.146a.5.5 0 0 0-.707-.708L8.683 7.293 6.536 5.146a.5.5 0 0 0-.707 0z"
        />
        <path
          d="M13.683 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-7.08a2 2 0 0 1-1.519-.698L.241 8.65a1 1 0 0 1 0-1.302L5.084 1.7A2 2 0 0 1 6.603 1h7.08zm-7.08 1a1 1 0 0 0-.76.35L1 8l4.844 5.65a1 1 0 0 0 .759.35h7.08a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1h-7.08z"
        /></svg
      >back</a
    >

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
        <input type="file" class="" />
      </p>
    </div>

    <!--입력항목-->
    <div class="d-flex justify-content-center mt-5">
      <div class="col-4">
        <p class="mb-5">상풍명</p>
        <p class="mt-4">정상가</p>
        <p>특이사항</p>
      </div>

      <!--정보입력칸-->
      <div class="col-8">
        <form class="mb-2">
          <v-text-field
            v-model="itemName"
            label="상품명을 입력해주세요."
          ></v-text-field>
          <v-text-field
            v-model="itemPrice"
            label="정상가를 입력해주세요."
          ></v-text-field>
          <v-text-field
            v-model="itemComment"
            label="특이사항을 적어주세요."
          ></v-text-field>
        </form>

        <b-button
          @click="register"
          pill
          variant="outline-success"
          class="d-grid col-8 mx-auto mt-5"
          >등록</b-button
        >
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
      stores: [],
      storeId: "",
      itemName: "",
      itemPrice: "",
      itemComment: "",
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
    register() {
      http
        .post(`/item/register/${this.storeId}`, {
          itemName: this.itemName,
          price: this.itemPrice,
          comment: this.itemComment,
        })
        .then((response) => {
          if (response.status == 200) {
            this.$router.push("/allprod");
          } else {
            alert("상품 등록에 실패했습니다.");
          }
        });
    },
  },
};
</script>

<style scoped>
.back-button {
  position: fixed;
  left: 50px;
}
</style>
