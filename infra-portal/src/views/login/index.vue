<template>
  <div class="login">
    <div class="main">
      <h3 style="text-align: center;color: #ffffff">登录 INFRA</h3>
      <el-form :rules="rules" ref="loginForm" :model="loginForm">
        <el-form-item prop="username">
          <el-input
            type="text"
            v-model="loginForm.username"
            placeholder="请输入用户名"
            @keyup.enter.native="handleLogin()"
          >
            <i slot="prepend" class="fa fa-user-o" aria-hidden="true"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="loginForm.password"
            show-password
            placeholder="请输入密码"
            @keyup.enter.native="handleLogin()"
          >
            <i slot="prepend" class="fa fa-unlock-alt" aria-hidden="true"></i>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            size="default"
            @click="handleLogin()"
            type="primary"
            :loading="loading"
            class="button-login"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loading: false, // 加载中
      loginForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 12, message: "长度在 5 到 12 个字符", trigger: "blur" }
        ]
      }
    };
  },
  watch: {
    $route: {
      // 监听路由变化
      handler: function(route) {
        const query = route.query; // 路由对象属性
        if (query) {
          this.redirect = query.redirect; // 路由的重定向属性
          this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          let data = {
            grant_type: this.$store.getters.grantType,
            client_id: this.$store.getters.clientId,
            client_secret: this.$store.getters.clientSecret,
            username: this.loginForm.username.trim(),
            password: this.loginForm.password.trim()
          };
          this.$store
            .dispatch("user/login", data)
            .then(() => {
              this.$router
                .push({
                  path: this.redirect || "/",
                  query: this.otherQuery
                })
                .catch(() => {});
              this.loading = false;
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== "redirect") {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    }
  }
};
</script>

<style scoped lang="scss">
.login {
  width: 100%;
  height: 100%;
  position: absolute;
  background: url("../../assets/bg.png") no-repeat;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  .main {
    width: 300px;
    margin: 0 auto;
    .button-login {
      width: 100%;
    }
  }
}
</style>
