<template>
  <div class="user join wrapC">

    <div class="form-wrap mt-5">
      <v-text-field class="input-with-label"
        v-model="currentPassword"
          id="current-password"
          :type="currentPasswordType"
           label="현재 비밀번호"
          >

      </v-text-field>
      <v-text-field class="input-with-label"
          v-model="password"
          id="password"
          :type="passwordType"
          label="새 비밀번호.">
      </v-text-field>
      <v-text-field 
        class="input-with-label"
        v-model="passwordConfirm"
          :type="passwordConfirmType"
          id="password-confirm"
          label="새 비밀번호 재입력">
      </v-text-field>
    </div>

    <button class="btn-bottom" @click="changePw">변경하기</button>
  </div>
</template>

<script>


export default {
  name: "PasswordChangeView",
  data: () => {
    return {
      email: "",
      currentPassword:"",
      password: "",
      passwordConfirm: "",
      isLoading: false,
      error: {
        email: false,
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
    };
  },
  methods: {
    changePw() {
      if (this.password == this.passwordConfirm) {
        this.$router.push({ name: "main" })
      } else {
        alert("비밀번호가 다릅니다.");
      }
    },
  },
};
</script>
<style scoped>
.btn-box {
  min-width: 344px;
}
.status-box {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.status-btn {
  padding: 0;
}
</style>