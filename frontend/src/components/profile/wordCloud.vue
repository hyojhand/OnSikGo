<template>
  <div>
    <wordcloud
      :data="this.wordCloud"
      :color="myColors"
      :showTooltip="false"
      class="word-cloud"
    >
    </wordcloud>
  </div>
</template>

<script>
import wordcloud from "vue-wordcloud";
import { mapGetters } from "vuex";

export default {
  name: "wordCloud",
  components: {
    wordcloud,
  },
  computed: {
    ...mapGetters("analysis", ["wordCloud"]),
  },
  data() {
    return {
      myColors: ["#FE2E2E", "#8904B1", "#FE9A2E", "#08088A", "#088A4B"],
    };
  },
};
</script>
<style scoped>
.word-cloud {
  opacity: 60%;
  background-color: whitesmoke;
}
</style>
