<template>
  <!--상품정렬-->
  <div>
    <img :src="`${itemImgUrl}`" alt="IMG-PRODUCT" style="height: 150px" />
    <div class="item-name">
      {{ itemName }}
    </div>
    <div class="info-container">
      <div class="item-info">
        <div class="info-box">
          <div>정가 :</div>
          <div class="ml-1">{{ price }}</div>
        </div>
        <div class="info-box">
          <div>수량 :</div>
          <div class="ml-1">{{ this.saleDto.stock}}</div>
        </div>
      </div>
      <button @click="prodmodify()" class="border-m radius-s">수 정</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "AllProductList",
  data() {
    return {
       saleDto: {},
       stock: Number,
    };
  },
  props: {
    storeId: Number,
    itemId: Number,
    itemName: String,
    price: Number,
    itemImgUrl: String,
    comment: String,
  },

  created() {
      http.get(`/sale/${this.itemId}`).then((response) => {
       if(response.status == 200) {
        this.saleDto = response.data;
      }
    });

    
  },

  methods: {
    prodmodify() {
      this.$router.push({
        name: "prodChange",
        params: { itemId: this.itemId, storeId: this.storeId },
      });
    },
  },
};
</script>

<style scoped>
img {
  width: 100%;
  height: 100%;
  padding-bottom: 3%;
  border-bottom: 2px solid rgba(255, 255, 255, 50%);
}
button {
  color: white;
  background-color: rgb(140, 184, 131);
  width: 100%;
}
.item-name {
  margin: 5% auto;
  padding-bottom: 3%;
  border-bottom: 2px solid rgba(255, 255, 255, 50%);
}
.info-container {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  margin: 0;
  flex-wrap: wrap;
}
.item-info {
  padding: 0;
}
.info-box {
  display: flex;
  flex-direction: row;
  padding-bottom: 3%;
}
</style>
