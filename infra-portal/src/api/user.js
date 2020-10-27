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

/**
 * 退出登录
 */
export function logout(token) {
  return request({
    url: "/auth/logout",
    method: "get",
    params: { token }
  });
}

/**
 * 修改个人信息
 */
export function updateInfo(data) {
  return request({
    url: "/api/sys_user/info/update",
    method: "post",
    data
  });
}

/**
 * 获取用户列表
 * 参数： 分页参数
 */
export function getUserList(data) {
  return request({
    url: "/api/sys_user/list",
    method: "get",
    params: data
  });
}

/**
 * 添加或修改用户
 */
export function saveOrUpdateUser(data) {
  return request({
    url: "/api/sys_user/save",
    method: "post",
    data
  });
}
