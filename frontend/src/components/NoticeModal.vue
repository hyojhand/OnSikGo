<template>
  <div class="text-center">
    <v-dialog v-model="parents" width="344">
      <template v-slot:activator="{ on, attrs }">
        <button class="border-m radius-m notice-btn" v-bind="attrs" v-on="on">
          주문확인하기
        </button>
      </template>

      <v-card>
        <v-card-title class="text-h5 lighten-2"> 주문 상세보기 </v-card-title>

        <v-card class="mx-auto my-auto" max-width="344" outlined>
          <div mt-5 class="row mt-3 card-box">
            <img
              class="img-box col-5"
              src="https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/4gqX/image/wIXZfUhOPGGGZxaZ0Nsmigd1paU.jpeg"
              alt="사진이었던것.."
            />
            <div class="col-7 mt-2 order-box">
              <v-list-item-content class="notice-box">
                <v-list-item-title class="msg-box">
                  <span class="text-m">{ 최지은 } </span>
                  <span class="notice-msg text-m">님의</span>
                </v-list-item-title>
                <v-list-item-title class="msg-box notice-msg text-m"
                  >주문이 도착했습니다.</v-list-item-title
                >
              </v-list-item-content>
            </div>
          </div>
          <v-card class="mx-auto m-3" max-width="300" outlined>
            <div>
              <img
                class="col-5 border-s food-pic"
                src="@/assets/images/hambuger.jpg"
                alt="사진이었던것.."
              />
              <div class="mt-2">
                <div>
                  <span class="text-m noti-title">제품명 : </span>
                  <span class="text-m notice-msg">{ 불고기 버거 }</span>
                </div>
                <div>
                  <span class="text-m noti-title">수량 : </span>
                  <span class="text-m notice-msg">{ 2개 }</span>
                </div>
                <div>
                  <span class="text-m noti-title">예상 도착 시간 : </span>
                  <span class="text-m notice-msg">{ 15분 }</span>
                </div>
              </div>
            </div>
            <div class="btn-box">
              <v-card-actions>
                <refuse-modal @check-it="checkIt"></refuse-modal>
              </v-card-actions>
              <v-card-actions>
                <v-btn rounded color="success" @click="accept"> 수락 </v-btn>
              </v-card-actions>
            </div>
          </v-card>
        </v-card>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import RefuseModal from "@/components/RefuseModal.vue";
export default {
  name: "NoticeModal",
  components: { RefuseModal },
  methods: {
    accept() {
      this.parents = false;
    },
    checkIt: function () {
      this.parents = false;
    },
  },
  data() {
    return {
      parents: false,
    };
  },
};
</script>

<style scoped>
.card-box {
  background-color: white;
  margin: 0;
  padding: 0;
  width: 100%;
}
.notice-box {
  background-color: white;
  display: flex;
  justify-content: space-evenly;
  text-align: start;
}
.msg-box {
  background-color: white;
}
.msg-box > span {
  background-color: white;
}
.order-box {
  display: flex;
  justify-content: center;
  flex-direction: column;
  background-color: white;
}
.img-box {
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background-color: white;
}
.btn-box {
  display: flex;
  justify-content: space-evenly;
}
.notice-btn {
  width: 110px;
}
.notice-msg {
  color: black;
}
.food-pic {
  padding: 0;
  margin: 5px;
}
.noti-title {
  text-align: start;
}
</style>
