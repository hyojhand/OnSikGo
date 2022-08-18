<template>
  <div class="container">
    <!-- 상단 가게 카테고리 -->
    <div class="row">
      <div class="col" id="category-img">
        <a>
          <img src="@/assets/images/all.png" alt="all" @click="selectAll()" />
        </a>
        <p class="mt-1">모두보기</p>
      </div>

      <div class="col" id="category-img">
        <a>
          <img
            src="@/assets/images/koreanfood.png"
            alt="koreanfood"
            @click="selectKorea()"
          />
        </a>
        <p class="mt-1">한식</p>
      </div>

      <div class="col" id="category-img">
        <a>
          <img
            src="@/assets/images/japanesefood.png"
            alt="japanesefood"
            @click="selectJapan()"
          />
        </a>
        <p class="mt-1">일식</p>
      </div>
    </div>
    <!-- 중단 가게 카테고리 -->
    <div class="row">
      <div class="col" id="category-img">
        <a>
          <img
            src="@/assets/images/snackbar.png"
            alt="westernfood"
            @click="selectWestern()"
          />
        </a>
        <p class="mt-1">양식</p>
      </div>
      <div class="col" id="category-img">
        <a>
          <img
            src="@/assets/images/chinesefood.png"
            alt="snackbar"
            @click="selectSnack()"
          />
        </a>
        <p class="mt-1">분식</p>
      </div>
      <div class="col" id="category-img">
        <a>
          <img
            src="@/assets/images/dessert.png"
            alt="dessert"
            @click="selectDesssert()"
          />
        </a>
        <p class="mt-1">디저트</p>
      </div>
    </div>
    <!-- 하단 가게 카테고리 -->
    <div class="row">
      <div class="col" id="category-img">
        <a
          ><img
            src="@/assets/images/westernfood.png"
            alt="ingredient"
            @click="selectIngredient()"
          />
        </a>
        <p class="mt-1">식자재</p>
      </div>
      <!-- 빈 공간 채우기 -->
      <div class="col" id="category-img"></div>
      <div class="col" id="category-img"></div>
    </div>
    <!-- 검색 서칭 -->
    <div class="search-container">
      <div class="row">
        <div class="col index-box">
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
      all: 1,
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
    selectAll() {
      this.$router.push({
        name: "shopView",
        params: { all: this.all },
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
.container .row .col p {
  letter-spacing: 3px;
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
  height: 30px;
  background-color: #fff;
}

.container .row #category-img:hover {
  width: 30px; /* 사진크기 조절 */
  transform: scale(1.2, 1.2); /* 가로2배 새로 1.5배 로 커짐 */
  transition: transform.5s; /* 커지는 시간 */
}
.index-box {
  display: flex;
  flex-direction: row;
}

.product-search {
  padding: 0;
  margin: 0;
  padding-right: 10px;
  padding-left: 15px;
}
.search-reset {
  padding: 0;
  margin: 0;
}
</style>
