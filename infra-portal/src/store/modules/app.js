const state = {
    isCollapse: false // 是否折叠侧边栏
}

const mutations = {
    changeCollapse: (state) => {
        state.isCollapse = !state.isCollapse
    }

}

const actions = {

}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}

