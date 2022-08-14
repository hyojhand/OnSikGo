<template>
  <div>
    <!-- 점주 사용법 -->
    <p class="info-title p-4">업주's 온식고 이용방법</p>
    <section>
      <div class="container">
          <div class="carousel">
              <input type="radio" name="slides" checked="checked" id="ownerslide-1">
              <input type="radio" name="slides" id="ownerslide-2">
              <input type="radio" name="slides" id="ownerslide-3">
              <input type="radio" name="slides" id="ownerslide-4">
              <input type="radio" name="slides" id="ownerslide-5">
              <input type="radio" name="slides" id="ownerslide-6">
              <ul class="carousel__slides">
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1041/800/450" alt="">
                          </div>
                          <figcaption>
                              온식고 사이트 회원가입 후 로그인
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
                              온식고를 통해 판매하고 싶은 상품 등록
                              <span class="credit">상품 등록</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1044/800/450" alt="">
                          </div>
                          <figcaption>
                              할인가 와 재고 수량을 입력 후 상품 등록
                              <span class="credit">재고 수량 등록</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1045/800/450" alt="">
                          </div>
                          <figcaption>
                              소비자로 부터 온 주문서 확인
                              <span class="credit">알림 확인</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1049/800/450" alt="">
                          </div>
                          <figcaption>
                              소비자가 오기전에 미리 상품 준비
                              <span class="credit">상품 준비</span>                            
                          </figcaption>
                      </figure>
                  </li>
                  <li class="carousel__slide">
                      <figure>
                          <div>
                              <img src="https://picsum.photos/id/1052/800/450" alt="">
                          </div>
                          <figcaption>
                              주문한 소비자에게 상품 전달
                              <span class="credit">상품 전달</span>                            
                          </figcaption>
                      </figure>
                  </li>
              </ul>    
              <ul class="carousel__thumbnails">
                  <li>
                      <label for="ownerslide-1"><img src="https://picsum.photos/id/1041/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="ownerslide-2"><img src="https://picsum.photos/id/1043/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="ownerslide-3"><img src="https://picsum.photos/id/1044/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="ownerslide-4"><img src="https://picsum.photos/id/1045/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="ownerslide-5"><img src="https://picsum.photos/id/1049/150/150" alt=""></label>
                  </li>
                  <li>
                      <label for="ownerslide-6"><img src="https://picsum.photos/id/1052/150/150" alt=""></label>
                  </li>
              </ul>
          </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name:"OwnerFlow"
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