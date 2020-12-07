<template>
  <el-tooltip effect="dark" content="主题" placement="bottom">
    <el-button type="text">
      <i class="fa fa-diamond fa-lg" @click="showDrawer"></i>
      <slot>
        <el-drawer
          title="主题"
          :visible.sync="isDrawer"
          direction="rtl"
          size="23%"
        >
          <el-row type="flex" align="middle" justify="space-around">
            <el-col :span="10">
              主题背景色:
            </el-col>
            <el-col :span="14">
              <el-color-picker
                v-model="baseBgColor"
                size="small"
                @change="changeThemeBg"
              ></el-color-picker>
            </el-col>
          </el-row>
        </el-drawer>
      </slot>
    </el-button>
  </el-tooltip>
</template>

<script>
import styles from "@/styles/index.scss";
export default {
  name: "theme",
  data() {
    return {
      isDrawer: false,
      baseBgColor: styles.baseBgColor
    };
  },
  methods: {
    showDrawer() {
      this.isDrawer = !this.isDrawer;
    },
    changeThemeBg(value) {
      // 改变主题背景
      console.log(value);
    }
  }
};
</script>

<style lang="scss" scoped></style>
