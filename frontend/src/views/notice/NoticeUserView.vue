<template>
  <div>
    <notice-user-card
      ref
      class="notice-card"
      v-for="(notice, index) in noticeList"
      :key="index"
      v-bind="notice"
    />
  </div>
</template>

<script>
import NoticeUserCard from "@/components/notice/NoticeUserCard.vue";
import http from "@/util/http-common";
export default {
  name: "NoticeUserView",
  data() {
    return {
      noticeList: [],
    };
  },
  components: {
    NoticeUserCard,
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    http.get("/notice").then((response) => {
      console.log(response.data);
      this.noticeList = response.data;
    });
  },
};
</script>

<style scoped>
.notice-card {
  margin: 3% auto 6% auto;
}
</style>
