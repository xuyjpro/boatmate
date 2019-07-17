/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : boatmate

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-05-08 23:07:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_version
-- ----------------------------
DROP TABLE IF EXISTS `app_version`;
CREATE TABLE `app_version` (
  `id` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `version_code` int(2) unsigned NOT NULL,
  `version_name` varchar(10) NOT NULL,
  `download_url` varchar(20) NOT NULL,
  `apk_name` varchar(20) NOT NULL,
  `update_info` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for awesome
-- ----------------------------
DROP TABLE IF EXISTS `awesome`;
CREATE TABLE `awesome` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `dynamic_id` int(4) unsigned NOT NULL,
  `uid` int(4) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dynamic_id` (`dynamic_id`,`uid`),
  KEY `dynamic_id_2` (`dynamic_id`),
  CONSTRAINT `awesome_ibfk_1` FOREIGN KEY (`dynamic_id`) REFERENCES `dynamic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(4) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `summary` tinytext NOT NULL,
  `descrip` varchar(100) NOT NULL,
  `url` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(4) unsigned NOT NULL,
  `time` bigint(8) unsigned NOT NULL,
  `awesome` int(2) unsigned DEFAULT '0',
  `comment` int(2) unsigned DEFAULT '0',
  `content` tinytext NOT NULL,
  `parent_id` int(4) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `parent_id_2` (`parent_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `dynamic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(4) unsigned NOT NULL,
  `awesome` int(2) unsigned DEFAULT '0',
  `comment` int(2) unsigned DEFAULT '0',
  `content` tinytext NOT NULL,
  `time` bigint(8) unsigned NOT NULL,
  `picture` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(4) unsigned NOT NULL,
  `feed_back` tinytext NOT NULL,
  `time` bigint(8) unsigned NOT NULL,
  `status` int(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for school_help
-- ----------------------------
DROP TABLE IF EXISTS `school_help`;
CREATE TABLE `school_help` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(4) unsigned NOT NULL,
  `time` bigint(8) unsigned NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` tinytext NOT NULL,
  `browser` int(4) unsigned DEFAULT '0',
  `bounty` int(4) unsigned DEFAULT '0',
  `status` int(2) unsigned DEFAULT '0',
  `picture` varchar(20) DEFAULT NULL,
  `post_uid` int(4) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stuff
-- ----------------------------
DROP TABLE IF EXISTS `stuff`;
CREATE TABLE `stuff` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(4) unsigned NOT NULL,
  `time` bigint(8) unsigned NOT NULL,
  `content` tinytext NOT NULL,
  `picture1` varchar(20) DEFAULT NULL,
  `picture2` varchar(20) DEFAULT NULL,
  `hot` int(4) unsigned DEFAULT '0',
  `keyword` varchar(20) DEFAULT NULL,
  `category` int(4) unsigned DEFAULT '0',
  `price` float(6,2) DEFAULT '0.00',
  `title` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sub_awesome
-- ----------------------------
DROP TABLE IF EXISTS `sub_awesome`;
CREATE TABLE `sub_awesome` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `comment_id` int(4) unsigned NOT NULL,
  `uid` int(4) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id` (`comment_id`,`uid`),
  KEY `comment_id_2` (`comment_id`),
  CONSTRAINT `sub_awesome_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sub_comment
-- ----------------------------
DROP TABLE IF EXISTS `sub_comment`;
CREATE TABLE `sub_comment` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(4) unsigned NOT NULL,
  `time` bigint(8) unsigned NOT NULL,
  `awesome` int(2) unsigned DEFAULT '0',
  `content` tinytext NOT NULL,
  `parent_id` int(4) unsigned NOT NULL,
  `to_uid` int(4) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `sub_comment_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(12) NOT NULL,
  `nickname` varchar(12) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT '1',
  `birthday` varchar(12) DEFAULT '1997-11-20',
  `heart_word` varchar(40) DEFAULT '快来发布心情吧~',
  `password` varchar(16) NOT NULL,
  `head_pic` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1042 DEFAULT CHARSET=utf8;
DROP TRIGGER IF EXISTS `add_awesome`;
DELIMITER ;;
CREATE TRIGGER `add_awesome` AFTER INSERT ON `awesome` FOR EACH ROW begin
update dynamic set awesome=awesome+1 where NEW.dynamic_id=dynamic.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_awesome`;
DELIMITER ;;
CREATE TRIGGER `del_awesome` AFTER DELETE ON `awesome` FOR EACH ROW begin
update dynamic set awesome=awesome-1 where OLD.dynamic_id=dynamic.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `add_comment`;
DELIMITER ;;
CREATE TRIGGER `add_comment` AFTER INSERT ON `comment` FOR EACH ROW begin

update dynamic set comment=comment+1 where   NEW.parent_id=id;


end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_comment`;
DELIMITER ;;
CREATE TRIGGER `del_comment` AFTER DELETE ON `comment` FOR EACH ROW begin
update dynamic set comment=comment-1 where OLD.parent_id=id;

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `add_sub_awesome`;
DELIMITER ;;
CREATE TRIGGER `add_sub_awesome` AFTER INSERT ON `sub_awesome` FOR EACH ROW begin
update comment set awesome=awesome+1 where NEW.comment_id=comment.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_sub_awesome`;
DELIMITER ;;
CREATE TRIGGER `del_sub_awesome` AFTER DELETE ON `sub_awesome` FOR EACH ROW begin
update comment set awesome=awesome-1 where OLD.comment_id=comment.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `add_sub_comment`;
DELIMITER ;;
CREATE TRIGGER `add_sub_comment` AFTER INSERT ON `sub_comment` FOR EACH ROW begin

update comment set comment=comment+1 where   NEW.parent_id=id;

end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_sub_comment`;
DELIMITER ;;
CREATE TRIGGER `del_sub_comment` AFTER DELETE ON `sub_comment` FOR EACH ROW begin
update comment set comment=comment-1 where OLD.parent_id=id;
end
;;
DELIMITER ;
