<template>
  <div class="container">
    <!--매장선택-->
    <div class="store-name mx-auto">
      {{ storeDto.storeName }}
    </div>
    <!--수정정보나타내기-->
    <div>
      <img :src="`${itemDto.itemImgUrl}`" alt="IMG-PRODUCT" />
      <div class="item-name">
        {{ this.itemDto.itemName }}
      </div>

      <!--상품정보-->

      <form class="info-container">
        <div class="info-box row">
          <div class="col-4">정상가</div>
          <div class="col-7 price">{{ this.itemDto.price }}</div>
        </div>
        <div class="info-box row">
          <div class="col-4">할인율</div>
          <div class="col-7 price font-l">🔻70%</div>
        </div>

        <div class="info-box row">
          <div class="col-4">할인가</div>
          <input
            class="col-7"
            v-model="salePrice"
            type="text"
            placeholder="할인된 판매 금액을 입력해주세요."
          />
        </div>
        <div class="info-box row">
          <div class="col-4">수량</div>
          <input
            class="col-7"
            v-model="stock"
            type="number"
            placeholder="등록할 수량을 입력해주세요."
          />
        </div>
        <div class="info-box row">
          <div class="col-4">특이사항</div>
          <input
            class="col-7"
            v-model="this.itemDto.price"
            type="text"
            placeholder="특이사항을 입력해주세요"
          />
        </div>
      </form>
    </div>
    <!--버튼모음-->
    <div class="btn-box">
      <button @click="prodchange" class="border-m radius-m edit-btn">
        수 정
      </button>
      <product-delete-modal :no="this.itemDto.itemId"></product-delete-modal>
    </div>
  </div>
</template>

<script>
import ProductDeleteModal from "@/components/management/ProductDeleteModal.vue";
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
img {
  width: 100%;
}
input {
  width: 57%;
  padding: 8px 3px;
  border-bottom: 1px solid black;
  color: rgba(0, 0, 0, 60%);
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.store-name {
  width: 40%;
  font-size: 30px;
  text-align: center;
  padding: 2% 0;
  color: rgba(0, 0, 0, 60%);
}
.item-name {
  font-size: 30px;
  margin: 5% auto;
  border-bottom: 2px solid rgba(0, 0, 0, 10%);
}
.info-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 10%;
}
.info-box {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.price {
  text-align: start;
}
.btn-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  margin: 0 auto 5% auto;
}
.edit-btn {
  width: 74px;
}
</style>
