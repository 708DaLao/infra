<template>
    <div class="navbar">
        <div class="header-left">
            <div class="sidebar" @click="handelChange">
                <i class="fa fa-bars fa-lg" aria-hidden="true"></i>
            </div>
        </div>
        <div class="header-right">
            <div class="navbar-item">
                <search />
                <fullscreen />
                <theme />
                <news />
            </div>
            <div>
                <el-dropdown>
                    <span class="el-dropdown-link">
                        <el-avatar shape="square" :size="40" fit="cover" :src="url" style="margin: 10px 0"></el-avatar>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>个人中心</el-dropdown-item>
                        <a target="_blank" href="https://gitee.com/dalao708/infra">
                            <el-dropdown-item>码云</el-dropdown-item>
                        </a>
                        <el-dropdown-item divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>

<script>
    import fullscreen from './navbar-item/fullscreen'
    import theme from './navbar-item/theme'
    import search from './navbar-item/search'
    import news from './navbar-item/news'
    export default {
        name: "Navbar",
        components: {
            fullscreen,
            theme,
            search,
            news
        },
        data () {
            return {
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
            }
        },
        methods: {
            handelChange() {
                this.$store.commit('app/changeCollapse')
            }
        }
    }
</script>

<style lang="scss" scoped>
    .navbar{
        width: 100%;
        height: 100%;
        min-width: 900px;
        overflow: hidden;
        /*position: relative;*/
        .header-left{
            float: left;
            display: flex;
            justify-content: center;
            align-items: center;
            .sidebar{
                margin: 0 20px;
                i{
                    color: #606266;
                    &:hover{
                        color: #409eff;
                    }
                }
            }
        }
        .header-right{
            float: right;
            display: flex;
            margin-right: 20px;
            .navbar-item{
                height: 60px;
                margin-right: 30px;
                .el-button{
                    margin-left: 25px;
                }
            }
        }


    }

</style>
