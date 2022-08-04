<template>
  <div>
    <card id="ordercard">
      <b-row>
        <b-col class="mt-3" md="4">
          <img
            fluid
            src="@/assets/images/hambuger.jpg"
            width="50"
            height="50"
          />
        </b-col>
        <b-col md="4">
          <div class="text-align-center" id="cardInText">
            <h5>제품명</h5>
            <span id="loca">매장위치: 매장위치</span><br />
            <span id="time">주문시각: 주문시각</span><br />
            <span id="count">수량: 개수</span>
          </div>
        </b-col>
        <b-col md="4">
          <button id="btn-order" type="button">주문취소</button><br /><br />
          <button id="btn-order" @click="goStore()" type="button">
            가게보기
          </button>
        </b-col>
      </b-row>
    </card>
  </div>
</template>

<script>
export default {
  name: "orderList",
  methods: {
    goStore() {
      this.$router.push("/store");
    },
  },
};
</script>

<style scoped>
#loca {
  font-size: 1em;
}
#time {
  font-size: 1em;
}
#count {
  font-size: 0.01em;
  color: gray;
}
#ordercard {
  height: 100px;
  width: 350px;
  border-radius: 15px;
  display: inline-block;
  margin-top: 30px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  overflow: hidden;
  background-color: white;
}
#btn-order {
  height: 25px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  box-sizing: border-box;
  background-color: #de9712;
  color: #ffffff;
  width: 70px;
  font-size: 0.75rem;
}
</style>
