<template>
  <div>
    <!-- 현재 주소 -->
    <!-- <div class="location mt-3 mb-3">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="16"
        height="16"
        fill="#8cb883"
        class="bi bi-pin-map"
        viewBox="0 0 16 16"
      >
        <path
          fill-rule="evenodd"
          d="M3.1 11.2a.5.5 0 0 1 .4-.2H6a.5.5 0 0 1 0 1H3.75L1.5 15h13l-2.25-3H10a.5.5 0 0 1 0-1h2.5a.5.5 0 0 1 .4.2l3 4a.5.5 0 0 1-.4.8H.5a.5.5 0 0 1-.4-.8l3-4z"
        />
        <path
          fill-rule="evenodd"
          d="M8 1a3 3 0 1 0 0 6 3 3 0 0 0 0-6zM4 4a4 4 0 1 1 4.5 3.969V13.5a.5.5 0 0 1-1 0V7.97A4 4 0 0 1 4 3.999z"
        />
      </svg>
      <span> {{ currentAddress }}</span>
    </div> -->
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
import { mapGetters, mapActions } from "vuex";
import http from "@/util/http-common";

export default {
  name: "SloganPage",
  data() {
    return {
      slide: 0,
      currentLongitude: 33.452278,
      currentxLatitude: 126.567803,
      userDto: {},
      tab: "",
    };
  },
  computed: {
    // ...mapGetters("store", ["currentAddress"]),
    ...mapGetters("accounts", ["userCheck"]),
  },
  methods: {
    ...mapActions("store", ["getAddress", "getCurrentX", "getCurrentY"]),
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
    findaddress() {
      if (navigator.geolocation) {
        // 현재 위치
        navigator.geolocation.getCurrentPosition((position) => {
          (this.currentxLatitude = position.coords.latitude), // 위도
            (this.currentLongitude = position.coords.longitude); // 경도
          this.changeaddress();
        });
      }
    },
    changeaddress() {
      var geocoder = new kakao.maps.services.Geocoder();

      this.getCurrentX(this.currentxLatitude);
      this.getCurrentY(this.currentLongitude);
      var coord = new kakao.maps.LatLng(
        this.currentxLatitude,
        this.currentLongitude
      );
      var callback = (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          var address = result[0].address.address_name;
          this.getAddress(address);
          // console.log(address);
        } else {
          // console.log("실패");
        }
      };
      geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
    },
    goIntro() {
      this.$router.push({
        name: "instruction",
        params: { tab: 0 },
      });
    },
    goHow() {
      this.$router.push({
        name: "instruction",
        params: { tab: 1 },
      });
    },
  },
  created() {
    this.findaddress();
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");

    http.get("/user").then((response) => {
      this.userDto = response.data;
      // console.log(this.userDto);
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
