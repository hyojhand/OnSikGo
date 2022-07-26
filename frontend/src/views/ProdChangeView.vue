<template>
  <div>
    <!--매장선택-->
    <div class = "d-flex justify-content-center">
      <b-dropdown id="dropdown1" text="1번째 매장 이름" style="border-color:#63BF68;">
        <b-dropdown-item>2번째 매장 이름</b-dropdown-item>
        <b-dropdown-item>3번째 매장 이름</b-dropdown-item>
        <b-dropdown-item>4번째 매장 이름</b-dropdown-item>
      </b-dropdown>
    </div>
    <!--수정정보나타내기-->
    <div class="head">
      <div class="container">
        <img src="@/assets/images/hambuger.jpg" alt="IMG-PRODUCT" class="mt-2 mb-5">
      </div>
      <div class="bg-secondary bg-opacity-75 text-white fw-bold fs-2">
        <p class="head">상품이름</p>
      </div>
      <br>

      <!--상품정보-->
      <div class="d-flex justify-content-center">
        <div class="col-4">
          <p class="mb-5">정상가</p>
          <br>
          <p class="mt-4">할인가</p>
          <p>수량</p>
          <p>특이사항</p>
        </div>
        <div class="col-8">
          <p>6000</p>
          <p>🔻할인율입력해야함</p>
          <input class="form-control form-control-sm" type="text" placeholder="원하시는 금액을 입력해주세요" aria-label=".form-control-sm example"> 
          <input class="form-control form-control-sm mt-2" type="text" placeholder="원하시는 수량을 입력해주세요" aria-label=".form-control-sm example"> 
          <input class="form-control form-control-sm mt-2" type="text" placeholder="특이사항을 입력해주세요" aria-label=".form-control-sm example"> 
        </div>
      </div>
      <br>
      <br>

      <!--버튼모음-->
      <div class="d-flex justify-content-around">
        <b-button @click="prodchange" pill variant="success" class="d-grid gap-2 col-4 mx-auto">수정</b-button>
        <b-button @click="proddelete" pill variant="danger" class="d-grid col-4 mx-auto">
          <div><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3 " viewBox="0 0 16 16">
            <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"></path>
          </svg> 삭제</div>
        </b-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProdChangeView",

  methods: {
    prodchange() {
      this.$router.push('/allprod/')
    },

    proddelete() {
      this.$router.push('/allprod/delete')
    },
  }
};
</script>

<style scoped>
  .container {
    width: 300px;
    height: 220px;
    overflow: hidden;
    position: relative;
  }

  .container > img {
    position: absolute;
    width: 100%;
    /* height: 100%; */
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .head {
    margin-top: 50px;
  }
</style>
