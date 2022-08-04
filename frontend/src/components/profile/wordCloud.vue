<template>
  <div>
    <div class="ti-box row">
      <div class="ti col-6">
        <div></div>
        다음 상품들이 <br />
        가장 많은 사랑을 받았어요!
      </div>
      <div class="col-6 imo-box">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="30"
          height="30"
          fill="black"
          class="bi bi-emoji-frown-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm-2.715 5.933a.5.5 0 0 1-.183-.683A4.498 4.498 0 0 1 8 9.5a4.5 4.5 0 0 1 3.898 2.25.5.5 0 0 1-.866.5A3.498 3.498 0 0 0 8 10.5a3.498 3.498 0 0 0-3.032 1.75.5.5 0 0 1-.683.183zM10 8c-.552 0-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5S10.552 8 10 8z"
          />
        </svg>
        <span
          >저희는 <br />
          관리가 필요해요..</span
        >
      </div>
    </div>
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
.ti {
  text-align: start;
  color: #464fa6;
  padding: 0;
}
.ti-box {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-evenly;
  align-items: center;
  margin: 5% 0;
}
.imo-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  padding: 0;
}
.imo-box > span {
  text-align: start;
  margin-left: 7%;
}
</style>
