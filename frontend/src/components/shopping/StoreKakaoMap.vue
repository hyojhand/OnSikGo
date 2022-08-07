<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script >

import {mapGetters} from "vuex";
import http from "@/util/http-common";
export default {
  name: "StoreKakaoMap",
  data() {
    return {
      mark: [],
      storeDto : [],
    };
  },
  computed: {
    ...mapGetters("storeStore", ["getStoreId"]),
  },

  async created() {
    await http
        .get(`/store/${this.getStoreId}`).then((response) => {
          this.mark = [response.data.lat, response.data.lng]
          
    });
    await this.createMap()
  },

  methods: {
    // 카카오맵 생성
    createMap(){
        if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=204f7abed9a6558eb3411fabf8202302";
        document.head.appendChild(script);
      }
    },
    initMap() {
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      
      mapOption = { 
          center: new kakao.maps.LatLng(this.mark[0], this.mark[1]), // 지도의 중심좌표
          level: 3 // 지도의 확대 레벨
      };
      var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
      var imageSrc = "https://cdn4.iconfinder.com/data/icons/food-delivery-service-3/100/food_delivery_gps_mark_service_boy_online-256.png" 
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(40, 45); 
      
      // 마커 이미지를 생성합니다    
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)   

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.mark[0], this.mark[1]), // 마커를 표시할 위치 
        image : markerImage, // 마커 이미지
      });
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);
    },


    // // 인포원도우를 표시하는 클로지를 만드는 함수

  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 300px;
  margin-bottom: 2%;
  border-top: 2px solid rgba(0, 0, 0, 0.2);
  border-bottom: 2px solid rgba(0, 0, 0, 0.2);
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}


</style>
