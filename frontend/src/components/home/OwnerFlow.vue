<template>
  <div>
    <!-- 점주 사용법 -->
    <v-stepper v-model="e1">
     <p class="info-title pt-3">업주's 온식고 이용방법</p>
      <v-stepper-items>
        <v-stepper-content class="stepper-content" step="1">
          <img src="@/assets/images/ownerstep_1.png" alt="" style="width:100%;">
          <p class="pb-1 mb-0"> 온식고 회원가입후.</p>
          <p class="pb-3">로그인으로 접속합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 2"
            style="text-align: ceter;"
          >
            로그인
          </v-btn>
        </v-stepper-content>
  
        <v-stepper-content class="stepper-content" step="2">
          <img src="@/assets/images/ownerstep_2.png" alt="" style="width:100%;">
          <p class="pb-1 mb-0">상품 이미지, 이름, 정상가, 특이사항을</p>
          <p class="pb-3">입력후 등록합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 3"
          >
            상품 등록
          </v-btn>
        </v-stepper-content>
  
        <v-stepper-content class="stepper-content" step="3">
          <img src="@/assets/images/ownerstep_3.png" alt="" style="width:100%;">
          <p class="pb-1 mb-0">할인 상품의 할인가와 수량을</p>
          <p class="pb-3">입력후 등록합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 4"
          >
            재고 수량 등록
          </v-btn>
        </v-stepper-content>

        <v-stepper-content class="stepper-content" step="4">
          <img src="@/assets/images/ownerstep_4.png" alt="" style="width:100%;">
          <p class="pb-1 mb-0">소비자로부터 온 주문서에서</p>
          <p class="pb-3">상품명과 수량을 확인합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 5"
            
          >
            주문 확인
          </v-btn>
        </v-stepper-content>

        <v-stepper-content class="stepper-content" step="5">
          <img src="@/assets/images/ownerstep_5.png" alt="" style="width:100%;">
          <p class="pb-3">승인후 상품을 준비합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 1"
          >
            승인 후 제품 준비
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
      <!-- 순서 -->
      <v-stepper-header>
        <v-stepper-step step="1">
        </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step step="2">
        </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step step="3">
        </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step step="4">
        </v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step step="5">
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper>
  </div>
</template>

<script>
export default {
  name:"OwnerFlow",
  data () {
    return {
      e1: 1,
    }
  }
}
</script>

<style scoped>
  .info-title {
    font-size: 25px;
    margin-bottom: 5px;
    color: rgba(140, 184, 131, 0.8);
  }

  .stepper-content {
    padding: 0;
  }

  .info-btn {
    position: relative;
    left: 50%;
    transform: translate(-50%, -50%);
  }
</style>

