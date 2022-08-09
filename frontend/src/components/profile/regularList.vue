<template>
  <div>
    <card id="regular-card">
      <div class="container">
        <div class="row">
          <div class="col-3">
            <img fluid :src="`${storeImgUrl}`" height="75" width="100" />
          </div>
          <div class="col-8 ml-3" id="cardText">
            <span style="color: black">{{ storeName }}</span
            ><br />
            <span style="color: gray; font-size: 0.7rem"
              >매장 위치: {{ location }}</span
            ><br />
            <span style="color: gray; font-size: 0.7rem"
              >매장 휴무일: {{ offDay }}</span
            ><br />
            <span style="color: gray; font-size: 0.7rem"
              >오늘 매장 할인 물품 개수:
            </span>
            <span style="color: rgba(140, 184, 131)">
              {{ saleItemDtoList.length }}개</span
            >
            <div class="d-flex justify-content-end">
              <button class="store-moving" @click="storeDetail()">
                가게보기
              </button>
            </div>
          </div>
        </div>
      </div>
    </card>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapActions } from "vuex";
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
  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    storeDetail() {
      this.getStoreId(this.storeId);
      this.$router.push({
        name: "storeView",
      });
    },
  },
};
</script>

<style>
#firstPrice {
  text-decoration: line-through;
}

#cardText {
  text-align: start;
}
#regular-card {
  display: flex;
  flex-direction: row;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 10%);
  margin: 0;
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
