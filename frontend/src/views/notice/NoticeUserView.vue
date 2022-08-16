<template>
  <div>
    <div v-if="this.noticeList.length">
      <notice-user-card
        ref
        class="notice-card"
        v-for="(notice, index) in noticeList"
        :key="index"
        v-bind="notice"
      />
    </div>
    <div v-else class="non-msg">
      <div>아직 보내드릴</div>
      <div>알림이 없어요 ㅠ</div>
    </div>
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
      this.noticeList = response.data.reverse();
    });
  },
};
</script>

<style scoped>
.notice-card {
  margin: 3% auto 6% auto;
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
</style>
