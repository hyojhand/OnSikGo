<template>
  <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img
          class="d-block w-100"
          src="@/assets/images/hambuger.jpg"
          alt="사진..이었던것"
        />
      </div>
      <div class="carousel-item">
        <img
          class="d-block w-100"
          src="@/assets/images/이마트24.png"
          alt="사진..이었던것"
        />
      </div>
      <div class="carousel-item">
        <img
          class="d-block w-100"
          src="@/assets/images/koreanfood.jpg"
          alt="사진..이었던것"
        />
      </div>
    </div>
    <a
      class="carousel-control-prev"
      href="#carouselExampleControls"
      role="button"
      data-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a
      class="carousel-control-next"
      href="#carouselExampleControls"
      role="button"
      data-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</template>
<script>
export default {
  name: "SloganPage",
};
</script>

<style scoped></style>
