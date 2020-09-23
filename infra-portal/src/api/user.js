import request from "@/utils/request";

/**
 * 登录
 */
export function login(data) {
  return request({
    url: "/auth/oauth/token",
    method: "post",
    params: data
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
