/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 127.0.0.1:3306
 Source Schema         : infra

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 21/10/2020 17:50:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端id',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源id集合，多个资源时用逗号分隔',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端申请权限范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端支持的grant_type',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重定向url',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT '访问令牌有效时间值(单位:秒)',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT '更新令牌有效时间值(单位:秒)',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户是否自动Approval操作',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('infra-portal', NULL, '$2a$10$By0/goObMIvqv6Lzl1PRsejElV2pdelmxkVeaw0t1u0wN4RwCpmrq', 'all', 'refresh_token,password', 'http://www.baidu.com', NULL, NULL, NULL, NULL, 'false');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '操作用户id',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志描述',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求ip',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `exception` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求异常',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拥有者',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (34, '/api/system/current_user', 'ADMIN', '获取当前登录用户');
INSERT INTO `sys_resource` VALUES (35, '/api/sys_user/info', 'ADMIN,TEST', '获取当前登录用户信息');
INSERT INTO `sys_resource` VALUES (36, '/api/role/async_routes', 'ADMIN,TEST', '根据角色获取角色的动态路由');
INSERT INTO `sys_resource` VALUES (37, '/api/sys_user/info/update', 'ADMIN,TEST', '修改用户个人信息');
INSERT INTO `sys_resource` VALUES (38, '/api/role/list', 'ADMIN', '获取角色列表');
INSERT INTO `sys_resource` VALUES (39, '/api/role/save', 'ADMIN', '添加或修改角色信息');
INSERT INTO `sys_resource` VALUES (40, '/api/role/delete', 'ADMIN', '根据id删除角色');
INSERT INTO `sys_resource` VALUES (41, '/api/role/routers/list', 'ADMIN', '获取路由列表');
INSERT INTO `sys_resource` VALUES (42, '/api/role/permission', 'ADMIN', '根据角色id获取角色权限');
INSERT INTO `sys_resource` VALUES (43, '/api/role/permission/save', 'ADMIN', '分配权限');
INSERT INTO `sys_resource` VALUES (44, '/api/role/routers/save', 'ADMIN', '添加或修改路由');
INSERT INTO `sys_resource` VALUES (45, '/api/role/routers/delete', 'ADMIN', '根据id删除路由');
INSERT INTO `sys_resource` VALUES (46, '/api/role/resource/list', 'ADMIN', '获取资源');
INSERT INTO `sys_resource` VALUES (47, '/api/role/resource/save', ',ADMIN', '添加或修改资源');
INSERT INTO `sys_resource` VALUES (48, '/api/role/resource/delete', 'ADMIN', '根据id删除资源');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '管理员拥有所有权限');
INSERT INTO `sys_role` VALUES (2, 'TEST', '测试角色');

-- ----------------------------
-- Table structure for sys_role_router
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_router`;
CREATE TABLE `sys_role_router`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色id',
  `router_id` int(10) NULL DEFAULT NULL COMMENT '角色路由权限,即菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 156 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色路由关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_router
-- ----------------------------
INSERT INTO `sys_role_router` VALUES (150, 1, 6);
INSERT INTO `sys_role_router` VALUES (151, 1, 7);
INSERT INTO `sys_role_router` VALUES (152, 1, 8);
INSERT INTO `sys_role_router` VALUES (153, 1, 9);
INSERT INTO `sys_role_router` VALUES (154, 1, 23);
INSERT INTO `sys_role_router` VALUES (155, 1, 24);

-- ----------------------------
-- Table structure for sys_router
-- ----------------------------
DROP TABLE IF EXISTS `sys_router`;
CREATE TABLE `sys_router`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由标题',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径,path为关键字',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称，要缓存视图组件，必须设置唯一name',
  `keep_alive` tinyint(1) NULL DEFAULT 0 COMMENT '是否缓存视图组件',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重定向路径',
  `parent_id` int(10) NULL DEFAULT NULL COMMENT '父id,根节点为0',
  `hidden` tinyint(1) NULL DEFAULT 0 COMMENT '是否隐藏(是true,否false)',
  `always_show` tinyint(1) NULL DEFAULT 0 COMMENT '是否显示根菜单（true,false）',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '路由表(其字段根据vue路由编写)，即菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_router
-- ----------------------------
INSERT INTO `sys_router` VALUES (6, '权限管理', 'el-icon-user', '/system', 'System', 0, 'Layout', '/system/role', 0, 0, 0, 10);
INSERT INTO `sys_router` VALUES (7, '角色管理', 'el-icon-help', 'role', 'Role', 1, 'system/role/index', '', 6, 0, 0, 11);
INSERT INTO `sys_router` VALUES (8, '路由管理', 'el-icon-phone-outline', 'router', 'Router', 1, 'system/router/index', '', 6, 0, 0, 12);
INSERT INTO `sys_router` VALUES (9, '用户管理', 'el-icon-eleme', 'user', 'User', 1, 'system/user/index', '', 6, 0, 0, 13);
INSERT INTO `sys_router` VALUES (23, '资源管理', 'el-icon-warning-outline', 'resource', 'Resource', 0, 'system/resource/index', '', 6, 0, 0, 14);
INSERT INTO `sys_router` VALUES (24, NULL, NULL, '*', NULL, 0, NULL, '/404', 0, 1, 0, 9999);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gender` int(1) NULL DEFAULT 0 COMMENT '性别（男1，女2，保密0）',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户基础信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1599526134, '超级管理员', 'aaa', 'https://w.wallhaven.cc/full/39/wallhaven-39dmkd.jpg', '13764599170', '1111@qq.com', 0, '2020-08-31', 'ss', '2020-09-23 14:36:30', '2020-10-12 15:51:06');
INSERT INTO `sys_user` VALUES (1599705554, '测试用户', '是是是', 'https://w.wallhaven.cc/full/dg/wallhaven-dgrgql.jpg', NULL, NULL, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_auth`;
CREATE TABLE `sys_user_auth`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态(开启1，锁定0)',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录地址',
  `online` tinyint(1) NULL DEFAULT 0 COMMENT '是否在线（在线1，不在线0）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户授权表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_auth
-- ----------------------------
INSERT INTO `sys_user_auth` VALUES (1, 1599526134, 'admin', '$2a$10$By0/goObMIvqv6Lzl1PRsejElV2pdelmxkVeaw0t1u0wN4RwCpmrq', 1, NULL, NULL, 0, NULL, NULL);
INSERT INTO `sys_user_auth` VALUES (2, 1599705554, 'test', '$2a$10$By0/goObMIvqv6Lzl1PRsejElV2pdelmxkVeaw0t1u0wN4RwCpmrq', 1, NULL, NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1599526134, 1);
INSERT INTO `sys_user_role` VALUES (2, 1599705554, 2);
INSERT INTO `sys_user_role` VALUES (3, 1599526134, 2);

SET FOREIGN_KEY_CHECKS = 1;
