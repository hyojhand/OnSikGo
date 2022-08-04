<template>
  <div>
    <div id="reviews">
      <b-card>
        <div class="container">
          <div class="row">
            <div class="col-3">
              <b-img
                :src="`${storeDto.storeImgUrl}`"
                rounded="circle"
                width="50"
                height="50"
              />
              <div>
                <span>{{ storeDto.storeName }}</span>
              </div>
            </div>
            <div class="col-6 mt-5">
              <span>{{}}</span>
              <span id="text"> {{ content }}</span>
            </div>
            <div class="col-3 mt-5">
              <button @click="reviewdelete" class="btn btn-danger btn-sm">
                삭제
              </button>
            </div>
          </div>
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
// import http from "@/util/http-common";
export default {
  name: "reviewList",
  props: {
    content: String,
    createdDate: String,
    nickname: String,
    reported: Boolean,
    storeDto: [],
    userImgUrl: String,
  },
  methods: {
    reviewdelete() {},
  },
};
</script>
<style scoped>
#text {
  font-size: 0.1rem;
  font-style: bold;
}
/* #reviews {
    display: flex;
    justify-content: start;
} */
</style>
