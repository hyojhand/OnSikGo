<template>
  <div class="store-container">
    <div class="row">
      <!-- 가게 이미지 -->
      <div class="col-2">
        <img
          class="store-image"
          :src="`${storeImgUrl}`"
          height="50"
          width="50"
        />
      </div>
      <!-- 가게 설명 -->
      <div class="store col">
        <p class="store-name">{{ storeName }}</p>
        <p class="store-prediction">300m, 도보로 3분</p>
      </div>
      <!-- 물품수량 & 버튼 -->
      <div class="col">
        <p class="store-product">
          등록물품 :
          <span class="product-count"> {{ saleItemDtoList.length }}</span> 개
        </p>
        <button class="store-moving" @click="storeDetail()">가게보기</button>
      </div>
      <hr style="border: 1px solid #222; margin: 3px" />
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
      }
    });
  },
};
</script>

<style scoped>
.store-container {
  padding: 6px;
}

.store-container .row .store-image {
  width: 50px;
}

.store-container .row .store {
  text-align: left;
}
.store-container .row .store .store-name {
  font-size: 12px;
  font-weight: bolder;
}
.store-container .row .store .store-prediction {
  font-size: 6px !important;
  color: #b9b9b9;
}

.store-container .row .store-product {
  font-size: 12px;
}
.store-container .row .product-count {
  color: red;
  font-weight: bolder;
}

.store-container .row .store-moving {
  border: 1px solid;
  text-decoration-line: none;
  border-radius: 15px;
  font-size: 12px;
  padding: 3px;
  color: #222;
}
</style>
