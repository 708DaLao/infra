import request from "@/utils/request";

/**
 * 根据角色获取权限
 */
export function getPermissionByRoles(data) {
  let data2 = {roles: data.join(",")}
  return request({
    url: "/api/role/permission",
    method: "get",
    params: data2
  });
}

/**
 * 获取角色列表
 */
export function getRoles(query) {
  return request({
    url: "/api/role/list",
    method: "get",
    params: query
  });
}

/**
 * 添加或修改角色信息
 */
export function saveRole(data) {
  return request({
    url: "/api/role/save",
    method: "post",
    data
  });
}

/**
 * 根据id进行删除角色
 */
export function deleteRole(data) {
  return request({
    url: `/api/role/delete`,
    method: "get",
    params: data
  });
}
