<template>
  <v-window class="carousel-box">
    <v-carousel
      cycle
      height="300"
      interval="2000"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(slide, i) in slides" :key="i">
        <v-sheet :color="colors[i]" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="text-h2">{{ slide }} 바보</div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </v-window>
</template>

<script>
export default {
  name: "RecoPage",
  data() {
    return {
      colors: [
        "pink darken-2",
        "warning",
        "red lighten-1",
        "indigo",
        "deep-purple accent-4",
      ],
      slides: ["손효재", "최지은", "김가흔", "공지훈", "짱짱완"],
    };
  },
};
</script>

<style scoped>
.carousel-box {
  margin: 3% 0;
}
</style>
