<template>
  <div>
    <h1>{{ store.storeId }}</h1>
    <b-card>
      <b-row>
        <b-col md="3">
          <img fluid :src="`${store.storeImgUrl}`" height="90" width="100" />
        </b-col>
        <b-col md="9">
          <div class="text-align-center" id="cardInText">
            <br />
            <span>안녕하세요, {{ store.storeName }}입니다.</span>
            <p>매장 위치: {{ store.location }}</p>
          </div>
          <div class="d-flex justify-content-end">
            <button @click="movetoClose">영업 종료</button>
          </div>
        </b-col>
      </b-row>
    </b-card>

    <br />
    <div id="space-even">
      <b-button size="sm" @click="dataAnalysis()" pill variant="outline-success"
        >데이터 분석</b-button
      >
      <b-button size="sm" @click="storechange()" pill variant="outline-success"
        >정보수정</b-button
      >
    </div>
    <div class="font-l sales">오늘 할인 판매 상품</div>
    <discountList
      v-for="(saleItem, index) in saleItemList"
      :key="index"
      v-bind="saleItem"
      :storeId="storeId"
    />
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
  },
};
</script>

<style></style>
