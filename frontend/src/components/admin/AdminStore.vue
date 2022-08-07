<template>
  <div>
    <h1>AdminStore</h1>
    <div
      v-for="(stores, index) in storeList"
      :key="index"
      class="row fs-5 text-start mt-3">
      <div>
        <p class="text-primary">가게명: {{ stores.storeName }} <br></p> 
        <p>가게주소: {{ stores.location }}</p> 
      </div>
        <br>
        <v-btn @click="deleteStore(stores)" color="error" samll depressed>삭제</v-btn>
        <hr>
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