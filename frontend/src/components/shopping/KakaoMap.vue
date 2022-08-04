<template>
  <div>
    <div id="map"></div>
    <div class="container">
    
    <!-- 검색란 -->
    <div class="row">
      <div class="col">
        <input
          v-model="keyword"
          @keyup.enter="keywordSelect()"
          class="search-box" 
          type="search" 
          placeholder="        상품을 입력해주세요"
        >
        <!-- 검색 아이콘 -->
        <button class="product-search">
          <svg 
            @click="keywordSelect()"
            xmlns="http://www.w3.org/2000/svg" 
            width="20" 
            height="20" 
            fill="currentColor" 
            class="bi bi-search" 
            viewBox="0 0 16 16"
          >
          <path 
            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
          </svg>
        </button>
        <!-- 초기화 -->
        <button class="search-reset">
          <svg 
            @click="resetLitemList()"
            xmlns="http://www.w3.org/2000/svg" 
            width="20" 
            height="20" 
            fill="currentColor" 
            class="bi bi-arrow-clockwise" 
            viewBox="0 0 16 16">
            <path fill-rule="evenodd" 
              d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
            <path 
              d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
          </svg>
        </button>
      </div>
    </div>

    <div
        class="item-card row"
        v-for="(item, index) in items"
        :key="index"
        v-bind="item"
      >
      <!-- 마커 -->
      <!-- <div class="col-2">
        <h1>{{ item.index }}</h1>
      </div> -->
      <div class="col-5">
        <img @click="moving(item)" :src="item.itemDto.itemImgUrl" style="widght:80px; height:80px; cursor:pointer;" alt="">
      </div>
      <div class="col-4">
        <div class="product-name">{{ item.itemDto.itemName }}</div>
        <div class="product-location">매장위치 : {{ item.saleDto.storeDto.storeName }}</div>
        <div class="product-prediction">현재 위치로부터 {{ item.distance }} m</div>
        <div class="product-discount">{{ ((1 - item.salePrice / item.itemDto.price) * 100).toFixed(0) }}%</div>
        <span class="price">{{ item.itemDto.price }}원</span>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
        </svg>
        <span class="discount-price">{{ item.salePrice }}원</span>
      </div>
      <div class="col-3">

        <p class="product-quantity">재고 : <span class="product-number">{{ item.stock }}</span> 개</p>
        <button class="border-m radius-s" @click="productOrder(item)">주문하기</button>
      </div>
      <hr style="border : 1px solid #222; margin: 0.5rem;">
    </div>
  </div>
  </div>
</template>

<script >
import http from "@/util/http-common";
import {mapGetters, mapActions} from "vuex";

