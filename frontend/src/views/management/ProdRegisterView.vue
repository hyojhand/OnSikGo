<template>
  <div>
    <!--매장선택-->
    <div class="mt-5">
      {{ this.store.storeName }}
    </div>

    <!--상품 이미지 업로드-->
    <div class="mt-5">
      <b-img :src="previewImg" height="200px" width="200px" />
      <p class="d-flex justify-content-end">
        <input @change="fileSelect" type="file" />
      </p>
    </div>

    <!--입력항목-->
    <div class="d-flex justify-content-center mt-5">
      <!--정보입력칸-->
      <div class="col-8 info-box mt-5">
        <form class="mb-2 form-box">
          <v-text-field
            v-model="itemName"
            label="상품명을 입력해주세요."
            color="black"
          ></v-text-field>
          <v-text-field
            v-model="itemPrice"
            label="정상가를 입력해주세요."
            color="black"
          ></v-text-field>
          <v-text-field
            v-model="itemComment"
            label="특이사항을 적어주세요."
            color="black"
          ></v-text-field>
        </form>

        <button @click="register" class="border-m radius-m notice-btn">
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapGetters } from "vuex";
export default {
  name: "ProdRegisterView",

  data() {
    return {
      imgFile: "",
      itemName: "",
      itemPrice: "",
      itemComment: "",
      itemDto: [],
      itemId: "",
      previewImg: "https://kare.ee/images/no-image.jpg",
      store: {},
    };
  },
  computed: {
    ...mapGetters("itemStore", ["getItemId"]),
    ...mapGetters("storeStore", ["getStoreId"]),
  },

  created() {
    http.get(`/store/${this.getStoreId}`).then((response) => {
      this.store = response.data;
    });
  },

  methods: {
    register() {
      this.itemDto = {
        itemName: this.itemName,
        price: this.itemPrice,
        comment: this.itemComment,
      };
      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "itemDto",
        new Blob([JSON.stringify(this.itemDto)], { type: "application/json" })
      );
      http
        .post(`/item/register/${this.getStoreId}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          if (response.status == 200) {
            this.$router.push("/allprod");
          } else {
            this.$alert("상품 등록에 실패했습니다. 다시 한번 확인해주세요.");
          }
        });
    },
    fileSelect(event) {
      var input = event.target;

      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.previewImg = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImg = null;
      }
      this.imgFile = input.files[0];
    },
  },
};
</script>

<style scoped>
.store-name {
  width: 40%;
  font-size: 30px;
  text-align: center;
  padding: 2% 0;
}
.option-store {
  width: 100%;
}
.notice-btn {
  width: 110px;
}
.info-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.form-box {
  width: 100%;
}
</style>
