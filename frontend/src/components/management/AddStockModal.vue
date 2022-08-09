<template>
  <div>
    <v-dialog class="modal-case" width="344">
      <template v-slot:activator="{ on, attrs }">
        <button
          v-bind="attrs"
          class="border-m radius-s text-m reason"
          v-on="on"
        >
          재고등록
        </button>
      </template>

      <!--수정정보나타내기-->
      <v-card class="modal-content">
        <v-card-title class="d-flex justify-content-center card-title">
          재고 등록하기
        </v-card-title>
        <img :src="`${item.itemImgUrl}`" alt="IMG-PRODUCT" />
        <div class="item-name">
          {{ item.itemName }}
        </div>

        <!--상품정보-->

        <form class="info-container">
          <div class="info-box row">
            <div class="col-5">정상가</div>
            <div class="col-7 price">{{ item.price }}</div>
          </div>
          <div class="info-box row">
            <div class="col-5">할인율</div>
            <div class="col-7 price">
              🔻{{ ((1 - this.salePrice / item.price) * 100).toFixed(2) }}%
            </div>
          </div>

          <div class="info-box row">
            <div class="col-5">할인가</div>
            <input
              class="col-7 content"
              v-model="salePrice"
              type="text"
              placeholder="판매 금액을 입력해주세요."
            />
          </div>
          <div class="info-box row">
            <div class="col-5">수량</div>
            <input
              class="col-7 content"
              v-model="stock"
              type="number"
              placeholder="등록 수량을 입력해주세요."
            />
          </div>
        </form>
        <div class="btn-box">
          <button @click="prodchange" class="border-m radius-m edit-btn">
            수량등록
          </button>
        </div>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "AddStockModal",
  props: {
    item: Object,
    storeId: Number,
  },
  data() {
    return {
      salePrice: "",
      stock: "",
    };
  },

  methods: {
    prodchange() {
      http
        .post(`/sale/${this.storeId}`, {
          itemId: this.item.itemId,
          salePrice: this.salePrice,
          stock: this.stock,
        })
        .then((response) => {
          if (response.status == 200) {
            console.log("완료");
          } else {
            alert("등록안됨");
          }
        })
        .catch((error) => {
          console.log("에러");
          console.log(error);
        });

      this.$router.go();
    },
  },
};
</script>

<style scoped>
.reason {
  display: flex;
  color: white;
  background-color: rgb(140, 184, 131);
  width: 100%;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  text-align: center;
}
.card-title {
  border-bottom: 1px solid rgba(0, 0, 0, 20%);
}
.modal-case {
  width: 100%;
  background-color: white;
}
.modal-content {
  min-height: 500px;
}
.content {
  font-size: 15px;
}
img {
  width: 100%;
}
input {
  width: 53%;
  padding: 0;
  border-bottom: 1px solid black;
  color: rgba(0, 0, 0, 60%);
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.store-name {
  width: 40%;
  font-size: 30px;
  text-align: center;
  padding: 2% 0;
  color: rgba(0, 0, 0, 60%);
}
.item-name {
  font-size: 30px;
  margin: 5% auto;
  border-bottom: 2px solid rgba(0, 0, 0, 10%);
}
.info-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 10%;
}
.info-box {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  padding: 0;
}
.price {
  text-align: start;
  font-size: 25px;
}
.btn-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  margin: 0 auto 5% auto;
}
</style>
