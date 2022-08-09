<template>
  <div class="container">
    <h1>관리자페이지</h1>
    <hr class="mt-5">
    <!-- Tab 기능 -->
      <ul class="tabs row ">
        <li class="col" v-for="tab in tabs" 
          v-bind:class="{active : tab === selectedTab}" 
          :key="tab"
          v-on:click="onClickTab(tab)">
          <span>{{ tab }}</span>
        </li>
      </ul>
    <hr class="mt-5">
      <div class="tab-content">
      <!-- 리뷰 관리 -->
        <admin-review
          class="tab-link current"
          v-if="selectedTab === tabs[0]">
        </admin-review>
      <!-- 사용자 관리 -->
        <admin-user 
          class="tab-link"
          v-else-if="selectedTab === tabs[1]">
          </admin-user>
      <!-- 가게 관리 -->
        <admin-store class="tap-link" v-else></admin-store>
      </div>
  </div>
</template>

<script>
import AdminReview from '@/components/admin/AdminReview';
import AdminUser from '@/components/admin/AdminUser';
import AdminStore from '@/components/admin/AdminStore';

export default {
  name: "AdminView",

  components: {
    AdminReview,
    AdminUser,
    AdminStore,
  },
  data() {
    return{
      tabs: ['리뷰관리', '사용자관리', '가게관리'],
      selectedTab: '',
    };
  },

  created() {
    this.selectedTab = this.tabs[0]
  },

  methods: {
    onClickTab(tab) {
      this.selectedTab = tab
    },
  },
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