<template>
  <div>
    <br />

    <div id="reviews">
      <div class="container">
        <div class="row">
          <div class="col-3">
            <img :src="`${userImgUrl}`" width="80" height="50" />
          </div>
          <div class="col-6">
            <span id="text"> {{ content }}</span>
          </div>
          <div class="col-3">
            <button
              class="btn btn-danger btn-sm"
              style="
                --bs-btn-padding-y: 0.01rem;
                --bs-btn-padding-x: 0.05rem;
                --bs-btn-font-size: 0.05rem;
              "
            >
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "reviewList",
  props: {
    content: String,
    createdDate: String,
    reported: Boolean,
    nickname: String,
    storeDto: [],
    userImgUrl: String,
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
