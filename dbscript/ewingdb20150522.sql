/*
SQLyog Professional v10.42 
MySQL - 5.1.30-community : Database - ewing
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`level`,`parentid`,`iseff`,`isleaf`,`des`,`create_time`,`last_update`,`icon`,`sort`) values (1,'控制面板',NULL,'1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:09','icon-dashboard',1),(2,'分组管理','Admin-Group-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:10','icon-list',2),(3,'导航栏','Admin-Nav-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:10','icon-globe',3),(4,'栏目管理','Admin-Banner-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-01-18 16:03:11','icon-bitcoin',4),(5,'资源管理','Admin-Res-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-05-13 17:24:33','icon-archive',5),(6,'模板管理','Admin-Template-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-03-01 23:13:21','icon-file',6),(7,'资源分类管理','Admin-ResCategory-show.action','1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-05-13 17:25:29','icon-archive',7);

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

insert  into `sys_param`(`param_code`,`param_name`,`param_value`,`root_code`,`des`,`seq`,`iseff`,`create_time`,`last_update`) values ('BANNER','栏目','1','GROUP_TYPE',NULL,'1','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('EFF','有效','1','IS_EFF',NULL,'1','1','2014-12-18 17:40:27','2014-12-18 17:40:30'),('NAV','导航栏','0','GROUP_TYPE',NULL,'0','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('NOTEFF','无效','0','IS_EFF',NULL,'0','1','2014-12-18 17:40:24','2014-12-18 17:40:26'),('PHONE_MEMORY_1G','内存1G','1','PHONE_MEMORY',NULL,'1','1','2014-12-18 17:40:24','2014-12-18 17:40:24'),('PHONE_MEMORY_2G','内存2G','2','PHONE_MEMORY',NULL,'2','1','2014-12-18 17:40:24','2014-12-18 17:40:24'),('PRODUCT','产品分类','2','GROUP_TYPE',NULL,'2','1','2014-12-18 17:40:24','2015-01-10 16:37:04');

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

/*Table structure for table `web_attr_conf` */

DROP TABLE IF EXISTS `web_attr_conf`;

