<template>
  <div class="container">
    <!--헤더-->
    <div class="store-header mb-5">
      <img :src="`${storeDto.storeImgUrl}`" alt="profile" class="store-img" />
      <div class="store-name">
        <div class="name-case">
          <div class="fw-bold" :class="{ 'sm-font': storeNameSize > 8 }">
            {{ storeDto.storeName }}
          </div>
          <!-- 좋아요 -->
          <div v-if="userCheck == 2" class="store-like">
            <!--좋아요 버튼-->
            <div v-if="liking == 'fail'">
              <svg
                @click="like"
                xmlns="http://www.w3.org/2000/svg"
                width="25"
                height="25"
                fill="red"
                class="bi bi-heart"
                viewBox="0 0 16 16"
              >
                <path
                  d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"
                />
              </svg>
            </div>
            <!-- 좋아요 된 상태 -->
            <div v-else>
              <svg
                @click="unlike"
                xmlns="http://www.w3.org/2000/svg"
                width="25"
                height="25"
                fill="red"
                class="bi bi-heart-fill"
                viewBox="0 0 16 16"
              >
                <path
                  fill-rule="evenodd"
                  d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                />
              </svg>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Tab 기능 -->
    <div class="about-store">
      <ul class="tabs mt-3">
        <li
          v-for="tab in tabs"
          v-bind:class="{
            active: tab === selectedTab,
            select: tab === selectedTab,
          }"
          :key="tab"
          v-on:click="onClickTab(tab)"
          class="tab"
        >
          <div :class="{ now: tab === selectedTab }">{{ tab }}</div>
        </li>
      </ul>

      <!-- 상품 설명란 -->
      <div class="product mt-3" v-if="selectedTab === tabs[0]">
        <div class="head mb-2">📃 해당 매장에서 오늘 등록된 상품</div>
        <div v-if="this.saleItemList.length" class="mb-3 case">
          <store-product-item
            v-for="(saleItem, index) in saleItemList"
            :key="index"
            v-bind="saleItem"
          />
        </div>
        <div v-else class="non-msg">
          <div>오늘은 등록된</div>
          <div>상품이 없어요 ㅠ</div>
        </div>
      </div>
      <div class="product mt-3" v-else-if="selectedTab == tabs[1]">
        <div class="head mb-3">🥨 온식고 식구들의 입소문</div>
        <!--리뷰입력창-->
        <div class="comment">
          <input
            v-if="userCheck"
            v-model="reviewContent"
            type="text"
            class="form-control"
            aria-label="Input Review"
            aria-describedby="basic-addon1"
            @keyup.enter="registerReview()"
          />
          <input
            v-else
            type="text"
            class="form-control"
            placeholder="리뷰를 입력해주세요"
            @click="login()"
          />
          <button @click="registerReview()">
            <img
              class="send"
              id="basic-addon1"
              src="@/assets/images/send.png"
              alt=""
            />
          </button>
        </div>
        <div v-if="storeReviewList.length" class="mb-5">
          <store-review
            class="review"
            v-for="(reviewDto, index) in storeReviewList"
            :key="index"
            v-bind="reviewDto"
          />
        </div>
        <div v-else class="non-msg">
          <div>아직 등록된</div>
          <div>리뷰가 없어요 ㅠ</div>
        </div>
      </div>
      <div class="product mt-3" v-else>
        <!--지도표시부분-->
        <store-kakao-map></store-kakao-map>
        <!-- 상품 설명 -->
        <div class="content">
          <div class="row info-text">
            <div class="col-3 store-title adtitle">상세주소 :</div>
            <div class="col-9 info-content adress">
              <div>{{ storeDto.address }}</div>
              <div>
                {{ storeDto.extraAddress }}
              </div>
            </div>
          </div>
          <div class="row info-text">
            <div class="col-3 store-title">전화번호 :</div>
            <div class="col-9 info-content">
              {{ storeDto.tel }}
            </div>
          </div>
          <div class="row info-text">
            <div class="col-3 store-title">영업시간 :</div>
            <div class="col-9 info-content">{{ storeDto.closingTime }}</div>
          </div>
          <div class="row info-text">
            <div class="col-3 store-title">휴무일 :</div>
            <div class="col-9 info-content">
              {{ storeDto.offDay }}
            </div>
          </div>
          <div class="row info-text">
            <div class="col-3 store-title">공유하기 :</div>
            <share-sns
              class="col-9 share-icon"
              v-bind:storeImgUrl="storeDto.storeImgUrl"
              v-bind:storeName="storeDto.storeName"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import StoreKakaoMap from "@/components/shopping/StoreKakaoMap";
