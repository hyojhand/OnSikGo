<template>
  <div class="main_container">
    <div class="mobile">
      <!-- 현재 주소 -->

      <div class="location">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="black"
          class="bi bi-pin-map"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"
          />
          <path
            fill-rule="evenodd"
            d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"
          />
        </svg>
        <span> {{ currentAddress }}</span>
      </div>
      <!-- Tab 기능 -->

      <ul class="tabs">
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
          <span>{{ tab }}</span>
        </li>
      </ul>
      <!-- test용 -->
      <!-- 지도 or Category Store -->
      <div class="tab-content">
        <kakao-map
          class="tab-link currnet"
          v-if="selectedTab === tabs[0]"
        ></kakao-map>
        <category-store
          class="tab-link"
          v-else
          :to="this.selectCategory"
          :keyw="this.selectKeyword"
        ></category-store>
      </div>
    </div>
    
  </div>
</template>

<script>
import KakaoMap from "@/components/shopping/KakaoMap";
import CategoryStore from "@/components/shopping/CategoryStore";
import { mapGetters } from "vuex";

export default {
  name: "ShopView",

  components: {
    KakaoMap,
    CategoryStore,
  },
  props: {
    category: {
      type: String,
      default: "",
    },
    keyword: {
      type: String,
      default: "",
    },
    all: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      tabs: ["지도보기", "가게보기"],
      selectedTab: "",
      selectCategory: "",
      selectKeyword: "",
      selectAll: 0,
    };
  },
  // vuex에서 현재주소 가져오기
  computed: {
    ...mapGetters("store", ["currentAddress"]),
  },
  // 디볼트는 지도보기
  created() {
    this.selectCategory = this.$route.params.category;
    this.selectKeyword = this.$route.params.keyword;
    this.selectAll = this.$route.params.all;
    if (this.selectCategory || this.selectKeyword || this.selectAll) {
      this.selectedTab = this.tabs[1];
    } else {
      this.selectedTab = this.tabs[0];
    }
  },
  methods: {
    onClickTab(tab) {
      this.selectedTab = tab;
    },
  },
};
</script>

<style scoped>
.mobile {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.tab-content {
  width: 100%;
}
.main_container {
  width: 100%;
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
  color: rgb(140, 184, 131);
}
.main_container .mobile {
  width: 100%;
  margin: 0 auto;
}
.location {
  text-align: start;
  padding: 8% 0 5% 3%;
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
}
.location span {
  margin-left: 2%;
  color: #333;
  font-weight: 500;
  font-size: 16px;
  padding: 0 2%;
  text-align: start;
  border-bottom: 2px solid rgba(0, 0, 0, 20%);
}
</style>
