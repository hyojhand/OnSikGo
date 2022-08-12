<template>
  <div class="notice-container">
    <div v-if="this.ownerOrderList.length">
      <notice-card
        class="notice-card"
        v-for="(notice, index) in ownerOrderList"
        :key="index"
        :notice="notice"
      />
    </div>
    <div v-else class="non-msg">
      <div>현재 들어온</div>
      <div>주문이 없어요</div>
    </div>
  </div>
</template>

<script>
import NoticeCard from "@/components/notice/NoticeCard.vue";
import http from "@/util/http-common";
import { mapGetters, mapActions } from "vuex";

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
  computed: {
    ...mapGetters("accounts", ["ownerOrderList"]),
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http.get("/notice").then((response) => {
      // console.log("notice", response.data);
      this.getOwnerOrderList(response.data.reverse());
      // this.noticeList = response.data;
    });
  },
  methods: {
    // 현재 위치 주소 vuex에 넣기
    ...mapActions("accounts", ["getOwnerOrderList"]),
  },
};
</script>

<style scoped>
.notice-container {
  display: flex;
  flex-direction: column;
  width: 95%;
  margin: 0 auto;
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
