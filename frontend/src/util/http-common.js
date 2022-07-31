import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://i7e201.p.ssafy.io/:8081",
  headers: {
    "Content-type": "application/json",
  },
});
