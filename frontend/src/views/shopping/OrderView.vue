<template>
  <div class="main_container">
    <div class="mobile">
      <!-- 단일 상품 설명 -->
      <!-- 상품명, 상품이미지, 주소,현재위치에서 거리, 매장상세보기 버튼, 정가, 할인가, 재고, 한줄평-->
      <div class="product_container border-l radius-m mt-5">
        <div class="item-card">
          <!-- 마커 -->
          <!-- <div class="col-2">
        <h1>{{ item.index }}</h1>
      </div> -->
          <div class="product-name pt-3">{{ productName }}</div>
          <div class="pro-info pt-3">
            <img
              :src="`${itemImgUrl}`"
              style="cursor: pointer"
              :alt="`${productName}`"
              class="product-img col-5"
            />
            <div class="col-7 p-0">
              <div class="info-case">
                <div class="store-name">
                  {{ storeName }}
                  <div class="product-location"></div>
                  <div v-if="distance < 3000" class="product-prediction">
                    현재 위치로부터 {{ this.distance }} m
                  </div>
                  <div v-else class="product-prediction">
                    현재 위치로부터 {{ this.distance / 1000 }} km
                  </div>
                  <button
                    class="order-button border-m radius-s"
                    @click="detailStore(item)"
                  >
                    상세보기
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div class="stock-box">
            <div class="product-quantity">
              <div>현재 재고 :</div>
              <div class="product-number">{{ stock }} 개</div>
            </div>

            <div class="price-case">
              <div style="sale-ment">지금 구매한다면?</div>
              <div class="product-discount">
                <div class="price col-10">({{ price }})원</div>
                <div class="col-10">
                  🔻
                  {{ ((1 - salePrice / price) * 100).toFixed(0) }}%
                </div>
                <div class="discount-price col-10">{{ salePrice }}원에!</div>
              </div>
            </div>
          </div>
        </div>
        <!-- 꿀팁 -->
        <div class="row" v-if="comment.length">
          <div class="col-4 m-1">
            <span style="font-size: 0.8rem">셰프의 한마디</span
            ><img src="@/assets/images/cooker.png" width="90%" />
          </div>
          <div
            class="col ment"
            style="text-align: left; font-size: 1rem; word-break: keep-all"
          >
            " {{ comment }} "
          </div>
        </div>
      </div>
      <!-- 픽업 주문서 -->
      <!-- 고객명, 매장, 상품명, 수량, 소요시간, 경고문, 픽업 신청버튼 -->
      <v-stepper class="order mt-3">
        <v-stepper-items>
          <v-stepper-content
            step="1"
            class="btn-box mt-3"
            black
            outlined
            min-height="450"
          >
            <div class="order-header">
              <img src="@/assets/images/note.png" height="30" width="30" />
              [ 주문서 ]
              <img src="@/assets/images/note.png" height="30" width="30" />
            </div>
            <br />
            <div class="order-name">주문자: {{ nickName }}</div>
            <div class="order-store">매장명: {{ storeName }}</div>
            <div class="order-product">상품명: {{ productName }}</div>
            <form class="mb-2 el-case">
              <v-text-field
                v-model="count"
                type="number"
                min="1"
                label="수량을 입력해주세요."
                :error-messages="countErrors"
                required
                class="input-box mt-5"
                color="black"
              >
              </v-text-field>
            </form>
            <div class="order-text" style="color: black">
              <img
                width="40"
                height="40"
                src="@/assets/images/alert.png"
              />필독사항
              <img width="40" height="40" src="@/assets/images/alert.png" />
            </div>
            <div class="order-text" style="font-size: 0.75rem">
              * 매장에서 '승인 완료'시 고객님에게 알림이 갑니다.
            </div>
            <div class="order-text" style="font-size: 0.75rem">
              * 반드시 알림을 확인하고 출발해주세요.
            </div>
            <div class="order-text" style="font-size: 0.75rem">
              * 상품은 금일 취식 부탁드립니다.
            </div>
            <div class="order-text" style="font-size: 0.75rem">
              * 주문 취소는 주문 신청 후 10분이내로 가능합니다.
            </div>
          </v-stepper-content>
          <div class="btn-case">
            <button
              class="pro-button border-m radius-m"
              @click="productOrder()"
            >
              신청하기
            </button>
          </div>
        </v-stepper-items>
      </v-stepper>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import http from "@/util/http-common";

