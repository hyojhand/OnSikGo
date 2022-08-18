<template>
  <div>
    <div class="mt-3">
      <div style="font-size: 1.5rem; font-weight: bold">🙋‍♀️ 리뷰 🙋‍♂️</div>
    </div>
    <div v-if="myReviewList.length">
      <reviewList
        v-for="(review, index) in myReviewList"
        :key="index"
        v-bind="review"
      />
    </div>
    <div v-else class="non-msg">
      <div>아직 등록한</div>
      <div>리뷰가 없어요!</div>
    </div>
  </div>
</template>

<script>
import reviewList from "@/components/profile/reviewList.vue";
import http from "@/util/http-common";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "MyReviewView",
  components: {
    reviewList,
  },
  computed: {
    ...mapGetters("accounts", ["myReviewList", "reviewNickName"]),
  },
  created() {
    http
      .post("/review/user", {
        nickname: this.reviewNickName,
      })
      .then((response) => {
        if (response.status == 200) {
          this.getMyReviewList(response.data.reverse());
        }
      });
  },
  methods: {
    ...mapActions("accounts", ["getMyReviewList"]),
  },
};
</script>

<style scoped>
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
