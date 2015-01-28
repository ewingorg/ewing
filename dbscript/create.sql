/*
SQLyog v10.2 
MySQL - 5.1.30-community : Database - ewing
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ewing` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ewing`;

/*Table structure for table `sys_department` */

DROP TABLE IF EXISTS `sys_department`;

CREATE TABLE `sys_department` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `des` varchar(250) DEFAULT NULL,
  `parentid` int(10) DEFAULT NULL COMMENT '上一层的部门构架',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '0:无效,1:有效',
   `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_department` */

LOCK TABLES `sys_department` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_log_trace` */

DROP TABLE IF EXISTS `sys_log_trace`;

CREATE TABLE `sys_log_trace` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `module_name` varchar(200) NOT NULL COMMENT '模块名称',
  `login_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_trace` */

LOCK TABLES `sys_log_trace` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `level` char(1) NOT NULL,
  `parentid` int(10) NOT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '0:无效,1:有效',
  `isleaf` char(1) NOT NULL COMMENT '是否也是 0:不是, 1:是',
  `des` varchar(100) DEFAULT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `icon` varchar(100) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

LOCK TABLES `sys_menu` WRITE;

insert  into `sys_menu`(`id`,`name`,`url`,`level`,`parentid`,`iseff`,`isleaf`,`des`,`createtime`,`last_update`,`icon`,`sort`) values (1,'控制面板',NULL,'1',0,'0','0',NULL,'2014-11-29 23:20:10','2014-11-22 22:45:59','icon-home',1),(2,'菜单管理',NULL,'1',0,'0','0',NULL,'2014-11-29 23:20:28','2014-11-29 22:47:31','icon-cogs',2),(3,'广告管理',NULL,'1',0,'0','0',NULL,'2014-11-29 23:20:32','2014-11-29 22:47:31','icon-bookmark-empty',3),(4,'导航栏','Admin-NavSetting-show.action','2',2,'0','1',NULL,'2014-11-30 22:12:49','2014-11-29 22:56:15',NULL,1),(5,'分类设置','Admin-NavSetting-show.action','2',2,'0','1',NULL,'2014-11-30 23:40:41','2014-11-29 23:46:21',NULL,2);

UNLOCK TABLES;

/*Table structure for table `sys_param` */

DROP TABLE IF EXISTS `sys_param`;

CREATE TABLE `sys_param` (
  `param_code` varchar(50) NOT NULL,
  `param_name` varchar(50) NOT NULL,
  `param_value` varchar(50) DEFAULT NULL,
  `root_code` varchar(50) DEFAULT NULL,
  `des` varchar(250) DEFAULT NULL,
  `seq` varchar(10) DEFAULT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_param` */

LOCK TABLES `sys_param` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_right_rel` */

DROP TABLE IF EXISTS `sys_right_rel`;

CREATE TABLE `sys_right_rel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL COMMENT '关系类型 0:用户 1：角色 2：部门',
  `rel_id` varchar(250) DEFAULT NULL COMMENT '关联对象',
  `menu_id` int(10) unsigned DEFAULT NULL COMMENT '菜单ID',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限菜单关联';

/*Data for the table `sys_right_rel` */

LOCK TABLES `sys_right_rel` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

LOCK TABLES `sys_role` WRITE;

UNLOCK TABLES;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `dep_id` int(10) unsigned DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `sex` char(1) DEFAULT NULL,
  `position` varchar(10) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `addr` varchar(200) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `iseff` char(1) DEFAULT '0',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_id` int(10) unsigned DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
 

 



DROP TABLE IF EXISTS web_group;
CREATE TABLE IF NOT EXISTS `web_group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,  
  `name` VARCHAR(50) NOT NULL COMMENT '组名称',
  `group_key` VARCHAR(50) NOT NULL COMMENT '组key',  
  `type` CHAR(2) NOT NULL COMMENT '链接类型 0:导航分类 1:分类链接 2:产品分类',
  `iseff` CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',  
   `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='组配置';
  
DROP TABLE IF EXISTS web_category;
CREATE TABLE IF NOT EXISTS `web_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT, 
  `parent_id` INT(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `group_key` VARCHAR(50) NOT NULL COMMENT '组key', 
  `name` VARCHAR(50) NOT NULL COMMENT '菜单名称',
  `image_url` VARCHAR(200) NULL COMMENT '菜单图片链接',
  `link_url` VARCHAR(200) NULL COMMENT '外链接',
  `rank` INT(3) NOT NULL DEFAULT '0' COMMENT '排序',  
  `iseff` CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',  
    `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='分类配置';
 

DROP TABLE IF EXISTS web_rel_resource;
CREATE TABLE IF NOT EXISTS `web_rel_resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT, 
  `category_id` INT(11) NOT NULL COMMENT '分类id', 
  `resource_id` INT(11) NOT NULL COMMENT '资源id', 
  `iseff` CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',  
    `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='分类和资源关系表';
 
 
DROP TABLE IF EXISTS web_resource; 
CREATE TABLE IF NOT EXISTS `web_resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT, 
  `name` VARCHAR(50) NOT NULL COMMENT '资源名称',   
  `image_url` VARCHAR(200) NOT NULL COMMENT '菜单图片链接',
  `short_desc` VARCHAR(200) NOT NULL COMMENT '简单描述',
  `long_desc` VARCHAR(1000) NOT NULL COMMENT '详细描述',
  `iseff` CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',  
   `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='资源';
 
  
DROP TABLE IF EXISTS web_resource_attr; 
CREATE TABLE IF NOT EXISTS `web_resource_attr`(
  `id` INT(11) NOT NULL AUTO_INCREMENT, 
  `key` VARCHAR(50) NOT NULL COMMENT '属性key',    
  `value` VARCHAR(500) NOT NULL COMMENT '属性值',
  `rank` INT(3) NOT NULL DEFAULT '0' COMMENT '排序',   
  `resource_id` INT(11) NOT NULL COMMENT '资源id，表web_resource主键', 
  `iseff` CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',  
   `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='资源属性表';


DROP TABLE IF EXISTS web_attr_conf; 
CREATE TABLE IF NOT EXISTS `web_attr_conf`(
  `id` INT(11) NOT NULL AUTO_INCREMENT, 
  `key` VARCHAR(50) NOT NULL COMMENT '属性key',
  `long_desc` VARCHAR(200) NOT NULL COMMENT '描述',
  `iseff` CHAR(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',  
   `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='资源属性表';







