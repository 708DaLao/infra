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
            </div>
            <div>
                <el-dropdown>
                <span class="el-dropdown-link avatar">
                    <img src="../../assets/vue.png" alt="">
                </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>个人中心</el-dropdown-item>
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
    export default {
        name: "Navbar",
        components: {
            fullscreen,
            theme,
            search
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
        background-color: #ebf1f6;
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
            img{
                width: 40px;
                height: 40px;
                border-radius: 10px;
                overflow: hidden;
                margin: 10px 0;
            }
        }


    }

</style>
