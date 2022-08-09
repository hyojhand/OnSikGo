<template>
  <div>
    <div>
      <div class="d-flex justify-content-center">
        <select
          id="dropdown1"
          class="store-name form-select"
          @change="selectStore($event)"
        >
          <option selected class="opt">
            {{ this.saveMyStore }}
          </option>
          <option
            :key="index"
            :value="store.storeId"
            v-for="(store, index) in stores"
          >
            {{ store.storeName }}
          </option>
        </select>
      </div>
    </div>

    <br />
    <div class="item-container">
      <mypage-owner-component :store="this.store"></mypage-owner-component>
    </div>
  </div>
</template>

<script>
import mypageOwnerComponent from "@/components/profile/mypageOwnerComponent.vue";
import http from "@/util/http-common";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "MypageOwnerView",
  components: {
    mypageOwnerComponent,
  },
  data() {
    return {
      stores: [],
      store: {},
      storeId: Number,
      saleItemList: [],
      itemList: [],
      storeName: "",
      storeImg: "",
      storeCnt: "",
      offDaylist_created: [],
      offDaylist_select: [],
      realoffDayList: "",
    };
  },
  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http.get("/store/list").then((response) => {
      this.stores = response.data;
      if (this.saveMyStore.length) {
        this.storeId = this.myStore;
        this.store = this.storeValue;
      } else {
        this.storeId = response.data[0].storeId;
        this.getMyStore(this.storeId);
        this.store = response.data[0];
      }
      this.storeCnt = this.stores.length;
      this.discardStoreId(this.storeId);
      this.discardStoreCnt(this.storeCnt);
      console.log(this.store.offDay);
      if (this.store.offDay.length >= 5) {
        console.log("2개이상임");
        this.offDaylist_created = [];
        this.store.offDay.split(",").map((day) => {
          this.offDaylist_created.push(day);
        });
        const daySorter = {
          월요일: 1,
          화요일: 2,
          수요일: 3,
          목요일: 4,
          금요일: 5,
          토요일: 6,
          일요일: 7,
        };
        this.offDaylist_created.sort(function sortBydaySorter(a, b) {
          return daySorter[a] - daySorter[b];
        });
        this.realoffDayList = this.offDaylist_created.join();
        this.storeOffday(this.realoffDayList);
      } else {
        this.realoffDayList = this.store.offDay;
        this.storeOffday(this.realoffDayList);
        console.log(this.realoffDayList);
      }
      console.log(this.realoffDayList);
    });

    await http.get(`/sale/list/${this.storeId}`).then((response) => {
      this.saleItemList = response.data;
      this.getDsicardStoreList(response.data);
    });
    await http.get(`/store/close/${this.storeId}`).then((response) => {
      this.getDiscardStoreClose(response.data.closed);
      // console.log(response.data);
    });
  },
  computed: {
    ...mapGetters("select", ["saveMyStore", "myStore", "storeValue"]),
  },
  methods: {
    ...mapActions("discardStore", [
      "discardStoreId",
      "discardStoreName",
      "discardStoreImg",
      "getDsicardStoreList",
      "getDiscardStoreClose",
      "discardStoreCnt",
    ]),
    ...mapActions("offdayStore", ["storeOffday"]),
    ...mapActions("select", ["getMyStore", "getStoreValue"]),
    async selectStore(event) {
      this.storeId = event.target.value;
      this.getMyStore(event.target.value);
      await http.get(`/store/${this.storeId}`).then((response) => {
        // console.log(response.data.offDay);
        if (response.data.offDay.length >= 5) {
          // console.log("ok");
          this.offDaylist_select = [];
          response.data.offDay.split(",").map((day) => {
            this.offDaylist_select.push(day);
          });
          // console.log(offDaylist_select);
          const daySorter = {
            월요일: 1,
            화요일: 2,
            수요일: 3,
            목요일: 4,
            금요일: 5,
            토요일: 6,
            일요일: 7,
          };
          this.offDaylist_select.sort(function sortBydaySorter(a, b) {
            return daySorter[a] - daySorter[b];
          });
          this.realoffDayList = this.offDaylist_select.join();
          // console.log(this.realoffDayList);
        } else {
          this.realoffDayList = response.data.offDay;
        }
        // console.log(this.realoffDayList);
        this.storeOffday(this.realoffDayList);
        this.storeName = response.data.storeName;
        this.storeImg = response.data.storeImgUrl;
      });
      await http.get(`/sale/list/${this.storeId}`).then((response) => {
        this.getDsicardStoreList(response.data);
      });
      await http.get(`/store/close/${this.storeId}`).then((response) => {
        this.getDiscardStoreClose(response.data.closed);
        // console.log(response.data);
      });
      this.discardStoreId(this.storeId);
      this.discardStoreName(this.storeName);
      this.discardStoreImg(this.storeImg);

      await this.changeStore();
    },
    changeStore() {
      http.get(`/store/${this.storeId}`).then((response) => {
        this.store = response.data;
        this.storeName = response.data.storeName;
        this.getStoreValue(response.data);
      });
    },
  },
};
</script>

<style scoped>
.sales {
  margin-top: 3%;
  padding: 3% 0;
  background-color: white;
  border-top: 2px solid rgba(0, 0, 0, 20%);
  border-bottom: 2px solid rgba(0, 0, 0, 20%);
}
.store-name {
  width: 80%;
  font-size: 25px;
  font-weight: 800;
  text-align: center;
  padding: 2% 0;
}
.opt {
  background-color: rgba(140, 184, 131, 0.5);
  color: white;
}
</style>
