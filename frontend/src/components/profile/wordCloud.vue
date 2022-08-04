<template>
  <div>
    <wordcloud
      :data="defaultWords"
      :color="myColors"
      :showTooltip="false"
      class="word-cloud"
    >
    </wordcloud>
  </div>
</template>

<script>
import wordcloud from "vue-wordcloud";

export default {
  name: "app",
  components: {
    wordcloud,
  },
  data() {
    return {
      myColors: ["#FE2E2E", "#8904B1", "#FE9A2E", "#08088A", "#088A4B"],
      defaultWords: [
        {
          name: "창완김밥",
          value: 102,
        },
        {
          name: "지훈파스타",
          value: 15,
        },
        {
          name: "식중독걸림",
          value: 6,
        },
        {
          name: "그게메뉴?",
          value: 5,
        },
        {
          name: "할인마감초밥",
          value: 4,
        },
        {
          name: "뷰엑스시렁",
          value: 20,
        },
        {
          name: "이제쓸말없다",
          value: 2,
        },
        {
          name: "양곱창",
          value: 30,
        },
        {
          name: "오늘쌀국수맛났음",
          value: 1,
        },
      ],
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
