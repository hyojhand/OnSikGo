<template>
  <div class="mt-5">
    <!--매장선택-->
    <div class="selec-box">
      <select
        id="dropdown1"
        class="store-name form-select"
        @change="selectStore($event)"
      >
        <option id="first" selected class="opt">
          {{ this.saveName }}
        </option>
        <option
          :key="index"
          :value="store.storeId"
          v-for="(store, index) in stores"
        >
          {{ store.storeName }}
        </option>
      </select>
    </div>
    <!-- 상품 등록 & 검색 탭 -->

    <div class="head-container row">
      <!--상품등록버튼-->
      <div class="regi-btn col-4">
        <button @click="prodregister()" class="mx-auto">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="15"
            height="20"
            fill="currentColor"
            class="bi bi-plus-square"
            viewBox="0 0 16 16"
          >
            <path
              d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"
            ></path>
            <path
              d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"
            ></path>
          </svg>
          상품등록
        </button>
      </div>
      <!-- 검색란 -->
      <div class="search-bar col-7">
        <v-text-field
          width="120"
          v-model="keyword"
          label="상품을 입력해주세요."
          color="black"
        ></v-text-field>
        <!-- 검색 아이콘 -->
        <button class="none-margin">
          <svg
            @click="keywordSelect(1)"
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
        <button class="none-margin">
          <svg
            @click="resetItemList()"
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

    <div class="item-container" v-if="this.items.length">
      <all-product-list
        class="item-card"
        v-for="(item, index) in items"
        :key="index"
        :item="item"
        :storeId1="Number(storeId)"
      />
    </div>
    <div v-else class="non-msg">
      <div>상품을 등록하여</div>
      <div>Onsikgo를 시작해주세요!</div>
    </div>
    <!--페이지네이션-->

    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center nav-box">
        <li class="page-item">
          <a class="page-link" href="#" @click="previousPage()">이전으로</a>
        </li>

        <li
          class="page-item"
          v-for="(index, page) in totalPage"
          :key="index"
          :v-bind="page"
        >
          <a class="page-link" href="#" @click="movePage(index)">{{ index }}</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#" @click="nextPage()">다음으로</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import AllProductList from "@/components/management/AllProductList.vue";
import http from "@/util/http-common";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "AllProdView",

  data() {
    return {
      stores: [],
      store: {},
      storeId: Number,
      items: {},
      keyword: "",
      saleList: [],
      totalPage: Number,
      page: Number,
      isKeyword: false,
    };
  },

  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http.get("/store/list").then((response) => {
      if (this.saveStore.length) {
        console.log("여기에 사람있어요");
        this.stores = response.data;
        this.storeId = this.saveStore;
      } else {
        console.log("여긴 없어요 ㅋ");
        this.stores = response.data;
        this.storeId = response.data[0].storeId;
        this.getSaveStore(this.storeId);
      }
    });

    await this.selectPage(1);
  },

  components: {
    AllProductList,
  },
  computed: {
    ...mapGetters("select", ["saveStore", "saveName"]),
  },

  methods: {
    ...mapActions("itemStore", ["getItemId"]),
    ...mapActions("storeStore", ["getStoreId"]),
    ...mapActions("select", ["getSaveStore"]),
    selectPage(index) {
      this.page = index - 1;
      http
        .post(`/item/page/${this.storeId}`, {
          page: this.page,
          size: 4,
        })
        .then((response) => {
          console.log(response);
          this.items = response.data.content;
          this.totalPage = response.data.totalPages;
          this.items.map(async (item, i) => {
            await http
              .get(`/sale/${item.itemId}`)
              //
              .then((response) => {
                if (response.status == 200) {
                  this.items[i] = {
                    ...this.items[i],
                    sale: response.data,
                  };
                }
              });
            this.$forceUpdate();
          });
        });
    },
    nextPage() {
      if (this.page + 1 >= this.totalPage) {
        this.page = this.totalPage;
        if (this.isKeyword === false) {
          this.selectPage(this.page);
        } else {
          this.keywordSelect(this.page);
        }
      } else {
        this.page = this.page + 2;
        if (this.isKeyword === false) {
          this.selectPage(this.page);
        } else {
          this.keywordSelect(this.page);
        }
      }
    },
    previousPage() {
      if (this.page - 1 < 0) {
        if (this.isKeyword === false) {
          this.selectPage(1);
        } else {
          this.keywordSelect(1);
        }
      } else {
        if (this.isKeyword === false) {
          this.selectPage(this.page);
        } else {
          this.keywordSelect(this.page);
        }
      }
    },
    prodregister() {
      this.getItemId(this.itemId);
      this.getStoreId(this.storeId);
      this.$router.push({
        name: "prodRegister",
      });
    },
    keywordSelect(index) {
      this.page = index - 1;
      this.isKeyword = true;
      http
        .post(`/item/page/keyword/${this.storeId}`, {
          keyword: this.keyword,
          page: this.page,
          size: 4,
        })
        .then((response) => {
          this.items = response.data.content;
          this.totalPage = response.data.totalPages;
          this.items.map(async (item, i) => {
            await http.get(`/sale/${item.itemId}`).then((response) => {
              if (response.status == 200) {
                this.items[i] = {
                  ...this.items[i],
                  sale: response.data,
                };
              }
            });
            this.$forceUpdate();
          });
        });
    },
    movePage(index) {
      this.page = index;
      if (this.isKeyword == false) {
        this.selectPage(this.page);
      } else {
        this.keywordSelect(this.page);
      }
    },
    resetItemList() {
      this.isKeyword = false;
      this.selectPage(1);
    },
    selectStore(event) {
      this.storeId = event.target.value;
      this.getSaveStore(event.target.value);
      this.selectPage(1);
    },
    click(e) {
      console.log(e);
    },
  },
};
</script>

<style scoped>
.none-margin {
  margin: 0;
  padding: 0;
}
.store-name {
  width: 80%;
  font-size: 25px;
  font-weight: 800;
  text-align: center;
  padding: 2% 0;
}
.head-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid rgba(0, 0, 0, 10%);
  width: 100%;
  margin: 0;
}
.regi-btn {
  display: flex;
}
.search-bar {
  display: flex;
  padding: 0;
  margin-right: 3%;
}
.item-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  padding: 5%;
}
.item-card {
  width: 45%;
  border: 3px solid white;
  margin: 2%;
}
.nav-box {
  padding: 0;
}
.non-msg {
  width: 100%;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.non-msg > div {
  font-size: 30px;
  color: rgba(0, 0, 0, 0.2);
}
.opt {
  background-color: rgba(140, 184, 131, 0.5);
  color: white;
}
.selec-box {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.selec-box > svg {
  margin-left: 7px;
}
.page-link {
  color: black;
}
</style>
