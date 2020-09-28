<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-card class="box-card" shadow="hover">
          <el-avatar src="https://w.wallhaven.cc/full/xl/wallhaven-xlx68l.jpg"></el-avatar>
      </el-card>
    </el-col>
    <el-col :span="18">
      <el-carousel :interval="4000" type="card" height="400px">
        <el-carousel-item v-for="(item, index) in img" :key="index">
          <el-image :src="item" :fit="fill"></el-image>
        </el-carousel-item>
      </el-carousel>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      img: [
        "https://w.wallhaven.cc/full/xl/wallhaven-xlx68l.jpg",
        "https://w.wallhaven.cc/full/5w/wallhaven-5w1w18.jpg",
        "https://w.wallhaven.cc/full/vg/wallhaven-vg7g73.jpg",
        "https://w.wallhaven.cc/full/md/wallhaven-mdxd7k.jpg"
      ]
    };
  }
};
</script>

<style lang="scss" scoped></style>
