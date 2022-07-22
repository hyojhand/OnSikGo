<template>
  <div>
    <h3>오늘 할인 판매 상품</h3>

          <b-card>
            <b-row>
            <b-col md="3">
              <div id="tobecenter">
              <img fluid src="@/assets/images/solo.jpg">
              </div>
            </b-col>
            <b-col md="9">
              <div class="text-align-center" id = "cardInText">
                <br>
                  <span>제품명</span>
                  <span>재고: 재고 개수</span>
                  <br>
                  <span>할인가: <class id = "firstPrice">원래 가격</class>  실제 가격</span>
              </div>
              <div class = "d-flex justify-content-end">
            <b-button size="sm" pill variant="outline-success">할인수정</b-button>
            </div>
            </b-col>
            </b-row>
            </b-card>
            
          
    </div>
</template>

<script>

export default {
    name: "discountList",
}
</script>

<style>
/* #tobecenter {
  align-content: center;
  align-items: center;
  margin-top: 4px;
} */
#firstPrice {
    text-decoration: line-through;
}

#cardInText {
    text-align: left;
    font-size: 10px;
}
</style>
