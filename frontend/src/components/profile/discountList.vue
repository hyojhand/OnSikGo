<template>
  <div class="container">
    <div
      v-for="(item, index) in discardStoreList"
      :key="index"
      class="row"
    >
      <div class="col-5">
        <img :src="`${item.itemDto.itemImgUrl}`" />
      </div>
      <div class="col-7">
        <div>
          <div class="info-box">상품명 : {{ item.itemDto.itemName }}</div>
          <div class="info-box">남은 재고: {{ item.saleDto.stock }} 개</div>

          <div class="info-box">정상판매가 : {{ item.itemDto.price }} 원</div>
          <div class="info-box sale">
            할인율 : 🔻{{ ((1 - item.salePrice / item.itemDto.price) * 100).toFixed(2) }}%
          </div>
          <div class="info-box">할인판매가: {{ item.salePrice }} 원</div>
        </div>
        <div>
          <edit-stock-modal
            :no="item.itemId"
            :store="item.saleDto.storeDto.storeId"
          ></edit-stock-modal>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>
import EditStockModal from "@/components/management/EditStockModal.vue";
import { mapGetters } from "vuex";
export default {
  name: "discountList",
  components: {
    EditStockModal,
  },
  computed: {
    ...mapGetters("discardStore", [
      "discardStoreList",
    ]),
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
