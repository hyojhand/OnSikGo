<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="success" dark v-bind="attrs" v-on="on"> 매장폐기 </v-btn>
      </template>

      <v-card>
        <v-card-title class="text-h5 lighten-2"> 매장 정보 폐기 </v-card-title>

        <v-card class="mx-auto my-auto card-box" max-width="344" outlined>
          <div mt-5 class="row mt-3">
            <img
              class="img-box col-5"
              src="@/assets/images/이마트24.png"
              alt="사진이었던것.."
            />
            <div class="col-7 mt-2 order-box">
              <v-list-item-content>
                <v-list-item-title class="text msg-box">
                  정말로 매장정보를
                </v-list-item-title>
                <v-list-item-title class="text msg-box"
                  >폐기하시겠습니까?</v-list-item-title
                >
              </v-list-item-content>
            </div>
          </div>
          <v-card class="mx-auto m-3" max-width="300" outlined>
            <div>
              <v-list-item-content>
                <v-list-item-subtitle
                  >해당 매장의 모든 정보가 폐기됩니다</v-list-item-subtitle
                >
              </v-list-item-content>
            </div>
            <div class="btn-box">
              <v-card-actions>
                <b-button
                  pill
                  @click="backToMypage()"
                  variant="outline-success"
                >
                  아니요
                </b-button>
              </v-card-actions>
              <v-card-actions>
                <b-button pill variant="outline-danger" @click="deleteStore()">
                  예, 삭제합니다
                </b-button>
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
import http from "@/util/http-common";
export default {
  name: "NoticeModal",
  props: {
    no: Number,
  },
  methods: {
    backToMypage() {
      this.dialog = false;
    },
    deleteStore() {
      http.delete(`/store/${this.no}`).then((response) => {
        if (response.status == 200) {
          alert("가게 정보가 삭제되었습니다.");
        } else {
          alert("가게 정보 삭제에 실패했습니다.");
        }
      });
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
