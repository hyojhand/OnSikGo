<template>
  <div class="container row">
    <div class="col-5">
      <img :src="`${this.itemDto.itemImgUrl}`" />
    </div>

    <div class="col-7">
      <div>
        <div class="info-box">상품명 : {{ this.itemDto.itemName }}</div>
        <div class="info-box">남은 재고: {{ this.saleDto.stock }} 개</div>

        <div class="info-box">정상판매가 : {{ this.itemDto.price }} 원</div>
        <div class="info-box sale">
          할인율 : 🔻{{ ((1 - salePrice / itemDto.price) * 100).toFixed(2) }}%
        </div>
        <div class="info-box">할인판매가: {{ this.salePrice }} 원</div>
      </div>
      <div>
        <edit-stock-modal
          :no="this.itemId"
          :store="this.storeId"
        ></edit-stock-modal>
      </div>
    </div>
  </div>
</template>

<script>
import EditStockModal from "@/components/management/EditStockModal.vue";
import http from "@/util/http-common";
export default {
  name: "discountList",
  components: {
    EditStockModal,
  },
  data() {
    return {
      itemDto: {},
      saleDto: {},
      salePrice: "",
    };
  },
  props: {
    storeId: Number,
    totalStock: Number,
    itemId: Number,
  },

  async created() {
    await http.get(`/sale/${this.itemId}`).then((response) => {
      if (response.status == 200) {
        this.saleDto = response.data;
        this.salePrice = this.saleDto.salePrice;
      }
    });

    await http.get(`/item/${this.itemId}`).then((response) => {
      this.itemDto = response.data;
    });
  },
  methods: {
    prodmodify() {
      this.$router.push({
        name: "prodChange",
        params: { itemId: this.itemId, storeId: this.no },
      });
    },
  },
};
</script>

<style scoped>
img {
  width: 100%;
}
.container {
  display: flex;
  flex-direction: row;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 10%);
  margin: 0;
}
.info-box {
  text-align: start;
}
.sale {
  color: tomato;
}
</style>
