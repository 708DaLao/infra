<template>
    <el-tooltip effect="dark" content="主题" placement="bottom">
        <el-button type="text">
            <i class="fa fa-diamond fa-lg" @click="showDrawer"></i>
            <slot>
                <el-drawer
                    title="主题"
                    :visible.sync="isDrawer"
                    direction="rtl"
                    :with-header="false"
                >
                    <span>我来啦!</span>
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
            }
        },
        methods: {
            showDrawer() {
                this.isDrawer = !this.isDrawer
            }
        }
    }
</script>

<style scoped>

</style>