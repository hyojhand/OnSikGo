<template>
  <div>
    <card id="regular-card">
      <b-row>
        <b-col md="3" class="ml-4 mt-6">
          <img fluid :src="`${storeImgUrl}`" height="75" width="100" />
        </b-col>
        <b-col class="ml-2 mt-3" md="8">
          <div class="text-align-center" id="cardInText">
            <br />
            <span>{{ storeName }}</span>
            <br />
            <span>매장 위치: {{ location }}</span>
            <br />
            <span>매장 휴무일: {{ offDay }}</span>
            <br />
            <span>오늘 매장 할인 물품 개수: {{ saleItemDtoList.length }}</span>
            <br />
          </div>
          <div class="d-flex justify-content-end mr-4">
            <router-link
              :to="{ name: 'storeView', params: { storeId: this.storeId } }"
              ><button class="store-moving">가게보기</button></router-link
            >
          </div>
        </b-col>
      </b-row>
    </card>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "regularList",
  props: {
    category: String,
    closingTime: String,
    lat: String,
    lng: String,
    location: String,
    offDay: String,
    storeId: Number,
    storeImgUrl: null,
    storeName: String,
    storeNum: String,
    tel: String,
  },
  data() {
    return {
      userDto: {},
      stores: [],
      saleItemDtoList: [],
    };
  },

  created() {
    http.get(`/sale/list/${this.storeId}`).then((response) => {
      if (response.status == 200) {
        this.saleItemDtoList = response.data;
      }
    });
  },
};
</script>

<style>
#firstPrice {
  text-decoration: line-through;
}

#cardInText {
  text-align: left;
  font-size: 10px;
}
#regular-card {
  height: 150px;
  width: 400px;
  border-radius: 15px;
  display: inline-block;
  margin-top: 30px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  overflow: hidden;
}
.store-moving {
  height: 25px;
  border-width: 1px;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  box-sizing: border-box;
  background-color: #ffffff;
  color: #368f3d;
  width: 70px;
  border-color: #368f3d;
}
</style>
