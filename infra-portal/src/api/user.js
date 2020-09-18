import request from "@/utils/request";

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

export function getInfo(token) {
  return request({
    url: "/api/user/info",
    method: "get",
    params: { token }
  });
}

export function logout() {
  return request({
    url: "/vue-element-admin/user/logout",
    method: "post"
  });
}
