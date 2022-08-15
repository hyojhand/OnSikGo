<template>
  <div class="info-container">
    <!-- 사용자 사용법 -->
    <v-stepper v-model="e1">
     <p class="info-title pt-3">소비자's 온식고 이용방법</p>
  
      <v-stepper-items>
        <v-stepper-content class="stepper-content" step="1">
          <img src="@/assets/images/step_1.png" alt="" style="width:100%;">
          <p class="pb-3"> 소셜 로그인 또는 온식고 회원가입후 로그인으로 접속합니다.</p>
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
          <img src="@/assets/images/step_2.png" alt="" style="width:100%;">
          <p class="pb-3">카테고리를 선택 후 상품을 올린 가게 또는 상품을 쇼핑합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 3"
          >
            상품 확인
          </v-btn>
        </v-stepper-content>
  
        <v-stepper-content class="stepper-content" step="3">
          <img src="@/assets/images/step_3.png" alt="" style="width:100%;">
          <p class="pb-3">원하시는 상품이 있으시면 해당 상품을 선택합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 4"
          >
            상품 선택
          </v-btn>
        </v-stepper-content>

        <v-stepper-content class="stepper-content" step="4">
          <img src="@/assets/images/step_4.png" alt="" style="width:100%;">
          <p class="pb-3">원하시는 수량을 선택 후 주문하기를 클릭합니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 5"
            
          >
            주문 요청
          </v-btn>
        </v-stepper-content>

        <v-stepper-content class="stepper-content" step="5">
          <img src="@/assets/images/step_5.png" alt="" style="width:100%;">
          <p class="pb-3">주문 승인 알림을 확인 후 상품을 픽업하러 갑니다.</p>
          <v-btn
            class="info-btn"
            color="primary"
            @click="e1 = 1"
          >
            알림 확인 및 픽업
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
      <v-stepper-header>
        <v-stepper-step
          step="1"
        >
        </v-stepper-step>
        <v-divider></v-divider>

        <v-stepper-step
          step="2"
        >
        </v-stepper-step>
  
        <v-divider></v-divider>
  
        <v-stepper-step 
          step="3"
        >
        </v-stepper-step>

        <v-divider></v-divider>
  
        <v-stepper-step 
          step="4">
        </v-stepper-step>

        <v-divider></v-divider>
        <v-stepper-step 
          step="5">
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper>
  </div>
</template>

<script>
export default {
  name : "UserFlow",
  data () {
    return {
      e1: 1,
    }
  }
}
</script>
<style scoped>
  .info-title {
    font-size: 18px;
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