export default {
  name: "OrderView",
  data() {
    return {
      productName: "",
      price: "",
      stock: "",
      salePrice: "",
      itemImgUrl: "",
      comment: "",
      userName: "",
      nickName: "",
      storeName: "",
      storeLocation: "",
      count: "",
      storeId: "",
      saleItemId: "",
      check: false,
      storelat: "",
      storelng: "",
      distance: 0,
    };
  },
  computed: {
    ...mapGetters("store", [
      "currentAddress",
      "currentItemId",
      "orderStore",
      "currentX",
      "currentY",
    ]),
    countErrors() {
      const errors = [];
      if (this.count <= this.stock) {
        return errors;
      }
      errors.push("재고보다 적은 수량을 입력해주세요.");
      return errors;
    },
  },
  created() {
    this.findProduct();
    this.findUser();
    this.findStock();
    this.getdistance(
      this.currentX,
      this.currentY,
      this.storelat,
      this.storelng
    );
  },
  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    // 상품 정보 조회
    findProduct() {
      http.get(`/item/${this.currentItemId}`).then((response) => {
        // console.log(response.data)
        this.productName = response.data.itemName;
        this.price = response.data.price;
        this.itemImgUrl = response.data.itemImgUrl;
        this.comment = response.data.comment;
        // console.log(response.data)
      }),
        http.get(`/sale/${this.currentItemId}`).then((response) => {
          this.stock = response.data.stock;
          this.salePrice = response.data.salePrice;
          this.saleItemId = response.data.saleItemId;
          // console.log(response.data)
        });
    },
    // 가게정보 조회
    findStock() {
      // console.log(this.orderStore)
      http.get(`/store/${this.orderStore}`).then((response) => {
        this.storeName = response.data.storeName;
        this.storeId = response.data.storeId;
        this.storeLocation = response.data.location;
        this.storelat = response.data.lat;
        this.storelng = response.data.lng;
        // console.log(response.data)
      });
    },
    // 유저 조회
    findUser() {
      http.defaults.headers["access-token"] =
        localStorage.getItem("access-token");
      http.get("/user").then((response) => {
        this.userName = response.data.userName;
        this.nickName = response.data.nickname;
        // console.log(response.data)
      });
    },
    //  주문하기
    productOrder() {
      const isLogin = JSON.parse(localStorage.getItem("vuex")).accounts
        .userCheck;
      if (isLogin == 1) {
        this.$alert("업주께서는 이용하실수 없는 서비스입니다.");
      } else {
        if (this.count <= this.stock || this.count >= 1) {
          http.defaults.headers["access-token"] =
            localStorage.getItem("access-token");
          http
            .post("/order", {
              saleItemId: this.saleItemId,
              count: this.count,
            })
            .then((response) => {
              console.log(response);
            });
          this.$alert("주문이 접수되었습니다.");
          this.$router.push("/mypage/user/history");
        }
      }
    },
    // 할때 가게정보도 추가 할 것
    detailStore() {
      this.getStoreId(this.storeId);
      console.log(this.storeId);
      this.$router.push({
        name: "storeView",
      });
    },
    getdistance(lat1, lon1, lat2, lon2) {
      if (lat1 == lat2 && lon1 == lon2) {
        return 0;
      }
      var radLat1 = (Math.PI * lat1) / 180;
      var radLat2 = (Math.PI * lat2) / 180;
      var theta = lon1 - lon2;
      var radTheta = (Math.PI * theta) / 180;
      var dist =
        Math.sin(radLat1) * Math.sin(radLat2) +
        Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
      if (dist > 1) dist = 1;

      dist = Math.acos(dist);
      dist = (dist * 180) / Math.PI;
      dist = dist * 60 * 1.1515 * 1.609344;
      if (dist < 100) dist = Math.round(dist / 10) * 10;
      else dist = Math.round(dist / 100) * 100;
      this.distance = dist;
      return dist;
    },
  },
};
</script>

