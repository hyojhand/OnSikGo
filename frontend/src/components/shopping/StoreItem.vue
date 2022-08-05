<template>
  <div
    class="store-container"
    :class="{ zerostock: saleItemDtoList.length == 0 }"
  >
    <!-- 가게 이미지 -->

    <img :src="`${storeImgUrl}`" class="col-3" />

    <!-- 가게 설명 -->
    <div class="col-6 store-case">
      <div class="store-name" :class="{ none: saleItemDtoList.length == 0 }">
        {{ storeName }}
      </div>
      <div
        class="store-prediction"
        :class="{ none: saleItemDtoList.length == 0 }"
      >
        300m, 도보로 3분
      </div>
      <div
        class="ment"
        :class="{ none: saleItemDtoList.length == 0 }"
        v-if="saleItemDtoList.length == 0"
      >
        오늘 등록된 물품이 없어요ㅠ
      </div>
    </div>
    <!-- 물품수량 & 버튼 -->
    <div class="col-3 product-case">
      <p class="store-product" :class="{ none: saleItemDtoList.length == 0 }">
        등록물품 :
        <sapn
          class="product-count"
          :class="{ none: saleItemDtoList.length == 0 }"
          >{{ saleItemDtoList.length }}</sapn
        >
        개
      </p>
      <button
        class="border-m radius-s"
        :class="{ none: saleItemDtoList.length == 0 }"
        @click="storeDetail()"
      >
        가게보기
      </button>
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
  flex-direction: column;
  justify-content: space-around;
  padding: 0;
  padding-right: 3%;
}
.store-case {
  padding: 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.store-name {
  font-size: 20px;
  margin-bottom: 5px;
}
.zerostock {
  background-color: rgba(0, 0, 0, 0.05);
}
.none {
  color: rgba(0, 0, 0, 0.7);
}
img {
  padding: 0;
}
</style>
