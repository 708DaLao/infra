<template>
    <el-tooltip effect="dark" content="消息" placement="bottom">
        <el-button type="text" >
            <i class="fa fa-bell-o fa-lg" aria-hidden="true"></i>
        </el-button>
    </el-tooltip>
</template>

<script>
    export default {
        name: "news",
        data () {
            return {
                isNew: false
            }
        }
    }
</script>

<style scoped>

</style>