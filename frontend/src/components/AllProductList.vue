<template>
  <div>
		<!--상품정렬-->
    <div class="row justify-content-center">
      <div class="mt-2 col-6">
        <img src="@/assets/images/hambuger.jpg" alt="IMG-PRODUCT" class="mt-2 mb-5">
        <div class="bg-secondary bg-opacity-50 text-white fw-bold">
          <p>상품이름</p>
        </div>
        <div class="d-flex">
          <div class="me-auto">
            <p class="mt-1">할인가: 3000</p>
            <p>수량: 2</p>
          </div>
          <button class="btn btn-success">수정</button>
        </div>
      </div>
    </div>
	</div>
</template>

<script>
export default {
	name: 'AllProductList',
}
</script>

<style>

</style>