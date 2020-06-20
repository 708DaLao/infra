<template>
  <div class="login">
    <div class="main">
      <h3 style="text-align: center;color: #ffffff">登录  INFRA</h3>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item>
          <el-input
            type="text"
            v-model="form.username"
            placeholder="请输入用户名">
            <i slot="prepend" class="fa fa-user-o" aria-hidden="true"></i>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            type="password"
            v-model="form.password"
            placeholder="请输入密码">
            <i slot="prepend" class="fa fa-unlock-alt" aria-hidden="true"></i>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            type="text"
            v-model="form.captcha"
            placeholder="验证码">
            <template slot="append">
              <img class="login-code" src="../../assets/login-code.png">
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            size="default"
            @click="onSubmit"
            type="primary"
            class="button-login">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default{
    name: 'Login',
    data() {
      return {
        form: {
          username: '',
          password: '',
          captcha: ''
        }
      }
    },
    methods:{
      onSubmit() {
        console.log(1111);
      }
    }

  }
</script>

<style scoped lang="scss">
  .login{
    width: 100%;
    height: 100%;
    position: absolute;
    background: url("../../assets/bg.jpg") no-repeat;
    background-size: cover;
    .main {
      width: 300px;
      margin-top: 220px;
      margin-left: auto;
      margin-right: 150px;
      position: relative;
      .button-login{
        width: 100%;
      }
      .login-code {
        height: 40px - 2px;
        display: block;
        margin: 0px -20px;
        border-top-right-radius: 2px;
        border-bottom-right-radius: 2px;
      }
    }
  }
</style>
