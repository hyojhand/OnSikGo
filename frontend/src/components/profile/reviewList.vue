<template>
  <div>
    <div id="reviews" class="ml-5">
      <div class="container">
        <div class="row mt-4">
          <div class="col-3">
            <b-img
              :src="`${storeDto.storeImgUrl}`"
              rounded="circle"
              width="50"
              height="50"
              style="margin: 0"
            />
            <div>
              <span>{{ storeDto.storeName }}</span>
            </div>
          </div>
          <div class="col-6 mt-4">
            <span class="d-flex justify-content-center"> {{ content }}</span>
          </div>
          <div class="col-3 mt-4">
            <button @click="reviewdelete" class="btn-delete">삭제</button>
          </div>
          <div class="d-flex justify-content-end">
            <span style="color: gray; font-size: 0.8rem">
              {{ createdDate.slice(2, 4) }}.{{ createdDate.slice(4, 6) }}.{{
                createdDate.slice(6, 8)
              }}
              {{ createdDate.slice(8, 10) }}:{{
                createdDate.slice(10, 12)
              }}</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "reviewList",
  props: {
    content: String,
    createdDate: String,
    nickname: String,
    reported: Boolean,
    storeDto: [],
    userImgUrl: String,
    reviewId: Number,
  },
  methods: {
    reviewdelete() {
      http.delete(`/review/${this.reviewId}`).then((response) => {
        console.log(response.data);
        alert("리뷰가 삭제되었습니다.");
      });
    },
  },
};
</script>
<style scoped>
#text {
  font-size: 1rem;
  font-style: bold;
  color: black;
  text-align: start;
}
#reviews {
  width: 370px;
  display: flex;
  flex-direction: row;
  align-items: center;
  border-bottom: 2px solid rgba(0, 0, 0, 10%);
  margin: 0;
}
.btn-delete {
  height: 30px;
  border: none;
  display: inline-block;
  border-radius: 5px;
  text-decoration: none;
  margin: 5 10;
  padding: 10 10;
  box-sizing: border-box;
  background-color: #de9712;
  color: #ffffff;
  width: 50px;
}
</style>
