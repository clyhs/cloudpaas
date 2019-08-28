/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.7
Source Server Version : 50724
Source Host           : 192.168.0.7:3306
Source Database       : dn1

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-08-28 11:32:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_gryj`
-- ----------------------------
DROP TABLE IF EXISTS `t_gryj`;
CREATE TABLE `t_gryj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jgdh` varchar(50) DEFAULT NULL,
  `jgmc` varchar(100) DEFAULT NULL,
  `hydh` varchar(50) DEFAULT NULL,
  `hymc` varchar(100) DEFAULT NULL,
  `zbmc` varchar(100) DEFAULT NULL,
  `bz` varchar(50) DEFAULT NULL,
  `sdbs` varchar(50) DEFAULT NULL,
  `zbz` double DEFAULT NULL,
  `zbdw` varchar(20) DEFAULT NULL,
  `khjs` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gryj
-- ----------------------------
