<template>
  <div>
    <notice-card
      class="notice-card"
      v-for="(notice, index) in noticeList"
      :key="index"
      v-bind="notice"
    />
  </div>
</template>

<script>
import NoticeCard from "@/components/notice/NoticeCard.vue";
import http from "@/util/http-common";
export default {
  name: "NoticeView",
  data() {
    return {
      noticeList: [],
    };
  },
  components: {
    NoticeCard,
  },

  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http.get("/order").then((response) => {
      console.log("order",response.data)
    })
    http.get("/notice").then((response) => {
      console.log("notice",response.data);
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
