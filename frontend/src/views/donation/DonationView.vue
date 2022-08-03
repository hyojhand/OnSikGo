<template>
  <div>
    <div class="dona-box">
      <img
        src="https://www.timeforum.co.kr/files/attach/images/77/520/177/012/5db2d3607e569a7520fde8009bb88c44.jpg"
        alt="기부사진이었던것.."
      />
      <i class="fa-solid fa-arrow-down fa-xl"></i>
      <h4>식품 기부를 원하신다면</h4>
      <h4>해당 아이콘을 클릭해주세요.</h4>
      <img
        src="https://blog.kakaocdn.net/dn/7h6Qz/btry8PE3lHb/6rN468v6KiLmiCcb75LA6K/img.jpg"
        alt="기부 사진이었던것"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "DonationView",
};
</script>

<style scoped>
img {
  height: inherit;
  width: 100%;
  margin: 5% 0%;
}
h4 {
  margin: 2% 0%;
}
.dona-box {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
