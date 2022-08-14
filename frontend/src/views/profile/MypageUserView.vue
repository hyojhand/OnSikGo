<template>
  <div>
    <div class="mt-5">
      <img :src="`${userDto.imgUrl}`" width="100" height="100" />
    </div>

    <div class="mt-3">
      <span
        >{{ userDto.nickname }} 님! <br />이번 달에
        <strong id="green">온식고</strong>를 통해 <br /><strong id="green"
          >{{ this.orderPrice }}원</strong
        >의 세상을 구하셨어요!</span
      >
    </div>
    <br /><br />
    <div id="space-even">
      <button @click="orderlist()" id="mypage-btn">주문내역</button>
      <button @click="reviewlist()" id="mypage-btn">리뷰내역</button>
    </div>
    <br />
    <hr />
    <br />
    <span style="font-weight: bolder; font-size: 1.3rem"
      >{{ userDto.nickname }} 님의 단골매장</span
    ><br />
    <span style="font-weight: light; font-size: 0.7rem; color: #d47d4a"
      >* 클릭하시면 각 매장의 정보 페이지로 이동합니다. *</span
    >
    <div v-if="this.storeregularList.length">
      <regularList
        v-for="(store, index) in storeregularList"
        :key="index"
        v-bind="store"
      >
      </regularList>
    </div>
    <div v-else class="non-msg">
      <div>단골 매장을</div>
      <div>등록해보는건 어떨까요?</div>
    </div>
    <br />
  </div>
</template>

<script>
import regularList from "@/components/profile/regularList.vue";
import http from "@/util/http-common";
import { mapActions } from "vuex";
export default {
  name: "MypageUserView",
  components: {
    regularList,
  },
  data() {
    return {
      userDto: {},
      storeregularList: [],
      orderPrice: "",
    };
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    http.get("/user").then((response) => {
      this.userDto = response.data;
      // console.log(this.userDto);
    });

    http.get("/follow").then((response) => {
      // console.log(response.data);
      this.storeregularList = response.data;
    });

    http.get("/order/price").then((response) => {
      this.orderPrice = response.data;
    });
  },
  methods: {
    ...mapActions("accounts", ["getReviewNickName"]),
    orderlist() {
      this.$router.push("/mypage/user/history");
    },
    reviewlist() {
      this.$router.push({
        name: "myReview",
      });
      this.getReviewNickName(this.userDto.nickname);
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
#mypage-btn {
  height: 40px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #368f3d;
  color: #ffffff;
  width: 100px;
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
