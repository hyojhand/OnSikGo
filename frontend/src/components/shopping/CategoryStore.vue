<template>
  <div class="container">
    <div class="category">
      <!-- 상위 가게 카테고리 -->
      <div class="menu">
        <div id="category-img" @click="selectKorea()">
          <a>
            <img src="@/assets/images/koreanfood.png" alt="koreanfood" />
          </a>
          <p>한식</p>
        </div>

        <div id="category-img" @click="selectJapan()">
          <a>
            <img src="@/assets/images/japanesefood.png" alt="japanesefood" />
          </a>
          <p>일식</p>
        </div>
        <div id="category-img" @click="selectWestern()">
          <a>
            <img src="@/assets/images/snackbar.png" alt="westernfood" />
          </a>
          <p>양식</p>
        </div>
      </div>
      <!-- 하위 가게 카테고리 -->
      <div class="menu">
        <div id="category-img" @click="selectSnack()">
          <a>
            <img src="@/assets/images/chinesefood.png" alt="snackbar" />
          </a>
          <p>분식</p>
        </div>
        <div id="category-img" @click="selectDesssert()">
          <a>
            <img src="@/assets/images/dessert.png" alt="dessert" />
          </a>
          <p>디저트</p>
        </div>

        <div id="category-img" @click="selectIngredient()">
          <a><img src="@/assets/images/westernfood.png" alt="ingredient" /> </a>
          <p>식자재</p>
        </div>
      </div>
    </div>
    <!-- 검색 서칭 -->
    <div class="search-container">
      <div class="search-result">총 {{ storeList.length }}개 검색 결과</div>

      <div class="search-case">
        <input
          v-model="keyword"
          class="search-box"
          type="search"
          placeholder=" 가게명을 입력해주세요"
        />
        <!-- 검색 아이콘 -->
        <button class="product-search" @click="selectKeyword()">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-search"
            viewBox="0 0 16 16"
          >
            <path
              d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
            />
          </svg>
        </button>
        <!-- 초기화 -->
        <button class="search-reset" @click="resetList()">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-arrow-clockwise"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"
            />
            <path
              d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"
            />
          </svg>
        </button>
      </div>
    </div>
    <!-- 검색 결과 -->

    <store-item
      v-for="(store, index) in storeList"
      :key="index"
      v-bind="store"
    />
  </div>
</template>

<script>
import StoreItem from "@/components/shopping/StoreItem.vue";
import http from "@/util/http-common";
export default {
  name: "CategoryStore",
  data() {
    return {
      category: "KOREA",
      storeList: [],
      keyword: "",
    };
  },
  props: ["to", "keyw"],
  components: {
    StoreItem,
  },
  created() {
    if (this.to) {
      this.category = this.to;
      this.selectStoreList();
    } else if (this.keyw) {
      this.keyword = this.keyw;
      this.selectKeyword();
    } else {
      this.selectStoreList();
    }
  },
  methods: {
    selectStoreList() {
      http
        .post("/store/list", {
          category: this.category,
        })
        .then((response) => {
          if (response.status == 200) {
            this.storeList = response.data;
          }
        });
    },
    selectDesssert() {
      this.category = "DESSERT";
      this.selectStoreList();
    },
    selectJapan() {
      this.category = "JAPAN";
      this.selectStoreList();
    },
    selectIngredient() {
      this.category = "INGREDIENT";
      this.selectStoreList();
    },
    selectKorea() {
      this.category = "KOREA";
      this.selectStoreList();
    },
    selectSnack() {
      this.category = "SNACK";
      this.selectStoreList();
    },
    selectWestern() {
      this.category = "WESTERN";
      this.selectStoreList();
    },
    selectKeyword() {
      http
        .post("/store/list", {
          category: this.category,
          keyword: this.keyword,
        })
        .then((response) => {
          if (response.status == 200) {
            this.storeList = response.data;
          }
        });
    },
    resetList() {
      this.category = "KOREA";
      this.selectStoreList();
      this.keyword = "";
    },
  },
};
</script>

<style scoped>
.container {
  width: 100%;
  padding: 0;
}
img {
  width: 70px;
  height: 70px;
}
.menu {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  width: 100%;
}
.category {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  border-top: 2px solid rgba(0, 0, 0, 0.2);
  border-bottom: 2px solid rgba(0, 0, 0, 0.2);
  height: 300px;
}
.search-container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin: 3% 0;
}
.search-case {
  display: flex;
  flex-direction: row;
}
#category-img {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.menu #category-img:hover {
  width: 50px; /* 사진크기 조절 */
  transform: scale(1.3, 1.3); /* 가로2배 새로 1.5배 로 커짐 */
  transition: transform.5s; /* 커지는 시간 */
}
</style>
