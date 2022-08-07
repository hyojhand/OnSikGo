<template>
  <!--상품정렬-->
  <div>
    <img :src="`${item.itemImgUrl}`" alt="IMG-PRODUCT" style="height: 150px" />
    <div class="item-name">
      {{ item.itemName }}
    </div>
    <div class="info-container">
      <div class="item-info">
        <div class="info-box">
          <div>정상 판매가 :</div>
          <div class="ml-1">{{ item.price }} 원</div>
        </div>
        <div class="info-box">
          <div>할인 판매가 :</div>
          <div class="ml-1">{{ item.sale.salePrice }} 원</div>
        </div>
        <div class="info-box">
          <div>등록 수량 :</div>
          <div class="ml-1">{{ item.sale.stock }} 개</div>
        </div>
      </div>
      <button @click="prodmodify()" class="border-m radius-s my-1 edit-btn">
        정보수정
      </button>
      <add-stock-modal
        :item="this.item"
        :storeId="this.storeId"
        class="stock-btn mb-1"
      ></add-stock-modal>
      <edit-stock-modal
        :item="this.item"
        :storeId="this.storeId"
        class="stock-btn"
      ></edit-stock-modal>
    </div>
  </div>
</template>

<script>
import EditStockModal from "@/components/management/EditStockModal.vue";
import AddStockModal from "@/components/management/AddStockModal.vue";
import { mapActions } from "vuex";
export default {
  name: "AllProductList",
  components: {
    AddStockModal,
    EditStockModal,
  },
  data() {
    return {};
  },
  props: {
    item: Object,
    storeId: Number,
  },

  methods: {
    ...mapActions("itemStore", ["getItemId"]),
    ...mapActions("storeStore", ["getStoreId"]),
    prodmodify() {
      this.getItemId(this.item.itemId);
      this.getStoreId(this.storeId);
      this.$router.push({
        name: "prodChange",
      });
    },
  },
};
</script>

<style scoped>
img {
  width: 100%;
  height: 100%;
  padding-bottom: 3%;
  border-bottom: 2px solid rgba(255, 255, 255, 50%);
}
button {
  color: white;
  background-color: rgb(140, 184, 131);
  width: 100%;
}
.stock-btn {
  width: 100%;
}

.item-name {
  margin: 5% auto;
  padding-bottom: 3%;
  border-bottom: 2px solid rgba(255, 255, 255, 50%);
}
.info-container {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  margin: 0;
  flex-wrap: wrap;
}
.item-info {
  padding: 0;
}
.info-box {
  display: flex;
  flex-direction: row;
  padding-bottom: 3%;
  margin: 2% 0;
}
.edit-btn {
  background-color: rgb(255, 82, 82);
}
</style>
