<template>
  <div>
    <div class="mt-3">
      <div class = "d-flex justify-content-center">
        <b-dropdown id="dropdown1" text="1번째 매장 이름" style="border-color:#63BF68;">
          <b-dropdown-item>2번째 매장 이름</b-dropdown-item>
          <b-dropdown-item>3번째 매장 이름</b-dropdown-item>
          <b-dropdown-item>4번째 매장 이름</b-dropdown-item>
        </b-dropdown>
      </div>

      <div class="d-flex mt-4">
        <a href="#" class="me-auto text-decoration-none"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
          <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
          <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
        </svg>상품등록</a>
        <div class="input-group w-50">
          <span class="input-group-text" id="basic-addon1">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
          <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
          </svg>
          </span>
          <input type="text" class="form-control" placeholder="상품검색" aria-label="Input group example" aria-describedby="basic-addon1">
        </div>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="mt-5 col-6">
        <img src="#" alt="IMG-PRODUCT" class="mt-2 mb-5">
        <div class="bg-secondary bg-opacity-50 text-white fw-bold">
          <p>상품이름</p>
        </div>
        <div class="d-flex">
          <div class="me-auto">
            <p class="mt-1">할인가: 3000</p>
            <p>수량: 2</p>
          </div>
          <button class="btn btn-success">수정</button>
        </div>
      </div>
      <div class="mt-5 col-6">
        <img src="#" alt="IMG-PRODUCT" class="mt-2 mb-5">
        <div class="bg-secondary bg-opacity-50 text-white fw-bold">
          <p>상품이름</p>
        </div>
        <div class="d-flex">
          <div class="me-auto">
            <p class="mt-1">할인가: 3000</p>
            <p>수량: 2</p>
          </div>
          <button class="btn btn-success">수정</button>
        </div>
      </div>
      <div class="mt-5 col-6">
        <img src="#" alt="IMG-PRODUCT" class="mt-2 mb-5">
        <div class="bg-secondary bg-opacity-50 text-white fw-bold">
          <p>상품이름</p>
        </div>
        <div class="d-flex">
          <div class="me-auto">
            <p class="mt-1">할인가: 3000</p>
            <p>수량: 2</p>
          </div>
          <button class="btn btn-success">수정</button>
        </div>
      </div>
      <div class="mt-5 col-6">
        <img src="#" alt="IMG-PRODUCT" class="mt-2 mb-5">
        <div class="bg-secondary bg-opacity-50 text-white fw-bold">
          <p>상품이름</p>
        </div>
        <div class="d-flex">
          <div class="me-auto">
            <p class="mt-1">할인가: 3000</p>
            <p>수량: 2</p>
          </div>
          <button class="btn btn-success">수정</button>
        </div>
      </div>
    </div>
    <br>
    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center">
        <li class="page-item disabled">
          <a class="page-link">Previous</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#">Next</a>
        </li>
      </ul>
    </nav>

  </div>
</template>

<script>
export default {
  name: "AllProdView",
};
</script>

<style scoped>

</style>
