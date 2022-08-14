<template>
  <div>
    <div v-for="(item, index) in discardStoreList" :key="index">
      <div class="container">
        <div class="row">
          <div class="col-4 mt-8">
            <img
              class="dcimg"
              :src="`${item.itemDto.itemImgUrl}`"
              width="80"
              height="80"
            />
          </div>
          <div class="col-8">
            <div>
              <div class="info-box" style="font-size: 1.3rem">
                {{ item.itemDto.itemName }}
              </div>
              <div class="info-box" style="color: gray">
                남은 재고: {{ item.stock }} 개
              </div>

              <div class="info-box" style="color: gray">
                정상판매가 : {{ item.itemDto.price }} 원
              </div>
              <div class="info-box sale">
                할인율 : 🔻{{
                  ((1 - item.salePrice / item.itemDto.price) * 100).toFixed(2)
                }}%
              </div>
              <div class="info-box">할인판매가: {{ item.salePrice }} 원</div>
            </div>
            <edit-discard-modal
              :item="item.itemDto"
              :stok="item.stock"
              :salePric="item.salePrice"
              :storeId="item.saleDto.storeDto.storeId"
            ></edit-discard-modal>
            <!-- <div>
              <edit-stock-modal
                :item="item.itemDto"
                :storeId="item.saleDto.storeDto.storeId"
              ></edit-stock-modal>
            </div> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EditDiscardModal from "@/components/management/EditDiscardModal.vue";
import { mapGetters } from "vuex";
export default {
  name: "discountList",
  components: {
    EditDiscardModal,
  },
  computed: {
    ...mapGetters("discardStore", ["discardStoreList"]),
  },
};
</script>

<style scoped>
.dcimg {
  border-radius: 50%;
}
.container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 95%;
  background-color: white;
  align-items: center;
  height: 170px;
  border-radius: 10px;
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
  margin: 0;
  background-color: rgba(0, 0, 0, 0.05);
}
.info-box {
  text-align: start;
}
.sale {
  color: tomato;
}
</style>
