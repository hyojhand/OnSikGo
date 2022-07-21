<template>
  <div class="main_container">
    <div class="mobile" >
      <!-- 현재 주소 -->
      <div>
        <span>아이콘 | </span>
        <span>서구 임시수도기념로 37, 구덕로 119길</span>
      </div>
      <!-- 단일 상품 설명 -->
      <!-- 상품명, 상품이미지, 주소,현재위치에서 거리, 매장상세보기 버튼, 정가, 할인가, 재고, 한줄평-->
      <div class="product">
        <h3>불고기 버거</h3>
        <img src="" alt="">
        <p></p>
      </div>
      <!-- 픽업 주문서 -->
      <!-- 고객명, 매장, 상품명, 수량, 소요시간, 경고문, 픽업 신청버튼 -->
      <div class="order">
        <p>주문서</p>
        <p></p>
        <input type="text" name="" id="">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderView",
};
</script>

<style scoped>
.main_container{
      width:100%;
      height:100%;

  }
    
.main_container .mobile{ 
    width:360px;
    height: 800px;
    margin:0 auto;
}
.order{
  height: 300px;
  background-color: blue;
}

.product{
  height: 300px;
  background-color: red;
}
</style>
