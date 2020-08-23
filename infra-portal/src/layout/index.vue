<template>
  <el-container style="height: 100%">
    <!-- 左侧菜单栏 -->
    <el-aside :width="$store.getters.isCollapse?'65px':'200px'"> <sidebar /> </el-aside>
    <el-container>
      <!-- 导航栏 -->
      <el-header> <navbar /> </el-header>
      <!-- 主体区域-->
      <el-main> <app-main /> </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { Navbar, Sidebar, AppMain } from "./components";
export default {
  name: "Layout",
  components: {
    Navbar,
    Sidebar,
    AppMain
  }
};
</script>

<style lang="scss" scoped>
  .el-header {
    color: #333;
    text-align: center;
    line-height: 60px;
    padding: 0;
  }
  .el-aside {
    height: 100vh;
    background-color: $baseBgColor;
  }

</style>
