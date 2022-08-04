import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "https://localhost:8080/api",
  headers: {
    "Content-type": "application/json",
  },
});
