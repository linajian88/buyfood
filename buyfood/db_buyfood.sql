/*
Navicat MySQL Data Transfer

Source Server         : 远程ubuntu
Source Server Version : 50717
Source Host           : 118.89.49.14:3306
Source Database       : db_buyfood

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-04-27 20:54:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for buyfood_buylist
-- ----------------------------
DROP TABLE IF EXISTS `buyfood_buylist`;
CREATE TABLE `buyfood_buylist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `foodID` int(11) NOT NULL,
  `foodNum` int(11) NOT NULL,
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `state` int(11) DEFAULT NULL COMMENT '状态 0是未支付，1是支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of buyfood_buylist
-- ----------------------------
INSERT INTO `buyfood_buylist` VALUES ('56', '7', '6', '1', '1493290719286', '1');
INSERT INTO `buyfood_buylist` VALUES ('58', '27', '6', '1', '1493290967963', '1');
INSERT INTO `buyfood_buylist` VALUES ('59', '7', '6', '1', '1493291225853', '1');
INSERT INTO `buyfood_buylist` VALUES ('60', '27', '6', '1', '1493291826175', '1');
INSERT INTO `buyfood_buylist` VALUES ('61', '27', '5', '1', '1493292902497', '1');
INSERT INTO `buyfood_buylist` VALUES ('62', '27', '6', '1', '1493292906235', '1');

-- ----------------------------
-- Table structure for buyfood_food
-- ----------------------------
DROP TABLE IF EXISTS `buyfood_food`;
CREATE TABLE `buyfood_food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` double(11,2) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `pic` varchar(255) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1',
  `num` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of buyfood_food
-- ----------------------------
INSERT INTO `buyfood_food` VALUES ('37', '零食', '2.00', '用来增肥的水果', '1493293592670', '../image/C80950F7_A946_4A4B_B689_403BA8FF4E1E.jpg', '0', '50');
INSERT INTO `buyfood_food` VALUES ('38', '甜品', '20.00', '用来增肥的水果', '1493293636206', '../image/045E37FF_507E_4CB2_8EEF_1205B1CDAC6A.jpg', '0', '50');
INSERT INTO `buyfood_food` VALUES ('39', '寿司', '20.00', '用来增肥的寿司', '1493293720600', '../image/3C6601B1_A8FB_46BC_9400_47A1B66A60A8.jpg', '0', '40');
INSERT INTO `buyfood_food` VALUES ('40', '寿司', '20.00', '用来增肥的寿司', '1493293730160', '../image/65B92A60_55B3_4BC5_88CE_5B0D53AA5BF6.jpg', '0', '40');
INSERT INTO `buyfood_food` VALUES ('41', '寿司', '20.00', '用来增肥的寿司', '1493293733355', '../image/0B555900_01FF_40F0_BF86_8BD306705280.jpg', '0', '40');
INSERT INTO `buyfood_food` VALUES ('42', '披萨', '20.00', '用来增肥的披萨', '1493293751261', '../image/CCF50F3B_04E7_46B3_9D63_CF1F98C6A19A.jpg', '0', '40');
INSERT INTO `buyfood_food` VALUES ('43', '披萨', '20.00', '用来增肥的披萨', '1493293753815', '../image/59D22AAB_9C14_460E_8C55_6FEF2A62F1EB.jpg', '0', '40');

-- ----------------------------
-- Table structure for buyfood_order
-- ----------------------------
DROP TABLE IF EXISTS `buyfood_order`;
CREATE TABLE `buyfood_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `num` int(11) NOT NULL DEFAULT '0',
  `price` double NOT NULL,
  `orderid` varchar(255) NOT NULL,
  `total` double NOT NULL,
  `foodid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyfood_order
-- ----------------------------
INSERT INTO `buyfood_order` VALUES ('16', '香蕉', '1', '8', '7529f121-81d1-4de1-8c7e-3a361d4b480e', '8', '6');
INSERT INTO `buyfood_order` VALUES ('17', '香蕉', '1', '8', '088065ff-93f4-486e-8f00-31d87bd7e81f', '8', '6');
INSERT INTO `buyfood_order` VALUES ('18', '香蕉', '1', '8', '2b4609c5-8d37-493a-826e-c9a3ef41e583', '8', '6');
INSERT INTO `buyfood_order` VALUES ('19', '香蕉', '1', '8', 'a3709985-23e2-4433-8658-37391cef0f74', '8', '6');
INSERT INTO `buyfood_order` VALUES ('20', '西瓜', '1', '20', 'e55ae9af-dbc1-45ec-9056-8575eb875f29', '20', '5');
INSERT INTO `buyfood_order` VALUES ('21', '香蕉', '1', '8', 'e55ae9af-dbc1-45ec-9056-8575eb875f29', '8', '6');

-- ----------------------------
-- Table structure for buyfood_orderlist
-- ----------------------------
DROP TABLE IF EXISTS `buyfood_orderlist`;
CREATE TABLE `buyfood_orderlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `addtime` bigint(20) NOT NULL DEFAULT '0',
  `stat` int(11) NOT NULL DEFAULT '0',
  `cause` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `usertel` varchar(255) NOT NULL,
  `useraddr` varchar(255) NOT NULL,
  `uid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of buyfood_orderlist
-- ----------------------------
INSERT INTO `buyfood_orderlist` VALUES ('22', '27', '1493291002150', '0', null, 'lnj', '18282829999', '李楠键', '088065ff-93f4-486e-8f00-31d87bd7e81f');
INSERT INTO `buyfood_orderlist` VALUES ('23', '7', '1493291255004', '0', null, 'cxf', '18282829999', 'sada', '2b4609c5-8d37-493a-826e-c9a3ef41e583');
INSERT INTO `buyfood_orderlist` VALUES ('24', '27', '1493291837258', '0', null, 'lnj', '18282829999', '阿斯顿', 'a3709985-23e2-4433-8658-37391cef0f74');
INSERT INTO `buyfood_orderlist` VALUES ('25', '27', '1493292927401', '0', null, 'lnj', '18282829999', '重庆邮电大学', 'e55ae9af-dbc1-45ec-9056-8575eb875f29');

-- ----------------------------
-- Table structure for buyfood_recordaddr
-- ----------------------------
DROP TABLE IF EXISTS `buyfood_recordaddr`;
CREATE TABLE `buyfood_recordaddr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `addr` varchar(255) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of buyfood_recordaddr
-- ----------------------------

-- ----------------------------
-- Table structure for buyfood_user
-- ----------------------------
DROP TABLE IF EXISTS `buyfood_user`;
CREATE TABLE `buyfood_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) NOT NULL,
  `loginpwd` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `usertel` varchar(255) DEFAULT NULL,
  `addtime` bigint(20) DEFAULT NULL,
  `groupid` int(11) DEFAULT '0' COMMENT '0为普通用户1为管理员',
  `idcard` varchar(255) DEFAULT '',
  `pic` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of buyfood_user
-- ----------------------------
INSERT INTO `buyfood_user` VALUES ('28', 'lnj', '3be3870feca6210f218fc86fa9ab6a4c', '管理员', '18', '18282829999', '1493293516106', '1', '513401199708080213', '../image/53B7D36F_4E32_4E42_AF91_A9958CE9E69E.png');
INSERT INTO `buyfood_user` VALUES ('30', 'cxf', '1db5edcf4818e5531ef9229146ec8ec0', '楠键', '18', '18282829999', '1493294029025', '0', '513401199708080213', '../image/53A709DC_D03F_46C0_9335_214F7FA19356.jpg');
