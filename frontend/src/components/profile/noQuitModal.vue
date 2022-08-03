<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <b-button pill variant="outline-danger" v-bind="attrs" v-on="on">
          예 탈퇴하겠습니다
        </b-button>
      </template>
      <v-card>
        <v-card-title class="d-flex justify-content-center">
          <br />
          그래도, 우리는 <br />세상을 구하는 당신을 응원합니다! <br />
        </v-card-title>
        <img
          src="@/assets/logo.png"
          width="120"
          height="80"
        /><br /><br /><br />
        <b-button pill variant="outline-danger" @click="deleteUser()"
          >안녕~!</b-button
        >

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
.btn-box {
  justify-content: center;
}
.reason {
  max-width: 344px;
}
#green {
  color: green;
}
</style>
