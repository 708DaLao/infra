const getters = {
    isCollapse: state => state.app.isCollapse, // 是否折叠左侧菜单栏
    token: state => state.user.token, // token令牌
    nickname: state => state.user.nickname, // 用户昵称
    avatar: state => state.user.avatar, // 用户头像
    roles: state => state.user.roles,  // 用户角色
    grantType: state => state.setting.grantType, // oauth认证模式
    clientId: state => state.setting.clientId, // 客户端id
    clientSecret: state => state.setting.clientSecret, // 客户端密钥
    permission_routes: state => state.role.routes, // 用户完整的权限路由

}
export default getters