export default {
  name: "KakaoMap",
  data() {
    return {
      currentxLatitude: 33.452278,
      currentLongitude: 126.567803,
      storexLatitude: "",
      storeLongitude: "",
      markers: [],
      infowindow: null,
      items: [],
      keyword: "",
      storeKeyword: "",
    };
  },
  computed: {
    ...mapGetters("store", [
      "aroundSaleStore",
      "currentX",
      "currentY",
    ])
  },

  created() {
    this.productFind();
    console.log(this.aroundSaleStore)
    if (navigator.geolocation) {
    // 현재 위치
      navigator.geolocation.getCurrentPosition((position) => {
      this.currentxLatitude = position.coords.latitude, // 위도
      this.currentLongitude = position.coords.longitude; // 경도
      this.storexLatitude = this.currentxLatitude
      this.storeLongitude = this.currentLongitude
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
      "getItemId",
      "getOrderStore",
      "getAroundSaleStore",
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
            position: new kakao.maps.LatLng(store.saleDto.storeDto.lat, store.saleDto.storeDto.lng), // 마커를 표시할 위치
            // title : positions[i].title, 
            image : markerImage, // 마커 이미지
            clickable: true,
          });
        var infowindow = new kakao.maps.InfoWindow({
            content: 
              `<div>${store.saleDto.storeDto.storeName}</div>
  
              <div>${store.saleDto.storeDto.tel}</div>
              <div>공휴일 : ${store.saleDto.storeDto.offDay}</div>
              <div>마감시간 : ${store.saleDto.storeDto.closingTime}</div>` // 인포윈도우에 표시할 내용
        });
          // console.log(this.map)
        

        kakao.maps.event.addListener(marker, 'mouseover', this.makeOverListener(this.map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', this.makeOutListener(infowindow));

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
    // 인포원도우를 표시하는 클로지를 만드는 함수
    makeOverListener(map, marker, infowindow) {
        return function() {
            infowindow.open(map, marker);
        };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
    makeOutListener(infowindow) {
        return function() {
            infowindow.close();
        };
    },
    // 상품 조회
    productFind() {
      this.keyword = "";
      http
        .post("/sale/keyword/", {
          keyword: this.keyword,
        })
        .then((response) => {
          // var saleStore = []
          response.data.forEach((item) => {
            var distance
            distance = this.getdistance(this.currentX, this.currentY, item.saleDto.storeDto.lat, item.saleDto.storeDto.lng)
            item.distance = distance
            // 초기에 근방에 위치한 가게만 보여주기
            if (distance < 100000000){
              this.items.push(item)
            }
          })
          this.getAroundSaleStore(response.data)
        })
    },
      // 
    productOrder(item) {
      if (localStorage.getItem("access-token") == null){
        this.$router.push({
          name: "login",
        })
      }
      else {
        this.getItemId(item.itemId),
        this.getOrderStore(item.saleDto.storeDto.storeId)
        this.$router.push({
          name: "orderView",
        })
      }
    },
    // 키워드 검색
    keywordSelect() {
      http
      .post("/sale/keyword/", {
        keyword: this.keyword,
      })
      .then((response) => {
        console.log(response)
        this.items = response.data;
        this.items.forEach((item) => {
            var distance
            distance = this.getdistance(this.currentX, this.currentY, item.saleDto.storeDto.lat, item.saleDto.storeDto.lng)
            item.distance = distance
        })
      });
    },
    // 초기화
    resetLitemList() {
      this.keyword = "";
      http
      .post("/sale/keyword/", {
        keyword: this.keyword,
      })
      .then((response) => {
        this.items = response.data;
        this.items.forEach((item) => {
            var distance
            distance = this.getdistance(this.currentX, this.currentY, item.saleDto.storeDto.lat, item.saleDto.storeDto.lng)
            item.distance = distance
        })
      });
      this.curruntLocation();
    },
    // 거리 구하기
    getdistance(lat1, lon1, lat2, lon2) {
      if ((lat1 == lat2) && (lon1 == lon2)){
        return 0;
      }
      var radLat1 = Math.PI * lat1 / 180;
      var radLat2 = Math.PI * lat2 / 180;
      var theta = lon1 - lon2;
      var radTheta = Math.PI * theta / 180;
      var dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
      if (dist > 1)
          dist = 1;

      dist = Math.acos(dist);
      dist = dist * 180 / Math.PI;
      dist = dist * 60 * 1.1515 * 1.609344 * 1000;
      if (dist < 100) dist = Math.round(dist / 10) * 10;
      else dist = Math.round(dist / 100) * 100;

      return dist;
    },
    moving(item) {
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = { 
          center: new kakao.maps.LatLng(this.storexLatitude, this.storeLongitude), // 지도의 중심좌표
          level: 3 // 지도의 확대 레벨
      };
      


      var map = new kakao.maps.Map(mapContainer, mapOption);
      this.storexLatitude = item.saleDto.storeDto.lat
      this.storeLongitude = item.saleDto.storeDto.lng
      // 이동할 위도 경도 위치를 생성합니다 
      var moveLatLon = new kakao.maps.LatLng(item.saleDto.storeDto.lat, item.saleDto.storeDto.lng);
      

      
      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      map.panTo(moveLatLon);

      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(24, 35); 
      
      // 마커 이미지를 생성합니다    
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)   

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(item.saleDto.storeDto.lat, item.saleDto.storeDto.lng), // 마커를 표시할 위치 
        image : markerImage, // 마커 이미지
      });


      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);     
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

.container .row .col-4 {
  text-align: left;
  margin: 0px;
  margin-bottom: 0px !important; 
  padding: 0;
}
.container .row .col-5 span{
  text-align: left;
}

.container .row .col-3{
  font-size: 12px;
  margin: 0px;
  padding: 0px
}
.product-name{
  font-size: 12px;
  font-weight: bolder;
}

.product-location{
  font-size: 10px;
}
/* 거리 에측 */
.product-prediction{
  font-size: 6px !important;
  color: #B9B9B9;
}
/* 할인율 */
.product-discount{
  padding-left: 8px;
  color: red;
  font-weight: bolder;
  font-size: 10px;
}
/* 상품 가격 (정가) */
.price {
  text-decoration:line-through; 
  text-decoration-color: red; 
  text-decoration-style:solid;
  font-size:12px;
}
/* 할인가 */
.discount-price{
  font-size:12px;
}
/* 주문하기 버튼 */

/* 재고 수량 글자 */
.product-quantity{
  padding-top: 20px;
}
/* 재고 수량 */
.product-number{
  color: red;
  font-weight: bolder;
}

.product-order{
  border:  1px solid;
  text-decoration-line: none;
  border-radius: 15px;
  font-size: 12px;
  padding: 3px;
  color: #222;
}


.search-result{
  font-size: 12px;
}


.product-search {
  padding-right: 5px;
  padding-left: 5px;
}

.search-box{
  border: solid 1px;
  border-radius: 12px;
  font-size: 12px;
  background-repeat: no-repeat;
  padding: 5px 5px;
  width: 260px;
  background-color: #FFF;
}

.search-box::-webkit-input-placeholder{
  background-image: url(https://cdn1.iconfinder.com/data/icons/hawcons/32/698627-icon-111-search-256.png) ;
  background-size: contain;
  background-position:  1px center;
  background-repeat: no-repeat;
  text-indent: 0;
}

</style>
