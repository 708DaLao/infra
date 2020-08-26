<template>
  <el-container class="container">
    <!-- 左侧菜单栏 -->
    <el-aside :width="$store.getters.isCollapse?'65px':'200px'"> <sidebar /> </el-aside>
    <el-container class="container2">
      <!-- 导航栏 -->
      <el-header> <navbar /> </el-header>
      <!-- 主体区域-->
      <el-main>
        <app-main />
      </el-main>
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
  .container {
    width: 100%;
    height: 100%;
    overflow: hidden;
    .el-aside {
      height: 100vh;
      background-color: $baseBgColor;
    }
    .container2 {
      min-width: 900px;
      .el-header {
        background-color: $baseBgColor;
        color: #333;
        text-align: center;
        line-height: 60px;
        padding: 0;
      }
      .el-main {
        overflow: auto;
      }
    }
  }



</style>
