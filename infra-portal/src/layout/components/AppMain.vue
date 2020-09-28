<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <router-view :key="key" />
    </transition>
  </section>
</template>

<script>
export default {
  name: "AppMain",
  computed: {
    key() {
      return this.$route.path;
    }
  }
};
</script>

<style scoped>
.app-main {
  /*导航栏高度+主题区域上下内边距 = 100px*/
  min-height: calc(100vh - 100px);
  width: 100%;
  position: relative;
}
</style>
