<template>
  <div class="row col-6">
    <!--상품정렬-->
    <div class="row">
      <div class="">
        <img
          src="@/assets/images/hambuger.jpg"
          alt="IMG-PRODUCT"
          class="img mt-2 mb-5"
        />
        <div class="bg-secondary bg-opacity-50 text-white fw-bold">
          <p>{{ itemName }}</p>
        </div>
        <div class="d-flex">
          <div class="me-auto">
            <span>정가</span>
            <p>{{ price }}</p>
            <span>수량</span>
            <p>0</p>
          </div>
          <b-button
            @click="prodmodify()"
            size="sm"
            variant="success"
            class="button"
            >수정</b-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AllProductList",
  props: {
    no: Number,
    itemId: Number,
    itemName: String,
    price: Number,
    itemImgUrl: String,
    comment: String,
  },

  create() {},

  methods: {
    prodmodify() {
      this.$router.push({
        name: "prodChange",
        params: { itemId: this.itemId, storeId: this.no },
      });
    },
  },
};
</script>

<style>
.img {
  width: 100%;
}

.button {
  width: 35%;
}
</style>
