<template>
  <div class="container">
    <div class="row">
      <!-- 마커 -->
      <div class="col-2">
        <h1>1</h1>
      </div>
      <!-- 상품이미지 -->
      <div class="col-2">
        <img src="@/assets/images/hambuger.jpg" style="widght:58px; height:58px" alt="">
      </div>
      <!-- 상품 설명 -->
      <!-- 상품 이름, 매장위치, 거리, 할인율, 정가, 할인가 -->
      <div class="col-5">
        <p class="product-name">불고기버거</p>
        <p class="product-location">매장위치 : 이마트 37</p>
        <p>300m, 도보로 3분</p>
        <p>30%</p>
        <span>6000원</span>
        <span>3000원</span>
      </div>
      <!-- 재고 & 주문하기 -->
      <div class="col-3">
        <p>재고 : <span>3</span> 개</p>
        <button><router-link :to="{ name: 'orderView' }">주문하기</router-link></button>
      </div>
    </div>
    

    <hr style="border : 2px solid #222">
  </div>
</template>

<script>
export default {
    name:'ProductItem',
}
</script>

<style>

</style>