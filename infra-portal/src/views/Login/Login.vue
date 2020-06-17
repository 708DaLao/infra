<template>
  <div class="login">
    <div class="main">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登陆</el-button>
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
          password: ''
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
  html, body {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
  }
  .login{
    height: 300px;
    width: 300px;
    margin: 0 auto; /*水平居中*/
    position: relative; /*设置position属性*/
    /*background: bisque;*/
    background-image: url("../../assets/vue.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
  }
</style>
