<template>
  <div class="text-modal">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <button id="button-discard" v-bind="attrs" v-on="on">
          매장정보폐기
        </button>
      </template>

      <div class="card">
        <div class="mt-4">
          <h1 style="color: black">매장 정보 폐기 확인창</h1>
          <div class="mt-3">
            <span style="color: rgb(213, 128, 48)" class="text-s"
              >💥해당 매장의 정보가 전부 삭제됩니다💥</span
            >
          </div>
        </div>
        <div class="card-box">
          <div mt-5 class="row mt-3 ml-3">
            <img
              width="120"
              height="150"
              class="col-5"
              :src="`${this.discardStoreImg}`"
            />
            <div class="col-6 mt-7 mr-2">
              <span style="color: black" class="text-m"
                >"{{ this.discardStoreName }}"의 매장정보를<br />
                폐기하시겠습니까?</span
              >
              <br />
            </div>
          </div>
          <div class="d-flex justify-content-around mt-10">
            <button id="nodiscard-btn" @click="backToMypage()">아니요</button>
            <button id="discard-btn" @click="deleteStore()">
              네 삭제합니다!
            </button>
          </div>
        </div>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </div>
    </v-dialog>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "NoticeModal",
  data() {
    return {
      dialog: false,
      stname: "",
      stimg: "",
    };
  },
  computed: {
    ...mapGetters("discardStore", [
      "discardStoreId",
      "discardStoreName",
      "discardStoreImg",
    ]),
  },
  created() {
    http.get(`/store/${this.discardStoreId}`).then((response) => {
      // console.log(this.discardStoreId);
      this.stname = response.data.storeName;
      this.stimg = response.data.storeImgUrl;
    });
  },
  methods: {
    ...mapActions("select", ["resetValue"]),
    backToMypage() {
      this.dialog = false;
    },
    deleteStore() {
      http.delete(`/store/${this.discardStoreId}`).then((response) => {
        if (response.status == 200) {
          alert("가게 정보 폐기 완료되었습니다!");
          this.resetValue();
          this.$router.go();
        } else {
          alert("가게 정보 삭제에 실패했습니다.");
        }
      });
    },
  },
};
</script>

<style scoped>
#nodiscard-btn {
  height: 40px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #64a258;
  color: #ffffff;
  width: 150px;
}
#discard-btn {
  height: 40px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #d97b38;
  color: #ffffff;
  width: 150px;
}
#button-discard {
  font-weight: bolder;
  width: 100%;
  margin: 0 0;
}
</style>
