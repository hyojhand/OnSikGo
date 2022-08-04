<template>
  <div>
    <h1>AdminStore</h1>
    <div
      v-for="(stores, index) in storeList"
      :key="index"
      class="row fs-5 text-start">
        가게명: {{ stores.storeName }} <br>
        가게주소: {{ stores.location }}
        <br>
        <v-btn color="error" samll depressed>삭제</v-btn>
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
        })
    }
  },

  created () {
    this.allStore();
  }
};
</script>

<style>

</style>