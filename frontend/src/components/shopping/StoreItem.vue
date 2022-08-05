<template>
  <div
    class="store-container row"
    :class="{ zerostock: saleItemDtoList.length == 0 }"
  >
    <!-- 가게 이미지 -->

    <img :src="`${storeImgUrl}`" height="50" width="50" class="col-4" />

    <!-- 가게 설명 -->
    <div class="col-3">
      <div class="store-name">{{ storeName }}</div>
      <div class="store-prediction">300m, 도보로 3분</div>
    </div>
    <!-- 물품수량 & 버튼 -->
    <div class="col-6">
      <div class="product-case">
        <div class="store-product">
          등록물품 :
          <div class="product-count">{{ saleItemDtoList.length }}</div>
          개
        </div>
      </div>
      <button @click="storeDetail()">가게보기</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapActions } from "vuex";
export default {
  name: "StoreItem",

  props: {
    storeId: Number,
    storeName: String,
    location: String,
    tel: String,
    storeNum: String,
    storeImgUrl: String,
    closingTime: String,
    offDay: String,
    category: String,
    lat: String,
    lng: String,
  },

  data() {
    return {
      saleItemDtoList: [],
    };
  },
  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    storeDetail() {
      this.getStoreId(this.storeId);
      this.$router.push({
        name: "storeView",
      });
    },
  },
  created() {
    http.get(`/sale/list/${this.storeId}`).then((response) => {
      if (response.status == 200) {
        this.saleItemDtoList = response.data;
        console.log(response);
      }
    });
    console.log(this.saleItemDtoList);
  },
};
</script>

<style scoped>
.store-container {
  display: flex;
  flex-direction: row;
  width: 100%;
  align-items: center;
  height: 120px;
  margin: 0;
}
.product-case {
  display: flex;
  flex-direction: row;
}
.zerostock {
  background-color: rgba(0, 0, 0, 0.05);
}
</style>
