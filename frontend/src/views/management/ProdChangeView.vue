<template>
  <div class="container">
    <!--매장선택-->
    <div class="store-name mx-auto">
      {{ storeDto.storeName }}
    </div>
    <!--수정정보나타내기-->
    <div>
      <div class="img-box">
        <img :src="`${itemDto.itemImgUrl}`" alt="IMG-PRODUCT" />
        <div class="img-input">
          <input v-on:change="fileSelect()" type="file" ref="imgFile" />
        </div>
      </div>
      <input
        class="item-name"
        v-model="this.itemDto.itemName"
        type="text"
        placeholder="상품명을 입력해주세요."
      />

      <!--상품정보-->

      <form class="info-container">
        <div class="info-box row">
          <div class="col-4">정상 판매가</div>
          <input
            class="col-4 price-input"
            v-model="this.itemDto.price"
            type="text"
            placeholder="정상 판매가를 입력해주세요."
          />
          <div class="col-1">원</div>
        </div>

        <div class="info-box row">
          <div class="col-4">특이사항</div>
          <input
            class="col-7 comment-input"
            v-model="this.itemDto.comment"
            type="text"
            placeholder="특이사항을 입력해주세요"
          />
        </div>
      </form>
    </div>
    <!--버튼모음-->
    <div class="btn-box">
      <button @click="prodchange" class="border-m radius-m edit-btn">
        수 정
      </button>
      <product-delete-modal :no="this.itemDto.itemId"></product-delete-modal>
    </div>
  </div>
</template>

<script>
import ProductDeleteModal from "@/components/management/ProductDeleteModal.vue";
import http from "@/util/http-common";
export default {
  name: "ProdChangeView",

  components: { ProductDeleteModal },
  data() {
    return {
      imgFile: "",
      itemDto: {},
      storeDto: {},
      storeId: Number,
    };
  },

  async created() {
    await http.get(`/store/${this.$route.params.storeId}`).then((response) => {
      this.storeDto = response.data;
    });

    await http.get(`/item/${this.$route.params.itemId}`).then((response) => {
      this.itemDto = response.data;
    });
    this.imgFile = this.itemDto.itemImgUrl;
  },

  methods: {
    fileSelect() {
      console.log(this.$refs);
      this.imgFile = this.$refs.imgFile.files[0];
    },
    prodchange() {
      this.itemDto = {
        itemName: this.itemDto.itemName,
        price: this.itemDto.price,
        comment: this.itemDto.comment,
      };
      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "itemDto",
        new Blob([JSON.stringify(this.itemDto)], { type: "application/json" })
      );
      http.put(`/item/${this.itemDto.itemId}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      this.$router.push("/allprod/");
      this.$router.go();
      console.log(this.itemDto);
    },
  },
};
</script>

<style scoped>
img {
  width: 100%;
}
.img-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.img-input {
  margin-top: 3%;
}
.price-input {
  width: 47%;
  padding: 8px 3px;
  color: rgba(0, 0, 0, 60%);
  text-align: right;
}
.comment-input {
  width: 47%;
  padding: 8px 3px;
  color: rgba(0, 0, 0, 60%);
  border-bottom: 1px solid rgba(0, 0, 0, 10%);
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
  width: 100%;
  margin: 5% auto;
  text-align: center;
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
}
.price {
  text-align: start;
}
.btn-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  margin: 0 auto 5% auto;
}
.edit-btn {
  width: 74px;
}
</style>
