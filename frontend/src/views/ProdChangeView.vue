<template>
  <div>
    <!--매장선택-->
    <div class="d-flex justify-content-center">
      {{ storeDto.storeName }}
    </div>
    <!--수정정보나타내기-->
    <div class="head">
      <div class="container">
        <img
          src="@/assets/images/hambuger.jpg"
          alt="IMG-PRODUCT"
          class="mt-2 mb-5"
        />
      </div>
      <div class="bg-secondary bg-opacity-75 text-white fw-bold fs-2">
        <p class="head">{{ this.itemDto.itemName }}</p>
      </div>
      <br />

      <!--상품정보-->
      <div class="d-flex justify-content-center">
        <div class="col-4">
          <p class="mb-5">정상가</p>
          <br />
          <p class="mt-4">할인가</p>
          <p>수량</p>
          <p>특이사항</p>
        </div>
        <div class="col-8">
          <p>{{ this.itemDto.price }}</p>
          <p>🔻할인율입력해야함</p>
          <input
            v-model="salePrice"
            class="form-control form-control-sm"
            type="text"
            placeholder="원하시는 금액을 입력해주세요"
            aria-label=".form-control-sm example"
          />
          <input
            v-model="stock"
            class="form-control form-control-sm mt-2"
            type="text"
            placeholder="원하시는 수량을 입력해주세요"
            aria-label=".form-control-sm example"
          />
          <input
            v-model="this.itemDto.price"
            class="form-control form-control-sm mt-2"
            type="text"
            placeholder="특이사항을 입력해주세요"
            aria-label=".form-control-sm example"
          />
        </div>
      </div>
      <br />
      <br />

      <!--버튼모음-->
      <div class="d-flex justify-content-around">
        <b-button
          @click="prodchange"
          pill
          variant="success"
          class="d-grid gap-2 col-4 mx-auto"
          >수정</b-button
        >
        <product-delete-modal></product-delete-modal>
      </div>
    </div>
  </div>
</template>

<script>
import ProductDeleteModal from "@/components/ProductDeleteModal.vue";
import http from "@/util/http-common";
export default {
  name: "ProdChangeView",

  components: { ProductDeleteModal },
  data() {
    return {
      itemDto: {},
      salePrice: "",
      stock: "",
      storeDto: {},
      storeId: Number,
    };
  },

  async created() {
    await http.get(`/store/${this.$route.params.storeId}`).then((response) => {
      this.storeDto = response.data;
    });

    await http.get(`/item/${this.$route.params.itemId}`).then((response) => {
      this.itemDto = response.data;
    });
  },

  methods: {
    prodchange() {
      http.post(`/sale/${this.$route.params.storeId}`, {
        itemId: this.$route.params.itemId,
        salePrice: this.salePrice,
        stock: this.stock,
      });

      this.$router.push("/allprod/");
    },
  },
};
</script>

<style scoped>
.container {
  width: 300px;
  height: 220px;
  overflow: hidden;
  position: relative;
}

.container > img {
  position: absolute;
  width: 100%;
  /* height: 100%; */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.head {
  margin-top: 50px;
}
</style>
