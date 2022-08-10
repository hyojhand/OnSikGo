<template>
  <div>
    <!--프로필 이미지 업로드-->
    <div class="mt-5">
      <b-img :src="previewImg" height="150px" width="150px" />
      <p class="d-flex justify-content-end">
        <input @change="fileSelect" type="file" />
      </p>
    </div>

    <div class="ml-4 mr-16 mt-10">
      <div>
        <div class="container">
          <div class="row">
            <div class="col-3">
              <span>이메일</span>
            </div>
            <div class="col-8">
              <div class="d-flex justify-content-start ml-5">
                <span id="black">{{ userDto.email }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="ml-2 mt-3">
      <div class="d-flex justify-content-between">
        <div class="container">
          <div class="row">
            <div class="col-3">
              <span>닉네임</span>
            </div>
            <div class="col-5 d-flex justify-content-start">
              <input
                v-model="userDto.nickname"
                type="text"
                placeholder="변경할 닉네임을 입력해주세요"
                id="input-id"
              />
            </div>
            <div class="ml-1 col-3">
              <button id="btn-check" @click="nicknameCheck">중복확인</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="this.userDto.loginType == 'ONSIKGO'" class="ml-6 mt-3">
      <div class="container">
        <div class="row">
          <div class="col-6">
            <div class="d-flex justify-content-start">
              <span>비밀번호 수정</span>
            </div>
          </div>
          <div class="col-3 ml-16">
            <button id="btn-check" @click="changePassword()">수정</button>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <div class="mt-4">
        <span style="color: rgb(222, 124, 39); font-size: 0.8rem"
          >소셜 로그인으로 가입한 경우 비밀번호 변경이 어렵습니다.</span
        >
      </div>
    </div>
    <div class="ml-10 mr-10 mt-10">
      <div class="d-flex justify-content-end">
        <button id="finish-btn" @click="changeUser()">수정완료</button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "UserInfoChangeView",
  data() {
    return {
      imgFile: null,
      userDto: {},
      nicknameDuple: false,
      previewImg: null,
    };
  },

  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    await http.get("/user").then((response) => {
      this.userDto = response.data;
      console.log(this.userDto.loginType);
      this.previewImg = this.userDto.imgUrl;
      this.role = response.data.role;
    });
  },
  methods: {
    changeUser() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");

      const formData = new FormData();
      formData.append("file", this.imgFile);
      formData.append(
        "userDto",
        new Blob([JSON.stringify(this.userDto)], { type: "application/json" })
      );
      http
        .put("/user", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          if (response.status == 200) {
            alert("유저정보 수정완료");
            if (this.userDto.role === "USER") {
              this.$router.push({ name: "mypageUser" });
            } else {
              this.$router.push({ name: "mypageOwner" });
            }
          }
        });
    },
    changePassword() {
      this.$router.push("/infochange/pwchange");
    },
    nicknameCheck() {
      http
        .post("/user/nickname", {
          nickname: this.userDto.nickname,
        })
        .then((response) => {
          if (response.status == 200) {
            this.nicknameDuple = !this.nicknameDuple;
            alert("중복된 닉네임이 없어 수정 완료되었습니다");
          } else {
            alert("중복된 닉네임이 있습니다");
          }
        });
    },
    fileSelect(event) {
      var input = event.target;

      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.previewImg = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImg = null;
      }
      this.imgFile = input.files[0];
    },
  },
};
</script>

<style scoped>
#black {
  color: black;
}
#input-id {
  color: black;
  width: 150px;
  height: 30px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
#btn-check {
  height: 30px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #45a245;
  color: #ffffff;
  width: 70px;
}
#finish-btn {
  height: 30px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #45a245;
  color: #ffffff;
  width: 150px;
}
</style>
