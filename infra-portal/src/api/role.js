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

export function getRoles() {
  return request({
    url: "/vue-element-admin/roles",
    method: "get"
  });
}

export function addRole(data) {
  return request({
    url: "/vue-element-admin/role",
    method: "post",
    data
  });
}

export function updateRole(id, data) {
  return request({
    url: `/vue-element-admin/role/${id}`,
    method: "put",
    data
  });
}

export function deleteRole(id) {
  return request({
    url: `/vue-element-admin/role/${id}`,
    method: "delete"
  });
}
