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
        <div class="product-prediction">300m, 도보로 3분</div>
        <div class="product-discount">
          {{ (1 - salePrice / itemDto.price) * 100 }} %
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
        <router-link class="product-order" :to="{ name: 'orderView' }"
          >주문하기</router-link
        >
      </div>
    </div>

    <hr class="mt-4" style="border: 1px solid #222; margin: 3px" />
  </div>
</template>

<script>
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
  created() {},
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
