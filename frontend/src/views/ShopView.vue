<template>
  <div class="main_container">
    <div class="mobile" >
      <!-- 현재 주소 -->

      <div class="location">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pin-map" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"/>
          <path fill-rule="evenodd" d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
        </svg>
        <span> 서구 임시수도기념로 37, 구덕로 119길</span>
      </div>
      <!-- Tab 기능 -->

      <ul class="tabs">
        <li v-for="tab in tabs" 
          v-bind:class="{active : tab === selectedTab}" 
          :key="tab"
          v-on:click="onClickTab(tab)">
          <span>{{ tab }}</span>
        </li>
      </ul>
<!-- test용 -->
      <!-- 지도 or Category Store -->
      <div class="tab-content">
        <kakao-map class="tab-link currnet" v-if="selectedTab === tabs[0]"></kakao-map>
        <category-store class="tab-link" v-else></category-store>
      </div>

      
      
      <!-- 검색란 -->
      <div class="container" v-if="selectedTab === tabs[0]">
        <div class="row">
          <div class="col-4">
            <span class="search-result">총 3개 검색 결과</span>
          </div>
        
          <div class="col">
            <input class="search-box" type="search" placeholder=" 상품을 입력해주세요">
            <!-- 검색 아이콘 -->
            <button class="product-search">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
              <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
              </svg>
            </button>
            <!-- 초기화 -->
            <button class="search-reset">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
                <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
              </svg>
            </button>
          </div>
        </div>
      </div>
      <!-- 상품 설명란 -->
      <div class="product" v-if="selectedTab === tabs[0]">
        <product-item></product-item>
        <product-item></product-item>
        <product-item></product-item>
      </div>
    </div>
  </div>
</template>

<script>
import KakaoMap from '@/components/KakaoMap';
import CategoryStore from '@/components/CategoryStore';
import ProductItem from '@/components/ProductItem';

export default {
  name: "ShopView",

  components: {
    KakaoMap,
    CategoryStore,
    ProductItem,
  },
  data: function() {
      return{
          tabs: ['지도보기', '가게보기'],
          selectedTab: '',
      };
    },
  // 디볼트는 지도보기
  created() {
      this.selectedTab = this.tabs[0]
  },
  methods:{
    onClickTab(tab) {
            this.selectedTab = tab

    },
  },
};
</script>

<style scoped>
/* 모든 폰트사이즈는 기본값이 15px */
* {
  font-size: 15px;
}
/* 각 블럭마다 빈공간 부여 */
div {
  padding-bottom: 5px;
  padding-top: 5px;
}

.tab-content{
  padding-top: 25px;
}
.main_container{
      width:100%;
      height:100%;

  }
    
.main_container .mobile{ 
    width:350px;
    margin:0 auto;
}

.main_container .mobile .location .bi-pin-map{
  margin-left: 0.5rem;
}
/* 점없애고 가로정렬 */

ul.tabs{
  margin: 0px;
  padding: 0px;
  list-style: none;
}
ul.tabs li{
  list-style-type: none;
  float: left;
  background: none;
  color: #222;
  padding: 10px 15px;
  cursor: pointer;
  border: 1px solid #B9B9B9;
  border-radius: 16px;
  width:78px;
  height: 21px;
  font-size: 12px;
  padding: 0;
  margin-left: 5px;
}

.location {
  text-align: left;
  padding-bottom: 10px;
}

.search-result{
  font-size: 12px;
}
.search-box{
  border: solid 1px;
  font-size: 12px
}

.product-search {
  padding-right: 5px;
  padding-left: 5px;
}
</style>
