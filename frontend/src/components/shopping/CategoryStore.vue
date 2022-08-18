<template>
  <div class="container">
    <!-- 상단 가게 카테고리 -->
    <div class="row case bbox">
      <div
        :class="{
          select: this.tabs[0] === this.category,
        }"
        class="col"
        id="category-img"
      >
        <a>
          <img
            class="tab"
            src="@/assets/images/all.png"
            alt="all"
            @click="selectAllList()"
          />
        </a>
        <p class="mt-1" style="color:#000">모두 보기</p>
      </div>

      <div
        :class="{
          select: this.tabs[1] === this.category,
        }"
        class="col"
        id="category-img"
      >
        <a>
          <img
            class="tab"
            src="@/assets/images/koreanfood.png"
            alt="koreanfood"
            @click="selectKorea()"
          />
        </a>
        <p class="mt-1" style="color:#000">한식</p>
      </div>

      <div
        :class="{
          select: this.tabs[2] === this.category,
        }"
        class="col"
        id="category-img"
      >
        <a>
          <img
            class="tab"
            src="@/assets/images/japanesefood.png"
            alt="japanesefood"
            @click="selectJapan()"
          />
        </a>
        <p class="mt-1" style="color:#000">일식</p>
      </div>
    </div>
    <!-- 중단 가게 카테고리 -->
    <div class="row bbox">
      <div
        :class="{
          select: this.tabs[3] === this.category,
        }"
        class="col"
        id="category-img"
      >
        <a>
          <img
            class="tab"
            src="@/assets/images/snackbar.png"
            alt="westernfood"
            @click="selectWestern()"
          />
        </a>
        <p class="mt-1" style="color:#000">양식</p>
      </div>
      <div
        :class="{
          select: this.tabs[4] === this.category,
        }"
        class="col"
        id="category-img"
      >
        <a>
          <img
            class="tab"
            src="@/assets/images/chinesefood.png"
            alt="snackbar"
            @click="selectSnack()"
          />
        </a>
        <p class="mt-1" style="color:#000">분식</p>
      </div>
      <div
        :class="{
          select: this.tabs[5] === this.category,
        }"
        class="col"
        id="category-img"
      >
        <a>
          <img
            class="tab"
            src="@/assets/images/dessert.png"
            alt="dessert"
            @click="selectDesssert()"
          />
        </a>
        <p class="mt-1" style="color:#000">디저트</p>
      </div>
    </div>
    <!-- 하단 가게 카테고리 -->
    <div class="row bbox">
      <div
        :class="{
          select: this.tabs[6] === this.category,
        }"
        class="col"
        id="category-img"
      >
        <a>
          <img
            class="tab"
            src="@/assets/images/westernfood.png"
            alt="ingredient"
            @click="selectIngredient()"
          />
        </a>
        <p class="mt-1" style="color:#000">식자재</p>
      </div>
      <!-- 빈 공간 채우기 -->
      <div class="col" id="category-img"></div>
      <div class="col" id="category-img"></div>
    </div>
    <!-- 검색 서칭 -->
    <div class="search-container">
      <div class="search-result">총 {{ storeList.length }}개 검색 결과</div>

      <div class="search-case">
        <input
          v-model="keyword"
          class="search-box"
          type="search"
          placeholder="         가게명을 입력해주세요"
        />
        <!-- 검색 아이콘 -->
        <button class="product-search" @click="selectKeyword()">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="black"
            class="bi bi-search"
            viewBox="0 0 16 16"
          >
            <path
              d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
            />
          </svg>
        </button>
      </div>
    </div>
    <!-- 검색 결과 -->
    <div
      v-if="this.storeList.length"
      style="padding-left: 1rem; background-color: rgb(240, 240, 240)"
    >
      <store-item
        v-for="(store, index) in storeList"
        :key="index"
        v-bind="store"
        class="mb-3"
      />
    </div>
    <div v-else class="none-msg">
      <div>찾을 수 있는</div>
      <div>가게가 없습니다!</div>
    </div>
  </div>
</template>

<script>
import StoreItem from "@/components/shopping/StoreItem.vue";
import http from "@/util/http-common";
export default {
  name: "CategoryStore",
  data() {
    return {
      tabs: ["", "KOREA", "JAPAN", "WESTERN", "SNACK", "DESSERT", "INGREDIENT"],
      category: "",
      storeList: [],
      keyword: "",
      selectTab: "",
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
      this.selectAllList();
    }
  },
  methods: {
    // 할인 상품 개수 추가하기
    selectSaleCount() {
      this.storeList.map(async (store, i) => {
        await http.get(`/sale/list/${store.storeId}`).then((response) => {
          this.storeList[i] = {
            ...this.storeList[i],
            saleCount: response.data.length,
          };
        });
        this.$forceUpdate();
      });
    },
    // 모두 검색하기
    selectAllList() {
      this.category = "";
      http.get("/store/total").then((response) => {
        if (response.status == 200) {
          this.storeList = response.data;
          this.selectSaleCount();
        }
      });
    },
    // 조회된 가게 리스트 검색
    async selectStoreList() {
      await http
        .post("/store/list", {
          category: this.category,
        })
        .then((response) => {
          if (response.status == 200) {
            this.storeList = response.data;

            this.selectSaleCount();
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

    // 키워드로 검색하기
    async selectKeyword() {
      if (this.category) {
        await http
          .post("/store/list", {
            category: this.category,
            keyword: this.keyword,
          })
          .then((response) => {
            if (response.status == 200) {
              this.storeList = response.data;
              this.selectSaleCount();
            }
          });
      } else {
        await http
          .post("/store/keyword", {
            keyword: this.keyword,
          })
          .then((response) => {
            if (response.status == 200) {
              this.storeList = response.data;
              this.selectSaleCount();
            }
          });
      }
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
.col > p {
  color: rgb(140, 184, 131);
}
.search-container {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  margin: 3% 0;
}
.search-box {
  border: solid 1px;
  border-radius: 12px;
  font-size: 12px;
  background-repeat: no-repeat;
  padding: 5px 5px;
  width: 210px;
  height: 30px;
  background-color: #fff;
}

.search-box::-webkit-input-placeholder {
  background-image: url(https://cdn1.iconfinder.com/data/icons/hawcons/32/698627-icon-111-search-256.png);
  background-size: contain;
  background-position: 1px center;
  background-repeat: no-repeat;
  text-indent: 0;
}
.search-case {
  display: flex;
  flex-direction: row;
}
#category-img {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 120px;
  padding: 0;
}

.select > a > img {
  filter: opacity(0.5) drop-shadow(0 0 0 #d75281);
}
.select > p {
  color: black;
  opacity: 0.4;
}
.search-result {
  text-align: center;
}

.container .row #category-img:hover {
  width: 30px; /* 사진크기 조절 */
  transform: scale(1.2, 1.2); /* 가로2배 새로 1.5배 로 커짐 */
  transition: transform.5s; /* 커지는 시간 */
}
.none-msg {
  border-top: 2px solid rgba(0, 0, 0, 0.2);
  margin-top: 1%;
  width: 100%;
  padding-top: 15%;
}
.none-msg > div {
  margin-top: 3%;
  color: rgba(0, 0, 0, 0.3);
  font-size: 30px;
}
.product-search {
  padding: 0;
  margin: 0;
  padding-right: 10px;
  padding-left: 15px;
}
.bbox {
  width: 100%;
  margin: 0;
}

.bbox > div > p {
  letter-spacing:2px;
}
.search-reset {
  padding: 0;
  margin: 0;
}
</style>
