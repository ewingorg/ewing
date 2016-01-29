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

/*Table structure for table `job_define` */

DROP TABLE IF EXISTS `job_define`;

CREATE TABLE `job_define` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cluster` varchar(50) NOT NULL COMMENT '集群组名称',
  `name` varchar(50) NOT NULL COMMENT '任务名称',
  `job_cron` varchar(20) NOT NULL COMMENT '任务执行时间',
  `job_class` varchar(100) NOT NULL COMMENT '定义作业类名称',
  `is_enable` int(1) DEFAULT '1' COMMENT '是否生效 0：未生效 1：生效',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务定义表';

/*Data for the table `job_define` */

/*Table structure for table `job_process_log` */

DROP TABLE IF EXISTS `job_process_log`;

CREATE TABLE `job_process_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '任务名称',
  `result` int(1) NOT NULL COMMENT '执行结果 0:开始 1:执行成功 2:执行失败',
  `message` varchar(100) DEFAULT NULL COMMENT '执行结果',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78814 DEFAULT CHARSET=utf8 COMMENT='定时任务执行结果日志';

/*Data for the table `job_process_log` */

/*Table structure for table `job_vote` */

DROP TABLE IF EXISTS `job_vote`;

CREATE TABLE `job_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cluster` varchar(50) NOT NULL COMMENT '集群组名称',
  `node` varchar(50) NOT NULL COMMENT '节点名称',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='定时作业进程投票选举';

