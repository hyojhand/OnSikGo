<template>
  <div>
    <div id="map"></div>
    <div class="button-group">

    </div>
  </div>
</template>

<script >

import {mapActions} from "vuex";

export default {
  name: "KakaoMap",
  data() {
    return {
      currentxLatitude: 33.452278,
      currentLongitude: 126.567803,
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markers: [],
      infowindow: null,
    };
  },

  created() {
  if (navigator.geolocation) {
    // 현재 위치
      navigator.geolocation.getCurrentPosition((position) => {
      this.currentxLatitude = position.coords.latitude, // 위도
      this.currentLongitude = position.coords.longitude; // 경도

      this.curruntLocation();
      
    });
    // 못찾은 경우
    } 
  },

  methods: {
    // 현재 위치 주소 vuex에 넣기
    ...mapActions("store", [
      "getAddress"
    ]),
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
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.currentxLatitude, this.currentLongitude),
        level: 3,
      };
      console.log(options)
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },


    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },

    // 현재 위치 찾기
    async curruntLocation() {
      this.changeaddress()
      this.createMap()

    },

    // 도로명 주소 변환
    changeaddress() {
      var geocoder = new kakao.maps.services.Geocoder();

      console.log(this.currentxLatitude, this.currentLongitude);
      var coord = new kakao.maps.LatLng(this.currentxLatitude, this.currentLongitude);
      var callback = (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
              var address = result[0].address.address_name
              this.getAddress(address);
          }
          else{
            console.log('실패')
          }
      };
      geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
    },

  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 330px;
  height: 280px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}


</style>
