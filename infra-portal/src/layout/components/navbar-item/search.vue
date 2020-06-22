<template>
    <el-tooltip effect="dark" content="搜索" placement="bottom">
        <el-button type="text" >
            <i class="fa fa-search fa-lg" @click="showInput"></i>
            <el-input
                v-if="isInput"
                autofocus="true"
                v-model="input"
                placeholder="请输入内容"
                @blur="blur()"
                clearable
            >
            </el-input>
        </el-button>
    </el-tooltip>
</template>

<script>
    export default {
        name: "search",
        data () {
            return {
                isInput: false,
                input: ''
            }
        },
        methods: {
            showInput() {
                this.isInput = !this.isInput
            },
            blur() {
                this.isInput = !this.isInput
                this.input = ''
            }
        }
    }
</script>

<style scoped>

</style>