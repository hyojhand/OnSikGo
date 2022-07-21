<template>
  <div class="main_container">
    <div class="mobile" >
      <!-- 현재 주소 -->
      <div>
        <span>아이콘 | </span>
        <span>서구 임시수도기념로 37, 구덕로 119길</span>
      </div>
      <!-- button -->
      <div>
        <button class="map_tag">지도로 보기</button>
        <button class="store_tag"> 가게로 보기</button>
      </div>
      <!-- 지도 or Category Store -->
      <div class="category">
        <kakao-map></kakao-map>
        <category-store></category-store>
      </div>
      <!-- 검색란 -->
      <div>
        <span>총 6 개 검색 결과</span>
        <input type="text" name="" id="">
        <span>아이콘</span>
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
import kakaoMap from '@/components/kakaoMap.vue';
import categoryStore from '@/components/categoryStore.vue';
import productItem from '@/components/productItem.vue';

export default {
  name: "ShopView",

  components: {
    kakaoMap,
    categoryStore,
    productItem,
  }
};
</script>

<style scoped>

.main_container{
      width:100%;
      height:100%;

  }
    
.main_container .mobile{ 
    width:360px;
    height: 1300px;
    margin:0 auto;
}

.category{
  height: 245px;
  background-color: greenyellow;
}
</style>
