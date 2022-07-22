<template>
  <div>
    <h1>상품수정페이지</h1>
    <div class = "d-flex justify-content-center">
      <b-dropdown id="dropdown1" text="1번째 매장 이름" style="border-color:#63BF68;">
        <b-dropdown-item>2번째 매장 이름</b-dropdown-item>
        <b-dropdown-item>3번째 매장 이름</b-dropdown-item>
        <b-dropdown-item>4번째 매장 이름</b-dropdown-item>
      </b-dropdown>
    </div>

    <div class="mt-5">
      <img src="#" alt="IMG-PRODUCT" class="mt-2 mb-5">
      <div class="bg-secondary bg-opacity-75 text-white fw-bold fs-2" height="">
        <p>상품이름</p>
      </div>
      <div class="d-flex justify-content-center mt-5">
        <div class="col-4">
          <p class="mb-5">정상가</p>
          <br>
          <p class="m">할인가</p>
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
    </div>
  </div>
</template>

<script>
export default {
  name: "ProdChangeView",
};
</script>

<style scoped></style>
