<template>
  <div>
      <!-- Tab 기능 -->
      <ul class="tabs row ">
        <li class="col" v-for="tab in tabs" 
          v-bind:class="{active : tab === selectedTab}" 
          :key="tab"
          v-on:click="onClickTab(tab)">
          <span>{{ tab }}</span>
        </li>
      </ul>

    <h1 class="mb-5">AdminReview</h1>
    <div class="tab-content">
      <!-- 전체 리뷰 -->
      <all-review
        class="tab-link current"
        v-if="selectedTab === tabs[0]">
      </all-review>
      <!-- 신고된 리뷰 -->
        <admin-user 
          class="tab-link"
          v-else>
        </admin-user>

    </div>
  </div>
</template>

<script>
import AllReview from '@/components/admin/AllReview.vue';


export default {
  name : "AdminReview",

  components: {
    AllReview,
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
  border: 1px solid #B9B9B9;
  border-radius: 16px;
  width:78px;
  height: 30px;
  font-size: 15px;
  padding: 0;
  margin-left: 5px;
}
</style>