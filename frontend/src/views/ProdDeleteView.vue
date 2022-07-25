<template>
  <div>
    <br>
    <hr>
    <hr>
    <!--상품 삭제 확인페이지-->
    <div class="head">
      <h3>상품을 삭제를 선택하셨습니다</h3>
      <br>
      <h5 class="danger mb-5">정말로 삭제하시겠습니까?</h5>
    </div>
    <div class="d-flex justify-content-around">
      <a class="btn btn-danger d-grid gap-2 col-4 mx-auto" style="right:150px;">예</a>
      <a class="btn btn-outline-secondary mx-auto">아니오</a>
    </div>
    <hr>
    <hr>
  </div>
</template>

<script>
export default {
  name: "ProdDeleteView",
};
</script>

<style scoped>
  .head {
    margin-top: 50px;
  }

  .danger {
    color: red;
  }
</style>
