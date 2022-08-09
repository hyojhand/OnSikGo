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
      myColors: ["#66AB48", "#037C5E", "#C67E53", "#BA4E61", "#BE4A05"],
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
