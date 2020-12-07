<template>
  <el-tooltip
    effect="dark"
    :content="isFullscreen ? '退出全屏' : '全屏'"
    placement="bottom"
  >
    <el-button type="text" @click="screenfull">
      <i v-if="isFullscreen" class="fa fa-compress fa-lg"></i>
      <i v-else class="fa fa-arrows-alt fa-lg"></i>
    </el-button>
  </el-tooltip>
</template>

<script>
// 导入全屏第三方模块
import screenfull from "screenfull";
export default {
  name: "fullscreen",
  data() {
    return {
      // 全屏/不全屏
      isFullscreen: false
    };
  },
  methods: {
    // 全屏模式 将方法绑定(点击事件)到页面的元素上即可
    screenfull() {
      screenfull.toggle();
      this.isFullscreen = !this.isFullscreen;
    },
    // Esc 全屏监测
    checkFull() {
      let isFull =
        document.fullscreenEnabled ||
        window.fullScreen ||
        document.webkitIsFullScreen ||
        document.msFullscreenEnabled;
      if (isFull === undefined) {
        isFull = false;
      }
      return isFull;
    }
  },
  mounted() {
    window.onresize = () => {
      // 全屏下监控是否按键了ESC
      if (!this.checkFull()) {
        // 退出全屏
        this.isFullscreen = false;
      }
    };
  }
};
</script>
