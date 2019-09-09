/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.7
Source Server Version : 50724
Source Host           : 192.168.0.7:3306
Source Database       : cpaas_admin_db

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-09-09 17:12:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_corp`
-- ----------------------------
DROP TABLE IF EXISTS `t_corp`;
CREATE TABLE `t_corp` (
  `id` int(11) NOT NULL,
  `corpname` varchar(200) NOT NULL,
  `corpcode` varchar(100) DEFAULT NULL,
  `corpdbcode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_corp
-- ----------------------------
INSERT INTO `t_corp` VALUES ('1', '基础平台', 'BASE', 'dn0');
INSERT INTO `t_corp` VALUES ('2', '渭城农商行', 'WC', 'dn1');
INSERT INTO `t_corp` VALUES ('3', '东莞银行', 'DG', 'dn2');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `url` varchar(500) NOT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `sort` int(5) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `is_show` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '权限管理', '#', 'fa fa-home', '200', null, '-1', '1', '2019-08-13 15:31:51', '1', '1');
INSERT INTO `t_menu` VALUES ('2', '用户管理', '/user/index.html', 'fa fa-tachometer', '100', null, '1', '2', '2019-08-13 15:32:50', '2', '1');
INSERT INTO `t_menu` VALUES ('3', '菜单管理', '/menu/index.html', 'fa fa-tachometer', '200', null, '1', '2', '2019-08-13 15:33:40', '2', '1');
INSERT INTO `t_menu` VALUES ('4', '角色管理', '/role/index.html', 'fa fa-tachometer', '300', null, '1', '2', '2019-08-13 15:34:11', '2', '1');
INSERT INTO `t_menu` VALUES ('5', '系统管理', '#', 'layui-icon layui-icon-util', '100', null, '-1', '1', '2019-08-13 15:35:16', '1', '1');
INSERT INTO `t_menu` VALUES ('6', '路由配置', '/router/index.html', 'layui-icon layui-icon-set', '100', null, '5', '2', '2019-08-13 15:36:13', '2', '1');
INSERT INTO `t_menu` VALUES ('13', '绩效管理', '#', 'layui-icon layui-icon-note', '300', null, '-1', '1', '2019-08-20 00:00:00', '1', '1');
INSERT INTO `t_menu` VALUES ('14', '个人业绩', '/gryj/index.html', 'layui-icon layui-icon-list', '100', null, '13', '2', '2019-08-20 00:00:00', '2', '1');
INSERT INTO `t_menu` VALUES ('15', '添加', 'add', 'layui-icon layui-icon-add-circle', '100', null, '14', '3', '2019-08-20 00:00:00', '3', '0');
INSERT INTO `t_menu` VALUES ('16', '修改', 'edit', 'layui-icon layui-icon-login-wechat', '200', null, '14', '3', '2019-08-20 00:00:00', '3', '0');
INSERT INTO `t_menu` VALUES ('24', '开发中心', '#', 'layui-icon layui-icon-senior', '400', null, '-1', '1', '2019-09-05 00:00:00', '1', '1');
INSERT INTO `t_menu` VALUES ('25', '测试页面', '/studio/index.html', 'layui-icon layui-icon-component', '100', null, '24', '2', '2019-09-05 00:00:00', '2', '1');

-- ----------------------------
-- Table structure for `t_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `name` varchar(200) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_org
-- ----------------------------

-- ----------------------------
-- Table structure for `t_position`
-- ----------------------------
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `name` varchar(200) NOT NULL,
  `remark` varchar(400) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_position
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `name` varchar(200) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'ROLE_SUPER_ADMIN', '超级管理员', '2019-08-14 14:17:50', '拥有全部权限');
INSERT INTO `t_role` VALUES ('2', 'ROLE_USER', '普通用户', '2019-08-14 14:20:11', null);
INSERT INTO `t_role` VALUES ('3', 'ROLE_ADMIN', '普通管理员', '2019-08-14 14:20:40', '只拥有部分权限');
INSERT INTO `t_role` VALUES ('47', 'ROLE_TEST', '测试', '2019-08-23 17:08:01', 'test');
INSERT INTO `t_role` VALUES ('48', 'ROLE_TEST2', '测试2', '2019-08-23 17:08:15', 'test4');

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `menu_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_router`
-- ----------------------------
DROP TABLE IF EXISTS `t_router`;
CREATE TABLE `t_router` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `router_id` varchar(50) NOT NULL,
  `uri` varchar(100) NOT NULL,
  `router_order` int(11) DEFAULT NULL,
  `path` varchar(50) NOT NULL,
  `strip_prefix` int(2) NOT NULL,
  `enabled` int(1) NOT NULL,
  `limiter_rate` varchar(255) DEFAULT NULL,
  `limiter_capacity` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_router
-- ----------------------------
INSERT INTO `t_router` VALUES ('1', 'cpaas-admin', 'lb://CPAAS-ADMIN', '8000', 'admin', '2', '1', null, null, '2019-09-09 10:23:51');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `corp_id` int(11) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '02371755596ff098ccf339adbd781e52', 'admin', '20', null, '1', '2019-08-13 16:21:21', '1', 'Zzfg7mgx/pC/8Wv8lV7+eQ==');
INSERT INTO `t_user` VALUES ('2', 'zhangsan', '123456', '张三', '18', '138', '2', '2019-08-14 11:51:45', '2', null);
INSERT INTO `t_user` VALUES ('3', 'lishi', '123456', '李四', '19', '139', '1', '2019-08-14 11:52:12', '1', null);
INSERT INTO `t_user` VALUES ('4', 'wangwu', '123456', '王五', '25', null, '0', '2019-08-14 11:52:46', '1', null);
INSERT INTO `t_user` VALUES ('5', 'test', '456789', '测试', '0', null, '0', '2019-08-14 11:53:09', '1', null);
INSERT INTO `t_user` VALUES ('6', 'guest', '123456', '访客', '99', null, '0', '2019-08-14 11:53:39', '1', null);
INSERT INTO `t_user` VALUES ('12', 'wcadmin', '02371755596ff098ccf339adbd781e52', '渭城管理员', '25', '13900000000', '1', '2019-08-21 14:53:52', '2', 'Zzfg7mgx/pC/8Wv8lV7+eQ==');
INSERT INTO `t_user` VALUES ('15', 'dgadmin', '02371755596ff098ccf339adbd781e52', '东莞银行管理员', '19', '13900000000', '2', '2019-08-27 12:01:29', '3', 'Zzfg7mgx/pC/8Wv8lV7+eQ==');

-- ----------------------------
-- Table structure for `t_user_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org` (
  `org_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_org
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_position`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_position`;
CREATE TABLE `t_user_position` (
  `user_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_position
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
