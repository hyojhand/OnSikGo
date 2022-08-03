<template>
  <div class="main_container">
    <div class="mobile" >
      <!-- <div class="location mt-5 ">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pin-map" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"/>
          <path fill-rule="evenodd" d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
        </svg>
        <span> {{ storeLocation }} </span>
      </div> -->
      <!-- 단일 상품 설명 -->
      <!-- 상품명, 상품이미지, 주소,현재위치에서 거리, 매장상세보기 버튼, 정가, 할인가, 재고, 한줄평-->
      <div class="product_container border-l radius-m mt-5">
        <!-- 상품명 -->
        <h3 class="product-name">{{productName}}</h3>
        <!-- 상품이미지, 상세설명 -->
        <div class="row">
          <div class="col-5">
            <img class="product-image" :src="`${ itemImgUrl }`" :alt="`${ productName }`">
          </div>
          <div class="col product-description">
            <p class="product-location">상호 명 : {{ storeName }}</p>
            <p class="product-prediction">300m, 도보로 3분</p>
            <button @click="detailStore(item)" class="store-moving">상세보기</button>
            <br><br>
            <p class="total-price">가격 : 
              <span class="price">{{ price }}원</span>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
              </svg>
              <span class="discount-price">{{ price - salePrice }}원</span>
            </p>
            <p style="margin-top:0px">재고 <span>{{ stock }}</span> 개</p>
          </div>
        </div>
        <!-- 꿀팁 -->
        <div class="row ">
          <p class="col-4 m-1">
            <img 
              src="@/assets/images/cooker.png"
              width="90%"
            >
          </p>
          <p class="col" style="text-align:left; font-size:12px; word-break: keep-all;">
            {{ comment }}</p>
        </div>
        
      </div>
      <!-- 픽업 주문서 -->
      <!-- 고객명, 매장, 상품명, 수량, 소요시간, 경고문, 픽업 신청버튼 -->
      <v-stepper  class="order mt-3">
        <v-stepper-items>
          <v-stepper-content
            step="1"
            class="btn-box mt-3"
            black
            outlined
            min-height="450">
            <p class="order-header">주문서</p>
            <p class="order-name">주문자 : {{ nickName }}</p>
            <p class="order-store">매장 명 : {{ storeName }}</p>
            <p class="order-product">상품 명 : {{productName}}</p>
            <form  class="mb-2 el-case">
              <v-text-field
                v-model="count"
                label="수량을 입력해주세요."
                required
                class="input-box mt-5"
                color="black"
              >
              </v-text-field>
              <v-text-field
                v-model="count"
                label="소요시간을 입력해주세요."
                required
                class="input-box mt-1"
                color="black"
              >
              </v-text-field>
            </form>
            <p class="order-text">* 매장에서 '승인 완료'시 고객님에게 알림이 갑니다</p>
            <p class="order-text">반드시 알림을 확인하고 출발해주세요.</p>
            <p class="order-text">상품은 금일 취식 부탁드립니다</p>
            <button class="order-button border-m radius-m" @click="productOrder()">신청하기</button>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
    </div>

  </div>
</template>

<script>
import { mapGetters } from "vuex";
import http from "@/util/http-common";

export default {
  name: "OrderView",
  data () {
    return {
      productName: "",
      price : "",
      stock : "",
      salePrice : "",
      itemImgUrl : "",
      comment : "",
      userName : "",
      nickName : "",
      storeName : "",
      storeLocation : "",
      count : "",
    }
  },
  computed: {
    ...mapGetters("store", [
      "currentAddress",
      "currentItemId",
      "orderStore",
    ])
  },
  created (){
    this.findProduct()
    this.findUser()
    this.findStock()
  },
  methods: {
    // 상품 정보 조회
    findProduct() {
      http
        .get(`/item/${this.currentItemId}`)
        .then((response) => {
          this.productName = response.data.itemName
          this.price = response.data.price
          this.itemImgUrl = response.data.itemImgUrl
          this.comment = response.data.comment
          console.log(response.data)
        }),
      http
        .get(`/sale/${this.currentItemId}`)
        .then((response) => {
          this.stock = response.data.stock
          this.salePrice = response.data.salePrice 
          console.log(response.data)
        })
    },
    // 가게정보 조회
    findStock() {
      console.log(this.orderStore)
      http
        .get(`/store/${this.orderStore}`)
        .then((response) => {
          this.storeName = response.data.storeName
          this.storeLocation = response.data.location
          // console.log(response.data)
        })
    },
    // 유저 조회
    findUser() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http
        .get('/user')
        .then((response) => {
          this.userName = response.data.userName
          this.nickName = response.data.nickname
          // console.log(response.data)
        })
    },
    //  주문하기
    productOrder() {
      http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
      http
        .post('/order', {
          saleItemId : this.currentItemId,
          count : this.count
        })
        .then((response) => {
          console.log(response)
        })
      this.$router.push("/shop")

      
    },
    // 할때 가게정보도 추가 할 것
    detailStore() {
        this.$router.push("/store")
    }
  }
};
</script>

<style scoped>


.main_container{
      width:100%;
      height:100%;

  }
    

.location span {
  color: #333;
  font-weight: 500;
  font-size : 16px;
  margin-left: 5px;
  /* 밑줄 */
  border-bottom:2px solid #8cb883;
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
  color : black;
}
.order-header {
  font-size: 16px;
  font-weight: bold;
}
.order-name {
  margin: 0;
  margin-bottom: 0.5rem;
  text-align: left;
}
.order-store {
  margin: 0;
  margin-bottom: 0.5rem;
  text-align: left;
}
.order-product {
  margin: 0;
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
  margin-bottom: 0.5rem;
  text-align: left;
}
.order-button {
  margin-left: 35%;
}
</style>
