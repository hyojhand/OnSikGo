<template>
  <div>
    <div class="d-flex justify-content-end">
      <select
        id="dropdown1"
        style="border-color: #63bf68"
        @change="selectStore($event)"
      >
        <option :key="index" :value="store" v-for="(store, index) in stores">
          {{ store.storeName }}
        </option>
      </select>
    </div>
    <br />
    <!--이미지 가져오기-->
    <b-card>
      <b-row>
        <b-col md="3">
          <img fluid :src="`${store.storeImgUrl}`" height="90" width="100" />
        </b-col>
        <b-col md="9">
          <div class="text-align-center" id="cardInText">
            <br />
            <span>안녕하세요, {{ store.storeName }}입니다.</span>
            <p>{{ store.location }}</p>
          </div>
          <div class="d-flex justify-content-end">
            <b-button
              size="sm"
              pill
              variant="outline-danger"
              @click="storeClose()"
              >영업종료</b-button
            >
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
    <hr />

    <discountList
      v-for="(saleItem, index) in saleItemList"
      :key="index"
      v-bind="saleItem"
      :no="storeId"
    />

    <!--모달 -->
    <MemberQuitModal></MemberQuitModal>
    <StoreInfoDiscardModal :no="this.storeId"></StoreInfoDiscardModal>
  </div>
</template>

<script>
import discountList from "@/components/profile/discountList.vue";
import http from "@/util/http-common";
export default {
  name: "MypageOwnerView",
  components: {
    discountList,
  },
  data() {
    return {
      stores: [],
      store: {},
      storeId: "",
      saleItemList: [],
    };
  },
  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http.get("/store/list").then((response) => {
      this.stores = response.data;
      this.store = response.data[0];
      this.storeId = response.data[0].storeId;
    });

    await http.get(`/sale/list/${this.storeId}`, {}).then((response) => {
      this.saleItemList = response.data;
    });
  },
  methods: {
    dataAnalysis() {
      this.$router.push("/mypage/owner/analysis");
    },
    storechange() {
      this.$router.push({
        name: "storeInfoChange",
        params: { storeId: this.storeId },
      });
    },
    selectStore(event) {
      this.store(event.target.value);
    },
    storeClose() {
      http.put(`/store/close/${this.storeId}`).then((response) => {
        if (response.status == 200) {
          alert("가게 결산이 완료되었습니다.");
        } else {
          alert("해당 날짜의 판매정보가 없습니다.");
        }
      });
    },
  },
};
</script>

<style scoped>
#drowpdown1 {
  border-color: #63bf68;
  border-block-width: 4px;
  background-color: white;
}
#space-even {
  display: flex;
  justify-content: space-evenly;
}
</style>
