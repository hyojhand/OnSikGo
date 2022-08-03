<template>
  <div>
    <b-card
    >
      <b-row>
        <b-col md="3">
          <div id="tobecenter">
            
            <img
              fluid
              src="`${storeImgUrl}`"
              height="50"
              width="50"
            />
          </div>
        </b-col>
        <b-col md="9">
          <div class="text-align-center" id="cardInText">
            <br />
            <span>{{storeName}}</span>
            <br />
            <span>매장 위치: {{location}}</span>
            <br />
            <span>매장 휴무일: {{offDay}}</span>
            <br />
            <span>오늘 매장 할인 물품 개수: {{ saleItemDtoList.length}}</span>
            <br />
          </div>
          <div class="d-flex justify-content-end">
            <b-button
              @click="shopinfo()"
              size="sm"
              pill
              variant="outline-success"
              >매장정보</b-button
            >
          </div>
        </b-col>
      </b-row>
    </b-card>
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
      saleItemDtoList:[],
    };
  },

  created() {
    
    http.get(`/sale/list/${this.storeId}`).then((response) => {
      if (response.status == 200) {
        this.saleItemDtoList = response.data;
      }
    });

  },
  methods: {
    shopinfo() {
      this.$router.push("/store");
    },
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
</style>
