<template>
  <div class="navbar">
    <div class="header-left">
      <div class="sidebar" @click="handleChange">
        <i class="fa fa-bars fa-lg" aria-hidden="true"></i>
      </div>
    </div>
    <div class="header-right">
      <div class="navbar-item">
        <search />
        <fullscreen />
        <theme />
        <news />
      </div>
      <div>
        <el-dropdown>
          <span class="el-dropdown-link">
            <el-avatar
              shape="square"
              :size="40"
              fit="cover"
              :src="url"
              style="margin-top: 10px"
            ></el-avatar>
          </span>
          <el-dropdown-menu slot="dropdown">
            <router-link to="/profile/index">
              <el-dropdown-item>个人中心</el-dropdown-item>
            </router-link>
            <a target="_blank" href="https://gitee.com/dalao708/infra">
              <el-dropdown-item>码云</el-dropdown-item>
            </a>
            <a target="_blank" href="https://github.com/708DaLao">
              <el-dropdown-item>GitHub</el-dropdown-item>
            </a>
            <el-dropdown-item divided @click.native="logout"
              >退出登录</el-dropdown-item
            >
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
import fullscreen from "./navbar-item/fullscreen";
import theme from "./navbar-item/theme";
import search from "./navbar-item/search";
import news from "./navbar-item/news";
export default {
  name: "Navbar",
  components: {
    fullscreen,
    theme,
    search,
    news
  },
  data() {
    return {
      url: this.$store.getters.avatar
    };
  },
  methods: {
    handleChange() {
      this.$store.commit("app/changeCollapse");
    },
    logout() {
      this.$confirm("确定退出吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(async () => {
        await this.$store.dispatch("user/logout");
        await this.$router.push(`/login?redirect=${this.$route.fullPath}`);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.navbar {
  .header-left {
    float: left;
    display: flex;
    justify-content: center;
    align-items: center;
    .sidebar {
      margin: 0 20px;
      i {
        color: #606266;
        &:hover {
          color: #409eff;
        }
      }
    }
  }
  .header-right {
    height: 60px;
    float: right;
    display: flex;
    margin-right: 20px;
    .navbar-item {
      height: 60px;
      margin-right: 30px;
      .el-button {
        margin-left: 25px;
      }
    }
    .el-dropdown {
      height: 60px;
    }
  }
}
</style>
