<template>
  <div class="main_container">
    <div class="mobile" >
      <!-- 주소 -->
      <div class="location">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pin-map" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"/>
          <path fill-rule="evenodd" d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
        </svg>
        <span> {{ currentAddress }} </span>
      </div>
      <!-- 단일 상품 설명 -->
      <!-- 상품명, 상품이미지, 주소,현재위치에서 거리, 매장상세보기 버튼, 정가, 할인가, 재고, 한줄평-->
      <div class="product_container">
        <!-- 상품명 -->
        <h3 class="product-name">불고기 버거</h3>
        <!-- 상품이미지, 상세설명 -->
        <div class="row">
          <div class="col-5">
            <img class="product-image" src="@/assets/images/hambuger.jpg" alt="e-mart">
          </div>
          <div class="col product-description">
            <p class="product-location">상호 명 : 이마트 37</p>
            <p class="product-prediction">300m, 도보로 3분</p>
            <router-link class="store-moving" :to="{name: 'storeView'}">상세보기</router-link>
            <p class="total-price">가격 : 
              <span class="price">5700원</span>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
              </svg>
              <span class="discount-price">4000원</span>
            </p>
            <p style="margin-top:0px">재고 <span>3</span> 개</p>
          </div>
        </div>
        <!-- 꿀팁 -->
        <div class="row">
          <p class="col-4">이미지</p>
          <p class="col" style="text-align:left; font-size:12px; word-break: keep-all;">생각보다 매워요.
            =.= 땅콩이 들어가있으니 주의바랍니다. 
            식었다면 랩씌우고 전자레인지에 30초~!</p>
        </div>
        
      </div>
      <!-- 픽업 주문서 -->
      <!-- 고객명, 매장, 상품명, 수량, 소요시간, 경고문, 픽업 신청버튼 -->
      <div class="order">
        <p class="order-header">주문서</p>
        <p class="order-name">주문자 : 동근곤듀</p>
        <p class="order-store">매장 명 : 이마트 37</p>
        <p class="order-product">불고기 버거</p>
        <div class="order-number" style="margin-bottom: 0.5rem">
          <span>수량 : </span>
          <input type="number">
        </div>
        <div class="order-number">
          <span>소요시간 : </span>
          <input type="number">
        </div>
        <p class="order-text">* 매장에서 '승인 완료'시 고객님에게 알림이 갑니다</p>
        <p class="order-text">반드시 알림을 확인하고 출발해주세요.</p>
        <p class="order-text">상품은 금일 취식 부탁드립니다</p>
        <router-link class='main-moving' :to="{name : 'shopView'}">픽업 신청하기</router-link>
      </div>
    </div>

  </div>
</template>

<script>
import { mapGetters } from "vuex";
// import http from "@/util/http-common";
export default {
  name: "OrderView",

  computed: {
    ...mapGetters("store", [
      "currentAddress",
      // "currentItemId",
    ])
  },
  created (){
    // this.findProduct()
  },
  methods: {
    // findProduct() {
    //   http
    //     .get(`/item/${this.currentItemId}`)
    //     .them((response) => {
    //       console.log(response)
    //     })
    // }
  }


};
</script>

<style scoped>


.main_container{
      width:100%;
      height:100%;

  }
    
.main_container .mobile{ 
    width:340px;
    margin:0 auto;
}
/* 주소 위치 */
.main_container .mobile .location{
  margin-left: 1.5rem;
  text-align: left;
  font-size: 15px;
}
/* 상품 이름 */
.main_container .mobile .product_container .product-name {
  font-weight: bolder;
  font-size: 20px;
  margin-top: 0.5rem;
}
/* 이미지 위치 & 크기 */
.main_container .mobile .product_container .product-image{
  margin-left: 1rem;
  width: 130px;
  height: 110px;
}
/* 상품 설명 위치 & 크기 */
.main_container .mobile .product_container .product-description{
  font-size: 12px ;
  text-align: left;
  margin: 0px;
  margin-left: 1rem;
  padding: 0;
}
.product-location{
  margin-bottom: 5px;
  margin-top: 1.5rem;
  font-size: 12px;
}
/* 거리 예측 */
.product-prediction{
  padding: 0px;
  font-size: 8px;
  margin: 3px;
  margin-bottom: 5px;
  margin-left: 3rem;
  color: #B9B9B9;
}
.total-price {
  margin-top: 5px;
}
.store-moving {
  margin-left: 4rem;
  border:  1px solid;
  text-decoration-line: none;
  border-radius: 15px;
  font-size: 12px;
  padding: 3px;
  color: #222;
}

/* 상품 가격 (정가) */
.price {
  text-decoration:line-through; 
  text-decoration-color: red; 
  text-decoration-style:solid;
  font-size:12px;
}
/* 할인가 */
.discount-price{
  font-size:12px;
}

.order {
  margin: 1.5rem;
  border: 1px solid;
  border-radius: 20px;
  font-size: 12px;
  
}
.order-header {
  font-size: 16px;
  font-weight: bold;
}
.order-name {
  margin: 0;
  margin-left:1rem;
  text-align: left;
}
.order-store {
  margin: 0;
  margin-left:1rem;
  text-align: left;
}
.order-product {
  margin: 0;
  margin-left:1rem;
  text-align: left;
}
.order input{
  border: 1px solid;
  margin-left:1rem;
  text-align: left;
}
.order .order-number{
  margin: 0;
  margin-left:1rem;
  text-align: left;
}
.order .order-text {
  color: red;
  margin: 0;
  margin-left:1rem;
  text-align: left;
}
.main-moving {
  border:  1px solid;
  text-decoration-line: none;
  border-radius: 15px;
  font-size: 12px;
  padding: 3px;
  color: #222;
}
</style>
