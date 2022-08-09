<template>
  <div>
    <div class="mt-7">
      <span style="font-size: 1.5rem; font-weight: bold">🙋‍♀️ 리뷰 🙋‍♂️</span>
    </div>
    <div v-if="myReviewList.length">
      <reviewList
        v-for="(review, index) in myReviewList.reverse()"
        :key="index"
        v-bind="review"
      />
    </div>
    <div v-else class="non-msg">
      <div>아직 등록한</div>
      <div>리뷰가 없어요 ㅠ</div>
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
  data() {
    return {
      nickname: "",
    };
  },
  computed: {
    ...mapGetters("accounts", ["myReviewList"]),
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http
      .post("/review/user", {
        nickname: this.$route.params.nickname,
      })
      .then((response) => {
        console.log(this.reviewList);
        if (response.status == 200) {
          if (response.data != null) {
            this.getMyReviewList(response.data);
            // console.log(this.reviewList);
          } else {
            alert("리뷰가 없습니다.");
          }
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
