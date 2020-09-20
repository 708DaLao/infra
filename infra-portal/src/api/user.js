import request from "@/utils/request";

/**
 * 登录
 */
export function login(data) {
  return request({
    url:
      "/auth/oauth/token?grant_type=" +
      data.grant_type +
      "&client_id=" +
      data.client_id +
      "&client_secret=" +
      data.client_secret +
      "&username=" +
      data.username +
      "&password=" +
      data.password,
    method: "post"
  });
}

/**
 * 获取当前登录用户信息
 * 不需要额外添加token参数，默认在请求头携带token去请求
 */
export function getInfo() {
  return request({
    url: "/api/sys_user/info",
    method: "get"
    // params: { token }
  });
}

export function logout() {
  return request({
    url: "/vue-element-admin/user/logout",
    method: "post"
  });
}
