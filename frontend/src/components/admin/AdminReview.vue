<template>
  <div>
    <!-- Tab 기능 -->
    <ul class="tabs row ">
      <li class="col" v-for="tab in tabs" 
        v-bind:class="{choice : tab === selectedTab}" 
        :key="tab"
        v-on:click="onClickTab(tab)">
        <span>{{ tab }}</span>
      </li>
    </ul>

    <div class="tab-content">
      <!-- 전체 리뷰 -->
      <all-review
        class="tab-link current"
        v-if="selectedTab === tabs[0]">
      </all-review>
      <!-- 신고된 리뷰 -->
      <report-review
        class="tab-link"
        v-else>
      </report-review>
    </div>
  </div>
</template>

<script>
import AllReview from '@/components/admin/AllReview.vue';
import ReportReview from '@/components/admin/ReportReview.vue';

export default {
  name : "AdminReview",

  components: {
    AllReview,
    ReportReview,
  },

  data() {
    return {
      tabs: ['전체 리뷰', '신고 리뷰'],
      selectedTab: '',
    };
  },

  methods : {

  onClickTab(tab) {
      this.selectedTab = tab
    },
  },

  created () {
    this.selectedTab = this.tabs[0]
  }
};
</script>

<style scoped>
div {
  padding-bottom: 5px;
  padding-top: 5px;
}

.tab-content{
  padding-top: 25px;
}

ul.tabs{
  margin: 0px;
  padding: 0px;
  list-style: none;
}

ul.tabs li{
  list-style-type: none;
  float: left;
  background: none;
  color: #222;
  padding: 10px 15px;
  cursor: pointer;
  border: 2px solid #B9B9B9;
  border-radius: 20px;
  width:30px;
  height: 25px;
  font-size: 15px;
  padding: 0;
  margin-left: 5px;
  margin-right: 5px;
}

.choice > span {
  color: rgb(140, 184, 131);
}

</style>