<style scoped>
.main_container {
  width: 100%;
  height: 100%;
}
.main_container .mobile {
  width: 80%;
  margin: 0 auto;
}
/* 주소 위치 */
/* .main_container .mobile .location {
  margin-left: 1.5rem;
  text-align: left;
  font-size: 15px;
} */
/* 거리 예측 */
.product-prediction {
  padding: 0px;
  font-size: 8px;
  margin: 3px;
  margin-bottom: 5px;
  margin-left: 3rem;
  color: #b9b9b9;
}

.order {
  color: black;
}
.order-header {
  font-size: 16px;
  font-weight: bold;
}
.order-name {
  margin: 0;
  margin-bottom: 0.5rem;
  text-align: left;
  color: gray;
  font-size: 0.8rem;
}
.order-store {
  margin: 0;
  margin-bottom: 0.5rem;
  text-align: left;
  color: gray;
  font-size: 0.8rem;
}
.order-product {
  margin: 0;
  text-align: left;
  color: gray;
  font-size: 0.8rem;
}
.order input {
  border: 1px solid;
  margin-left: 1rem;
  text-align: left;
}
.order .order-number {
  margin: 0;
  margin-left: 1rem;
  text-align: left;
}
.order .order-text {
  color: red;
  margin: 0;
  margin-bottom: 0.5rem;
  text-align: left;
}

.item-card {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 3px 0p;
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
}
.pro-info {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.store-name {
  width: 100%;
  text-align: start;
  font-size: 15px;
}
.product-img {
  border-radius: 50%;
  width: 120px;
  height: 120px;
  padding-bottom: 5px;
}
.product-location {
  font-size: 13px;
  margin-top: 3px;
  color: rgba(0, 0, 0, 0.5);
}
.info-case {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 0px;
  padding-left: 15px;
}
.product-name {
  font-size: 20px;
  font-weight: bolder;
}
.product-prediction {
  font-size: 0.75rem;
  color: #b9b9b9;
  width: 100%;
  text-align: start;
  margin: 0;
}
.product-quantity {
  display: flex;
  align-items: center;
  margin: 10px 0 3px 20px;
  padding-top: 20px;
  font-size: 15px;
  padding: 0;
}
/* 재고 수량 */
.product-number {
  color: red;
  font-weight: bolder;
  padding-left: 3px;
  font-size: 15px;
}
/* 할인율 */
.product-discount {
  padding-left: 3px;
  color: red;
  font-weight: bolder;
  font-size: 15px;
  width: 60%;
}
.product-discount > div {
  padding: 0;
  margin-bottom: 5px;
}
/* 상품 가격 (정가) */
.price-case {
  width: 100%;
  display: flex;
  flex-direction: row;
  height: 100px;
  margin-left: 20px;
}
.price {
  color: rgba(0, 0, 0, 0.2);
  font-size: 1rem;
}
.sale-ment {
  text-align: start;
}
/* 할인가 */
.discount-price {
  font-size: 1.2rem;
  text-align: center;
}
.order-button {
  width: 150px;
  height: 30px;
  margin: 0;
  margin-top: 5px;
}
.order-button:hover {
  background-color: rgba(140, 184, 131, 0.5);
  color: #fff;
}
.stock-box {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.ment {
  display: flex;
  align-items: center;
  width: 100%;
}
.pro-button {
  width: 130px;
  height: 30px;
}
.btn-case {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}
</style>
