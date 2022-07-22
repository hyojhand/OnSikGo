<template>
  <v-window class="carousel-box">
    <v-carousel
      cycle
      height="300"
      interval="3000"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(slide, i) in slides" :key="i">
        <v-sheet :color="colors[i]" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="text-h2">{{ slide }}</div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </v-window>
</template>

<script>
export default {
  name: "SloganPage",
  data() {
    return {
      colors: [
        "indigo",
        "warning",
        "pink darken-2",
        "red lighten-1",
        "deep-purple accent-4",
      ],
      slides: ["장창완", "손효재", "공지훈", "김가흔", "최지은"],
    };
  },
};
</script>

<style scoped>
.carousel-box {
  margin: 5% 0;
}
</style>
