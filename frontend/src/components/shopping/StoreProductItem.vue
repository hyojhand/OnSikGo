<template>
  <div class="container">
    <div
      class="item-card mt-3"
      @click="productOrder(itemId, saleDto.storeDto.storeId)"
    >
      <!-- 상품이미지 -->
      <img
        :src="`${itemDto.itemImgUrl}`"
        style="cursor: pointer"
        alt=""
        class="product-img col-3"
      />
      <!-- 상품 설명 -->
      <div class="col-6 info-case">
        <div class="product-name">{{ itemDto.itemName }}</div>
        <div class="product-location">
          {{ saleDto.storeDto.location }}
        </div>
        <div v-if="distance < 3000" class="product-prediction">
          현재 위치로부터 {{ distance }} m
        </div>
        <div v-else class="product-prediction">
          현재 위치로부터 {{ distance / 1000 }} km
        </div>
        <div class="product-quantity">
          <div>재고 :</div>
          <div class="product-number">{{ stock }} 개</div>
        </div>
      </div>
      <!-- 상품 가격 및 주문하기 버튼 -->
      <div class="col-3 stock-case">
        <div>
          <div class="price-case">
            <div class="product-discount">
              <div class="price">({{ itemDto.price }})</div>
              🔻
              {{ ((1 - salePrice / itemDto.price) * 100).toFixed(0) }} %
            </div>
          </div>
          <div class="discount-price">{{ salePrice }}원</div>
        </div>
        <button
          class="border-m radius-s order-button"
          @click="productOrder(itemId, saleDto.storeDto.storeId)"
        >
          주문하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import http from "@/util/http-common";
export default {
  name: "StoreProductItem",
  props: {
    no: Number,
    itemDto: {},
    saleDto: {},
    stock: Number,
    totalStock: Number,
    salePrice: Number,
    itemId: Number,
  },
  data() {
    return {
      distance: "",
      userDto: {},
    };
  },
  computed: {
    ...mapGetters("store", ["currentX", "currentY"]),
  },
  created() {
    this.distance = this.getdistance(
      this.currentX,
      this.currentY,
      this.saleDto.storeDto.lat,
      this.saleDto.storeDto.lng
    );
    this.getUser();
  },
  methods: {
    // 현재 위치 주소 vuex에 넣기
    ...mapActions("store", ["getItemId", "getOrderStore"]),
    // 거리 계산
    getdistance(lat1, lon1, lat2, lon2) {
      if (lat1 == lat2 && lon1 == lon2) {
        return 0;
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

      return dist;
    },
    // 주문하기
    productOrder(itemId, storeId) {
      if (localStorage.getItem("access-token") == null) {
        this.$alert("로그인이 필요합니다.");
        this.$router.push({
          name: "login",
        });
      } else if (this.userDto.ban > 5){
        this.$alert("해당 유저는 이 기능을 이용 하실수 없습니다.")
      } else {
        this.getItemId(itemId), this.getOrderStore(storeId);
        this.$router.push({
          name: "orderView",
        });
      }
    },
    getUser(){
      http.defaults.headers["access-token"] = 
        localStorage.getItem("access-token");
      http.get("/user").then((response) => {
        this.userDto = response.data
      })
    }
  },
};
</script>

<style scoped>
.container {
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  background-color: rgb(240, 240, 240);
}
.container .row .col-4 {
  text-align: left;
  margin: 0px;
  margin-bottom: 0px !important;
  padding: 0;
}
.container .row .col-5 span {
  text-align: left;
}

.container .row .col-3 {
  font-size: 12px;
  margin: 0px;
  padding: 0px;
}

.product-order {
  background: none;
  width: 130px;
  height: 30px;
  color: #222;
  cursor: pointer;
  border: 1px solid #b9b9b9;
  border-radius: 16px;

  font-size: 10px;
  padding: 0;
}
.item-card {
  width: 95%;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 3px 0p;
  background-color: white;
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}
.product-img {
  border-radius: 50%;
  width: 90px;
  height: 90px;
  padding-bottom: 12px;
  padding-left: 5px;
}
.info-case {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 0px;
  padding-left: 15px;
}
.product-name {
  font-size: 15px;
  font-weight: bolder;
}
.product-prediction {
  font-size: 0.75rem;
  color: #b9b9b9;
}
.product-quantity {
  display: flex;
  align-items: center;
  padding-top: 20px;
  font-size: 13px;
  padding: 0;
}
/* 재고 수량 */
.product-number {
  color: red;
  font-weight: bolder;
  padding-left: 3px;
  font-size: 15px;
}
.stock-case {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  padding: 0;
  margin: 0;
}
/* 할인율 */
.product-discount {
  padding-left: 3px;
  color: red;
  font-weight: bolder;
  font-size: 15px;
}
/* 상품 가격 (정가) */
.price-case {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.price {
  color: rgba(0, 0, 0, 0.2);
  font-size: 0.85rem;
}
/* 할인가 */
.discount-price {
  font-size: 15px;
  text-align: end;
}
.order-button {
  margin-top: 5px;
  margin-bottom: 5px;
  margin-right: 10px;
  width: 100%;
  font-size: 13px;
  padding: 3px 10px;
}
.order-button:hover {
  background-color: rgb(140, 184, 131);
  cursor: pointer;
  color: #fff;
}
.item-card:hover {
  cursor: pointer;
}
</style>
