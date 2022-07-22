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
    <div class="search-container">
        <div class="row">
          <div class="col-4">
            <span class="search-result">총 3개 검색 결과</span>
          </div>
        
          <div class="col">
            <input class="search-box" type="search" placeholder=" 가게명을 입력해주세요">
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
    <!-- 검색 결과 -->
    <div>
      <store-item></store-item>
      <store-item></store-item>
    </div>
  </div>
</template>

<script>
import StoreItem from '@/components/StoreItem.vue'

export default {
    name: "CategoryStore",

    components:{
      StoreItem,
    }
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

.location {
  text-align: left;
  padding-bottom: 10px;
}

.search-container .row .col {
  padding: 0;
}
.search-result{
  padding-right: 5px; 
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