CREATE TABLE `web_attr_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` int(11) NOT NULL COMMENT '模板id，web_template的主键',
  `attr_key` varchar(50) NOT NULL COMMENT '属性key',
  `attr_name` varchar(200) NOT NULL COMMENT '属性名称',
  `attr_type` char(1) NOT NULL COMMENT '属性类型 0:text 1:textarea 2:select 3:checkbox 4:radio',
  `param_code` varchar(100) DEFAULT NULL COMMENT '对应表sys_param的系统参数编码',
  `long_desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `sort` int(10) DEFAULT '0',
  `require` char(1) NOT NULL DEFAULT '0' COMMENT '是否必填 0:不必要 1:必要',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='模板资源的属性';

/*Data for the table `web_attr_conf` */

insert  into `web_attr_conf`(`id`,`template_id`,`attr_key`,`attr_name`,`attr_type`,`param_code`,`long_desc`,`sort`,`require`,`iseff`,`create_time`,`last_update`) values (1,3,'price','价格','0',NULL,'价格',0,'1','1','2015-05-20 15:57:33','2015-05-20 18:25:17'),(2,3,'screen_size','屏膜大小','0',NULL,'屏膜大小',1,'1','1','2015-05-20 15:57:33','2015-05-20 18:25:17'),(3,3,'memory','内存','2','PHONE_MEMORY','内存大小',2,'1','1','2015-05-20 15:57:33','2015-05-20 18:25:18');

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

insert  into `web_category`(`id`,`parent_id`,`group_type`,`group_key`,`name`,`image_url`,`link_url`,`rank`,`iseff`,`create_time`,`last_update`,`tempalte`,`short_desc`) values (1,0,'0','SITE_NAV','首页1',NULL,NULL,1,'1','2014-12-17 14:36:20','2015-03-14 20:21:37',NULL,NULL),(2,0,'0','SITE_NAV','产品列表',NULL,NULL,2,'1','2014-12-17 14:36:20','2015-02-20 22:40:35',NULL,NULL),(8,0,'0','SITE_NAV','优惠券',NULL,NULL,3,'1','2015-02-20 21:46:10','2015-02-20 21:47:46',NULL,NULL),(9,0,'0','SITE_NAV','V频道',NULL,NULL,4,'1','2015-02-20 21:52:59','2015-02-20 21:52:59',NULL,NULL),(10,0,'0','SITE_NAV','售后服务',NULL,NULL,5,'1','2015-02-20 21:53:14','2015-02-20 21:53:14',NULL,NULL),(11,0,'0','SITE_NAV','VIP/基金',NULL,NULL,6,'1','2015-02-20 21:54:17','2015-02-20 21:54:17',NULL,NULL),(12,0,'0','SITE_NAV','联通4G',NULL,NULL,7,'1','2015-02-20 21:54:27','2015-02-20 21:54:27',NULL,NULL),(13,0,'0','HELP_NAV','关于我们',NULL,NULL,1,'1','2015-02-20 22:44:44','2015-02-20 22:44:44',NULL,NULL),(14,0,'0','HELP_NAV','公司动态',NULL,NULL,2,'1','2015-02-20 23:09:52','2015-02-20 23:09:52',NULL,NULL),(15,0,'0','HELP_NAV','活动专题',NULL,NULL,3,'1','2015-02-20 23:10:07','2015-02-20 23:10:07',NULL,NULL),(16,0,'0','HELP_NAV','网站公告',NULL,NULL,4,'1','2015-02-20 23:10:28','2015-02-20 23:10:28',NULL,NULL),(17,0,'0','HELP_NAV','联系我们',NULL,NULL,5,'1','2015-02-20 23:16:27','2015-02-20 23:16:27',NULL,NULL),(18,0,'0','HELP_NAV','合作伙伴',NULL,NULL,6,'1','2015-02-20 23:16:50','2015-02-20 23:16:50',NULL,NULL),(19,0,'0','HELP_NAV','友情链接',NULL,NULL,8,'1','2015-02-20 23:17:01','2015-02-20 23:17:01',NULL,NULL),(20,0,'0','HELP_NAV','友情链接',NULL,NULL,9,'1','2015-02-20 23:17:19','2015-02-20 23:17:19',NULL,NULL),(21,0,'0','HELP_NAV','高薪诚聘',NULL,NULL,10,'1','2015-02-20 23:17:34','2015-02-20 23:17:34',NULL,NULL),(22,0,'1','HOT_PHONE_LIST','苹果 iPhone 6','/fileupload/14109388251432274532030.jpg',NULL,2,'1','2015-03-01 21:43:38','2015-05-22 14:02:16',NULL,'¥4680'),(23,0,'1','HOT_PHONE_LIST','苹果 iPhone 6 Plus','/fileupload/14109428841432274633815.jpg',NULL,2,'1','2015-03-01 22:18:10','2015-05-22 14:03:56',NULL,' ¥5200');

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

insert  into `web_group`(`id`,`name`,`group_key`,`group_type`,`iseff`,`create_time`,`last_update`,`type`) values (1,'网站导航栏','SITE_NAV','0','1','2015-01-10 17:32:30','2015-01-10 17:32:30',NULL),(2,'帮助导航','HELP_NAV','0','1','2015-01-10 17:33:31','2015-02-20 22:44:23',NULL),(3,'热门机型','HOT_PHONE_LIST','1','1','2015-03-01 21:41:04','2015-03-01 21:41:18',NULL);

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

insert  into `web_rel_resource`(`id`,`category_id`,`resource_id`,`iseff`,`create_time`,`last_update`,`tempalte`) values (10,7,1,'1','2015-02-05 22:10:49','2015-02-05 22:10:49',NULL);

/*Table structure for table `web_resource` */

DROP TABLE IF EXISTS `web_resource`;

CREATE TABLE `web_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `template_id` int(11) DEFAULT NULL COMMENT '模板id，web_template的主键',
  `category_id` int(11) DEFAULT NULL COMMENT '资源分类id，web_resource_category的主键',
  `tag_id` varchar(100) DEFAULT NULL COMMENT '资源标签id，多个标签以","分隔',
  `image_url` varchar(200) NOT NULL COMMENT '菜单图片链接',
  `short_desc` varchar(200) NOT NULL COMMENT '简单描述',
  `long_desc` longtext NOT NULL COMMENT '详细描述',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `web_resource` */

