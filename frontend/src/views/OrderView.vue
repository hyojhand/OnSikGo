<template>
  <div class="main_container">
    <div class="mobile" >
      <!-- 주소 -->
      <div class="location">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pin-map" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"/>
  <path fill-rule="evenodd" d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"/>
</svg>
        <span> 서구 임시수도기념로 37, 구덕로 119길</span>
      </div>
      <!-- 단일 상품 설명 -->
      <!-- 상품명, 상품이미지, 주소,현재위치에서 거리, 매장상세보기 버튼, 정가, 할인가, 재고, 한줄평-->
      <div class="product">
        <!-- 상품명 -->
        <h3>불고기 버거11</h3>
        <!-- 상품이미지, 상세설명 -->
        <div>
          <div>
            <img class="product-image" src="@/assets/images/hambuger.jpg" alt="e-mart">
          </div>
          <div>
            <p>매장위치 : 이마트 37</p>
            <p>300m, 도보로 3분</p>
            <button>매장 상세보기</button>
            <p>가격 : 
              <span>5700원</span>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
              </svg>
              <span>4000원</span>
            </p>
          </div>
        </div>
        <!-- 꿀팁 -->
        <div>
          <!-- <i class="fa-solid fa-user-chef"></i> -->
          <!-- <i class="fa-light fa-user-chef"></i> -->
          <i class="fa-light fa-utensils"></i>
          <p class="" style="word-break: keep-all;">생각보다 매워요.
            =.= 땅콩이 들어가있으니 주의바랍니다. 
            식었다면 랩씌우고 전자레인지에 30초~!</p>
        </div>
        
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

* {
  font-size: 15px;
}
.main_container{
      width:100%;
      height:100%;

  }
    
.main_container .mobile{ 
    width:360px;
    height: 800px;
    margin:0 auto;
}

.main_container .mobile .location .bi-bookmark-check{
  margin-left: 0.5rem;
}

.main_container .mobile .product .product-image{
  width: 150px;
  height: 130px;
}


</style>
