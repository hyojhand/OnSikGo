<template>
  <div>
    <card id="mypage-card">
      <div class="row">
        <div class="col-3">
          <div class="mt-7 ml-4">
            <img fluid :src="`${store.storeImgUrl}`" height="80" width="100" />
          </div>
        </div>
        <div class="col-7 ml-5">
          <div style="text-align: start">
            <br />
            <span id="color-black">
              안녕하세요,👨‍🍳<br />{{ store.storeName }}입니다. </span
            ><br />
            <span style="color: gray; font-size: 0.7rem"
              >매장 위치: {{ store.location }}</span
            ><br />
            <span style="color: gray; font-size: 0.7rem"
              >매장 휴무일: {{ store.offDay }}</span
            >
          </div>

          <div class="d-flex justify-content-end">
            <!--영업종료 버튼은 빨간색으로 하기-->
            <button id="mypage-button" @click="movetoClose">영업 종료</button>
          </div>
        </div>
      </div>
    </card>

    <br />
    <div>
      <div class="container mt-10">
        <div class="d-flex justify-content-around">
          <button @click="dataAnalysis()" id="mypage-btn">데이터 분석</button>
          <button @click="storechange()" id="mypage-btn">정보수정</button>
        </div>
      </div>
    </div>
    <br />

    <div class="container">
      <div class="font-l sales">오늘 할인 판매 상품</div>
      <discountList
        v-for="(saleItem, index) in saleItemList"
        :key="index"
        v-bind="saleItem"
        :storeId="storeId"
      />
    </div>
  </div>
</template>

<script>
import discountList from "@/components/profile/discountList.vue";
import http from "@/util/http-common";
export default {
  name: "mypageOwnerComponent",
  data() {
    return {
      storeName: "",
      saleItemList: [],
    };
  },
  props: {
    store: Object,
  },
  components: {
    discountList,
  },
  methods: {
    dataAnalysis() {
      this.$router.push("/mypage/owner/analysis");
    },
    storechange() {
      this.$router.push({
        name: "storeInfoChange",
        params: { storeId: this.store.storeId },
      });
    },
    movetoClose() {
      this.$router.push({
        name: "closeCheck",
        params: { storeId: this.store.storeId },
      });
    },
  },
  created() {
    console.log(this.store);
    http.get(`/sale/list/${this.store.storeId}`).then((response) => {
      this.saleItemList = response.data;
    });

    http.get(`/store/close/${this.store.storeId}`).then((response) => {
      console.log(response.data);
    });
  },
};
</script>

<style>
#mypage-card {
  height: 170px;
  width: 400px;
  border-radius: 15px;
  display: inline-block;
  margin-top: 30px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  overflow: hidden;
}
#color-black {
  color: black;
}
#mypage-button {
  height: 25px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #d07127;
  color: #ffffff;
  width: 80px;
}
#mypage-btn {
  height: 40px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #37a62f;
  color: #ffffff;
  width: 100px;
}
</style>
