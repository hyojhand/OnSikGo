<template>
  <div>
    <h5>✨ {{ userDto.userName }} 님의 단골매장</h5>

    <b-card>
      <b-row>
        <b-col md="3">
          <div id="tobecenter">
            <img
              fluid
              src="@/assets/images/이마트24.png"
              height="50"
              width="50"
            />
          </div>
        </b-col>
        <b-col md="9">
          <div class="text-align-center" id="cardInText">
            <br />
            <span>매장명</span>
            <br />
            <span>오늘의 제품: 5개</span>
            <br />
          </div>
          <div class="d-flex justify-content-end">
            <b-button
              @click="shopinfo()"
              size="sm"
              pill
              variant="outline-success"
              >매장정보</b-button
            >
          </div>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "regularList",

  data() {
    return {
      userDto: {},
    };
  },

  created() {
    http.defaults.headers["access-token"] =
      localStorage.getItem("access-token");
    http.get("/user").then((response) => {
      this.userDto = response.data;
    });
  },
  methods: {
    shopinfo() {
      this.$router.push("/store");
    },
  },
};
</script>

<style>
#firstPrice {
  text-decoration: line-through;
}

#cardInText {
  text-align: left;
  font-size: 10px;
}
</style>
