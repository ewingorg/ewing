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
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `icon` varchar(100) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

LOCK TABLES `sys_menu` WRITE;

insert  into `sys_menu`(`id`,`name`,`url`,`level`,`parentid`,`iseff`,`isleaf`,`des`,`create_time`,`last_update`,`icon`,`sort`,`createtime`) values (1,'控制面板',NULL,'1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:09','icon-dashboard',1,NULL),(2,'分组管理','Admin-Group-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:10','icon-list',2,NULL),(3,'导航栏','Admin-Nav-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:10','icon-globe',3,NULL),(4,'栏目管理','Admin-Banner-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:11','icon-bitcoin',4,NULL),(5,'资源管理','Admin-Res-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:05:50','icon-archive',5,NULL);

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
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_param` */

LOCK TABLES `sys_param` WRITE;

insert  into `sys_param`(`param_code`,`param_name`,`param_value`,`root_code`,`des`,`seq`,`iseff`,`create_time`,`last_update`) values ('BANNER','栏目','1','GROUP_TYPE',NULL,'1','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('EFF','有效','1','IS_EFF',NULL,'1','1','2014-12-18 17:40:27','2014-12-18 17:40:30'),('NAV','导航栏','0','GROUP_TYPE',NULL,'0','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('NOTEFF','无效','0','IS_EFF',NULL,'0','1','2014-12-18 17:40:24','2014-12-18 17:40:26'),('PRODUCT','产品分类','2','GROUP_TYPE',NULL,'2','1','2014-12-18 17:40:24','2015-01-10 16:37:04');

UNLOCK TABLES;

/*Table structure for table `sys_right_rel` */

DROP TABLE IF EXISTS `sys_right_rel`;

CREATE TABLE `sys_right_rel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL COMMENT '关系类型 0:用户 1：角色 2：部门',
  `rel_id` varchar(250) DEFAULT NULL COMMENT '关联对象',
  `menu_id` int(10) unsigned DEFAULT NULL COMMENT '菜单ID',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_id` int(10) unsigned DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

LOCK TABLES `sys_user` WRITE;

UNLOCK TABLES;

/*Table structure for table `web_attr_conf` */

DROP TABLE IF EXISTS `web_attr_conf`;

CREATE TABLE `web_attr_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) NOT NULL COMMENT '属性key',
  `long_desc` varchar(200) NOT NULL COMMENT '描述',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源属性表';

/*Data for the table `web_attr_conf` */

LOCK TABLES `web_attr_conf` WRITE;

UNLOCK TABLES;

/*Table structure for table `web_category` */

DROP TABLE IF EXISTS `web_category`;

CREATE TABLE `web_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `group_type` char(2) NOT NULL COMMENT '0:导航分类 1:分类链接 2:产品分类',
  `group_key` varchar(50) NOT NULL COMMENT '组key',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `image_url` varchar(200) DEFAULT NULL COMMENT '菜单图片链接',
  `link_url` varchar(200) DEFAULT NULL COMMENT '外链接',
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='分类配置';

/*Data for the table `web_category` */

LOCK TABLES `web_category` WRITE;

insert  into `web_category`(`id`,`parent_id`,`group_type`,`group_key`,`name`,`image_url`,`link_url`,`rank`,`iseff`,`create_time`,`last_update`,`type`) values (1,0,'0','SITE_NAV','首页',NULL,NULL,0,'1','2014-12-17 14:36:20','2015-01-12 17:37:09',NULL),(2,0,'0','SITE_NAV','产品列表',NULL,NULL,1,'1','2014-12-17 14:36:20','2015-01-12 17:37:10',NULL),(3,0,'0','SITE_NAV','投书建议',NULL,NULL,2,'1','2014-12-17 14:36:20','2015-01-12 17:37:11',NULL),(4,0,'0','SITE_NAV','关于我们',NULL,NULL,3,'0','2014-12-17 14:36:20','2015-01-12 17:37:12',NULL),(5,0,'0','SITE_NAV','2121','11','1',1,'1','2014-12-18 22:55:57','2015-01-12 17:37:14',NULL),(7,0,'1','HELP_BANNER','公司历史',NULL,NULL,12,'1','2015-01-12 17:32:30','2015-01-12 17:32:30',NULL);

UNLOCK TABLES;

/*Table structure for table `web_group` */

DROP TABLE IF EXISTS `web_group`;

CREATE TABLE `web_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '组名称',
  `group_key` varchar(50) NOT NULL COMMENT '组key',
  `group_type` char(2) NOT NULL COMMENT '链接类型 0:导航分类 1:分类链接 2:产品分类',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='组配置';

/*Data for the table `web_group` */

LOCK TABLES `web_group` WRITE;

insert  into `web_group`(`id`,`name`,`group_key`,`group_type`,`iseff`,`create_time`,`last_update`,`type`) values (1,'网站导航栏','SITE_NAV','0','1','2015-01-10 17:32:30','2015-01-10 17:32:30',NULL),(2,'帮助栏目','HELP_BANNER','1','1','2015-01-10 17:33:31','2015-01-10 17:33:31',NULL);

UNLOCK TABLES;

/*Table structure for table `web_rel_resource` */

DROP TABLE IF EXISTS `web_rel_resource`;

CREATE TABLE `web_rel_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类和资源关系表';

/*Data for the table `web_rel_resource` */

LOCK TABLES `web_rel_resource` WRITE;

UNLOCK TABLES;

/*Table structure for table `web_resource` */

DROP TABLE IF EXISTS `web_resource`;

CREATE TABLE `web_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `image_url` varchar(200) NOT NULL COMMENT '菜单图片链接',
  `short_desc` varchar(200) NOT NULL COMMENT '简单描述',
  `long_desc` longtext NOT NULL COMMENT '详细描述',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `web_resource` */

LOCK TABLES `web_resource` WRITE;

insert  into `web_resource`(`id`,`name`,`image_url`,`short_desc`,`long_desc`,`iseff`,`create_time`,`last_update`) values (1,'1','/fileupload/91421591868087.jpg','vvvvvvvvvvzzzzzzzzz','<p>ddddvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv</p>\r\n','1','2015-01-18 21:42:42','2015-01-18 22:38:09'),(2,'1','/fileupload/avatar11421591522067.jpg','你好啊','<p>我是神人么？？？</p>\r\n','1','2015-01-18 22:33:19','2015-01-18 22:50:11');

UNLOCK TABLES;

/*Table structure for table `web_resource_attr` */

DROP TABLE IF EXISTS `web_resource_attr`;

CREATE TABLE `web_resource_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) NOT NULL COMMENT '属性key',
  `value` varchar(500) NOT NULL COMMENT '属性值',
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源属性表';

/*Data for the table `web_resource_attr` */

LOCK TABLES `web_resource_attr` WRITE;

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
