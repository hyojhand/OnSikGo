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
          @click="detailStore()"
          width="95%"
          height="220px"
          src="@/assets/images/dduk.png"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
      <v-carousel-item>
        <img
          @click="detailStore()"
          width="95%"
          height="220px"
          src="@/assets/images/fish.png"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
      <v-carousel-item>
        <img
          @click="detailStore()"
          width="95%"
          height="220px"
          src="@/assets/images/sundae.png"
          alt="image slot"
          style="border-radius: 7px"
        />
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "RecoPage",
  data() {
    return {
      slide: 0,
    };
  },
  methods: {
    ...mapActions("storeStore", ["getStoreId"]),
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
    detailStore() {
      this.getStoreId(16);
      this.$router.push({
        name: "storeView",
      });
    },
  },
};
</script>

<style scoped></style>
