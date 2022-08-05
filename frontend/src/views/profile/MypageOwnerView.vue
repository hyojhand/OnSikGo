<template>
  <div>
    <div class="mt-5">
      <div class="d-flex justify-content-center">
        <select
          id="dropdown1"
          class="store-name"
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
    };
  },
  async created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    await http.get("/store/list").then((response) => {
      this.stores = response.data;
      this.store = response.data[0];
      this.storeId = response.data[0].storeId;
      console.log(this.store);
    });

    await http.get(`/sale/list/${this.storeId}`).then((response) => {
      this.saleItemList = response.data;
    });

    // await http.get(`/item/list/${this.storeId}`).then((response) => {
    //   this.itemList = response.data;
    // });
  },
  methods: {
    async selectStore(event) {
      this.storeId = event.target.value;
      await this.changeStore();
      // await this.changeSaleItem();
    },
    changeStore() {
      http.get(`/store/${this.storeId}`).then((response) => {
        this.store = response.data;
        this.storeName = response.data.storeName;
        console.log(response.data);
      });
    },
    // changeSaleItem() {
    //   http.get(`/sale/list/${this.storeId}`).then((response) => {
    //     this.saleItemList = response.data;
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
.store-name {
  width: 40%;
  font-size: 30px;
  text-align: center;
  padding: 2% 0;
}
</style>
