<template>
  <div>
    <div class="mt-10">
      <h3>♥오늘 영업 끝!!♥</h3>
      <span>오늘 하루 수고하셨습니다!</span>
    </div>
    <div class="mt-10">
      <p>
        <strong>{{ storeName }}매장</strong>의 영업을 정말로 종료하시겠습니까?
      </p>
      <!--글자 사이즈 줄이고 빨간색으로 표시할부분!-->
      <span
        >💥영업을 종료하시면 <br />오늘 등록하신 마감할인 상품이 전부
        <strong id="redColor">초기화</strong>됩니다💥</span
      >
    </div>
    <br />
    <br />

    <div>
      <b-container class="bv-example-row">
        <b-row align-h="around">
          <b-col cols="4">
            <button @click="noClose" class="border-l radius-m">아니오</button>
          </b-col>
          <b-col cols="4">
            <button @click="realClose" class="border-l radius-m">
              영업종료
            </button>
          </b-col>
        </b-row>
      </b-container>
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
    noClose() {
      this.$router.push("/mypage/owner");
    },
  },
};
</script>

<style scoped>
#redColor {
  font: red;
}
</style>
