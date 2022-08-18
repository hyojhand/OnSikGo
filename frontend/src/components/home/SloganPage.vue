<template>
  <div>
    <!-- 환영 인사 -->
    <div v-if="this.userCheck !== 0">
      <div class="mt-4 greeting">안녕하세요 {{ userDto.nickname }}님</div>
      <div class="mb-4 greeting">온식고를 찾아주셔서 감사합니다</div>
    </div>
    <!-- 슬로건 -->
    <v-carousel
      cycle
      height="220px"
      hide-delimiter-background
      show-arrows-on-hover
      class="mt-3"
    >
      <v-carousel-item>
        <img
          @click="goIntro()"
          width="95%"
          height="220px"
          src="@/assets/images/main.png"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
      <v-carousel-item>
        <img
          @click="goHow()"
          width="95%"
          height="220px"
          src="@/assets/images/howto.png"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
      <v-carousel-item>
        <img
          width="95%"
          height="220px"
          src="@/assets/images/ment.png"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
      <v-carousel-item>
        <a
          href="https://docs.google.com/forms/d/e/1FAIpQLSd06jrEu_mbcrAuiuSOw5JhffJI1aboOUGJX81hHCYbPYHoNQ/viewform"
        >
          <img
            width="95%"
            height="220px"
            src="@/assets/images/ask.png"
            alt="image slot"
            style="border-radius: 7px"
          />
        </a>
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=204f7abed9a6558eb3411fabf8202302&libraries=services,clusterer,drawing"
></script>
<script>
import { mapGetters } from "vuex";
import http from "@/util/http-common";

export default {
  name: "SloganPage",
  data() {
    return {
      slide: 0,
      userDto: {},
      tab: "",
    };
  },
  computed: {
    ...mapGetters("accounts", ["userCheck"]),
  },
  methods: {
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
    //  소개글로 가기
    goIntro() {
      this.$router.push({
        name: "instruction",
        params: { tab: 0 },
      });
    },
    // 이용글로 가기
    goHow() {
      this.$router.push({
        name: "instruction",
        params: { tab: 1 },
      });
    },
  },
  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    http.get("/user").then((response) => {
      this.userDto = response.data;
    });
  },
};
</script>

<style scoped>
.location {
  margin: 1px;
}

.location span {
  color: #333;
  font-weight: 500;
  font-size: 16px;
  margin-left: 5px;
  /* 밑줄 */
  border-bottom: 2px solid #8cb883;
}
.greeting {
  font-size: 18px;
  width: 100%;
  padding-left: 5%;
  text-align: start;
}
</style>
