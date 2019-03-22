/*
Navicat MySQL Data Transfer

Source Server         : localMysal
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-03-13 17:37:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `sexly` varchar(10) DEFAULT NULL,
  `interest` varchar(60) DEFAULT NULL,
  `mark` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL COMMENT '性别',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `code` varchar(20) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `inDicSeq` varchar(30) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idindex_userInfo_name` (`username`),
  KEY `inde_name_phone` (`phone`),
  KEY `index_name_gender` (`gender`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '杨浩营', '123', '26', 'boy', 'baeketball', '今天下雨了', '15737313694', '961344691@qq.com', 'b', '2019-01-09 15:37:41', '1', null, null, '1');
INSERT INTO `user_info` VALUES ('2', '张蕾', '0416', '25', 'girl', 'draw', '今天下雨了', '15737310000', 'zs@qq.com', 'g', '2019-02-12 15:37:48', '1', null, null, '2');
INSERT INTO `user_info` VALUES ('3', '杰森斯坦森', '456', '23', '男', null, '你好么!', null, null, 'b', '2019-03-04 15:37:35', '1', null, null, '2');
INSERT INTO `user_info` VALUES ('5', 'James', '23-6', '29', 'man', null, '打铁男神!', null, null, 'b', '2019-02-04 15:37:28', '3', '2018-07-19 17:49:58', null, '1');
INSERT INTO `user_info` VALUES ('6', '科比', '824', '21', 'test', null, '打铁男神MVP!', null, null, null, '2019-02-12 15:37:22', '1', '2019-03-07 15:36:44', null, '2');
INSERT INTO `user_info` VALUES ('8', 'pake', '16', '33', 'man', null, '法国小跑车', null, null, null, '2018-07-19 18:14:23', '1', '2019-03-05 15:36:53', null, '1');
INSERT INTO `user_info` VALUES ('9', '露娜', '16', '18', 'girl', null, '法国跑车', null, 'luna@qq.com', null, '2018-07-23 17:05:56', '1', '2019-03-08 15:36:58', null, '2');
INSERT INTO `user_info` VALUES ('10', 'ldp', '16', '25', 'girl', null, '法国跑车', null, null, null, '2018-07-23 17:08:09', '1', '2019-03-19 15:37:02', null, '1');
INSERT INTO `user_info` VALUES ('12', '李东san', '16', '25', 'boy', null, '法国跑车', null, null, 'g', '2018-07-23 17:35:16', '1', '2019-03-03 15:37:07', null, '2');
INSERT INTO `user_info` VALUES ('14', 'nana', '321', '23', 'g', null, 'puyang', null, null, null, '2018-07-24 17:57:30', '1', '2019-03-06 15:37:12', '1008620180724175730326964', '1');
