import request from "@/utils/request";

/**
 * 获取全部路由列表,且包含树结构。传分页参数则返回分页数据
 */
export function getResource() {
  return request({
    url: `/api/role/resource/list`,
    method: "get"
  });
}
