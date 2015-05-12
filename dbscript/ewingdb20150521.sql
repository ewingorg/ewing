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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

LOCK TABLES `sys_menu` WRITE;

insert  into `sys_menu`(`id`,`name`,`url`,`level`,`parentid`,`iseff`,`isleaf`,`des`,`create_time`,`last_update`,`icon`,`sort`,`createtime`) values (1,'控制面板',NULL,'1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:09','icon-dashboard',1,NULL),(2,'分组管理','Admin-Group-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:10','icon-list',2,NULL),(3,'导航栏','Admin-Nav-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:10','icon-globe',3,NULL),(4,'栏目管理','Admin-Banner-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:11','icon-bitcoin',4,NULL),(5,'资源管理','Admin-Res-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:05:50','icon-archive',5,NULL),(6,'模板管理','Admin-Template-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-03-01 23:13:21','icon-file',6,NULL);

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
  `tempalte` varchar(100) DEFAULT NULL,
  `short_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='分类配置';

/*Data for the table `web_category` */

LOCK TABLES `web_category` WRITE;

insert  into `web_category`(`id`,`parent_id`,`group_type`,`group_key`,`name`,`image_url`,`link_url`,`rank`,`iseff`,`create_time`,`last_update`,`tempalte`,`short_desc`) values (1,0,'0','SITE_NAV','首页1',NULL,NULL,1,'1','2014-12-17 14:36:20','2015-03-14 20:21:37',NULL,NULL),(2,0,'0','SITE_NAV','产品列表',NULL,NULL,2,'1','2014-12-17 14:36:20','2015-02-20 22:40:35',NULL,NULL),(8,0,'0','SITE_NAV','优惠券',NULL,NULL,3,'1','2015-02-20 21:46:10','2015-02-20 21:47:46',NULL,NULL),(9,0,'0','SITE_NAV','V频道',NULL,NULL,4,'1','2015-02-20 21:52:59','2015-02-20 21:52:59',NULL,NULL),(10,0,'0','SITE_NAV','售后服务',NULL,NULL,5,'1','2015-02-20 21:53:14','2015-02-20 21:53:14',NULL,NULL),(11,0,'0','SITE_NAV','VIP/基金',NULL,NULL,6,'1','2015-02-20 21:54:17','2015-02-20 21:54:17',NULL,NULL),(12,0,'0','SITE_NAV','联通4G',NULL,NULL,7,'1','2015-02-20 21:54:27','2015-02-20 21:54:27',NULL,NULL),(13,0,'0','HELP_NAV','关于我们',NULL,NULL,1,'1','2015-02-20 22:44:44','2015-02-20 22:44:44',NULL,NULL),(14,0,'0','HELP_NAV','公司动态',NULL,NULL,2,'1','2015-02-20 23:09:52','2015-02-20 23:09:52',NULL,NULL),(15,0,'0','HELP_NAV','活动专题',NULL,NULL,3,'1','2015-02-20 23:10:07','2015-02-20 23:10:07',NULL,NULL),(16,0,'0','HELP_NAV','网站公告',NULL,NULL,4,'1','2015-02-20 23:10:28','2015-02-20 23:10:28',NULL,NULL),(17,0,'0','HELP_NAV','联系我们',NULL,NULL,5,'1','2015-02-20 23:16:27','2015-02-20 23:16:27',NULL,NULL),(18,0,'0','HELP_NAV','合作伙伴',NULL,NULL,6,'1','2015-02-20 23:16:50','2015-02-20 23:16:50',NULL,NULL),(19,0,'0','HELP_NAV','友情链接',NULL,NULL,8,'1','2015-02-20 23:17:01','2015-02-20 23:17:01',NULL,NULL),(20,0,'0','HELP_NAV','友情链接',NULL,NULL,9,'1','2015-02-20 23:17:19','2015-02-20 23:17:19',NULL,NULL),(21,0,'0','HELP_NAV','高薪诚聘',NULL,NULL,10,'1','2015-02-20 23:17:34','2015-02-20 23:17:34',NULL,NULL),(22,0,'1','HOT_PHONE_LIST','苹果 iPhone 6','/fileupload/14109388251425218610049.jpg',NULL,2,'1','2015-03-01 21:43:38','2015-03-01 22:07:19',NULL,'¥4680'),(23,0,'1','HOT_PHONE_LIST','苹果 iPhone 6 Plus','/fileupload/14109428841425219486942.jpg',NULL,2,'1','2015-03-01 22:18:10','2015-03-01 22:18:10',NULL,' ¥5200');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='组配置';

/*Data for the table `web_group` */

LOCK TABLES `web_group` WRITE;

insert  into `web_group`(`id`,`name`,`group_key`,`group_type`,`iseff`,`create_time`,`last_update`,`type`) values (1,'网站导航栏','SITE_NAV','0','1','2015-01-10 17:32:30','2015-01-10 17:32:30',NULL),(2,'帮助导航','HELP_NAV','0','1','2015-01-10 17:33:31','2015-02-20 22:44:23',NULL),(3,'热门机型','HOT_PHONE_LIST','1','1','2015-03-01 21:41:04','2015-03-01 21:41:18',NULL);

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
  `tempalte` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='分类和资源关系表';

/*Data for the table `web_rel_resource` */

LOCK TABLES `web_rel_resource` WRITE;

insert  into `web_rel_resource`(`id`,`category_id`,`resource_id`,`iseff`,`create_time`,`last_update`,`tempalte`) values (10,7,1,'1','2015-02-05 22:10:49','2015-02-05 22:10:49',NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `web_resource` */

LOCK TABLES `web_resource` WRITE;

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

/*Table structure for table `web_template` */

DROP TABLE IF EXISTS `web_template`;

CREATE TABLE `web_template` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '模板名称',
  `template_path` varchar(200) NOT NULL COMMENT '模板路径',
  `group_keys` varchar(500) DEFAULT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '0:无效,1:有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='模板';

/*Data for the table `web_template` */

LOCK TABLES `web_template` WRITE;

insert  into `web_template`(`id`,`name`,`template_path`,`group_keys`,`iseff`,`create_time`,`last_update`) values (2,'首页','/vgooo/index.html','SITE_NAV,HELP_NAV,HOT_PHONE_LIST','1','2015-03-02 21:44:54','2015-03-02 22:30:28');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