insert  into `web_resource`(`id`,`name`,`template_id`,`category_id`,`tag_id`,`image_url`,`short_desc`,`long_desc`,`iseff`,`create_time`,`last_update`) values (3,'MacBook2007',3,11,NULL,'/fileupload/785487901432201969673.jpg','最美丽的笔记本','<p>最美丽的笔记本</p>\r\n','1','2015-05-21 18:03:53','2015-05-21 18:03:53');

/*Table structure for table `web_resource_attr` */

DROP TABLE IF EXISTS `web_resource_attr`;

CREATE TABLE `web_resource_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attr_key` varchar(50) NOT NULL COMMENT '属性key',
  `attr_name` varchar(50) NOT NULL COMMENT '属性key',
  `attr_value` varchar(500) NOT NULL COMMENT '属性值',
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='资源属性表';

/*Data for the table `web_resource_attr` */

insert  into `web_resource_attr`(`id`,`attr_key`,`attr_name`,`attr_value`,`rank`,`resource_id`,`iseff`,`create_time`,`last_update`) values (1,'price','价钱','1000',0,1,'1','2015-05-20 17:51:37','2015-05-20 17:51:40'),(2,'price','价格','19999',0,3,'1','2015-05-21 18:04:03','2015-05-21 18:04:03'),(3,'screen_size','屏膜大小','14寸',1,3,'1','2015-05-21 18:04:07','2015-05-21 18:04:07');

/*Table structure for table `web_resource_category` */

DROP TABLE IF EXISTS `web_resource_category`;

CREATE TABLE `web_resource_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `level` char(1) NOT NULL,
  `parentid` int(10) NOT NULL,
  `sort` int(10) DEFAULT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '0:无效,1:有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='资源分类';

/*Data for the table `web_resource_category` */

insert  into `web_resource_category`(`id`,`name`,`level`,`parentid`,`sort`,`iseff`,`create_time`,`last_update`) values (1,'资源分类','0',-1,1,'1','2015-05-13 18:19:34','2015-05-14 17:51:19'),(3,'手机','1',1,1,'1','2015-05-13 18:19:34','2015-05-14 17:51:24'),(4,'苹果','2',3,1,'0','2015-05-13 18:19:34','2015-05-14 17:51:25'),(5,'联想','2',3,2,'1','2015-05-13 18:19:34','2015-05-14 17:51:27'),(6,'无人机','2',8,1,'1','2015-05-13 18:19:34','2015-05-14 17:51:33'),(7,'单反','2',8,2,'1','2015-05-13 18:19:34','2015-05-14 17:51:34'),(8,'数码产品','1',1,2,'1','2015-05-13 18:19:34','2015-05-14 17:51:37'),(9,'电脑','1',1,3,'1','2015-05-14 18:32:43','2015-05-14 18:32:43'),(11,'超级笔记本','2',9,2,'1','2015-05-14 18:33:12','2015-05-15 11:30:14');

/*Table structure for table `web_resource_tag` */

DROP TABLE IF EXISTS `web_resource_tag`;

CREATE TABLE `web_resource_tag` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '0:无效,1:有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源标签';

/*Data for the table `web_resource_tag` */

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
  `template_type` char(1) NOT NULL DEFAULT '0' COMMENT '0:普通页面 1:资源模版',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='模板';

/*Data for the table `web_template` */

insert  into `web_template`(`id`,`name`,`template_path`,`group_keys`,`iseff`,`create_time`,`last_update`,`template_type`) values (2,'首页','/vgooo/index.html','SITE_NAV,HELP_NAV,HOT_PHONE_LIST','1','2015-03-02 21:44:54','2015-05-18 16:09:43','0'),(3,'手机详细页','/vgooo/phonedetail.html',NULL,'1','2015-03-02 21:44:54','2015-05-18 16:09:41','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
