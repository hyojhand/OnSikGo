<template>
  <div class="case">
    <div
      class="store-container"
      @click="storeDetail()"
      :class="{ zerostock: saleCount == 0 }"
    >
      <!-- 가게 이미지 -->

      <img :src="`${storeImgUrl}`" class="col-3" />

      <!-- 가게 설명 -->
      <div class="col-6 store-case">
        <div class="store-name" :class="{ none: saleCount == 0 }">
          {{ storeName }}
        </div>
        <div
          v-if="this.distance < 3000"
          class="store-prediction"
          :class="{ none: saleCount == 0 }"
        >
          현재 위치로 부터 {{ this.distance }} m
        </div>
        <div v-else class="store-prediction" :class="{ none: saleCount == 0 }">
          현재 위치로 부터 {{ this.distance / 1000 }} km
        </div>
        <div
          class="ment"
          :class="{ none: saleCount == 0 }"
          v-if="saleCount == 0"
        >
          오늘 등록된 물품이 없어요ㅠ
        </div>
      </div>
      <!-- 물품수량 & 버튼 -->
      <div class="col-3 product-case">
        <p class="store-product" :class="{ none: saleCount == 0 }">
          등록물품 :
          <span class="product-count" :class="{ none: saleCount == 0 }">{{
            saleCount
          }}</span>
          개
        </p>
        <!-- <button
          class="border-m radius-s btn"
          :class="{ none: saleCount == 0 }"
          @click="storeDetail()"
        >
          가게보기
        </button> -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "StoreItem",

  props: {
    storeId: Number,
    storeName: String,
    storeImgUrl: String,
    category: String,
    lat: String,
    lng: String,
    saleCount: Number,
  },
  computed: {
    ...mapGetters("store", ["currentX", "currentY"]),
  },
  data() {
    return {
      distance: "",
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
    getdistance(lat1, lon1, lat2, lon2) {
      if (lat1 == lat2 && lon1 == lon2) {
        this.distance = 0;
        return;
      }
      var radLat1 = (Math.PI * lat1) / 180;
      var radLat2 = (Math.PI * lat2) / 180;
      var theta = lon1 - lon2;
      var radTheta = (Math.PI * theta) / 180;
      var dist =
        Math.sin(radLat1) * Math.sin(radLat2) +
        Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
      if (dist > 1) dist = 1;

      dist = Math.acos(dist);
      dist = (dist * 180) / Math.PI;
      dist = dist * 60 * 1.1515 * 1.609344 * 1000;
      if (dist < 100) dist = Math.round(dist / 10) * 10;
      else dist = Math.round(dist / 100) * 100;
      this.distance = dist;
      return;
    },
  },
  created() {
    this.getdistance(this.currentX, this.currentY, this.lat, this.lng);
  },
};
</script>

<style scoped>
.case {
  margin: 0;
  display: flex;
  flex-direction: column;
  width: 95%;
}
.store-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: white;
  align-items: center;
  height: 100px;
  border-radius: 10px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
  margin: 0;
}
.store-container:hover {
  cursor: pointer;
}
.store-product {
  font-size: 13px;
  margin: 0;
}
.btn {
  margin: 0;
  width: 80px;
  height: 30px;
  padding: 0;
  font-weight: 800;
}
.product-case {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  text-align: center;
  padding: 0;
}
.product-count {
  color: red;
}
.store-case {
  padding: 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: left;
  padding-left: 10px;
}
.store-name {
  font-size: 15px;
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
  height: 90px;
  width: 90px;
  border-radius: 10px;
}
.ment {
  font-size: 13px;
}
</style>
