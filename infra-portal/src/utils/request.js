import axios from "axios";
import { Message, MessageBox } from "element-ui";
import store from "@/store";
import { getToken } from "@/utils/auth";

const http = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // 使用代理解决跨域问题
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
});

// request拦截器
http.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers["Authorization"] = "Bearer " + getToken(); // 每个请求都携带token
    }
    return config;
  },
  error => {
    // do something with request error
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

// response拦截器
http.interceptors.response.use(
  response => {
    const res = response.data;

    if (response.error_description) {
      Message({
        message: response.error_description || "Error",
        type: "error",
        duration: 5 * 1000
      });
    } else {
      // if the custom code is not 20000, it is judged as an error.
      if (res.code !== 200) {
        Message({
          message: res.message || "Error",
          type: "error",
          duration: 5 * 1000
        });

        if (res.code === 401) {
          MessageBox.confirm(
            "您可以选择退出重新登录，或继续留在此页面",
            "确认是否退出",
            {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }
          ).then(() => {
            store.dispatch("user/resetToken").then(() => {
              location.reload();
            });
          });
        }
        return Promise.reject(new Error(res.message || "Error"));
      } else {
        return res;
      }
    }
  },
  error => {
    Message({
      message: error.message,
      type: "error",
      duration: 5 * 1000
    });
    return Promise.reject(error);
  }
);

export default http;
