<template>
  <div>
    <div class="d-flex justify-content-end">
      <select
        id="dropdown1"
        style="border-color: #63bf68"
        @change="selectStore($event)"
      >
        <option
          :key="index"
          :value="store.storeId"
          v-for="(store, index) in stores"
        >
          {{ store.storeName }}
        </option>
      </select>
    </div>

    <br />
    <div class="item-container">
      <mypageOwnerComponent :storeId="this.storeId"></mypageOwnerComponent>
    </div>
  </div>
</template>

<script>
import mypageOwnerComponent from "@/components/profile/mypageOwnerComponent.vue";
import http from "@/util/http-common";
export default {
  name: "MypageOwnerView",
  components: {
    mypageOwnerComponent,
  },
  data() {
    return {
      stores: [],
      store: {},
      storeId: "",
      saleItemList: [],
      itemList: [],
      storeName: "",
    };
  },
  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http.get("/store/list").then((response) => {
      this.stores = response.data;
      this.store = response.data[0];
      this.storeId = response.data[0].storeId;
      console.log(this.store.storeName);
    });

    await http.get(`/sale/list/${this.storeId}`, {}).then((response) => {
      this.saleItemList = response.data;
    });

    await http.get(`/item/list/${this.storeId}`).then((response) => {
      this.itemList = response.data;
    });
  },
  methods: {
    selectStore(event) {
      this.storeId = event.target.value;
      this.changeStore();
    },
    changeStore() {
      http.get(`/store/${this.storeId}`).then((response) => {
        this.store = response.data;
        this.storeName = response.data.storeName;
        // console.log(this.storeName);
        // console.log(response.data);
      });
    },
    // storeClose() {
    //   http.put(`/store/close/${this.storeId}`).then((response) => {
    //     if (response.status == 200) {
    //       alert("가게 결산이 완료되었습니다.");
    //     } else {
    //       alert("해당 날짜의 판매정보가 없습니다.");
    //     }
    //   });
    // },
  },
};
</script>

<style scoped>
#drowpdown1 {
  border-color: #63bf68;
  border-block-width: 4px;
  background-color: white;
}
#space-even {
  display: flex;
  justify-content: space-evenly;
}
.sales {
  margin-top: 3%;
  padding: 3% 0;
  background-color: white;
  border-top: 2px solid rgba(0, 0, 0, 20%);
  border-bottom: 2px solid rgba(0, 0, 0, 20%);
}
</style>
