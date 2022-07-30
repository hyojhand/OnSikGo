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
          <img
            fluid
            src="@/assets/images/이마트24.png"
            height="50"
            width="50"
          />
        </b-col>
        <b-col md="9">
          <div class="text-align-center" id="cardInText">
            <br />
            <span>안녕하세요, 온식고입니다.</span>
            <p>매장 위치 가져오기</p>
          </div>
          <div class="d-flex justify-content-end">
            <b-button size="sm" pill variant="outline-danger"
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

    <discountList> </discountList>

    <!--모달 -->
    <MemberQuitModal></MemberQuitModal>
    <StoreInfoDiscardModal></StoreInfoDiscardModal>
  </div>
</template>

<script>
import discountList from "@/components/discountList.vue";
import StoreInfoDiscardModal from "@/components/StoreInfoDiscardModal.vue";
import MemberQuitModal from "@/components/MemberQuitModal.vue";
import http from "@/util/http-common";
export default {
  name: "MypageOwnerView",
  components: {
    discountList,
    StoreInfoDiscardModal,
    MemberQuitModal,
  },
  data() {
    return {
      stores: [],
      storeId: "",
      date: Date.today().toString("yyyyMMdd"),
    };
  },
  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http.get("/store/list").then((response) => {
      this.stores = response.data;
      this.storeId = response.data[0].storeId;
    });

    console.log(this.date);

    await http
      .get(`/sale/list/${this.storeId}`, {
        date: this.date,
      })
      .then((response) => {
        console.log(response.data);
      });
  },
  methods: {
    dataAnalysis() {
      this.$router.push("/mypage/owner/analysis");
    },
    storechange() {
      this.$router.push("/mypage/store/infochange");
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
