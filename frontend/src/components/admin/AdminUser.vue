<template>
  <div>
    <h1>AdminUser</h1>
    <div
      v-for="(users, index) in userList"
      :key="index"
      class="row fs-5 text-start">
      <div>
        <p>사용자명: {{ users.userName }}</p>
        <p class="text-primary">닉네임: {{ users.nickname }}</p> 
      </div>
      <v-btn @click="deleteUser(users)" color="error" samll depressed>삭제</v-btn>
      <hr>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";

export default {
  name : 'AdminUser',

  data: function () {
    return {
      userList: [],
    }
  },

  methods: {
    allUser() {
      http
        .get('/user/total')
        .then((response) => {
          this.userList = response.data;
          console.log(response.data);
        })
    },
    
    deleteUser(users) {
      console.log(users);
      http
        .delete(`/user/${users.userId}`) 
        .then((response) => {
          if (response.status == 200) {
            this.$router.go();
          }
        });
    },
  },

  created () {
  this.allUser();
  }
};
</script>

<style>

</style>