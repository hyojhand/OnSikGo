<template>
  <div>
    <!-- 현재 주소 -->
    <div class="location mt-3 mb-3">
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
    </div>

    <!-- 슬로건 -->
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="3000"
      controls
      indicators
      img-width="1024"
      img-height="500"
      style="text-shadow: 1px 1px 2px #333; margin-top: 1rem"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <b-carousel-slide>
        <template #img>
          <img
            width="90%"
            height="180px"
            src="@/assets/images/onsikgo_slogan.png"
            alt="image slot"
            style="border-radius: 7px"
          />
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <img
            width="90%"
            height="180px"
            src="@/assets/images/earth.jpg"
            alt="image slot"
            style="border-radius: 7px"
          />
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <img
            width="90%"
            height="180px"
            src="@/assets/images/save.jpg"
            alt="image slot"
            style="border-radius: 7px"
          />
        </template>
      </b-carousel-slide>
      <b-carousel-slide>
        <template #img>
          <img
            width="90%"
            height="180px"
            src="@/assets/images/donation.jpg"
            alt="image slot"
            style="border-radius: 7px"
          />
        </template>
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=204f7abed9a6558eb3411fabf8202302&libraries=services,clusterer,drawing"
></script>
<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "SloganPage",
  data() {
    return {
      slide: 0,
      currentLongitude: 33.452278,
      currentxLatitude: 126.567803,
    };
  },
  computed: {
    ...mapGetters("store", ["currentAddress"]),
  },
  methods: {
    ...mapActions("store", [
      "getAddress",
      "getCurrentX",
      "getCurrentY",
    ]),
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
  },
  created() {
    this.findaddress()
  }
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
</style>
