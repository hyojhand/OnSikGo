import axios from "axios";

// axios 객체 생성
export default axios.create({
  // baseURL: "https://i7e201.p.ssafy.io:8081/api",
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-type": "application/json",
  },
});