import StoreProductItem from "@/components/shopping/StoreProductItem.vue";
import StoreReview from "@/components/shopping/StoreReview.vue";
import ShareSns from "@/components/share/ShareSns.vue";
import http from "@/util/http-common";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "StoreView",

  components: {
    StoreKakaoMap,
    StoreProductItem,
    StoreReview,
    ShareSns,
  },

  data: function () {
    return {
      tabs: ["상품", "입소문", "상세정보"],
      selectedTab: "",
      storeDto: [],
      saleItemList: [],
      reviewContent: "",
      reviewList: [],
      liking: "fail",
      storeNameSize: 0,
    };
  },

  computed: {
    ...mapGetters("storeStore", ["getStoreId"]),
    ...mapGetters("accounts", ["userCheck"]),
    ...mapGetters("store", ["storeReviewList"]),
  },

  async created() {
    this.selectedTab = this.tabs[0];

    await http.get(`/store/${this.getStoreId}`).then((response) => {
      this.storeDto = response.data;
      this.storeNameSize = response.data.storeName.length;
      // console.log(this.storeNameSize);
      // console.log(this.storeDto);
    });

    await http.get(`/sale/list/${this.getStoreId}`).then((response) => {
      this.saleItemList = response.data;
      // console.log(response.data);
    });

    await this.selectReview();
    await this.likeCheck();
    // await console.log(this.likeState)
  },

  methods: {
    ...mapActions("store", ["getStoreReviewList"]),
    onClickTab(tab) {
      this.selectedTab = tab;
    },
    selectReview() {
      http.get(`/review/store/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.getStoreReviewList(response.data.reverse());
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
            this.reviewContent = "";
            this.selectReview();
          } else if (response.status == 204) {
            this.$alert("리뷰를 입력해주세요!");
          }
        });
    },
    likeCheck() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get(`/follow/find/${this.getStoreId}`).then((res) => {
        // console.log(res.data)
        this.liking = res.data;
        // console.log(this.liking)
      });
    },
    like() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get(`/follow/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.likeCheck();
        }
      });
    },
    unlike() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.delete(`/follow/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.likeCheck();
        }
      });
    },
    login() {
      this.$alert("로그인이 필요합니다.");
      this.$router.push("/login");
    },
  },
};
</script>
<style scoped>
div {
  padding: 0;
}
.content {
  width: 100%;
  display: flex;
  margin: 0;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
}
.container {
  display: flex;
  flex-direction: column;
  padding: 0;
}
.info-text {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  text-align: start;
  margin: 0;
  margin-bottom: 2px;
  width: 100%;
}
.store-title {
  padding-left: 15px;
  font-size: 15px !important;
  font-weight: 800;
}
.info-content {
  text-align: start;
  padding-left: 20px;
}
.store-header {
  position: relative;
  width: 100%;
  height: 100%;
  z-index: 1;
}
.adress {
  display: flex;
  flex-direction: column;
  font-size: 13px;
}
.store-name {
  position: absolute;
  border-radius: 10px;
  background-color: white;
  top: 85%;
  left: 25%;
  width: 50%;
  height: 20%;
  z-index: 2;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px solid rgba(0, 0, 0, 0.7);
}
.name-case {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  justify-content: center;
  font-size: 20px;
  width: 100%;
}
.store-like {
  margin-left: 5px;
}

/* 점없애고 가로정렬 */
.store-img {
  width: 100%;
}
.adtitle {
  height: 100%;
  vertical-align: top;
  font-family: "IBM Plex Sans KR", sans-serif;
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

.comment {
  padding-left: 10px;
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.comment > button {
  background-color: white;
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

.tabs {
  width: 100%;
  display: flex;
  flex-direction: row;
  padding-left: 5%;
}
.tab {
  font-size: 18px;
  margin-right: 5%;
}
.tab:hover {
  cursor: pointer;
}
.select > span {
  color: black;
}
.sm-font {
  font-size: 15px;
}
.about-store {
  width: 100%;
  background-color: rgb(240, 240, 240);
}
.now {
  color: rgb(140, 184, 131);
}
.form-control {
  width: 80%;
}
.review {
  width: 95%;
  margin: 0;
}
.send {
  width: 20px;
  height: 20px;
}
#basic-addon1 {
  height: 38px;
  width: 40px;
}

</style>
