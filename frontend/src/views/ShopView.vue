<template>
  <div class="main_container">
    <div class="mobile" >
      <!-- 현재 주소 -->

      <div class="location">
        <i class="fa-regular fa-map"></i>
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

      <!-- 지도 or Category Store -->
      <div class="tab-content">
        <kakao-map class="tab-link currnet" v-if="selectedTab === tabs[0]"></kakao-map>
        <category-store class="tab-link" v-else></category-store>
      </div>

      
    
      <!-- 검색란 -->
      <div>
        <span>총 3 개 검색 결과</span>
        <input type="text" name="" id="">
        <button>아이콘</button>
      </div>
      <!-- 상품 설명란 -->
      <div class="product">
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
    width:360px;
    height: 1300px;
    margin:0 auto;
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
</style>
