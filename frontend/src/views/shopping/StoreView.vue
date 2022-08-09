<template>
  <div>
    <!--헤더-->
    <div class="store-header mb-5">
      <img
        :src="`${storeDto.storeImgUrl}`"
        alt="profile"
        class="store-img"
      />
      <div class="store-name">
        <span class="mt-2 fw-bold">{{ storeDto.storeName }}</span>
      </div>
      <!-- 좋아요 -->
      <div class="store-like mt-2">
        <!--좋아요 버튼-->
        <div 
          v-if="liking == 'fail'"
        >
          <svg 
            @click="like"
            xmlns="http://www.w3.org/2000/svg" 
            width="16" 
            height="16" 
            fill="red" 
            class="bi bi-heart" 
            viewBox="0 0 16 16">
            <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
          </svg>
        </div>
        <!-- 좋아요 된 상태 -->
        <div 
          v-else 
        > 
          <svg 
            @click="unlike"
            xmlns="http://www.w3.org/2000/svg" 
            width="16" 
            height="16" 
            fill="red" 
            class="bi bi-heart-fill" 
            viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
          </svg>
        </div>

      </div>
    </div>

    <div>
      <!--지도표시부분-->
      <div>
        <store-kakao-map></store-kakao-map>
      </div>
      <div class="mt-2">
        <ul class="content">
          <li>상세위치: {{ storeDto.address + " " + storeDto.extraAddress}}</li>
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
        v-bind:class="{ 
          active: tab === selectedTab,
          select: tab === selectedTab, }"
        :key="tab"
        v-on:click="onClickTab(tab)"
        class="tab"
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
        />
      </div>
      <div v-else class="non-msg">
        <div>오늘은 등록된</div>
        <div>상품이 없어요 ㅠ</div>
      </div>
    </div>
    <div class="product mt-3" v-else>
      <p class="head">🥨 온식고 식구들의 입소문</p>
      <!--리뷰입력창-->
      <div class="input-group comment">
        <input
          v-if="userCheck"
          v-model="reviewContent"
          type="text"
          class="form-control"
          aria-label="Input Review"
          aria-describedby="basic-addon1"
          @keyup.enter="registerReview()"/>
        <input
          v-else
          type="text"
          class="form-control"
          placeholder="리뷰를 입력해주세요"
          @click="login()"/>
        <button @click="registerReview()">
          <span class="input-group-text" id="basic-addon1">
            <i class="fa-solid fa-comment"></i>
          </span>
        </button>
      </div>
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
    </div>
  </div>
</template>

<script>
import StoreKakaoMap from "@/components/shopping/StoreKakaoMap";
import StoreProductItem from "@/components/shopping/StoreProductItem.vue";
import StoreReview from "@/components/shopping/StoreReview.vue";
import http from "@/util/http-common";
import { mapGetters } from "vuex";
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
      liking:""
    };
  },
  computed: {
    ...mapGetters("storeStore", ["getStoreId"]),
    ...mapGetters("accounts", ["userCheck"]),
  },

  async created() {
    this.selectedTab = this.tabs[0];
    
    await http.get(`/store/${this.getStoreId}`).then((response) => {
      this.storeDto = response.data;
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
    onClickTab(tab) {
      this.selectedTab = tab;
    },
    selectReview() {
      http.get(`/review/store/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.reviewList = response.data.reverse();
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
    likeCheck() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get(`/follow/find/${this.getStoreId}`).then((res) => {
        // console.log(res.data)
        console.log("찍힘");
        this.liking = res.data
        // console.log(this.liking)
      });
    },
    like() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get(`/follow/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.likeCheck()
          // alert("좋아요 눌렀음");
        }
      });
    },
    unlike() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.delete(`/follow/${this.getStoreId}`).then((response) => {
        if (response.status == 200) {
          this.likeCheck()
          // alert("좋아요 취소");
        }
      });
    },
    login(){
      this.$router.push("/login");
    }
  },
};
</script>
<style scoped>
.store-header{
  position: relative;
  width:100%;
  height:100%;
  z-index: 1;
}
.store-name{
  position: absolute;
  border-radius: 10px;
  background-color: white;
  top: 85%;
  left: 25%;
  width:50%;
  height:20%;
  z-index: 2;
}
.store-like{
  position: absolute; 
  top: 5%;
  left: 90%;
  z-index: 9999;
}

/* 점없애고 가로정렬 */
.store-img{
  width: 100%;
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
.select > span {
  color: black;
}

</style>
