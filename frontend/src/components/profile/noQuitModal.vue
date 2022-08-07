<template>
  <div class="text-center">
    <v-dialog width="500" height="800">
      <template v-slot:activator="{ on, attrs }">
        <button id="btn-quit" v-bind="attrs" v-on="on">탈퇴!</button>
      </template>
      <v-card>
        <v-card-title class="d-flex justify-content-center">
          <div class="container">
            그래도, 온식고는 <br />세상을 구하는 당신을 응원합니다!
          </div>
          <div class="img-box">
            <img src="@/assets/logo.png" width="120" height="80" />
          </div>
          <div class="button-box">
            <button id="btn-real-quit" @click="deleteUser()">안녕~!</button>
          </div>
        </v-card-title>

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
  name: "noQuitModal",
  methods: {
    deleteUser() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.delete(`/user`).then(() => {
        localStorage.removeItem("access-token");
        this.$router.push("/");
      });
    },
  },
};
</script>

<style scoped>
.reason {
  max-width: 500px;
  height: 500px;
}
#green {
  color: green;
}
#btn-quit {
  width: 100px;
}
#btn-real-quit {
  width: 100px;
  color: red;
}
</style>
