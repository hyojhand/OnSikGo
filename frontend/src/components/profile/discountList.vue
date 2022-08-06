<template>
  <div>
    <div
      v-for="(item, index) in discardStoreList"
      :key="index"
      class="container row"
    >
      <div class="col-5">
        <img :src="`${item.itemDto.itemImgUrl}`" />
      </div>
      <div class="col-7">
        <div>
          <div class="info-box">상품명 : {{ item.itemDto.itemName }}</div>
          <div class="info-box">남은 재고: {{ item.stock }} 개</div>

          <div class="info-box">정상판매가 : {{ item.itemDto.price }} 원</div>
          <div class="info-box sale">
            할인율 : 🔻{{ ((1 - item.salePrice / item.itemDto.price) * 100).toFixed(2) }}%
          </div>
          <div class="info-box">할인판매가: {{ item.salePrice }} 원</div>
        </div>
        <div>
          <edit-test
            :item="item"
            :storeId="item.saleDto.storeDto.storeId"
          ></edit-test>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>
import EditTest from "@/components/management/EditTest.vue";
import { mapGetters } from "vuex";
export default {
  name: "discountList",
  components: {
    EditTest,
  },
  computed: {
    ...mapGetters("discardStore", [
      "discardStoreList",
    ]),
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
