<template>
  <div>
    <b-card>
      <b-row>
        <b-col md="4">
          <div class="align-center">
            <img fluid :src="`${store.storeImgUrl}`" height="120" width="100" />
          </div>
        </b-col>
        <b-col md="8" class="ml-auto p-3">
          <div class="text-align-center" id="cardInText">
            <br />
            <h3>👨‍🍳안녕하세요,<br />{{ store.storeName }}입니다.</h3>
            <span>매장 위치: {{ store.location }}</span
            ><br />
            <span>매장 휴무일: {{ store.offDay }}</span>
          </div>
          <div class="d-flex justify-content-end">
            <!--영업종료 버튼은 빨간색으로 하기-->
            <button @click="movetoClose">영업 종료</button>
          </div>
        </b-col>
      </b-row>
    </b-card>

    <br />
    <div>
      <b-container class="bv-example-row">
        <b-row align-h="around">
          <b-col cols="4">
            <b-button @click="dataAnalysis()" pill variant="outline-success"
              >데이터 분석</b-button
            ></b-col
          >
          <b-col cols="4">
            <b-button @click="storechange()" pill variant="outline-success"
              >정보수정</b-button
            ></b-col
          >
        </b-row>
      </b-container>
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

<style></style>
