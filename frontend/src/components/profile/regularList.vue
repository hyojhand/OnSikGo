<template>
  <div>
    <div class="d-flex justify-content-center mt-5">
      <div
        v-if="saleItemDtoList.length == 0"
        id="regular-card"
        @click="storeDetail()"
      >
        <div class="container">
          <div class="row">
            <div class="col-2 mt-1">
              <img fluid :src="`${storeImgUrl}`" height="75" width="80" />
            </div>
            <div class="col-9 ml-6" id="cardText">
              <span style="color: black">{{ storeName }}</span
              ><br />
              <span style="color: gray; font-size: 0.7rem"
                >{{ address }} {{ extraAddress }}</span
              ><br />

              <span style="color: gray; font-size: 0.7rem"
                >매장 휴무일: {{ offDay }}</span
              ><br />
              <span style="color: gray; font-size: 0.7rem"
                >오늘 매장 할인 물품 개수:
              </span>
              <span style="color: gray; font-size: 0.7rem">
                {{ saleItemDtoList.length }}개</span
              >
            </div>
          </div>
        </div>
      </div>

      <div v-else id="regular-card-nozero" @click="storeDetail()">
        <div class="container">
          <div class="row">
            <div class="col-2 mt-1">
              <img fluid :src="`${storeImgUrl}`" height="75" width="80" />
            </div>
            <div class="col-9 ml-6" id="cardText">
              <span style="color: black">{{ storeName }}</span
              ><br />
              <span style="color: gray; font-size: 0.7rem"
                >{{ address }} {{ extraAddress }}</span
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
            </div>
          </div>
        </div>
      </div>
    </div>
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
    address: String,
    extraAddress: String,
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
  justify-content: center;
  align-items: center;
  width: 95%;
  background-color: white;
  align-items: center;
  height: 130px;
  border-radius: 10px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
  margin: 0;
  background-color: rgba(0, 0, 0, 0.05);
}
#regular-card:hover {
  cursor: pointer;
}
#regular-card-nozero {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 95%;
  background-color: white;
  align-items: center;
  height: 130px;
  border-radius: 10px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
  margin: 0;
}
#regular-card-nozero:hover {
  cursor: pointer;
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
