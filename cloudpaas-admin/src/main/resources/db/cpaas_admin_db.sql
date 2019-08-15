/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : cpaas_admin_db

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-08-15 20:29:04
*/

SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '权限管理', '#', 'fa fa-home', '200', null, '-1', '1', '2019-08-13 15:31:51', '1');
INSERT INTO `t_menu` VALUES ('2', '用户管理', '/user/index.html', 'fa fa-tachometer', '100', null, '1', '2', '2019-08-13 15:32:50', '2');
INSERT INTO `t_menu` VALUES ('3', '菜单管理', '/menu/index.html', 'fa fa-tachometer', '200', null, '1', '2', '2019-08-13 15:33:40', '2');
INSERT INTO `t_menu` VALUES ('4', '角色管理', '/role/index.html', 'fa fa-tachometer', '300', null, '1', '2', '2019-08-13 15:34:11', '2');
INSERT INTO `t_menu` VALUES ('5', '系统管理', '#', 'fa fa-snowflake-o', '100', null, '-1', '1', '2019-08-13 15:35:16', '1');
INSERT INTO `t_menu` VALUES ('6', '系统配置', '/menu/index.html', 'fa fa-tachometer', '100', null, '5', '2', '2019-08-13 15:36:13', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'ROLE_SUPER_ADMIN', '超级管理员', '2019-08-14 14:17:50', '拥有全部权限');
INSERT INTO `t_role` VALUES ('2', 'ROLE_USER', '普通用户', '2019-08-14 14:20:11', null);
INSERT INTO `t_role` VALUES ('3', 'ROLE_ADMIN', '普通管理员', '2019-08-14 14:20:40', '只拥有部分权限');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', 'admin', '20', null, '1', '2019-08-13 16:21:21', '1', null);
INSERT INTO `t_user` VALUES ('2', 'zhangsan', '123456', '张三', '18', '138', '2', '2019-08-14 11:51:45', '2', null);
INSERT INTO `t_user` VALUES ('3', 'lishi', '123456', '李四', '19', '139', '1', '2019-08-14 11:52:12', '1', null);
INSERT INTO `t_user` VALUES ('4', 'wangwu', '123456', '王五', '25', null, '0', '2019-08-14 11:52:46', '1', null);
INSERT INTO `t_user` VALUES ('5', 'test', '456789', '测试', '0', null, '0', '2019-08-14 11:53:09', '1', null);
INSERT INTO `t_user` VALUES ('6', 'guest', '123456', '访客', '99', null, '0', '2019-08-14 11:53:39', '1', null);

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
