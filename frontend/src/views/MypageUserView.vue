<template>
  <div>
    <img src="@/assets/logo.png" width="60" height="40" />
    <br /><br />

    <span
      >{{ userDto.userName }} 님!, <br />이번 달에
      <strong id="green">온식고</strong>를 통해 <br /><strong id="green"
        >주문금액원</strong
      >의 세상을 구하셨어요!</span
    >
    <br /><br />
    <div id="space-even">
      <b-button @click="orderlist()" pill variant="outline-success"
        >주문내역</b-button
      >
      <b-button @click="reviewlist()" pill variant="outline-success"
        >리뷰내역</b-button
      >
    </div>
    <br />
    <hr />
    <br />
    <regularList></regularList>

    <br />
  </div>
</template>

<script>
import regularList from "@/components/regularList.vue";
import http from "@/util/http-common";
export default {
  name: "MypageUserView",
  components: {
    regularList,
  },
  data() {
    return {
      userDto: {},
    };
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http.get("/user").then((response) => {
      this.userDto = response.data;
    });
  },
  methods: {
    orderlist() {
      this.$router.push("/mypage/user/history");
    },
    reviewlist() {
      this.$router.push("/mypage/user/review");
    },
  },
};
</script>

<style scoped>
#green {
  color: green;
}
#space-even {
  display: flex;
  justify-content: space-evenly;
}
</style>
