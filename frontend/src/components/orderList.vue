<template>
<div>
    <h3>주문내역</h3>
    <b-card>
            <b-row>
            <b-col md="3">
              <img fluid src="@/assets/images/hambuger.jpg" width="50" height="50">
            </b-col>
            <b-col md="5">
              <div class="text-align-center" id = "cardInText">
                  <h5>제품명</h5>
                  <span id="loca">매장위치: 매장위치</span><br>
                  <span id="time">주문시각: 주문시각</span><br>
                  <span id="count">수량: 개수</span>
              </div>
            </b-col>
            <b-col md="4">
                <button type="button" class="btn btn-outline-success" style="--bs-btn-padding-y: .1rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">주문취소</button><br>
                <button @click="goStore()" type="button" class="btn btn-outline-success" style="--bs-btn-padding-y: .1rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">가게보기</button>
            </b-col>
            </b-row>
            </b-card>
</div>

</template>

<script>
export default {
    name:'orderList',
    methods: {
      goStore() {
        this.$router.push('/store')
      }
    }
}
</script>

<style scoped>
#loca {
  font-size:1em;
}
#time {
  font-size: 1em;
}
#count {
  font-size: 0.01em;
  color: gray;
}
</style>