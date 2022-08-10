<template>
  <div>
    <div
      v-for="(stores, index) in storeList"
      :key="index"
      class="row text-start mt-3">
      <div class="content">
      <div class="detail text-start">
        <p class="text-primary">가게명: {{ stores.storeName }} <br></p> 
        <p>{{ stores.address}}</p> 
      </div>
        <img
          class="siren"
          src="@/assets/images/trash.png"
          @click="deleteStore(stores)"
          alt="삭제버튼이었던것.."
        />
        </div>
        <hr class="mt-5">
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name : "AdminStore",

  data: function () {
    return {
          storeList: [],
    }
  },

  methods: {
    allStore() {
      http
        .get('/store/total')
        .then((response) => {
          this.storeList = response.data;
        })
    },
    deleteStore(stores) {
      http
        .delete(`/store/${stores.storeId}`)
        .then((response) => {
          if (response.status == 200) {
            this.allStore();
          }
        });
    },
  },

  created () {
    this.allStore();
  }
};
</script>

<style>
.content{
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 5%;
  margin-right: 5%;
}
.detail{
  width: 320px;
}
.siren {
  width: 25px;
  height: 25px;
}
</style>