const getters = {
    isCollapse: state => state.app.isCollapse, // 是否折叠左侧菜单栏
    token: state => state.user.token, // token令牌
    nickname: state => state.user.nickname, // 用户昵称
    roles: state => state.user.roles  // 用户角色

}
export default getters
