<template>
  <div>
    <!--헤더-->
    <div class="d-flex text-align-center mt-2 ml-2 mr-2">
      <img
        :src="`${storeDto.storeImgUrl}`"
        alt="profile"
        class="col-4"
        width="30"
        height="100"
      />
      <span class="col-4 mt-2 fw-bold">{{ storeDto.storeName }}</span>
      <div class="col-4 mt-2">
        <!--좋아요 버튼-->
        <button 
          @click="like" 
          v-if="likeCheck == fail"
        ><i class="fa-regular fa-heart">좋아요 안된상태{{ likeCheck }}</i>
        </button>
        <!-- 좋아요 된 상태 -->
        <button 
          v-else 
          @click="unlike"
        ><i class="fa-solid fa-heart">좋아요 된상태</i>
        </button>

      </div>
    </div>

    <div>
      <!--지도표시부분-->
      <div>
        <store-kakao-map></store-kakao-map>
      </div>
      <div class="mt-2">
        <ul class="content">
          <li>상세위치: {{ storeDto.location }}</li>
          <li>전화번호: {{ storeDto.tel }}</li>
          <li>영업시간: {{ storeDto.closingTime }}</li>
          <li>휴무일: {{ storeDto.offDay }}</li>
        </ul>
      </div>
      <hr />
    </div>

    <!-- Tab 기능 -->
    <ul class="tabs">
      <li
        v-for="tab in tabs"
        v-bind:class="{ active: tab === selectedTab }"
        :key="tab"
        v-on:click="onClickTab(tab)"
      >
        <span>{{ tab }}</span>
      </li>
    </ul>
    <br />
    <!-- 상품 설명란 -->
    <div class="product mt-3" v-if="selectedTab === tabs[0]">
      <p class="head">📃 해당 매장에서 오늘 등록된 상품</p>
      <div v-if="this.saleItemList.length">
        <store-product-item
          v-for="(saleItem, index) in saleItemList"
          :key="index"
          v-bind="saleItem"
          :no="storeId"
        />
      </div>
      <div v-else class="non-msg">
        <div>오늘은 등록된</div>
        <div>상품이 없어요 ㅠ</div>
      </div>
    </div>
    <div class="product mt-3" v-else>
      <p class="head">🥨 온식고 식구들의 입소문</p>
      <div v-if="this.reviewList.length">
        <store-review
          v-for="(reviewDto, index) in reviewList"
          :key="index"
          v-bind="reviewDto"
        />
      </div>
      <div v-else class="non-msg">
        <div>오늘은 등록된</div>
        <div>상품이 없어요 ㅠ</div>
      </div>
      <!--리뷰입력창-->
      <div class="input-group comment">
        <input
          v-model="reviewContent"
          type="text"
          class="form-control"
          placeholder="리뷰를 입력해주세요"
          aria-label="Input Review"
          aria-describedby="basic-addon1"
        />
        <button @click="registerReview()" @keyup.enter="registerReview()">
          <span class="input-group-text" id="basic-addon1">
            <i class="fa-solid fa-comment"></i>
          </span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import StoreKakaoMap from "@/components/shopping/StoreKakaoMap";
import StoreProductItem from "@/components/shopping/StoreProductItem.vue";
import StoreReview from "@/components/shopping/StoreReview.vue";
import http from "@/util/http-common";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "StoreView",

  components: {
    StoreKakaoMap,
    StoreProductItem,
    StoreReview,
  },

  data: function () {
    return {
      tabs: ["상품", "입소문"],
      selectedTab: "",
      storeDto: {},
      saleItemList: [],
      reviewContent: "",
      reviewList: [],
    };
  },
  computed: {
    ...mapGetters("storeStore", ["getStoreId"]),
    ...mapGetters("store" ["likeCheck"]),
  },

  async created() {
    this.selectedTab = this.tabs[0];

    await http.get(`/store/${this.getStoreId}`).then((response) => {
      this.storeDto = response.data;
      console.log(this.storeDto);
    });

    await http.get(`/sale/list/${this.getStoreId}`).then((response) => {
      this.saleItemList = response.data;
      console.log(response.data);
    });

    await this.selectReview();
    await this.likecheck();
  },

  methods: {
    ...mapActions("store", [
      "getlikeCheck"
    ]),
    onClickTab(tab) {
      this.selectedTab = tab;
    },
    selectReview() {
      http.get(`/review/store/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.reviewList = response.data;
        }
      });
    },
    registerReview() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http
        .post("/review", {
          storeId: this.getStoreId,
          content: this.reviewContent,
        })
        .then((response) => {
          if (response.status == 200) {
            // alert("리뷰작성이 완료되었습니다.");
            this.reviewContent = "";
            this.selectReview();
          }
        });
    },
    likecheck() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get(`/follow/find/${this.getStoreId}`).then((res) => {
        console.log(res.data)
        this.getlikeCheck(res.data)
        console.log(this.likeCheck)
      });
    },
    like() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get(`/follow/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.getlikeCheck("success")
          console.log(response)
          // alert("좋아요 눌렀음");
        }
      });
    },
    unlike() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.delete(`/follow/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.getlikeCheck("fail")
          console.log(response)
          // alert("좋아요 취소");
        }
      });
    },
  },
};
</script>
<style scoped>
/* 점없애고 가로정렬 */

ul.tabs {
  margin: 0px;
  padding: 0px;
  list-style: none;
}
ul.tabs li {
  list-style-type: none;
  float: left;
  background: none;
  color: #222;
  padding: 10px 15px;
  cursor: pointer;
  border: 1px solid #b9b9b9;
  border-radius: 16px;
  width: 78px;
  height: 21px;
  font-size: 12px;
  padding: 0;
  margin-left: 5px;
}

.location {
  text-align: left;
  padding-bottom: 10px;
}

.head {
  margin-top: auto;
  margin-left: 5%;
  text-align: start;
  font-weight: bold;
}

.content {
  text-align: start;
  font-size: medium;
  font-weight: 500;
  margin: auto;
}

.comment {
  width: 100%;
}
.non-msg {
  width: 100%;
  height: 170px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.non-msg > div {
  font-size: 30px;
  color: rgba(0, 0, 0, 0.2);
}

.likeButton {
  color: red;
}
</style>
