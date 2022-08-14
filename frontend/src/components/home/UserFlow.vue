<template>
  <div class="info-container">
    <!-- 사용자 사용법 -->
    <p class="info-title pt-3">소비자's 온식고 이용방법</p>
    <div class="row">
        <div class="col-4">
            <p class="order-number">1</p>
            <img src="@/assets/images/step_1.png" alt="" style="width:100%;">
            <p>로그인</p>
        </div>
        <div class="col-4">
            <p class="order-number">2</p>
            <img src="@/assets/images/step_2.png" alt="" style="width:100%;">
            <p>상품 확인</p>
        </div>
        <div class="col-4">
            <p class="order-number">3</p>
            <img src="@/assets/images/step_3.png" alt="" style="width:100%;">
            <p>상품 선택</p>
        </div>
        <div class="col-4">
            <p class="order-number">4</p>
            <img src="@/assets/images/step_4.png" alt="" style="width:100%;">
            <p>주문 요청</p>
        </div>
        <div class="col-4">
            <p class="order-number">5</p>
            <img src="@/assets/images/step_5.png" alt="" style="width:100%;">
            <p>알림 확인 및 픽업</p>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name : "UserFlow",
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