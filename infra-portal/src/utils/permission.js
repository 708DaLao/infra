import Layout from "@/layout";

/**
 * 对后端传来的数据进行处理
 */
export function filterAsyncRouter(asyncRouteMap) {
  return asyncRouteMap.filter(route => {
    if (route.component) {
      if (route.component === "Layout") {
        route.component = Layout;
      } else {
        route.component = () => import(route.component);
      }
    }
    if (route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children);
    }
    return true;
  });
}

/**
 * 异步加载组件
 */
export function loadView(component) {
  return () => import(`${component}`+".vue");
}
