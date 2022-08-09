<template>
  <div>
    <div
      v-for="(stores, index) in storeList"
      :key="index"
      class="row text-start mt-3">
      <div>
        <p class="text-primary fs-5">가게명: {{ stores.storeName }} <br></p> 
        <p>가게주소: {{ stores.address}}</p> 
      </div>
        <br>
        <v-btn @click="deleteStore(stores)" color="error" style="width: 100px; left:300px;" depressed>삭제</v-btn>
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
          console.log(response.data);
        })
    },
    deleteStore(stores) {
      console.log(stores);
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

</style>