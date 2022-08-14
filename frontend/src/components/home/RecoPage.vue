<template>
  <div>
    <v-carousel
      cycle
      height="220px"
      hide-delimiter-background
      show-arrows-on-hover
      class="mt-3"
    >
      <v-carousel-item>
        <img
          width="95%"
          height="220px"
          src="@/assets/images/recommend_1.jpg"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
      <v-carousel-item>
        <img
          width="95%"
          height="220px"
          src="@/assets/images/recommend_2.jpg"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
export default {
  name: "RecoPage",
  data() {
    return {
      slide: 0,
    };
  },
  methods: {
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
  },
};
</script>

<style scoped></style>
