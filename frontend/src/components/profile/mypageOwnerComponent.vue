<template>
  <div>
    <!--마이페이지 점주 카드-->
    <div class="card" id="mypage-card">
      <!--마이페이지 매장 사진-->
      <img
        class="mt-5"
        fluid
        :src="`${store.storeImgUrl}`"
        width="200"
        height="150"
      />

      <div>
        <div class="mt-2">
          <span id="color-black">
            안녕하세요,👨‍🍳 {{ store.storeName }}입니다.
          </span>
        </div>
        <div class="ml-7 mt-5" style="text-align: start">
          <span class="mypage_asset">매장 위치: </span>
          <span class="mypage">{{ store.address }}</span>
          <span class="mypage">{{ store.extraAddress }}</span
          ><br />
          <span class="mypage_asset">문 닫는 시간: </span>
          <span class="mypage">{{ store.closingTime }}</span
          ><br />
          <span class="mypage_asset">전화번호: </span>
          <span class="mypage">{{ store.tel }}</span
          ><br />
          <span class="mypage_asset">매장 휴무일: </span>
          <span class="mypage">{{ this.storeOffday }}</span
          ><br />
        </div>
      </div>

      <div class="d-flex justify-content-end mt-5 mr-6">
        <!--영업종료 버튼은 빨간색으로 하기-->
        <button
          id="mypage-button"
          @click="movetoClose"
          :disabled="this.discardStoreClose == true"
        >
          영업 종료 {{ discardStoreClose }}
        </button>
      </div>
    </div>

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
      <div v-if="this.discardStoreList.length">
        <discount-list />
      </div>
      <div v-else class="non-msg">
        <div>오늘은 등록한</div>
        <div>상품이 없어요 ㅠ</div>
      </div>
    </div>
  </div>
</template>

<script>
import discountList from "@/components/profile/discountList.vue";
// import http from "@/util/http-common";
import { mapGetters } from "vuex";
export default {
  name: "mypageOwnerComponent",
  data() {
    return {
      storeName: "",
      saleItemList: [],
      closedCheck: "",
    };
  },
  props: {
    store: Object,
  },
  components: {
    discountList,
  },
  computed: {
    ...mapGetters("discardStore", [
      "discardStoreId",
      "discardStoreList",
      "discardStoreClose",
    ]),
    ...mapGetters("offdayStore", ["storeOffday"]),
  },
  methods: {
    dataAnalysis() {
      this.$router.push({
        name: "dataAnalysis",
        params: { storeId: this.store.storeId, storeName: this.storeName },
      });
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
};
</script>

<style>
#mypage-card {
  height: 400px;
  width: 400px;
  border-radius: 15px;
  display: inline-block;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  overflow: hidden;
}
.mypageimg {
  border-radius: 50%;
}
.mypage {
  color: gray;
  font-size: 0.8rem;
}
.mypage_asset {
  color: gray;
  font-size: 0.8rem;
  font-weight: bolder;
}
#mypage-button {
  height: 30px;
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
.non-msg {
  width: 100%;
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.non-msg > div {
  font-size: 30px;
  color: rgba(0, 0, 0, 0.2);
}
.sales {
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
  padding-bottom: 3%;
}
</style>
