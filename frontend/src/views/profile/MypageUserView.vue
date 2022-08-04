<template>
  <div>
    <div class="mt-6">
      <img :src="`${userDto.imgUrl}`" width="100" height="75" />
    </div>

    <div class="mt-3">
      <span
        >{{ userDto.userName }} 님!, <br />이번 달에
        <strong id="green">온식고</strong>를 통해 <br /><strong id="green"
          >주문금액원</strong
        >의 세상을 구하셨어요!</span
      >
    </div>
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
    <h5>✨ {{ userDto.userName }} 님의 단골매장</h5>
    <div>
      <regularList
        v-for="(store, index) in storeregularList"
        :key="index"
        v-bind="store"
      >
      </regularList>
    </div>

    <br />
  </div>
</template>

<script>
import regularList from "@/components/profile/regularList.vue";
import http from "@/util/http-common";
export default {
  name: "MypageUserView",
  components: {
    regularList,
  },
  data() {
    return {
      userDto: {},
      storeregularList: [],
    };
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    http.get("/user").then((response) => {
      this.userDto = response.data;
    });

    http.get("/follow").then((response) => {
      console.log(response.data);
      this.storeregularList = response.data;
    });
  },
  methods: {
    orderlist() {
      this.$router.push("/mypage/user/history");
    },
    reviewlist() {
      this.$router.push({
        name: "myReview",
        params: { nickname: this.userDto.nickname },
      });
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
