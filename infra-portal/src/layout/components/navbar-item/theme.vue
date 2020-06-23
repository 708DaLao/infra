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
                    <el-row type="flex" align="middle" justify="space-around" style="font-size: 18px;color: #303133">
                        <el-col :span="10">
                            主题背景色:
                        </el-col>
                        <el-col :span="14">
                            <el-color-picker v-model="color1"></el-color-picker>
                        </el-col>
                    </el-row>


                </el-drawer>
            </slot>
        </el-button>
    </el-tooltip>
</template>

<script>
    export default {
        name: "theme",
        data () {
            return {
                isDrawer: false,
                color1: '#409EFF',
            }
        },
        methods: {
            showDrawer() {
                this.isDrawer = !this.isDrawer
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>