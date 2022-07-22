<template>
  <v-card class="mx-auto" max-width="344" outlined>
    <v-list-item three-line>
      <v-list-item-avatar tile size="80"
        ><img
          src="https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/4gqX/image/wIXZfUhOPGGGZxaZ0Nsmigd1paU.jpeg"
          alt="사진이었던것.."
      /></v-list-item-avatar>
      <v-list-item-content class="notice-box">
        <v-list-item-title class="text mb-1"> 최지은 님의 </v-list-item-title>
        <v-list-item-title class="text mb-1"
          >주문이 도착했습니다.</v-list-item-title
        >
      </v-list-item-content>
    </v-list-item>

    <v-card-actions>
      <v-list-item-subtitle> 10분전</v-list-item-subtitle>
      <v-btn outlined rounded text> Button </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "NoticeCard",
};
</script>

<style scoped>
.notice-box {
  display: flex;
  justify-content: flex-start;
}
</style>
