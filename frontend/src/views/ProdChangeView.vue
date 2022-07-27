<template>
  <div>
    <!--매장선택-->
    <div class = "d-flex justify-content-center">
      <b-dropdown id="dropdown1" text="1번째 매장 이름" style="border-color:#63BF68;">
        <b-dropdown-item>2번째 매장 이름</b-dropdown-item>
        <b-dropdown-item>3번째 매장 이름</b-dropdown-item>
        <b-dropdown-item>4번째 매장 이름</b-dropdown-item>
      </b-dropdown>
    </div>
    <!--수정정보나타내기-->
    <div class="head">
      <div class="container">
        <img src="@/assets/images/hambuger.jpg" alt="IMG-PRODUCT" class="mt-2 mb-5">
      </div>
      <div class="bg-secondary bg-opacity-75 text-white fw-bold fs-2">
        <p class="head">상품이름</p>
      </div>
      <br>

      <!--상품정보-->
      <div class="d-flex justify-content-center">
        <div class="col-4">
          <p class="mb-5">정상가</p>
          <br>
          <p class="mt-4">할인가</p>
          <p>수량</p>
          <p>특이사항</p>
        </div>
        <div class="col-8">
          <p>6000</p>
          <p>🔻할인율입력해야함</p>
          <input class="form-control form-control-sm" type="text" placeholder="원하시는 금액을 입력해주세요" aria-label=".form-control-sm example"> 
          <input class="form-control form-control-sm mt-2" type="text" placeholder="원하시는 수량을 입력해주세요" aria-label=".form-control-sm example"> 
          <input class="form-control form-control-sm mt-2" type="text" placeholder="특이사항을 입력해주세요" aria-label=".form-control-sm example"> 
        </div>
      </div>
      <br>
      <br>

      <!--버튼모음-->
      <div class="d-flex justify-content-around">
        <b-button @click="prodchange" pill variant="success" class="d-grid gap-2 col-4 mx-auto">수정</b-button>
        <product-delete-modal></product-delete-modal>
      </div>
    </div>
  </div>
</template>

<script>
import ProductDeleteModal from "@/components/ProductDeleteModal.vue";

export default {
  name: "ProdChangeView",

  components: { ProductDeleteModal },

  methods: {
    prodchange() {
      this.$router.push('/allprod/')
    },

  }
};
</script>

<style scoped>
  .container {
    width: 300px;
    height: 220px;
    overflow: hidden;
    position: relative;
  }

  .container > img {
    position: absolute;
    width: 100%;
    /* height: 100%; */
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .head {
    margin-top: 50px;
  }
</style>
