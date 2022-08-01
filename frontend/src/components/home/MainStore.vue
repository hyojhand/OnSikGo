<template>
  <div class="container">
    <!-- 검색 서칭 -->
    <div class="search-container mb-5">
        <div class="row">
          <div class="col">
            <!-- 검색 아이콘 -->
            <input class="search-box" type="search" placeholder="        가게 명을 입력해주세요.">
          </div>
        </div>
    </div>
    <!-- 상위 가게 카테고리 -->
    <div class="row">
      <div class="col">
        <img src="@/assets/images/dessert.png" alt="dessert">
      </div>
      <div class="col">
        <img src="@/assets/images/japanesefood.png" alt="japanesefood">
      </div>
      <div class="col">
        <img src="@/assets/images/chinesefood.png" alt="chinesefood">
      </div>
    </div>
    <!-- 하위 가게 카테고리 -->
    <div class="row">
      <div class="col">
        <img src="@/assets/images/koreanfood.png" alt="koreanfood">
      </div>
      <div class="col">
        <img src="@/assets/images/snackbar.png" alt="snackbar">
      </div>
      <div class="col">
        <img src="@/assets/images/westernfood.png" alt="westernfood">
      </div>
    </div>

  </div>
</template>

<script>
import {mapGetters} from 'vuex';

export default {
  name: "CategoryStore",

  computed: {
    ...mapGetters("store",[
    "currentAddress"
  ]),
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

.search-box{
  border: solid 1px;
  border-radius: 12px;
  font-size: 12px;
  background-repeat: no-repeat;
  padding: 5px 5px;
  width: 370px;
  background-color: #FFF;
}

.search-box::-webkit-input-placeholder{
  background-image: url(https://cdn1.iconfinder.com/data/icons/hawcons/32/698627-icon-111-search-256.png) ;
  background-size: contain;
  background-position:  1px center;
  background-repeat: no-repeat;
  text-indent: 0;
}

</style>