<template>
<div>
   <form name="form">
        <div class="form-group">
          <input
            type="text"
            class="form-control"
            name="username"
            v-validate="'required'"
            placeholder="아이디를 입력해주세요"
          />
        </div>
        <br>
        <div class="form-group">
          <input
            type="text"
            class="form-control"
            name="username"
            v-validate="'required'"
            placeholder="비밀번호를 입력해주세요"
          />
        </div>
        <br>
   <b-button type="button" id="btn_login" squared variant="primary">로그인하기</b-button>
   </form>
   </div>
</template>

<script>
export default {
    name: "LoginComponent"
}
</script>

<style scoped>
#btn_login {
  width: 100%;
}
</style>