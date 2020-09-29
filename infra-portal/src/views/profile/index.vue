<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover">
          <div class="flex-row-center flex-column-center margin-bottom-20">
            <div class="margin-bottom-10">
              <el-avatar :size="80" :src="avatar"></el-avatar>
            </div>
            <div>
              欢迎登录：
              <span class="text-orange">{{ nickname }}</span>
            </div>
            <div class="margin-top-20 ft-13">
              <el-button type="text" @click="dialogUserInfo = true"
                >个人信息</el-button
              >
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-carousel :interval="4000" type="card" height="400px">
          <el-carousel-item v-for="(item, index) in img" :key="index">
            <el-image :src="item" fit="fill"></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>

    <el-dialog
      title="个人信息"
      :visible.sync="dialogUserInfo"
      width="30%"
      center
    >
      <el-form ref="userForm" label-width="80px" :model="userInfo">
        <el-form-item label="昵称">
          <el-input v-model="userInfo.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input
            v-model="userInfo.realname"
            disabled
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="userInfo.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="userInfo.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="userInfo.gender" placeholder="请选择性别">
            <el-option label="保密" :value="0"></el-option>
            <el-option label="男" :value="1"></el-option>
            <el-option label="女" :value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-date-picker
            v-model="userInfo.birthday"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="家庭地址">
          <el-input v-model="userInfo.addr" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogUserInfo = false">取 消</el-button>
        <el-button type="primary" @click="dialogUserInfo = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
const defaultUserInfo = {
  id: "",
  nickname: "",
  realname: "",
  phone: "",
  email: "",
  gender: "",
  birthday: "",
  addr: ""
};
export default {
  data() {
    return {
      avatar: this.$store.getters.avatar,
      nickname: this.$store.getters.nickname,
      userInfo: Object.assign(defaultUserInfo, this.$store.getters.userInfo),
      dialogUserInfo: false,
      img: [
        "https://w.wallhaven.cc/full/xl/wallhaven-xlx68l.jpg",
        "https://w.wallhaven.cc/full/5w/wallhaven-5w1w18.jpg",
        "https://w.wallhaven.cc/full/vg/wallhaven-vg7g73.jpg",
        "https://w.wallhaven.cc/full/md/wallhaven-mdxd7k.jpg"
      ]
    };
  },
  created() {
    console.log(this.userInfo);
  },
  methods: {}
};
</script>

<style lang="scss" scoped></style>