/*Data for the table `job_vote` */

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`level`,`parentid`,`iseff`,`isleaf`,`des`,`create_time`,`last_update`,`icon`,`sort`) values (1,'控制面板',NULL,'2',8,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:49:54','icon-dashboard',1),(2,'分组管理','Admin-Group-show.action','2',8,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:49:55','icon-list',2),(3,'导航栏','Admin-Nav-show.action','2',8,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:49:56','icon-globe',3),(4,'栏目管理','Admin-Banner-show.action','2',8,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:49:56','icon-bitcoin',4),(5,'商品管理','Admin-Res-show.action','2',9,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:51:19','icon-archive',5),(6,'模板管理','Admin-Template-show.action','2',8,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:49:57','icon-file',6),(7,'资源分类管理','Admin-ResCategory-show.action','2',8,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:49:59','icon-archive',7),(8,'内容',NULL,'1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:50:51',NULL,1),(9,'商品',NULL,'1',0,'1','0',NULL,'2015-01-10 17:05:35','2015-10-06 21:50:53',NULL,2);

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

insert  into `sys_param`(`param_code`,`param_name`,`param_value`,`root_code`,`des`,`seq`,`iseff`,`create_time`,`last_update`) values ('BANNER','栏目','1','GROUP_TYPE',NULL,'1','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('EFF','有效','1','IS_EFF',NULL,'1','1','2014-12-18 17:40:27','2014-12-18 17:40:30'),('NAV','导航栏','0','GROUP_TYPE',NULL,'0','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('NOTEFF','无效','0','IS_EFF',NULL,'0','1','2014-12-18 17:40:24','2014-12-18 17:40:26'),('PRODUCT','产品分类','2','GROUP_TYPE',NULL,'2','1','2014-12-18 17:40:24','2015-01-10 16:37:04'),('RES_NOTSHOW','不显示','0','RES_SHOW_TYPE',NULL,'2','1','2016-01-18 16:43:01','2016-01-18 16:43:27'),('RES_OFFLINE','下架','0','RES_ONLINE_TYPE',NULL,'2','1','2016-01-18 16:43:59','2016-01-18 16:44:03'),('RES_ONLINE','上架','1','RES_ONLINE_TYPE',NULL,'1','1','2016-01-18 16:43:01','2016-01-18 16:43:46'),('RES_SHOW','显示','1','RES_SHOW_TYPE',NULL,'1','1','2016-01-18 16:43:01','2016-01-18 16:43:05');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`dep_id`,`password`,`sex`,`position`,`phone`,`addr`,`zipcode`,`iseff`,`create_time`,`last_update`,`role_id`,`email`) values (1,'007',NULL,'1',NULL,NULL,NULL,NULL,NULL,'1','2015-06-09 21:29:06','2015-06-08 21:29:09',NULL,NULL);

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '0:无效,1:有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源标签';

/*Data for the table `tag` */

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

/*Table structure for table `web_block` */

DROP TABLE IF EXISTS `web_block`;

CREATE TABLE `web_block` (
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='分类配置';

/*Data for the table `web_block` */

insert  into `web_block`(`id`,`parent_id`,`group_type`,`group_key`,`name`,`image_url`,`link_url`,`rank`,`iseff`,`create_time`,`last_update`,`tempalte`,`short_desc`) values (24,0,'0','WEBSITE_LOGO','网站logo图标','/fileupload/logo1433687744489.jpg','#',1,'1','2015-06-07 22:36:00','2015-06-07 22:36:00',NULL,NULL),(25,0,'0','SITE_NAV','简介',NULL,'#Home',1,'1','2015-06-07 22:52:12','2015-06-10 22:24:25',NULL,NULL),(26,0,'0','SITE_NAV','案例',NULL,'#Portfolio',2,'1','2015-06-07 22:52:41','2015-06-07 23:01:01',NULL,NULL),(27,0,'0','SITE_NAV','明星代言',NULL,'#Services',3,'1','2015-06-07 22:53:00','2015-06-08 23:45:39',NULL,NULL),(28,0,'0','SITE_NAV','热门货品',NULL,'#HotPro',4,'1','2015-06-07 22:53:14','2015-06-08 23:37:38',NULL,NULL),(29,0,'0','SITE_NAV','加盟须知',NULL,'#Join',5,'1','2015-06-07 22:53:30','2015-06-08 23:45:30',NULL,NULL),(30,0,'0','SITE_NAV','关于我们',NULL,'#About',6,'1','2015-06-07 22:53:44','2015-06-08 23:38:03',NULL,NULL),(31,0,'1','INDEX_INTRO','简介页面','/fileupload/home1433692651710.jpg',NULL,1,'1','2015-06-07 23:57:47','2015-06-08 00:07:53',NULL,NULL),(32,0,'1','INDEX_CASE','首页案例图','/fileupload/case1433693350537.jpg',NULL,1,'1','2015-06-08 00:09:18','2015-06-08 00:14:31',NULL,NULL),(33,0,'1','INDEX_STAR','首页明星代言图','/fileupload/start1433694059588.jpg',NULL,1,'1','2015-06-08 00:21:07','2015-06-08 00:21:07',NULL,NULL),(34,0,'1','INDEX_ABOUT','首页关于我们','/fileupload/aboutme1433694278087.jpg',NULL,1,'1','2015-06-08 00:24:45','2015-06-08 00:24:45',NULL,NULL),(35,0,'1','INDEX_HOTPRO','INDEX_HOTPRO','/fileupload/hotpro1433778004623.jpg',NULL,1,'1','2015-06-08 23:40:11','2015-06-08 23:41:08',NULL,NULL),(36,0,'1','INDEX_JOIN','首页加盟须知图片','/fileupload/join1433778667871.jpg',NULL,1,'1','2015-06-08 23:51:12','2015-06-08 23:51:12',NULL,NULL);

/*Table structure for table `web_category` */

DROP TABLE IF EXISTS `web_category`;

CREATE TABLE `web_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(50) NOT NULL,
  `level` char(1) NOT NULL,
  `parentid` int(10) NOT NULL,
  `sort` int(10) DEFAULT NULL,
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '0:无效,1:有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='资源分类';

/*Data for the table `web_category` */

insert  into `web_category`(`id`,`user_id`,`name`,`level`,`parentid`,`sort`,`iseff`,`create_time`,`last_update`) values (1,0,'资源分类','0',-1,1,'1','2015-05-13 18:19:34','2015-05-14 17:51:19'),(3,0,'手机','1',1,1,'1','2015-05-13 18:19:34','2015-05-14 17:51:24'),(4,0,'苹果','2',3,1,'0','2015-05-13 18:19:34','2015-05-14 17:51:25'),(5,0,'联想','2',3,2,'1','2015-05-13 18:19:34','2015-05-14 17:51:27'),(6,0,'无人机','2',8,1,'1','2015-05-13 18:19:34','2015-05-14 17:51:33'),(7,0,'单反','2',8,2,'1','2015-05-13 18:19:34','2015-05-14 17:51:34'),(8,0,'数码产品','1',1,2,'1','2015-05-13 18:19:34','2015-05-14 17:51:37'),(9,0,'电脑','1',1,3,'1','2015-05-14 18:32:43','2015-05-14 18:32:43'),(11,0,'超级笔记本','2',9,2,'1','2015-05-14 18:33:12','2015-05-15 11:30:14');

/*Table structure for table `web_category_conf` */

DROP TABLE IF EXISTS `web_category_conf`;

CREATE TABLE `web_category_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '分类id，表category主键',
  `type` int(11) NOT NULL COMMENT '设置类型 0:参数 1:属性 2:规格',
  `param_code` varchar(50) NOT NULL COMMENT '参数编码',
  `param_name` varchar(50) NOT NULL COMMENT '参数名称',
  `param_value` varchar(50) DEFAULT NULL COMMENT '参数值',
  `root_code` varchar(50) DEFAULT NULL COMMENT '父编码',
  `des` varchar(250) DEFAULT NULL,
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类参数设置';

/*Data for the table `web_category_conf` */

/*Table structure for table `web_category_param` */

DROP TABLE IF EXISTS `web_category_param`;

CREATE TABLE `web_category_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL COMMENT '分类id，表category主键',
  `param_name` varchar(50) NOT NULL COMMENT '参数名称',
  `param_value` varchar(50) DEFAULT NULL COMMENT '参数值',
  `root_param_name` varchar(50) DEFAULT NULL COMMENT '父参数名称',
  `des` varchar(250) DEFAULT NULL,
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_param_name` (`category_id`,`param_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='分类参数';

/*Data for the table `web_category_param` */

insert  into `web_category_param`(`id`,`category_id`,`param_name`,`param_value`,`root_param_name`,`des`,`rank`,`iseff`,`create_time`,`last_update`) values (1,11,'屏膜尺寸',NULL,'屏膜',NULL,1,'1','2015-12-16 18:37:41','2015-12-16 18:37:47'),(2,11,'分辨率',NULL,'屏膜',NULL,2,'1','2015-12-16 18:38:22','2015-12-16 18:38:24'),(3,11,'系统',NULL,'系统参数',NULL,1,'1','2015-12-16 18:38:22','2015-12-16 18:38:46'),(4,11,'CPU',NULL,'系统参数',NULL,2,'1','2015-12-16 18:38:22','2015-12-16 18:39:04'),(5,11,'屏膜',NULL,NULL,NULL,1,'1','2015-12-16 18:39:28','2015-12-16 18:39:32'),(6,11,'系统参数',NULL,NULL,NULL,2,'1','2015-12-16 18:39:28','2015-12-16 18:39:50');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='组配置';

/*Data for the table `web_group` */

insert  into `web_group`(`id`,`name`,`group_key`,`group_type`,`iseff`,`create_time`,`last_update`,`type`) values (1,'网站导航栏','SITE_NAV','0','1','2015-01-10 17:32:30','2015-01-10 17:32:30',NULL),(2,'帮助导航','HELP_NAV','0','1','2015-01-10 17:33:31','2015-02-20 22:44:23',NULL),(4,'网站logo','WEBSITE_LOGO','0','1','2015-06-07 22:34:27','2015-06-07 22:34:27',NULL),(5,'首页简介','INDEX_INTRO','1','1','2015-06-07 23:55:45','2015-06-08 00:08:27',NULL),(6,'首页案例','INDEX_CASE','1','1','2015-06-08 00:08:45','2015-06-08 00:08:45',NULL),(7,'首页明星代言','INDEX_STAR','1','1','2015-06-08 00:20:34','2015-06-08 00:20:34',NULL),(8,'首页关于我们','INDEX_ABOUT','1','1','2015-06-08 00:24:14','2015-06-08 00:24:14',NULL),(9,'首页热门产品','INDEX_HOTPRO','1','1','2015-06-08 23:40:52','2015-06-08 23:40:52',NULL),(10,'首页加盟须知','INDEX_JOIN','1','1','2015-06-08 23:47:59','2015-06-08 23:47:59',NULL),(11,'1','1','0','1','2016-01-15 12:01:32','2016-01-15 12:01:32',NULL);

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
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `template_id` int(11) DEFAULT NULL COMMENT '模板id，web_template的主键',
  `category_id` int(11) DEFAULT NULL COMMENT '资源分类id，category的主键',
  `tag_id` varchar(100) DEFAULT NULL COMMENT '资源标签id，多个标签以","分隔',
  `image_url` varchar(200) NOT NULL COMMENT '菜单图片链接',
  `short_desc` varchar(200) DEFAULT NULL COMMENT '简单描述',
  `long_desc` longtext COMMENT '详细描述',
  `cost` float DEFAULT NULL COMMENT '产品成本价',
  `price` float DEFAULT NULL COMMENT '产品价格',
  `unit` varchar(20) DEFAULT NULL COMMENT '产品单位',
  `weight` varchar(20) DEFAULT NULL COMMENT '产品重量',
  `gift_score` varchar(20) DEFAULT NULL COMMENT '赠送积分',
  `stock_num` int(11) DEFAULT NULL COMMENT '库存',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID',
  `promotion_type` int(11) DEFAULT NULL COMMENT '促销类型 0:满减促销  1:折扣促销  2:返券促销',
  `is_online` int(2) DEFAULT NULL COMMENT '是否上架 0:发布中 1:上架 2:下架',
  `is_hot` int(2) DEFAULT NULL COMMENT '是否热门推荐',
  `is_top` int(2) DEFAULT NULL COMMENT '是否置顶 0:不 1:是',
  `need_carry` int(2) DEFAULT NULL COMMENT '是否需要物流 0:否 1:是',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `search_keyword` varchar(200) DEFAULT NULL COMMENT '搜索关键词',
  `page_title` varchar(200) DEFAULT NULL COMMENT '页面标题',
  `page_keyword` varchar(200) DEFAULT NULL COMMENT '页面关键词',
  `page_desc` varchar(200) DEFAULT NULL COMMENT '页面描述',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_show` int(11) DEFAULT NULL COMMENT 'ÊÇ·ñÁÐ³ö 0:²»ÏÔÊ¾ 1:ÏÔÊ¾',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `web_resource` */

insert  into `web_resource`(`id`,`user_id`,`name`,`template_id`,`category_id`,`tag_id`,`image_url`,`short_desc`,`long_desc`,`cost`,`price`,`unit`,`weight`,`gift_score`,`stock_num`,`brand_id`,`promotion_type`,`is_online`,`is_hot`,`is_top`,`need_carry`,`remark`,`search_keyword`,`page_title`,`page_keyword`,`page_desc`,`iseff`,`create_time`,`last_update`,`is_show`) values (7,0,'222',5,11,NULL,'/fileupload/6975397_IMG_82461_thumb1444572941089.jpg','222','<p>212121</p>\r\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','2015-10-11 22:16:01','2015-10-11 22:16:01',NULL),(8,0,'红蟹王',5,11,NULL,'../images/haixian1.jpg','多肉新鲜红蟹王','<p><img width=\"100%\" src=\"https://img.yzcdn.cn/upload_files/2015/11/23/FvG1TEpsHofNJ21yE8tJW9JarAs-.jpg!730x0.jpg\"> &nbsp;&nbsp;</p>\r\n<p><img width=\"100%\" src=\"https://img.yzcdn.cn/upload_files/2015/12/01/FjyEeFT6yNC3Esqo1IKEm_b05Abj.jpg!730x0.jpg\"></p>\r\n<p style=\"margin-bottom: 16px; white-space: normal;\">挪威鲑鱼生活在冰冷纯净的挪威海域，其养殖环境与野生环境类似。挪威三文鱼：世界上最健康的鱼类之一。</p>\r\n<p style=\"margin-bottom: 16px; white-space: normal;\">挪威鲑鱼富含<span style=\"font-family: Helvetica, sans-serif;\">DHA</span>和<span style=\"font-family: Helvetica, sans-serif;\">EPA</span>这两种<span style=\"font-family: Helvetica, sans-serif;\">omega-3</span>脂肪酸，同时有助于改善大脑的中枢神经系统健康、眼健康、心理健 康、调节情绪、和改善精神状态。</p>\r\n<p style=\"margin-bottom: 16px; white-space: normal;\"><span style=\"font-family: Helvetica, sans-serif;\">omega-3</span>同样有益于心脏和循环系统健康。挪威鲑鱼富含蛋白质，每<span style=\"font-family: Helvetica, sans-serif;\">100</span>克中约含有<span style=\"font-family: Helvetica, sans-serif;\">22.1</span>克。</p>\r\n<p style=\"margin-bottom: 16px; white-space: normal;\">挪威鲑鱼富含维生素<span style=\"font-family: Helvetica, sans-serif;\">A</span>、<span style=\"font-family: Helvetica, sans-serif;\">B1</span>、<span style=\"font-family: Helvetica, sans-serif;\">B2</span>、<span style=\"font-family: Helvetica, sans-serif;\">B3</span>、\r\n<span style=\"font-family: Helvetica, sans-serif;\">B12</span>、维生素<span style=\"font-family: Helvetica, sans-serif;\">D</span>和维生素<span style=\"font-family: Helvetica, sans-serif;\">E</span>，也是钙、铁、锌、镁、磷的优质来源。</p>\r\n<p style=\"margin-bottom: 16px; white-space: normal;\">挪威鲑鱼富含多种对皮肤健康有益的营养元素，多次食用都会显著改善皮肤状况。</p>\r\n<p><img width=\"100%\" src=\"https://img.yzcdn.cn/upload_files/2015/11/23/FubuMokhtYXg2OUMxcOqZUaVU59x.jpg!730x0.jpg\"></p>\r\n<p><img width=\"100%\" src=\"https://img.yzcdn.cn/upload_files/2015/12/02/Fo2wSFquaFQFoFDgGrE7LAJxrjZy.jpg!730x0.jpg\"></p>\r\n<p><img width=\"100%\" src=\"https://img.yzcdn.cn/upload_files/2015/11/27/FkfssanSm7CchJP2mldHwE2XicLy.jpg!730x0.jpg\"><img width=\"100%\" src=\"https://img.yzcdn.cn/upload_files/2015/11/27/FgIoNOeY1c6Kcv4DhYI-yrgbjey8.jpg!730x0.jpg\"></p>\r\n<p><img width=\"100%\" src=\"https://img.yzcdn.cn/upload_files/2015/12/03/FsDeNxzwvrW91HN6wdfrQlaFzdeJ.jpg!730x0.jpg\"></p>',30,30,'1',NULL,NULL,1000,NULL,NULL,1,0,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-10-11 23:17:42','2016-01-27 14:46:56',NULL),(9,0,'老虎虾',5,11,NULL,'../images/haixian2.jpg','马来西亚空运老虎虾',NULL,111,30,'22','1',NULL,1,NULL,NULL,1,0,1,1,NULL,NULL,'1','1','1','1','2015-12-06 21:44:17','2016-01-26 15:24:28',NULL),(10,0,'鱼子',5,7,NULL,'../images/haixian3.jpg','挪威鱼子',NULL,1,40,'1','1',NULL,1,NULL,NULL,1,0,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:45:27','2016-01-26 15:24:32',NULL),(11,0,'生蚝',5,5,NULL,'../images/haixian4.jpg','澳洲生蚝王',NULL,1,30,'1',NULL,NULL,1,NULL,NULL,1,0,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:46:37','2016-01-26 15:24:33',NULL),(12,0,'1',5,5,NULL,'/fileupload/01_m1449409582611.jpg','1',NULL,1,60,'1',NULL,NULL,1,NULL,NULL,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:47:23','2016-01-26 15:24:37',NULL),(13,0,'11',5,11,NULL,'/fileupload/01_m1449409766681.jpg','1',NULL,1,1,'1','1',NULL,1,NULL,NULL,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:49:47','2015-12-06 21:49:47',NULL),(14,0,'1',5,5,NULL,'/fileupload/6975397_dinggai2_thumb1449409918920.jpg','1',NULL,1,1,'1','1',NULL,1,NULL,NULL,1,NULL,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:52:09','2015-12-06 21:52:09',NULL);

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

/*Table structure for table `web_resource_param` */

DROP TABLE IF EXISTS `web_resource_param`;

CREATE TABLE `web_resource_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `category_id` int(11) NOT NULL COMMENT '分类id，表category主键',
  `param_name` varchar(50) NOT NULL COMMENT '参数名称',
  `param_value` varchar(50) DEFAULT NULL COMMENT '参数值',
  `root_param_name` varchar(50) DEFAULT NULL COMMENT '父参数名称',
  `des` varchar(250) DEFAULT NULL,
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_param_name` (`resource_id`,`param_name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='资源参数';

/*Data for the table `web_resource_param` */

insert  into `web_resource_param`(`id`,`resource_id`,`category_id`,`param_name`,`param_value`,`root_param_name`,`des`,`rank`,`iseff`,`create_time`,`last_update`) values (23,7,11,'系统参数',NULL,NULL,NULL,1,'1','2015-12-24 11:15:13','2015-12-24 11:15:13'),(24,7,11,'系统','window系统','系统参数',NULL,2,'1','2015-12-24 11:15:13','2015-12-24 11:15:13'),(25,7,11,'CPU','Intel I5双核','系统参数',NULL,1,'1','2015-12-24 11:15:13','2015-12-24 11:15:13'),(26,7,11,'屏膜',NULL,NULL,NULL,2,'1','2015-12-24 11:15:13','2015-12-24 11:15:13'),(27,7,11,'屏膜尺寸','5.5寸屏','屏膜',NULL,2,'1','2015-12-24 11:15:13','2015-12-24 11:15:13'),(28,7,11,'分辨率','2','屏膜',NULL,1,'1','2015-12-24 11:15:13','2015-12-24 11:15:13');

/*Table structure for table `web_resource_price` */

DROP TABLE IF EXISTS `web_resource_price`;

CREATE TABLE `web_resource_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `spec_ids` varchar(50) NOT NULL COMMENT '规格ID，对应表web_resource_spec,多个以逗号分隔',
  `cost` float DEFAULT '0' COMMENT '产品成本价',
  `price` float DEFAULT '0' COMMENT '产品价格',
  `gift_score` varchar(20) DEFAULT '0' COMMENT '赠送积分',
  `stock_num` int(11) DEFAULT '0' COMMENT '库存',
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='资源价格';

/*Data for the table `web_resource_price` */

insert  into `web_resource_price`(`id`,`resource_id`,`spec_ids`,`cost`,`price`,`gift_score`,`stock_num`,`rank`,`iseff`,`create_time`,`last_update`) values (14,8,'16,19',60,30,NULL,10,1,'1','2016-01-15 15:26:48','2016-01-27 17:01:51'),(18,8,'25,19',70,40,NULL,10,2,'1','2016-01-18 13:56:01','2016-01-27 17:01:51'),(19,8,'20,16',20,50,NULL,10,2,'1','2016-01-27 17:01:52','2016-01-27 17:01:52'),(20,8,'20,25',25,60,NULL,10,3,'1','2016-01-27 17:01:52','2016-01-27 17:01:52');

/*Table structure for table `web_resource_screenshot` */

DROP TABLE IF EXISTS `web_resource_screenshot`;

CREATE TABLE `web_resource_screenshot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `name` varchar(50) NOT NULL COMMENT '主题名称',
  `image_url` varchar(200) NOT NULL COMMENT '菜单图片链接',
  `short_desc` varchar(200) DEFAULT NULL COMMENT '简单描述',
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='资源截图';

/*Data for the table `web_resource_screenshot` */

insert  into `web_resource_screenshot`(`id`,`resource_id`,`name`,`image_url`,`short_desc`,`rank`,`iseff`,`create_time`,`last_update`) values (19,7,'1','/fileupload/6975397_dinggai2_thumb1449411518643.jpg',NULL,1,'1','2015-12-06 22:18:44','2015-12-06 22:18:44');

/*Table structure for table `web_resource_spec` */

DROP TABLE IF EXISTS `web_resource_spec`;

CREATE TABLE `web_resource_spec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `spec` varchar(100) NOT NULL COMMENT '规格名称',
  `root_spec` varchar(100) DEFAULT NULL COMMENT '规格组名称',
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_spec` (`resource_id`,`spec`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='资源规格';

/*Data for the table `web_resource_spec` */

insert  into `web_resource_spec`(`id`,`resource_id`,`spec`,`root_spec`,`rank`,`iseff`,`create_time`,`last_update`) values (16,8,'普通包装','包装',1,'1','2016-01-14 13:45:53','2016-01-27 16:03:56'),(18,8,'包装',NULL,2,'1','2016-01-14 13:45:53','2016-01-27 16:03:44'),(19,8,'澳洲','来源',1,'1','2016-01-14 13:45:53','2016-01-27 16:04:44'),(20,8,'挪威','来源',2,'1','2016-01-14 13:45:53','2016-01-27 16:04:48'),(23,8,'来源',NULL,1,'1','2016-01-18 11:29:22','2016-01-27 16:04:38'),(25,8,'高级包装','包装',2,'1','2016-01-18 13:38:47','2016-01-27 16:04:12');

/*Table structure for table `web_resource_tag` */

DROP TABLE IF EXISTS `web_resource_tag`;

CREATE TABLE `web_resource_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `iseff` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='模板';

/*Data for the table `web_template` */

insert  into `web_template`(`id`,`name`,`template_path`,`group_keys`,`iseff`,`create_time`,`last_update`,`template_type`) values (4,'首页','/d1box/index.html','WEBSITE_LOGO,SITE_NAV,INDEX_INTRO,INDEX_CASE,INDEX_STAR,INDEX_HOTPRO,INDEX_JOIN','1','2015-03-02 21:44:54','2015-06-09 22:44:24','0'),(5,'手机','/vgooo/phone.html',NULL,'1','2015-03-02 21:44:54','2015-10-11 21:36:38','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
