<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="success" dark v-bind="attrs" v-on="on">
          주문확인하기
        </v-btn>
      </template>

      <v-card>
        <v-card-title class="text-h5 lighten-2"> 주문 상세보기 </v-card-title>

        <v-card class="mx-auto my-auto card-box" max-width="344" outlined>
          <div mt-5 class="row mt-3">
            <img
              class="img-box col-5"
              src="https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/4gqX/image/wIXZfUhOPGGGZxaZ0Nsmigd1paU.jpeg"
              alt="사진이었던것.."
            />
            <div class="col-7 mt-2 order-box">
              <v-list-item-content>
                <v-list-item-title class="text msg-box">
                  최지은 님의
                </v-list-item-title>
                <v-list-item-title class="text msg-box"
                  >주문이 도착했습니다.</v-list-item-title
                >
              </v-list-item-content>
            </div>
          </div>
          <v-card class="mx-auto m-3" max-width="300" outlined>
            <div>
              <img
                class="col-5"
                src="@/assets/images/hambuger.jpg"
                alt="사진이었던것.."
              />
              <v-list-item-content>
                <v-list-item-subtitle
                  >제품명 : 불고기 버거</v-list-item-subtitle
                >
                <v-list-item-subtitle>수량 : 2개</v-list-item-subtitle>
                <v-list-item-subtitle
                  >예상 도착 시간 : 15분</v-list-item-subtitle
                >
              </v-list-item-content>
            </div>
            <div class="btn-box">
              <v-card-actions>
                <refuse-modal></refuse-modal>
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
      this.dialog = false;
    },
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style scoped>
.order-box {
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.msg-box {
  display: flex;
  text-align: start;
}
.img-box {
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
.btn-box {
  display: flex;
  justify-content: space-evenly;
}
</style>
