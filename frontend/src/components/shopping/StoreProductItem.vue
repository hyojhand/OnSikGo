<template>
  <div class="container">
    <div class="row ml-2">
      <!-- 상품이미지 -->
      <div class="col-3">
        <img
          :src="`${itemDto.itemImgUrl}`"
          style="widght: 80px; height: 80px"
          alt=""
        />
      </div>
      <!-- 상품 설명 -->
      <!-- 상품 이름, 매장위치, 거리, 할인율, 정가, 할인가 -->
      <div class="col-4 ml-5">
        <div class="product-name">{{ itemDto.itemName }}</div>
        <div class="product-location">
          매장위치 : {{ saleDto.storeDto.location }}
        </div>
        <div class="product-prediction">현재 위치로 부터 {{distance}}m</div>
        <div class="product-discount">
          {{ ((1 - salePrice / itemDto.price) * 100).toFixed(0) }} %
        </div>
        <span class="price">{{ itemDto.price }} 원 </span>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="bi bi-arrow-right"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"
          />
        </svg>
        <span class="discount-price"> {{ salePrice }} 원</span>
      </div>
      <!-- 재고 & 주문하기 -->
      <div class="col-3">
        <p class="product-quantity">
          재고 : <span class="product-number"> {{ stock }}</span> 개
        </p>
        <button class="border-m radius-s" @click="productOrder(itemId, saleDto.storeDto.storeId)">주문하기</button>
      </div>
    </div>

    <hr class="mt-4" style="border: 1px solid #222; margin: 3px" />
  </div>
</template>

<script>
import {mapGetters, mapActions} from "vuex";
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
      distance : ""
    }
  },
  computed: {
    ...mapGetters("store", [
      "currentX",
      "currentY"
    ]),
  },
  created() {
    this.distance = this.getdistance(this.currentX, this.currentY, this.saleDto.storeDto.lat, this.saleDto.storeDto.lng)
  },
  methods: {
    // 현재 위치 주소 vuex에 넣기
      ...mapActions("store", [
        "getItemId",
        "getOrderStore",
      ]),
    getdistance(lat1, lon1, lat2, lon2) {
      if ((lat1 == lat2) && (lon1 == lon2)){
        return 0;
      }
      var radLat1 = Math.PI * lat1 / 180;
      var radLat2 = Math.PI * lat2 / 180;
      var theta = lon1 - lon2;
      var radTheta = Math.PI * theta / 180;
      var dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
      if (dist > 1)
          dist = 1;

      dist = Math.acos(dist);
      dist = dist * 180 / Math.PI;
      dist = dist * 60 * 1.1515 * 1.609344 * 1000;
      if (dist < 100) dist = Math.round(dist / 10) * 10;
      else dist = Math.round(dist / 100) * 100;

      return dist;
    },
    productOrder(itemId,storeId) {
      if (localStorage.getItem("access-token") == null){
        this.$router.push({
          name: "login",
        })
      }
      else {
        this.getItemId(itemId),
        this.getOrderStore(storeId)
        this.$router.push({
          name: "orderView",
        })
      }
    },
  }
};
</script>

<style scoped>
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
.product-name {
  font-size: 12px;
  font-weight: bolder;
}

.product-location {
  font-size: 10px;
}
/* 거리 에측 */
.product-prediction {
  font-size: 6px !important;
  color: #b9b9b9;
}
/* 할인율 */
.product-discount {
  padding-left: 8px;
  color: red;
  font-weight: bolder;
  font-size: 12px;
}
/* 상품 가격 (정가) */
.price {
  text-decoration: line-through;
  text-decoration-color: red;
  text-decoration-style: solid;
  font-size: 12px;
}
/* 할인가 */
.discount-price {
  font-size: 12px;
}
/* 주문하기 버튼 */

/* 재고 수량 글자 */
.product-quantity {
  padding-top: 20px;
}
/* 재고 수량 */
.product-number {
  color: red;
  font-weight: bolder;
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
</style>
