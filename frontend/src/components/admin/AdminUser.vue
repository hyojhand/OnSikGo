<template>
  <div>
    <div
      v-for="(users, index) in userList"
      :key="index"
      class="row text-start">
      <div class="content">
      <div class="detail text-start">
        <p class="text-primary">😀 사용자명: {{ users.userName }}</p>
        <p>닉네임: {{ users.nickname }}</p> 
      </div>
        <img
          class="siren"
          src="@/assets/images/trash.png"
          @click="deleteUser(users)"
          alt="삭제버튼이었던것.."
        />
      </div>
      <hr class="mt-2">
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
.content{
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 5%;
  margin-right: 5%;
}
.detail{
  width: 320px;
}
.siren {
  width: 25px;
  height: 25px;
}
</style>