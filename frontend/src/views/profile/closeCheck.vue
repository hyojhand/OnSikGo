<template>
  <div>
    <h3>영업종료 확인 페이지입니다</h3>
    <p>{{ storeName }}매장의 영업을</p>
    <p>정말로 종료하시겠습니까?</p>
    <br />
    <br />
    <span
      >영업을 종료하시면 <br />오늘 등록하신 마감할인 상품이 <br />전부
      <strong id="redColor">초기화</strong>됩니다</span
    >
    <div>
      <button @click="realClose" class="border-m radius-m">
        예 영업을 종료합니다
      </button>
      <button @click="noClose" class="border-m radius-m">아니오</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "closeCheck",
  data() {
    return {
      storeId: Number,
      storeName: "",
    };
  },
  created() {
    http.get(`/store/${this.$route.params.storeId}`).then((response) => {
      this.storeName = response.data.storeName;
      console.log(this.storeName);
    });
  },
  methods: {
    realClose() {
      this.storeId = this.$route.params.storeId;
      console.log(this.storeId);
      http.put(`/store/close/${this.storeId}`).then((response) => {
        console.log(response);
      });
    },
    // noClose() {
    //   this.$route.push(
    //     name:
    //   )
    // }
  },
};
</script>

<style scoped>
#redColor {
  font: red;
}
</style>
