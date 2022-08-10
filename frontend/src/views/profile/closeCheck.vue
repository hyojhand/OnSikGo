<template>
  <div>
    <div class="mt-16">
      <img src="@/assets/images/closed.png" width="150" height="130" /><br />
      <div class="mt-5">
        <span style="font-size: 1.3rem">🌏오늘 영업 끝!!🌏</span><br />
        <span>오늘 하루도 수고하셨습니다!</span>
      </div>
    </div>
    <div class="mt-10">
      <span
        >"{{ storeName }}" 매장의 영업을 <br />정말로 종료하시겠습니까? </span
      ><br />
      <!--글자 사이즈 줄이고 빨간색으로 표시할부분!-->
      <div class="mt-7">
        <span id="red-small"
          >💥영업을 종료하시면 오늘 등록하신 마감할인 상품이 전부
          초기화됩니다💥</span
        >
      </div>
    </div>
    <br />
    <br />

    <div class="d-flex justify-content-around">
      <button @click="noClose" class="border-l radius-m">아니오</button>
      <button @click="realClose" class="border-l radius-m">영업종료</button>
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
  async created() {
    await http.get(`/store/${this.$route.params.storeId}`).then((response) => {
      console.log(response.data);
      this.storeName = response.data.storeName;
      console.log(this.storeName);
    });

    // await http
    //   .get(`sale/list/${this.$route.params.storeId}`)
    //   .then((response) => {
    //     console.log(response.data);
    //   });
  },
  methods: {
    realClose() {
      this.storeId = this.$route.params.storeId;
      console.log(this.storeId);
      http.put(`/store/close/${this.storeId}`).then((response) => {
        if (response.data == "fail") {
          alert("오늘 해당 매장 정보가 없습니다!");
        } else {
          alert("매장 결산이 완료되었습니다");
          this.$router.push("/mypage/owner");
        }
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
