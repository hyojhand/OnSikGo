<template>
  <div>
    <h1>AdminReview</h1>
    <div
      v-for="(reviewDto, index) in reviewList"
      :key="index"
      v-bind="reviewDto">
    </div>
  </div>
</template>

<script>
export default {
  name : "AdminReview",

  data: function () {
    return {
      reviewList: [],
    }
  },
};
</script>

<style>

</style>