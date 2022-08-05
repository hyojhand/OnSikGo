<template>
  <div>
    <order-list
      v-for="(order, index) in orderList"
      :key="index"
      :order = order
    ></order-list>
  </div>
</template>

<script>
import orderList from "@/components/profile/orderList.vue";
import http from '@/util/http-common.js';
import { mapActions } from "vuex";


export default {
  name: "OrderHistoryView",
  data(){
    return{
      orderList : [],
    }
  },
  created(){
    this.getOrderList()
  },
  components: {
    orderList,
  },
  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    getOrderList() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http
        .get('/order')
        .then((response) =>{
          console.log(response.data)
          this.orderList = response.data.reverse()
        })
    },
  }
};
</script>

<style scoped></style>
