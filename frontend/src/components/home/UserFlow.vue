<template>
  <div>
    <!-- 사용자 사용법 -->
    <p class="info-title ">소비자's 온식고 이용방법</p>
    <section>
      <div class="container">
          <div class="carousel">
              <input type="radio" name="slides" checked="checked" id="slide-1">
              <input type="radio" name="slides" id="slide-2">
              <input type="radio" name="slides" id="slide-3">
              <input type="radio" name="slides" id="slide-4">
              <input type="radio" name="slides" id="slide-5">
              <input type="radio" name="slides" id="slide-6">
              <ul class="carousel__slides">
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="data:image/jpeg;base64,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" alt="">
                          </div>
                          <figcaption>
                              <p>소셜 로그인 또는</p>
                              <p>회원가입 후 로그인</p>
                              <span class="credit">로그인</span>
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1043/800/450" alt="">
                          </div>
                          <figcaption>
                              상품 검색 또는 가게 카테고리를 통해 쇼핑
                              <span class="credit">상품 확인</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1044/800/450" alt="">
                          </div>
                          <figcaption>
                              구매하고 싶은 상품 선택
                              <span class="credit">상품 선택</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1045/800/450" alt="">
                          </div>
                          <figcaption>
                              상품 수량을 선택 후 주문 신청
                              <span class="credit">주문 신청</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1049/800/450" alt="">
                          </div>
                          <figcaption>
                              가게로 부터 주문 승인 알림 확인후 가게로 이동
                              <span class="credit">알림 확인</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1052/800/450" alt="">
                          </div>
                          <figcaption>
                              가게로 부터 상품 수령
                              <span class="credit">상품 수령</span>                            
                          </figcaption>
                      </figure>
                  </li>
              </ul>    
              <ul class="carousel__thumbnails">
                  <li>
                      <label for="slide-1"><img src="data:image/jpeg;base64,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" alt=""></label>
                  </li>
                  <li>
                      <label for="slide-2"><img src="https://picsum.photos/id/1043/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="slide-3"><img src="https://picsum.photos/id/1044/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="slide-4"><img src="https://picsum.photos/id/1045/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="slide-5"><img src="https://picsum.photos/id/1049/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="slide-6"><img src="https://picsum.photos/id/1052/150/150" alt=""></label>
                  </li>
              </ul>
          </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name : "UserFlow",
}
</script>
<style scoped>
  .info-title {
    font-size: 18px;
  }
  
</style>
<style lang="scss" scoped>
@mixin aspect-ratio($width, $height) {
  position: relative;
    
  &:before {
    display: block;
    content: "";
    width: 100%;
    padding-top: ($height / $width) * 100%;
  }
    
  > img {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        width: 100%;
        height: 100%;
  }
}

// Styling

section {
    background: #F4F4F4;
    padding:0;
}

.container {
    max-width: 1044px;
    margin: 0 auto;
    padding: 0 20px;
}

.carousel {
    display: block;
    text-align: left;
    position: relative;
    margin-bottom: 22px;
    
    > input {
        clip: rect(1px, 1px, 1px, 1px);
        clip-path: inset(50%);
        height: 1px;
        width: 1px;
        margin: -1px;
        overflow: hidden;
        padding: 0;
        position: absolute;
        
        &:nth-of-type(6):checked ~ .carousel__slides .carousel__slide:first-of-type { margin-left: -500%; }
        &:nth-of-type(5):checked ~ .carousel__slides .carousel__slide:first-of-type { margin-left: -400%; }
        &:nth-of-type(4):checked ~ .carousel__slides .carousel__slide:first-of-type { margin-left: -300%; }
        &:nth-of-type(3):checked ~ .carousel__slides .carousel__slide:first-of-type { margin-left: -200%; }
        &:nth-of-type(2):checked ~ .carousel__slides .carousel__slide:first-of-type { margin-left: -100%; }
        &:nth-of-type(1):checked ~ .carousel__slides .carousel__slide:first-of-type { margin-left: 0%; }
        
        &:nth-of-type(1):checked ~ .carousel__thumbnails li:nth-of-type(1) { box-shadow: 0px 0px 0px 5px rgba(0,0,255,0.5); }
        &:nth-of-type(2):checked ~ .carousel__thumbnails li:nth-of-type(2) { box-shadow: 0px 0px 0px 5px rgba(0,0,255,0.5); }
        &:nth-of-type(3):checked ~ .carousel__thumbnails li:nth-of-type(3) { box-shadow: 0px 0px 0px 5px rgba(0,0,255,0.5); }
        &:nth-of-type(4):checked ~ .carousel__thumbnails li:nth-of-type(4) { box-shadow: 0px 0px 0px 5px rgba(0,0,255,0.5); }
        &:nth-of-type(5):checked ~ .carousel__thumbnails li:nth-of-type(5) { box-shadow: 0px 0px 0px 5px rgba(0,0,255,0.5); }
        &:nth-of-type(6):checked ~ .carousel__thumbnails li:nth-of-type(6) { box-shadow: 0px 0px 0px 5px rgba(0,0,255,0.5); }
    }
}

.carousel__slides {
    position: relative;
    z-index: 1;
    padding: 0;
    margin: 0;
    overflow: hidden;
    white-space: nowrap;
    box-sizing: border-box;
    display: flex;
}

.carousel__slide {
    position: relative;
    display: block;
    flex: 1 0 100%;
    width: 100%;
    height: 100%;
    overflow: hidden;
    transition: all 300ms ease-out;
    vertical-align: top;
    box-sizing: border-box;
    white-space: normal;
    
    figure {
        display: flex;
        margin: 0;
    }
    
    div {
        @include aspect-ratio(3, 2);
        width: 100%;
    }
    
    img {
        display: block;
        flex: 1 1 auto;
        object-fit: cover;
    }
    
    figcaption {
        align-self: flex-end;
        padding: 20px 20px 0 20px;
        flex: 0 0 auto;
        width: 25%;
        min-width: 150px;
    }
    
    .credit {
        margin-top: 1rem;
        color: rgba(0, 0, 0, 0.5);
        display: block;        
    }
    
    &.scrollable {
        overflow-y: scroll;
    }
}

.carousel__thumbnails {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
    
    margin: 0 -10px;
    
    .carousel__slides + & {
        margin-top: 20px;
    }
    
    li {        
        flex: 1 1 auto;
        max-width: calc((100% / 6) - 20px);  
        margin: 0 10px;
        transition: all 300ms ease-in-out;
    }
    
    label {
        display: block;
        @include aspect-ratio(1,1);
        
                  
        &:hover,
        &:focus {
            cursor: pointer;
            
            img {
                box-shadow: 0px 0px 0px 1px rgba(0,0,0,0.25);
                transition: all 300ms ease-in-out;
            }
        }
    }
    
    img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
}
</style>