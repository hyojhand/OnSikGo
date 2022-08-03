<template>
  <div class="container">
    
    <!-- 검색란 -->
    <div class="row">
      <div class="col">
        <input
          v-model="keyword"
          @keyup.enter="keywordSelect()"
          class="search-box" 
          type="search" 
          placeholder="        상품을 입력해주세요"
        >
        <!-- 검색 아이콘 -->
        <button class="product-search">
          <svg 
            @click="keywordSelect()"
            xmlns="http://www.w3.org/2000/svg" 
            width="20" 
            height="20" 
            fill="currentColor" 
            class="bi bi-search" 
            viewBox="0 0 16 16"
          >
          <path 
            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
        </button>
        <!-- 초기화 -->
        <button class="search-reset">
          <svg 
            @click="resetLitemList()"
            xmlns="http://www.w3.org/2000/svg" 
            width="20" 
            height="20" 
            fill="currentColor" 
            class="bi bi-arrow-clockwise" 
            viewBox="0 0 16 16">
            <path fill-rule="evenodd" 
              d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
            <path 
              d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
          </svg>
        </button>
      </div>
    </div>

    <div
        class="item-card row"
        v-for="(item, index) in items"
        :key="index"
        v-bind="item"
      >
      <!-- 마커 -->
      <!-- <div class="col-2">
        <h1>{{ item.index }}</h1>
      </div> -->
      <div class="col-5">
        <img :src="item.itemDto.itemImgUrl" style="widght:80px; height:80px" alt="">
      </div>
      <div class="col-4">
        <div class="product-name">{{ item.itemDto.itemName }}</div>
        <div class="product-location">매장위치 : {{ item.saleDto.storeDto.storeName }}</div>
        <div class="product-prediction">현재 위치로부터 {{ item.distance }} m</div>
        <div class="product-discount">{{ ((1 - item.salePrice / item.itemDto.price) * 100).toFixed(0) }}%</div>
        <span class="price">{{ item.itemDto.price }}원</span>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
        </svg>
        <span class="discount-price">{{ item.salePrice }}원</span>
      </div>
      <div class="col-3">

        <p class="product-quantity">재고 : <span class="product-number">{{ item.stock }}</span> 개</p>
        <button class="border-m radius-s" @click="productOrder(item)">주문하기</button>
      </div>
      <hr style="border : 1px solid #222; margin: 0.5rem;">
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import {mapGetters, mapActions} from "vuex";

export default {
    name:'ProductItem',

    data() {
      return {
        items: [],
        keyword: "",
        storeKeyword: "",
      }
    },
    computed: {
    ...mapGetters("store", [
      "currentX",
      "currentY"
    ])
  },
    created () {
      this.productFind();
    },
    methods: {
      // 현재 위치 주소 vuex에 넣기
      ...mapActions("store", [
        "getItemId",
        "getOrderStore",
        "getAroundSaleStore",
      ]),
      // 상품 조회
      productFind() {
        this.keyword = "";
        http
          .post("/sale/keyword/", {
            keyword: this.keyword,
          })
          .then((response) => {
            var saleStore = []
            response.data.forEach((item) => {
              var distance
              saleStore.push([item.saleDto.storeDto.lat, item.saleDto.storeDto.lng])
              distance = this.getdistance(this.currentX, this.currentY, item.saleDto.storeDto.lat, item.saleDto.storeDto.lng)
              item.distance = distance
              // 초기에 근방에 위치한 가게만 보여주기
              if (distance < 100000000){
                this.items.push(item)
              }
            })
            this.getAroundSaleStore(saleStore)
          })
      },
      // 
      productOrder(item) {
        console.log(item)
        this.getItemId(item.itemId),
        this.getOrderStore(item.saleDto.storeDto.storeId)
        this.$router.push({
          name: "orderView",
        })
      },
      // 키워드 검색
      keywordSelect() {
        http
        .post("/sale/keyword/", {
          keyword: this.keyword,
        })
        .then((response) => {
          console.log(response)
          this.items = response.data;
          this.items.forEach((item) => {
              var distance
              distance = this.getdistance(this.currentX, this.currentY, item.saleDto.storeDto.lat, item.saleDto.storeDto.lng)
              item.distance = distance
          })
        });
      },
      // 초기화
      resetLitemList() {
        this.keyword = "";
        http
        .post("/sale/keyword/", {
          keyword: this.keyword,
        })
        .then((response) => {
          this.items = response.data;
          this.items.forEach((item) => {
              var distance
              distance = this.getdistance(this.currentX, this.currentY, item.saleDto.storeDto.lat, item.saleDto.storeDto.lng)
              item.distance = distance
          })
        });
      },
      // 거리 구하기
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
        dist = dist * 60 * 1.1515 * 1.609344 * 100 / 2;
        if (dist < 100) dist = Math.round(dist / 10) * 10;
        else dist = Math.round(dist / 100) * 100;

        return dist;
      }
    }
    
}
</script>

<style scoped>

.container .row .col-4 {
  text-align: left;
  margin: 0px;
  margin-bottom: 0px !important; 
  padding: 0;
}
.container .row .col-5 span{
  text-align: left;
}

.container .row .col-3{
  font-size: 12px;
  margin: 0px;
  padding: 0px
}
.product-name{
  font-size: 12px;
  font-weight: bolder;
}

.product-location{
  font-size: 10px;
}
/* 거리 에측 */
.product-prediction{
  font-size: 6px !important;
  color: #B9B9B9;
}
/* 할인율 */
.product-discount{
  padding-left: 8px;
  color: red;
  font-weight: bolder;
  font-size: 10px;
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
/* 주문하기 버튼 */

/* 재고 수량 글자 */
.product-quantity{
  padding-top: 20px;
}
/* 재고 수량 */
.product-number{
  color: red;
  font-weight: bolder;
}

.product-order{
  border:  1px solid;
  text-decoration-line: none;
  border-radius: 15px;
  font-size: 12px;
  padding: 3px;
  color: #222;
}


.search-result{
  font-size: 12px;
}


.product-search {
  padding-right: 5px;
  padding-left: 5px;
}

.search-box{
  border: solid 1px;
  border-radius: 12px;
  font-size: 12px;
  background-repeat: no-repeat;
  padding: 5px 5px;
  width: 260px;
  background-color: #FFF;
}

.search-box::-webkit-input-placeholder{
  background-image: url(https://cdn1.iconfinder.com/data/icons/hawcons/32/698627-icon-111-search-256.png) ;
  background-size: contain;
  background-position:  1px center;
  background-repeat: no-repeat;
  text-indent: 0;
}
</style>