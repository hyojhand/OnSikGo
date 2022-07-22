<template>
  <div class="container">
    <!-- 상위 가게 카테고리 -->
    <div class="row">
      <div class="col">
        <img src="@/assets/images/dessert.jpg" alt="dessert">
        <p>디저트</p>
      </div>
      <div class="col">
        <img src="@/assets/images/japanesefood.jpg" alt="japanesefood">
        <p>일식</p>
      </div>
      <div class="col">
        <img src="@/assets/images/chinesefood.jpg" alt="chinesefood">
        <p>중식</p>
      </div>
    </div>
    <!-- 하위 가게 카테고리 -->
    <div class="row">
      <div class="col">
        <img src="@/assets/images/koreanfood.jpg" alt="koreanfood">
        <p>한식</p>
      </div>
      <div class="col">
        <img src="@/assets/images/snackbar.jpg" alt="snackbar">
        <p>분식</p>
      </div>
      <div class="col">
        <img src="@/assets/images/westernfood.jpg" alt="westernfood">
        <p>양식</p>
      </div>
    </div>
    <!-- 검색 서칭 -->
    <div>
        <span>총 3 개 검색 결과</span>
        <input type="text" name="" id="">
        <button><i class="fa-regular fa-magnifying-glass"></i></button>
    </div>
    <!-- 검색 결과 -->
  </div>
</template>

<script>
export default {
    name: "CategoryStore",
}
</script>

<style scoped>
 .container {
  width: 360px;

 }
 .container .row .col{
  font-size: 20px;
  font-weight: bolder;
  padding: 10px;
 }
 .container .row .col img{
  width: 70px;
  height: 70px;
 }

</style>