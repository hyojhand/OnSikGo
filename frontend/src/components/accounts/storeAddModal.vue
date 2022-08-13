<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <button v-bind="attrs" v-on="on">제발 되어주세요</button>
      </template>

      <v-card>
        <v-card-title class="text-h5 lighten-2"> 회원 탈퇴하기 </v-card-title>

        <v-card class="mx-auto my-auto card-box" max-width="344" outlined>
          <div mt-5 class="row mt-3">
            <h1>뭐야</h1>
            <img
              class="img-box col-5"
              src="https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/4gqX/image/wIXZfUhOPGGGZxaZ0Nsmigd1paU.jpeg"
              alt="사진이었던것.."
            />
            <div class="col-7 mt-2 order-box">
              <v-list-item-content>
                <v-list-item-title class="text msg-box">
                  {{ userDto.userName }}님, 정말 탈퇴하시겠습니까?
                </v-list-item-title>
                <v-list-item-title class="text msg-box"
                  >지구를 지키는 용사여,,
                </v-list-item-title>
              </v-list-item-content>
            </div>
          </div>
          <br />

          <div class="btn-box">
            <v-card-actions>
              <noQuitModal></noQuitModal>
            </v-card-actions>
            <b-button pill variant="outline-success" @click="backToMypage()">
              아니요!
            </b-button>
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
export default {};
</script>

<style></style>
