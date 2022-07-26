<template>
  <div>
    <br>
    <hr>
    <hr>
    <!--상품 삭제 확인페이지-->
    <div class="head">
      <h4>상품을 삭제를 선택하셨습니다</h4>
      <br>
      <h5 class="danger mb-5">정말로 삭제하시겠습니까?</h5>
      <br>
    </div>
    <div class="d-flex justify-content-around">
      <b-button @click="prodlist" pill variant="outline-success" class="d-grid gap-2 col-4 mx-auto" style="right:150px;">예</b-button>
      <b-button @click="prodchange" pill variant="danger" class="d-grid col-4 mx-auto">아니오</b-button>
    </div>
    <hr>
    <hr>
  </div>
</template>

<script>
export default {
  name: "ProdDeleteView",

  methods: {
    prodlist () {
      this.$router.push('/allprod')
    },

    prodchange () {
      this.$router.push('/allprod/change')
    },
  }
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
