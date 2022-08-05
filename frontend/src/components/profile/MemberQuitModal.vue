<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <button v-bind="attrs" v-on="on">회원탈퇴</button>
      </template>

      <v-card>
        <div class="d-flex justify-content-center">
          <v-card-title> 회원 탈퇴하기 </v-card-title>
        </div>
        <v-card class="mx-auto my-auto card-box" max-width="400" outlined>
          <div mt-5 class="mt-3">
            <div class="d-flex justify-content-center">
              <span
                >지구를 지키는 {{ userDto.nickname }}님, <br />정말
                탈퇴하시겠습니까?</span
              >
            </div>
          </div>
          <br />

          <div class="btn-box">
            <v-card-actions>
              <noQuitModal></noQuitModal>
            </v-card-actions>
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
.btn-box {
  display: flex;
  justify-content: space-evenly;
}
</style>
