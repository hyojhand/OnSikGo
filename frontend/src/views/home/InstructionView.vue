<template>
  <div>
    <!-- 탭 기능 -->
    <ul class="tabs mt-5 mb-3">
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
    <div class="tab-content">
      <onsikgo-intro
        class="currnet"
        v-if="selectedTab === tabs[0]"
      ></onsikgo-intro>
      <onsikgo-how-to v-else></onsikgo-how-to>
    </div>
  </div>
</template>

<script>
import OnsikgoIntro from "@/components/home/OnsikgoIntro.vue";
import OnsikgoHowTo from "@/components/home/OnsikgoHowTo.vue";

export default {
  name: "InstructionView",
  props: {
    tab: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      tabs: ["온식고 소개", "온식고 이용법"],
      selectedTab: "",
    };
  },
  components: { OnsikgoIntro, OnsikgoHowTo },
  methods: {
    onClickTab(tab) {
      this.selectedTab = tab;
    },
  },
  created() {
    this.selectedTab = this.$route.params.tab;
    if (this.selectedTab) {
      this.selectedTab = this.tabs[1];
    } else {
      this.selectedTab = this.tabs[0];
    }
  },
};
</script>

<style scoped>
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
  color: rgb(140, 184, 131);
}
</style>
