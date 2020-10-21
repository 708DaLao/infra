import request from "@/utils/request";

/**
 * 获取全部路由列表,且包含树结构。传分页参数则返回分页数据
 */
export function getResource(data) {
  return request({
    url: `/api/role/resource/list`,
    method: "get",
    params: data
  });
}

/**
 * 添加或修改资源
 */
export function saveOrUpdateResource(data) {
  return request({
    url: "/api/role/resource/save",
    method: "post",
    data
  });
}

/**
 * 根据id进行删除资源
 */
export function deleteResource(data) {
  return request({
    url: `/api/role/resource/delete`,
    method: "get",
    params: data
  });
}
