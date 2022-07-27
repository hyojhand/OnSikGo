<template>
  <div>
    <h1>점주 마이페이지</h1>

    <div class = "d-flex justify-content-end">
    <b-dropdown id="dropdown1" text="1번째 매장 이름" class="m-md-2" style="border-color:#63BF68;">
      <b-dropdown-item>2번째 매장 이름</b-dropdown-item>
      <b-dropdown-item>3번째 매장 이름</b-dropdown-item>
      <b-dropdown-item>4번째 매장 이름</b-dropdown-item>
    </b-dropdown>
    </div>
    <br>
    <!--이미지 가져오기-->
    <b-card>
            <b-row>
            <b-col md="3">
              <img fluid src="@/assets/images/이마트24.png" height="50" width="50">

            </b-col>
            <b-col md="9">
              <div class="text-align-center" id = "cardInText">
                <br>
                  <span>안녕하세요, 온식고입니다.</span>
                  <p>매장 위치 가져오기</p>

              </div>
              <div class = "d-flex justify-content-end">
            <b-button size="sm" pill variant="outline-danger">영업종료</b-button>
            </div>
            </b-col>
            </b-row>
            </b-card>

    <br>
    <div id="space-even">
    <b-button size="sm" @click="dataAnalysis()" pill variant="outline-success">데이터 분석</b-button>
    <b-button size="sm" @click="storechange()" pill variant="outline-success">정보수정</b-button>
    </div>
    <hr>
    
    <discountList></discountList>
    
    <!--모달 -->
    <MemberQuitModal></MemberQuitModal>
    <StoreInfoDiscardModal></StoreInfoDiscardModal>
  </div>
</template>

<script>
import discountList from "@/components/discountList.vue";
import StoreInfoDiscardModal from "@/components/StoreInfoDiscardModal.vue";
import MemberQuitModal from "@/components/MemberQuitModal.vue";
export default {
  name: "MypageOwnerView",
  components: {
    discountList,
    StoreInfoDiscardModal,
    MemberQuitModal,
  },
  methods: {
    dataAnalysis() {
      this.$router.push('/mypage/owner/analysis')
    },
    storechange() {
      this.$router.push('/mypage/store/infochange')
    },
  }
};
</script>

<style scoped>
#drowpdown1 {
  border-color : #63BF68;
  border-block-width: 4px;
  background-color: white;
}
#space-even {
  display: flex;
  justify-content: space-evenly; 
}
</style>
