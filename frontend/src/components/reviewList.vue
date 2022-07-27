<template>
<div>
  <br>
  <h5>🙋‍♀️🙋‍♂️리뷰 내역</h5>
  <div id="reviews">
    <div class="container">
        <div class="row">
            <div class="col-3"><img src="@/assets/images/이마트24.png" width="30" height="30"></div>
            <div class="col-6"><span id="text"> 오 여기 좀 맛있음</span></div>
            <div class="col-3"><button class="btn btn-danger btn-sm" style="--bs-btn-padding-y: 0.01rem; --bs-btn-padding-x: .05rem; --bs-btn-font-size: .05rem;">삭제</button></div>
            
        </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
    name:"reviewList",
}
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