<template>
  <div>
    <div
      v-for="(users, index) in userList"
      :key="index"
      class="row text-start">
      <div>
        <p>사용자명: {{ users.userName }}</p>
        <p class="text-primary">닉네임: {{ users.nickname }}</p> 
      </div>
      <v-btn @click="deleteUser(users)" color="error" style="width: 100px; left:300px;" depressed>삭제</v-btn>
      <hr class="mt-5">
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
        })
    },
    
    deleteUser(users) {
      console.log(users);
      http
        .delete(`/admin/${users.email}`)
        .then((response) => {
          if (response.status == 200) {
            this.allUser();
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