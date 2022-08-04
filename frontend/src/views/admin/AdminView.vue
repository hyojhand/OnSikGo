<template>
  <div>
    <h1>관리자페이지</h1>

    <!-- Tab 기능 -->
      <ul class="tabs">
        <li v-for="tab in tabs" 
          v-bind:class="{active : tab === selectedTab}" 
          :key="tab"
          v-on:click="onClickTab(tab)">
          <span>{{ tab }}</span>
        </li>
      </ul>
    
    <!-- 리뷰 -->
      <div>
        <admin-review
          class="tab-link current"
          v-if="selectedTab === tabs[0]">
        </admin-review>
    <!-- 사용자 -->
        <admin-user 
          class="tab-link"
          v-else-if="selectedTab === tabs[1]">
          </admin-user>
    <!-- 가게 -->
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

<style scop>

</style>