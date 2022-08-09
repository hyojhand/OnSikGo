<template>
  <div>
    <div class="mt-10">
      <span>🧚‍♂️여러분의 도움이 필요한 곳으로 연결해드립니다🧚‍♂️</span>
    </div>
    <div>
      <img src="@/assets/images/donation_need.jpg" alt="기부사진이었던것.." />
      <!-- <i class="fa-solid fa-arrow-down fa-xl"></i> -->
      <br />
      <div class="mt-10">
        <span style="color: black"
          >기부는 전국 푸드뱅크 사이트를 통해 가능합니다</span
        >
        <br />
        <div class="text-start mt-10 ml-8 mr-8">
          <span style="color: black; font-size: 1.3rem"
            >1. 푸드뱅크 홈페이지 신청</span
          >
          <br />
          <div class="mt-5">
            <span style="color: black; font-size: 0.8rem"
              >전국푸드뱅크 홈페이지(www.foodbank1377.org)에 접속하여
              <br />기부회원으로 가입하여 기부 신청을 할 수 있습니다.</span
            >
          </div>
        </div>
        <div class="d-flex justify-content-end mr-8">
          <button @click="moveFood" id="dona_btn">푸드뱅크 사이트</button>
        </div>

        <div class="text-start mt-5 ml-8 mr-8">
          <span style="color: black; font-size: 1.3rem"
            >2. 푸드뱅크 전화 신청</span
          >
          <br />
          <div class="mt-5">
            <span style="color: black; font-size: 0.8rem"
              >√ 전국단위 대규모 기부 신청 : ☎ 02-713-1377 <br />
              √ 지역단위 소규모 기부 신청 : ☎ 1688-1377
            </span>
          </div>
        </div>
        <div class="text-start mt-16 ml-8 mr-8">
          <span style="color: black; font-size: 1.3rem"
            >3. 기부 식품 관련 법령</span
          >
          <br />
          <div class="mt-5">
            <span style="color: black; font-size: 0.8rem"
              >식품 기부에 대한 법령과 <br />
              식품 기부할 경우에 받을 수 있는 혜택도 알 수 있습니다.
            </span>
          </div>
        </div>
        <div class="d-flex justify-content-end mr-8">
          <button @click="donaLaw" id="dona_btn">기부 식품 관련 법령</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "DonationView",
  methods: {
    moveFood() {
      var link2 = "https://www.foodbank1377.org/";
      window.open(link2);
    },
    donaLaw() {
      var link3 =
        "https://easylaw.go.kr/CSP/CnpClsMain.laf?popMenu=ov&csmSeq=703&ccfNo=2&cciNo=1&cnpClsNo=2&menuType=cnpcls&search_put=";
      window.open(link3);
    },
  },
};
</script>

<style scoped>
img {
  height: inherit;
  width: 100%;
  margin: 5% 0%;
}
#dona_btn {
  width: 150px;
  height: 50px;
  background-color: white;
  font-size: 15px;
  font-weight: 800;
  color: rgba(140, 184, 131, 100%);
  border-radius: 20px;
  margin: 3% 0;
  padding: 3%;
  box-shadow: 10px 5px 5px rgba(0, 0, 0, 0.2);
}
</style>
