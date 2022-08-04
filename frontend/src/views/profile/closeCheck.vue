<template>
  <div>
    <div class="mt-10">
      <h3>💙오늘 영업 끝!!🌏</h3>
      <span>오늘 하루도 수고하셨습니다!</span>
    </div>
    <div class="mt-10">
      <p>
        <strong>"{{ storeName }}" 매장</strong>의 영업을 <br />정말로
        종료하시겠습니까?
      </p>
      <!--글자 사이즈 줄이고 빨간색으로 표시할부분!-->
      <span id="red-small"
        >💥영업을 종료하시면 오늘 등록하신 마감할인 상품이 전부
        초기화됩니다💥</span
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
        alert("매장 결산이 완료되었습니다");
        this.$router.push("/mypage/owner");
      });
    },
    noClose() {
      this.$router.push("/mypage/owner");
    },
  },
};
</script>

<style scoped>
#red-small {
  color: rgb(222, 124, 39);
  font-size: 0.75rem;
}
</style>
