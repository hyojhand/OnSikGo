<template>
  <div>
    <!-- <b-button @click="modalShow = !modalShow" pill variant="danger" class="mr-5">
      <div><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3 " viewBox="0 0 16 16">
        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"></path>
      </svg> 삭제</div>
    </b-button>
    <b-modal v-model="modalShow" button-size="lg"> -->
    <!--상품 삭제 확인페이지-->
    <!-- <div class="head">
        <h4>상품을 삭제를 선택하셨습니다</h4>
        <br>
        <h5 class="danger mb-5">정말로 삭제하시겠습니까?</h5>
        <br>
      </div>
    </b-modal> -->
    <div class="mb-1">
      <button @click="showMsgBoxTwo" class="border-m radius-m del-btn">
        <div class="font-color">
          <!-- <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            class="bi bi-trash3 font-color"
            viewBox="0 0 16 16"
          >
            <path
              d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"
            ></path>
          </svg> -->
          삭 제
        </div>
      </button>
      <!-- Return value: {{ String(boxTwo) }} -->
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  data() {
    return {
      // modalShow: false
      boxTwo: "",
    };
  },
  props: {
    no: Number,
  },
  methods: {
    async showMsgBoxTwo() {
      this.boxTwo = "";
      await this.$bvModal
        .msgBoxConfirm("정말로 삭제하시겠습니까?", {
          title: "상품 삭제를 선택하셨습니다",
          size: "m",
          buttonSize: "sm",
          okVariant: "danger",
          okTitle: "YES",
          cancelTitle: "NO",
          footerClass: "p-2",
          hideHeaderClose: false,
          centered: true,
        })
        .then((value) => {
          this.boxTwo = value;
        });

      if (this.boxTwo == true) {
        await http.delete(`/item/${this.no}`).then((response) => {
          if (response.status == 200) {
            this.$router.push("ProdChangeView");
          }
        });
      }
      // .catch(err => {
      //   // An error occurred
      // })
    },
  },
};
</script>

<style scoped>
.del-btn {
  width: 74px;
  height: 34.3px;
  color: rgb(255, 82, 82);
}
.font-color {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-evenly;
  color: rgb(255, 82, 82);
}
</style>
