<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <button v-bind="attrs" v-on="on">회원탈퇴</button>
      </template>

      <v-card>
        <div class="d-flex justify-content-center">
          <v-card-title> <h1>회원 탈퇴</h1> </v-card-title>
        </div>
        <v-card class="mx-auto my-auto card-box" max-width="400" outlined>
          <div class="container">
            <div class="row">
              <div class="col-3">
                <img :src="this.userDto.imgUrl" width="80px" height="=80px" />
              </div>
              <div class="col-8 mt-6">
                <span
                  >{{ userDto.nickname }}님, <br />정말
                  탈퇴하시겠습니까?😢</span
                >
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-around mt-6">
            <noQuitModal></noQuitModal>
            <button @click="backToMypage()">아니요!</button>
          </div>
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
import noQuitModal from "@/components/profile/noQuitModal.vue";
import http from "@/util/http-common";
export default {
  name: "NoticeModal",
  components: {
    noQuitModal,
  },
  data() {
    return {
      dialog: false,
      userDto: [],
    };
  },
  created() {
    if (localStorage.getItem("access-token") == null) return;
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http.get("/user").then((response) => {
      this.userDto = response.data;
      console.log(this.userDto);
      console.log("&&");
    });
  },
  methods: {
    backToMypage() {
      this.dialog = false;
    },
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
  font-size: 0.7rem;
}
.img-box {
  margin: 0 auto;
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
</style>
