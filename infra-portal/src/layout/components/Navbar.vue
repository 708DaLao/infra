<template>
    <div class="navbar">
        <div class="header-left">
            <div class="sidebar" @click="handelChange">
                <i class="fa fa-bars fa-lg" aria-hidden="true"></i>
            </div>
        </div>
        <div class="header-right">
            sadas
        </div>
    </div>
</template>

<script>
    export default {
        name: "Navbar",
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
        }


    }

</style>
