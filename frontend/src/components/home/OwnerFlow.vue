<template>
  <div>
    <!-- 점주 사용법 -->
    <p class="info-title pt-3">업주's 온식고 이용방법</p>
    <div class="row">
        <div class="col-4">
            <p class="order-number">1</p>
            <img src="@/assets/images/ownerstep_1.png" alt="" style="width:100%;">
            <p>로그인</p>
        </div>
        <div class="col-4">
            <p class="order-number">2</p>
            <img src="@/assets/images/ownerstep_2.png" alt="" style="width:100%;">
            <p>상품 등록</p>
        </div>
        <div class="col-4">
            <p class="order-number">3</p>
            <img src="@/assets/images/ownerstep_3.png" alt="" style="width:100%;">
            <p>재고 수량 등록</p>
        </div>
        <div class="col-4">
            <p class="order-number">4</p>
            <img src="@/assets/images/ownerstep_4.png" alt="" style="width:100%;">
            <p>주문 확인</p>
        </div>
        <div class="col-4">
            <p class="order-number">5</p>
            <img src="@/assets/images/ownerstep_5.png" alt="" style="width:100%;">
            <p>승인 후 제품 준비</p>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name:"OwnerFlow"
}
</script>

<style scoped>
  .info-title {
    font-size: 18px;
  }
  .order-number {
    position: relative;
    margin: 0px;
    left: 20px;
    background-color: red;
    border-radius: 100%;
    width: 22.5px;
    height: 22.5px;
    color: #fff;
  }
</style>

