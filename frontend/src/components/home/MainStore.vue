<template>
  <div class="container">
    <!-- 상위 가게 카테고리 -->
    <div class="row">
      <div class="col" id="category-img" @click="selectKorea()">
        <a>
          <img src="@/assets/images/koreanfood.png" alt="koreanfood" />
        </a>
        <p>한식</p>
      </div>

      <div class="col" id="category-img" @click="selectJapan()">
        <a>
          <img src="@/assets/images/japanesefood.png" alt="japanesefood" />
        </a>
        <p>일식</p>
      </div>
      <div class="col" id="category-img" @click="selectWestern()">
        <a>
          <img src="@/assets/images/snackbar.png" alt="westernfood" />
        </a>
        <p>양식</p>
      </div>
    </div>
    <!-- 하위 가게 카테고리 -->
    <div class="row">
      <div class="col" id="category-img" @click="selectSnack()">
        <a>
          <img src="@/assets/images/chinesefood.png" alt="snackbar" />
        </a>
        <p>분식</p>
      </div>
      <div class="col" id="category-img" @click="selectDesssert()">
        <a>
          <img src="@/assets/images/dessert.png" alt="dessert" />
        </a>
        <p>디저트</p>
      </div>

      <div class="col" id="category-img" @click="selectIngredient()">
        <a><img src="@/assets/images/westernfood.png" alt="ingredient" /> </a>
        <p>식자재</p>
      </div>
    </div>
    <!-- 검색 서칭 -->
    <div class="search-container">
      <div class="row">
        <div class="col">
          <input
            v-model="keyword"
            class="search-box"
            type="search"
            placeholder=" 가게명을 입력해주세요"
            @keyup.enter="sendKeyword()"
          />
          <!-- 검색 아이콘 -->
          <button class="product-search" @click="sendKeyword()">
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
          <button class="search-reset">
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
    </div>
  </div>
</template>

<script>
export default {
  name: "CategoryStore",
  data() {
    return {
      category: "",
      keyword: "",
    };
  },
  methods: {
    sendParams() {
      this.$router.push({
        name: "shopView",
        params: { category: this.category },
      });
    },
    sendKeyword() {
      this.$router.push({
        name: "shopView",
        params: { keyword: this.keyword },
      });
    },
    selectDesssert() {
      this.category = "DESSERT";
      this.sendParams();
    },
    selectJapan() {
      this.category = "JAPAN";
      this.sendParams();
    },
    selectIngredient() {
      this.category = "INGREDIENT";
      this.sendParams();
    },
    selectKorea() {
      this.category = "KOREA";
      this.sendParams();
    },
    selectSnack() {
      this.category = "SNACK";
      this.sendParams();
    },
    selectWestern() {
      this.category = "WESTERN";
      this.sendParams();
    },
  },
};
</script>

<style scoped>
.container {
  width: 360px;
}
.container .row .col {
  font-size: 20px;
  font-weight: bolder;
}
.container .row .col img {
  width: 70px;
  height: 70px;
}

.location {
  text-align: left;
  padding-bottom: 10px;
}

.search-container .row .col {
  padding: 0;
}

.search-box {
  border: solid 1px;
  border-radius: 12px;
  font-size: 12px;
  background-repeat: no-repeat;
  padding: 5px 8px;
  width: 370px;
  background-color: #fff;
}

.search-box::-webkit-input-placeholder {
  background-image: url(https://cdn1.iconfinder.com/data/icons/hawcons/32/698627-icon-111-search-256.png);
  background-size: contain;
  background-position: 1px center;
  background-repeat: no-repeat;
  text-indent: 0;
}

.container .row #category-img:hover {
  width: 50px; /* 사진크기 조절 */
  transform: scale(1.3, 1.3); /* 가로2배 새로 1.5배 로 커짐 */
  transition: transform.5s; /* 커지는 시간 */
}
</style>
