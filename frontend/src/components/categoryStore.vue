<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <p>아이콘</p>
        <p>1인분</p>
      </div>
      <div class="col">
        <p>아이콘</p>
        <p>돈까스◾회◾일식</p>
      </div>
      <div class="col">
        <p>아이콘</p>
        <p>중식</p>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <p>아이콘</p>
        <p>카페◾디저트</p>
      </div>
      <div class="col">
        <p>아이콘</p>
        <p>분식</p>
      </div>
      <div class="col">
        <p>아이콘</p>
        <p>찜◾탕◾찌개</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "categoryStore",
}
</script>

<style scoped>
 .container {
  width: 360px;
  height: 280px;
 }
 .col {
  font-size: 15px;
 }
</style>