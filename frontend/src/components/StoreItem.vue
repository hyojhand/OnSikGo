<template>
  <div class="store-container">
    <div class="row">
      <!-- 가게 이미지 -->
      <div class="col-2">이미지</div>
      <!-- 가게 설명 -->
      <div class="store col">
        <p class="store-name">이마트 24</p>
        <p class="store-prediction">300m, 도보로 3분</p>
      </div>
      <!-- 물품수량 & 버튼 -->
      <div class="col">
        <p>등록물품 : <span>2</span> 개</p>
        <router-link :to="{ name: 'storeView' }">주문하기</router-link>
      </div>
      <hr style="border : 1px solid #222; margin: 3px">
    </div>
  </div>
</template>

<script>
export default {
  name : "StoreItem",
}
</script>

<style scoped>

.store-container{
  padding: 6px;
}

.store-container .row .store{
  text-align: left;
}
.store-container .row .store .store-name{
  font-size: 12px;
  font-weight: bolder;
}
.store-container .row .store .store-prediction{
  font-size: 6px !important;
  color: #B9B9B9;
}
</style>