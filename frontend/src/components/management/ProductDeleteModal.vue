<template>
  <div>
    <div class="mb-1">
      <button
        @click="showMsgBoxTwo"
        class="border-m radius-m del-btn"
        width="340"
      >
        <div class="font-color">삭 제</div>
      </button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  data() {
    return {
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
          okTitle: "네",
          cancelTitle: "아니오",
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
            this.$router.push({ name: "allProduct" });
          }
        });
      }
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
