<template>
  <div>

    <!--프로필 이미지 업로드-->
    <div class="mt-5">
      <b-img :src="previewImg" height="150px" width="150px" />
      <p class="d-flex justify-content-end">
        <input @change="fileSelect" type="file" />
      </p>
    </div>

    <!--입력항목-->
    <div class="d-flex justify-content-center mt-5">
      <div class="col-4">
        <p class="mb-5">이메일</p>
        <p class="mt-4">닉네임</p>
        <p class="mt-5">비밀번호</p>
      </div>

      <!--정보입력칸-->
      <div class="col-8">
        <p>{{ userDto.email }}</p>
        <div class="container">
          <div class="row">
            <div class="col-7">
              <input
                class="form-control form-control-sm mt-2"
                type="text"
                placeholder="변경할 닉네임을 입력해주세요"
                aria-label=".form-control-sm example"
                v-model="userDto.nickname"
              />
            </div>
            <!--닉네임 중복확인 시작-->
            <div class="col-5">
              <button
                class="border-m radius-m name-confrim-btn"
                @click="nicknameCheck"
              >
                중복확인
              </button>
            </div>
            <!--닉네임 중복확인 끝-->
          </div>
        </div>
        <b-button
          @click="changePassword()"
          pill
          variant="outline-success"
          class="d-grid col-4 mt-5"
          >수정
        </b-button>
      </div>
    </div>
    <b-button
      @click="changeUser()"
      pill
      variant="outline-success"
      class="d-grid col-8 mx-auto mt-5"
      >수정완료</b-button
    >
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
      previewImg:null
    };
  },

  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    await http.get("/user").then((response) => {
      this.userDto = response.data;
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
        reader.onload = e => {
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
.name-confrim-btn {
  right: 0px;
  top: 15px;
  width: 90px;
  font-size: 13px;
  color: black;
}
</style>
