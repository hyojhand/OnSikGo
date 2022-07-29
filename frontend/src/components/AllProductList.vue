<template>
  <div class="row col-6">
		<!--상품정렬-->
    <div class="row">
      <div class="">
        <img src="@/assets/images/hambuger.jpg" alt="IMG-PRODUCT" class="img mt-2 mb-5">
        <div class="bg-secondary bg-opacity-50 text-white fw-bold">
          <p>상품이름</p>
        </div>
        <div class="d-flex">
          <div class="me-auto">
            <span>할인가</span>
            <p>3000</p>
            <span>수량</span>
            <p>2</p>
          </div>
          <b-button @click="prodmodify()" size="sm" variant="success" class="button">수정</b-button>
        </div>
      </div>
    </div>
	</div>
</template>

<script>
export default {
	name: 'AllProductList',

  methods: {
    prodmodify() {
      this.$router.push('/allprod/change')
    },
  } 
}
</script>

<style>

  .img {
  width: 100%;
  }

  .button {
    width: 35%;
  }
</style>