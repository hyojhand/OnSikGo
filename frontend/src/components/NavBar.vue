<template>
  <div>
    <router-link :to="{ name: 'home' }">토글바 |</router-link>
    <router-link :to="{ name: 'home' }">로고 |</router-link>
    <router-link :to="{ name: 'home' }">알림설정</router-link>
  </div>
</template>

<script>
export default {
  name: "NavBar",
};
</script>

<style></style>
