<template>
    <div>
        <div class="logo">
            <img v-if="!$store.getters.isCollapse" src="../../../assets/img/all.png" alt=""
                 style="height: 100%;width: 200px">
            <img v-else src="../../../assets/img/icon-only.png" alt="" style="height: 100%;width: 65px">
        </div>
        <el-collapse-transition>
            <el-menu
                background-color="#ebf1f6"
                :collapse="$store.getters.isCollapse"
            >
                <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path"></sidebar-item>
            </el-menu>
        </el-collapse-transition>
    </div>
</template>

<script>
    import SidebarItem from "./SidebarItem";
    export default {
        name: "Sidebar",
        components: {SidebarItem},
        data() {
            return {}
        },
        computed: {
            routes() {
                return this.$router.options.routes
            }
        }
    }
</script>

<style lang="scss" scoped>
    .logo {
        width: auto; /*高度固定，宽度变化*/
        height: 60px;
    }

</style>
