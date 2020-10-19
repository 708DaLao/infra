import request from "@/utils/request";

/**
 * 添加或修改路由
 */
export function saveOrUpdateRouter(data) {
  return request({
    url: "/api/role/routers/save",
    method: "post",
    data
  });
}

/**
 * 根据id删除路由
 */
export function deleteRouter(data) {
  return request({
    url: `/api/role/routers/delete`,
    method: "get",
    params: data
  });
}
