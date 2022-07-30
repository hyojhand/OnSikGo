<template>
  <div>
    <div class="mt-3">
      <!--매장선택-->
      <div class="d-flex justify-content-center">
        <select
          id="dropdown1"
          style="border-color: #63bf68"
          @change="selectStore($event)"
        >
          <option :key="index" :value="store" v-for="(store, index) in stores">
            {{ store.storeName }}
          </option>
        </select>

        <!-- <b-dropdown
          id="dropdown-1"
          style="border-color: #63bf68"
          text="this.stores[0].storeName"
        >
          <b-dropdown-item>
            <option
              :key="index"
              :value="store"
              v-for="(store, index) in stores"
            >
              {{ store.storeName }}
            </option>
          </b-dropdown-item>
        </b-dropdown> -->
      </div>

      <div class="container">
        <div class="row">
          <!--상품등록버튼-->
          <div class="col-4">
            <b-button
              @click="prodregister()"
              size="sm"
              pill
              variant="outline-success"
              ><svg
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
              상품등록</b-button
            >
          </div>
          <!-- 검색란 -->
          <div class="col">
            <input
              v-model="keyword"
              class="search-box"
              type="search"
              placeholder=" 상품을 입력해주세요"
            />
            <!-- 검색 아이콘 -->
            <button class="product-search">
              <svg
                @click="keywordSelect()"
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
      </div>
    </div>

    <div class="row justify-content-center">
      <all-product-list
        v-for="(item, index) in items"
        :key="index"
        v-bind="item"
        :no="storeId"
      />
    </div>
    <br />
    <br />
    <br />
    <br />
    <!--페이지네이션-->
    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center">
        <li class="page-item disabled">
          <a class="page-link">Previous</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#">Next</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import AllProductList from "@/components/AllProductList.vue";
import http from "@/util/http-common";
export default {
  name: "AllProdView",

  data() {
    return {
      stores: [],
      storeId: "",
      items: [],
      keyword: "",
    };
  },

  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http.get("/store/list").then((response) => {
      this.stores = response.data;
      this.storeId = response.data[0].storeId;
    });

    await http.get(`/item/list/${this.storeId}`).then((response) => {
      this.items = response.data;
    });
  },

  components: {
    AllProductList,
  },

  methods: {
    prodregister() {
      this.$router.push("/allprod/register");
    },
    selectStore() {
      this.storeId = event.target.value;
    },
    keywordSelect() {
      http
        .post(`/item/list/keyword/${this.storeId}`, {
          keyword: this.keyword,
        })
        .then((response) => {
          this.items = response.data;
        });
    },
    resetItemList() {
      this.keyword = "";
      http.get(`/item/list/${this.storeId}`).then((response) => {
        this.items = response.data;
      });
    },
  },
};
</script>

<style scoped>
.push {
  margin-left: 200px;
}
.search-result {
  padding-right: 5px;
  font-size: 12px;
}
.search-box {
  border: solid 1px;
  font-size: 12px;
}

.product-search {
  padding-right: 5px;
  padding-left: 5px;
}
</style>
