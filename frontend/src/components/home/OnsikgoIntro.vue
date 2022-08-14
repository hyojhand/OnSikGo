<template>
  <div class="container">
    <img src="@/assets/images/onsikgo.png" alt="온식고였던것.." width="100%" />
    <div class="intro">Onsikgo란</div>
    <div class="intro-ment">
      <div>저희는 유통과 섭취에 전혀 문제가 없는 제품들에 한해</div>
      <div>정상 판매가보다 할인된 가격에 제품을 즐길 수 있도록</div>
      <div>돕고 있습니다</div>
    </div>
    <div class="intro">Onsikgo 정신</div>
    <div class="intro-ment">
      <div>저희는 세상에 판매되지 못한 채 버려지는 음식들이</div>
      <div>환경 오염에 끼치는 영향에 대해 생각했습니다</div>
      <div>여러분과 함께하는 착한 소비로 지구를 지켜나가고 싶습니다</div>
    </div>
    <div class="intro">유통기한 임박 제품이 불안해요</div>
    <div class="intro-ment">
      <div>유통기한은 제조일로부터 소비자에게 유통 및 판매가</div>
      <div>허용되는 기간으로 소비기한과는 차이가 있습니다</div>
      <div>소비기한 내에 섭취 시 문제가 없습니다</div>
      <div>다만 식품 변질을 막기 위해 구입 후</div>
      <div>빠른 시일내에 섭취하여 주시기 바랍니다</div>
    </div>
    <div class="intro mb-3">Onsikgo 로고</div>
    <img src="@/assets/real_logo.png" alt="로고였던 것" width="60%" />
    <div class="intro-ment">
      <div>쇼핑카트에 지구를 담은 모습으로</div>
      <div>현명한 소비로 지구를 구하자는 의미를 담았습니다</div>
    </div>
    <div class="intro">Onsikgo 메인컬러</div>
    <div class="color-case mt-5">
      <div class="circle"></div>
      <div class="color-ment mt-3">rgb(140, 184, 131)</div>
    </div>
    <div class="intro">Onsikgo 원산지</div>
    <div class="intro-ment">
      <div>FE - 김가흔, 이동근, 장창완, 최지은</div>
      <div>BE - 공지훈, 손효재</div>
      <div>감사합니다</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "OnsikgoIntro",
};
</script>

<style scoped>
* {
  letter-spacing: 1px;
}
.container {
  padding: 0;
  width: 100%;
  background-color: white;
}
.intro {
  font-size: 25px;
  text-align: start;
  padding-left: 15px;
  margin-bottom: 5px;
  color: rgba(140, 184, 131, 0.8);
}

.intro-ment {
  text-align: start;
  padding-left: 20px;
  margin-bottom: 30px;
}
.circle {
  margin: 0 auto;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: rgb(140, 184, 131);
}
.color-ment {
  margin-bottom: 30px;
}
</style>
