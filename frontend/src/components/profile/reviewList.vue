<template>
  <div>
    <div id="reviews">
      <card style="height: 100px">
        <div class="container">
          <div class="row">
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
              <span id="text"> {{ content }}</span>
            </div>
            <div class="col-3 mt-4">
              <button @click="reviewdelete" class="btn-delete">삭제</button>
            </div>
          </div>
        </div>
      </card>
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
}
#reviews {
  height: 100px;
  width: 350px;
  border-radius: 15px;
  display: inline-block;
  margin-top: 30px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  overflow: hidden;
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
