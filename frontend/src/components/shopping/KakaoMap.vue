<template>
  <div>
    <div id="map"></div>
    <div class="button-group">

    </div>
  </div>
</template>

<script >

import {mapGetters, mapActions} from "vuex";

export default {
  name: "KakaoMap",
  data() {
    return {
      currentxLatitude: 33.452278,
      currentLongitude: 126.567803,
      markerPositions1: [
        [35.230016, 129.076428],
        [35.200016, 129.056428],
        [35.160016, 129.036428],
      ],
      markers: [],
      infowindow: null,
    };
  },
  computed: {
    ...mapGetters("store", [
      "aroundSaleStore",
    ])
  },

  created() {
    console.log(this.aroundSaleStore)
    if (navigator.geolocation) {
    // 현재 위치
      navigator.geolocation.getCurrentPosition((position) => {
      this.currentxLatitude = position.coords.latitude, // 위도
      this.currentLongitude = position.coords.longitude; // 경도
      // 현재위치
      // console.log(this.currentLongitude, this.currentxLatitude)
      this.curruntLocation();
      
    });
    // 못찾은 경우
    } 
  },

  methods: {
    // 현재 위치 주소 vuex에 넣기
    ...mapActions("store", [
      "getAddress",
      "getCurrentX",
      "getCurrentY",
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

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      this.nowMarker()
    },
    // 
    nowMarker(){

      this.aroundSaleStore.forEach((store) =>{
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35); 
        console.log("가게 하나의 위도 경도",store)
        
        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 

        var marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(store[0], store[1]), // 마커를 표시할 위치
            // title : positions[i].title, 
            image : markerImage // 마커 이미지 
          });
          // console.log(this.map)
          marker.setMap(this.map);
      })
    },
      
    // 현재 위치 찾기
    async curruntLocation() {
      this.changeaddress()
      this.createMap()
    },

    // 도로명 주소 변환
    changeaddress() {
      var geocoder = new kakao.maps.services.Geocoder();

      this.getCurrentX(this.currentxLatitude)
      this.getCurrentY(this.currentLongitude)
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
