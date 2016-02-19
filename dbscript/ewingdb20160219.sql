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

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '消费用户名称',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `head_icon` varchar(300) DEFAULT NULL COMMENT '头像',
  `phone` varchar(50) NOT NULL DEFAULT '0' COMMENT '电话号码',
  `sex` char(1) NOT NULL DEFAULT '0' COMMENT '性别 0:男 1:女',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='消费者';

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`password`,`head_icon`,`phone`,`sex`,`iseff`,`create_time`,`last_update`) values (8,'test nan','1111',NULL,'0','0','1','2016-02-16 16:40:37','2016-02-16 16:40:39');

/*Table structure for table `customer_address` */

DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `customer_id` int(11) NOT NULL COMMENT '消费者ID',
  `receiver` varchar(10) DEFAULT NULL COMMENT '收件人',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `region` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '具体位置',
  `phone` varchar(50) NOT NULL DEFAULT '0' COMMENT '电话号码',
  `post_code` varchar(15) DEFAULT NULL COMMENT '邮编',
  `is_default` char(1) DEFAULT '0' COMMENT '是否默认 0 不是 1 是',
  `iseff` char(1) DEFAULT NULL COMMENT '是否有效 0 无效 1有效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='消费者收获地址';

/*Data for the table `customer_address` */

insert  into `customer_address`(`id`,`customer_id`,`receiver`,`province`,`city`,`region`,`address`,`phone`,`post_code`,`is_default`,`iseff`,`create_time`,`last_update`) values (1,10,'Joeson','广东省','广州','天河区','潭村','18826416772',NULL,NULL,NULL,'2016-02-02 17:24:54','2016-02-03 19:35:01'),(2,10,'Tom','广东省','广州','天河区','潭村','18826416770',NULL,NULL,NULL,'2016-02-02 17:42:45','2016-02-03 19:35:04'),(3,10,'Jannic','广东省','广州','天河区','潭村','18826416771',NULL,NULL,NULL,'2016-02-02 17:42:58','2016-02-03 19:35:08'),(4,10,'Kitty','广东省','广州','天河区','潭村','18826416772',NULL,'1',NULL,'2016-02-02 17:43:07','2016-02-04 17:15:34');

/*Table structure for table `customer_third_account` */

DROP TABLE IF EXISTS `customer_third_account`;

CREATE TABLE `customer_third_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '消费者ID',
  `third_platform` int(11) NOT NULL COMMENT '第三方账号接入平台(1-微信,2-淘宝)',
  `user_id` varchar(100) NOT NULL COMMENT '第三方账号ID',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '第三方账号昵称',
  `head_icon` varchar(300) DEFAULT NULL COMMENT '头像',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_third_part_account_id` (`customer_id`,`third_platform`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方登录账号表';

/*Data for the table `customer_third_account` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务执行结果日志';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时作业进程投票选举';

/*Data for the table `job_vote` */

/*Table structure for table `order_cart` */

DROP TABLE IF EXISTS `order_cart`;

CREATE TABLE `order_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '消费者ID',
  `user_id` int(11) NOT NULL COMMENT '商户用户ID',
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `item_count` int(11) NOT NULL DEFAULT '0' COMMENT '件数',
  `unit_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '每件单价',
  `total_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '总价格',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

/*Data for the table `order_cart` */

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `customer_id` int(11) NOT NULL COMMENT '消费者ID',
  `biz_id` varchar(50) NOT NULL COMMENT '流水号',
  `user_id` int(11) NOT NULL COMMENT '商户用户ID',
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `item_count` int(11) NOT NULL DEFAULT '0' COMMENT '产品数量',
  `unit_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '产品单价',
  `cargo_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '运费',
  `total_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '总费用',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '购物状态 0:待付款 1:待发货 2:待收货',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='订单明细';

/*Data for the table `order_detail` */

insert  into `order_detail`(`id`,`order_id`,`customer_id`,`biz_id`,`user_id`,`resource_id`,`item_count`,`unit_price`,`cargo_price`,`total_price`,`status`,`iseff`,`create_time`,`last_update`) values (1,1,8,'111',10,7,2,20.00,0.00,40.00,'0','1','2016-02-16 15:39:24','2016-02-19 10:24:48'),(2,1,8,'111',10,8,3,10.00,0.00,30.00,'0','1','2016-02-16 15:39:24','2016-02-19 10:11:57'),(3,2,8,'111',10,7,1,80.00,0.00,80.00,'0','1','2016-02-16 15:39:24','2016-02-19 10:25:02');

/*Table structure for table `order_detail_view` */

DROP TABLE IF EXISTS `order_detail_view`;

CREATE TABLE `order_detail_view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `biz_id` varchar(255) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `item_count` int(11) NOT NULL,
  `unit_price` float NOT NULL,
  `cargo_price` float NOT NULL,
  `total_price` float NOT NULL,
  `status` char(1) NOT NULL,
  `iseff` char(1) NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_detail_view` */

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '消费者ID',
  `user_id` int(11) NOT NULL COMMENT '商户用户ID',
  `biz_id` varchar(50) NOT NULL COMMENT '流水号',
  `product_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '产品费用',
  `cargo_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '运费',
  `total_price` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '总费用',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '订单状态 0:待付款 1:待发货 2:待收货 3:退款中 4:已完成 5:已关闭',
  `receiver` varchar(20) DEFAULT NULL COMMENT '收件人',
  `post_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `region` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '具体位置',
  `phone` varchar(50) NOT NULL DEFAULT '0' COMMENT '电话号码',
  `cargo_name` varchar(100) DEFAULT NULL COMMENT '物流公司名称',
  `cargo_number` varchar(100) DEFAULT NULL COMMENT '物流编号',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单';

/*Data for the table `order_info` */

insert  into `order_info`(`id`,`customer_id`,`user_id`,`biz_id`,`product_price`,`cargo_price`,`total_price`,`status`,`receiver`,`post_code`,`province`,`city`,`region`,`address`,`phone`,`cargo_name`,`cargo_number`,`iseff`,`create_time`,`last_update`) values (1,8,10,'NO111121',0.00,0.00,100.11,'0','林生',NULL,'广东省','广州','海珠区','赤岗','13826031714',NULL,NULL,'1','2016-02-16 15:39:11','2016-02-18 18:17:23'),(2,8,10,'NO222222',0.00,0.00,100.00,'0','黄生',NULL,'广东省','广州','荔湾区','芳村','13826031714',NULL,NULL,'1','2016-02-14 15:39:11','2016-02-19 14:56:30');

/*Table structure for table `order_refund` */

DROP TABLE IF EXISTS `order_refund`;

CREATE TABLE `order_refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单ID,表order主键',
  `order_detail_id` int(11) NOT NULL COMMENT '订单明细ID,表order_detail主键',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '退款类型 0:退货退款 1:仅退款',
  `customer_id` int(11) NOT NULL COMMENT '消费者ID',
  `user_id` int(11) NOT NULL COMMENT '商户用户ID',
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `reason_type` int(11) NOT NULL DEFAULT '0' COMMENT '退货原因',
  `reason` int(11) NOT NULL DEFAULT '0' COMMENT '退货说明',
  `cargo_number` int(11) DEFAULT NULL COMMENT '物流号',
  `refund_money` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '退款金额',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '退款申请状态 0:发起退款 1:待商户确认 2:商户已经收货 3:已经退款 4:用户取消',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单退款';

/*Data for the table `order_refund` */

/*Table structure for table `seller` */

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `sex` char(1) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `addr` varchar(200) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `iseff` char(1) DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dep_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商家';

/*Data for the table `seller` */

insert  into `seller`(`id`,`user_name`,`password`,`sex`,`phone`,`addr`,`zipcode`,`email`,`iseff`,`create_time`,`last_update`,`dep_id`) values (10,'tanson','111111','1',NULL,NULL,NULL,NULL,'1','2016-02-16 13:41:43','2016-02-16 13:41:47',NULL);

/*Table structure for table `seller_shop` */

DROP TABLE IF EXISTS `seller_shop`;

CREATE TABLE `seller_shop` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL COMMENT '商店的创建者ID',
  `shop_name` varchar(200) NOT NULL COMMENT '商店名称',
  `shop_icon` varchar(200) NOT NULL COMMENT '商店名称',
  `shop_desc` varchar(200) NOT NULL COMMENT '商店描述',
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `addr` varchar(200) DEFAULT NULL,
  `iseff` char(1) DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家店铺';

/*Data for the table `seller_shop` */

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`level`,`parentid`,`iseff`,`isleaf`,`des`,`create_time`,`last_update`,`icon`,`sort`) values (1,'控制面板',NULL,'2',10,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:25:54','icon-dashboard',1),(2,'分组管理','Admin-Group-show.action','2',10,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:25:48','icon-list',2),(3,'导航栏','Admin-Nav-show.action','2',10,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:25:47','icon-globe',3),(4,'栏目管理','Admin-Banner-show.action','2',10,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:25:46','icon-bitcoin',4),(5,'商品管理','Admin-Res-show.action','2',9,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:26:45','icon-archive',5),(6,'模板管理','Admin-Template-show.action','2',10,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:25:44','icon-file',6),(7,'商品分类','Admin-ResCategory-show.action','2',9,'1','0',NULL,'2015-01-10 17:05:35','2016-02-04 09:44:01','icon-archive',7),(9,'商品',NULL,'1',0,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:26:10',NULL,1),(10,'内容',NULL,'1',0,'1','0',NULL,'2015-01-10 17:05:35','2016-02-03 18:26:04',NULL,2),(11,'订单','Admin-Order-show.action','2',9,'1','0',NULL,'2015-01-10 17:05:35','2016-02-18 16:34:30','icon-archive',3);

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

insert  into `sys_param`(`param_code`,`param_name`,`param_value`,`root_code`,`des`,`seq`,`iseff`,`create_time`,`last_update`) values ('BANNER','栏目','1','GROUP_TYPE',NULL,'1','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('EFF','有效','1','IS_EFF',NULL,'1','1','2014-12-18 17:40:27','2014-12-18 17:40:30'),('NAV','导航栏','0','GROUP_TYPE',NULL,'0','1','2014-12-18 17:40:24','2015-01-10 16:37:02'),('NEED','需要','1','NEED_TYPE',NULL,'1','1','2016-01-18 16:43:01','2016-01-18 16:43:01'),('NOTEFF','无效','0','IS_EFF',NULL,'0','1','2014-12-18 17:40:24','2014-12-18 17:40:26'),('NOTNEED','不需要','0','NEED_TYPE',NULL,'0','1','2016-01-18 16:43:01','2016-01-18 16:43:01'),('PRODUCT','产品分类','2','GROUP_TYPE',NULL,'2','1','2014-12-18 17:40:24','2015-01-10 16:37:04'),('RESHOT_TYPE_COMMON','普通','0','RES_HOT_TYPE',NULL,'0','1','2016-01-18 16:43:01','2016-02-03 17:09:48'),('RESHOT_TYPE_HOT','推荐','1','RES_HOT_TYPE',NULL,'1','1','2016-01-18 16:43:01','2016-02-03 17:09:54'),('RES_OFFLINE','下架','2','RES_ONLINE_TYPE',NULL,'2','1','2016-01-18 16:43:59','2016-02-03 17:01:23'),('RES_ONLINE','上架','1','RES_ONLINE_TYPE',NULL,'1','1','2016-01-18 16:43:01','2016-01-18 16:43:46'),('RES_PUBLISH','发布中','0','RES_ONLINE_TYPE',NULL,'0','1','2016-01-18 16:43:01','2016-01-18 16:43:01');

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

insert  into `web_block`(`id`,`parent_id`,`group_type`,`group_key`,`name`,`image_url`,`link_url`,`rank`,`iseff`,`create_time`,`last_update`,`tempalte`,`short_desc`) values (24,0,'0','WEBSITE_LOGO','网站logo图标','/fileupload/logo1433687744489.jpg','#',1,'1','2015-06-07 22:36:00','2016-02-04 13:30:37',NULL,NULL),(25,0,'0','SITE_NAV','简介',NULL,'#Home',1,'1','2015-06-07 22:52:12','2015-06-10 22:24:25',NULL,NULL),(26,0,'0','SITE_NAV','案例',NULL,'#Portfolio',2,'1','2015-06-07 22:52:41','2015-06-07 23:01:01',NULL,NULL),(27,0,'0','SITE_NAV','明星代言',NULL,'#Services',3,'1','2015-06-07 22:53:00','2015-06-08 23:45:39',NULL,NULL),(28,0,'0','SITE_NAV','热门货品',NULL,'#HotPro',4,'1','2015-06-07 22:53:14','2015-06-08 23:37:38',NULL,NULL),(29,0,'0','SITE_NAV','加盟须知',NULL,'#Join',5,'1','2015-06-07 22:53:30','2015-06-08 23:45:30',NULL,NULL),(30,0,'0','SITE_NAV','关于我们',NULL,'#About',6,'1','2015-06-07 22:53:44','2015-06-08 23:38:03',NULL,NULL),(31,0,'1','INDEX_INTRO','简介页面','/fileupload/home1433692651710.jpg',NULL,1,'1','2015-06-07 23:57:47','2015-06-08 00:07:53',NULL,NULL),(32,0,'1','INDEX_CASE','首页案例图','/fileupload/case1433693350537.jpg',NULL,1,'1','2015-06-08 00:09:18','2015-06-08 00:14:31',NULL,NULL),(33,0,'1','INDEX_STAR','首页明星代言图','/fileupload/start1433694059588.jpg',NULL,1,'1','2015-06-08 00:21:07','2015-06-08 00:21:07',NULL,NULL),(34,0,'1','INDEX_ABOUT','首页关于我们','/fileupload/aboutme1433694278087.jpg',NULL,1,'1','2015-06-08 00:24:45','2015-06-08 00:24:45',NULL,NULL),(35,0,'1','INDEX_HOTPRO','INDEX_HOTPRO','/fileupload/hotpro1433778004623.jpg',NULL,1,'1','2015-06-08 23:40:11','2015-06-08 23:41:08',NULL,NULL),(36,0,'1','INDEX_JOIN','首页加盟须知图片','/fileupload/join1433778667871.jpg',NULL,1,'1','2015-06-08 23:51:12','2015-06-08 23:51:12',NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='资源分类';

/*Data for the table `web_category` */

insert  into `web_category`(`id`,`user_id`,`name`,`level`,`parentid`,`sort`,`iseff`,`create_time`,`last_update`) values (1,10,'资源分类','0',-1,1,'1','2015-05-13 18:19:34','2016-02-16 13:41:00'),(4,10,'苹果','2',3,1,'0','2015-05-13 18:19:34','2016-02-16 13:41:00'),(12,10,'虾类','1',1,1,'1','2016-02-04 09:47:06','2016-02-16 13:41:00'),(13,10,'贝类','1',1,2,'1','2016-02-04 09:56:31','2016-02-16 13:41:00'),(14,10,'背壳','1',1,3,'1','2016-02-04 09:57:02','2016-02-16 13:41:00'),(15,10,'鱼类','1',1,4,'1','2016-02-04 09:57:19','2016-02-16 13:41:00');

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
  `cost` float(9,2) DEFAULT NULL COMMENT '产品成本价',
  `price` float(9,2) DEFAULT NULL COMMENT '产品价格',
  `unit` varchar(20) DEFAULT NULL COMMENT '产品单位',
  `weight` varchar(20) DEFAULT NULL COMMENT '产品重量',
  `gift_score` varchar(20) DEFAULT NULL COMMENT '赠送积分',
  `stock_num` int(11) DEFAULT NULL COMMENT '库存',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID',
  `promotion_type` int(11) DEFAULT NULL COMMENT '促销类型 0:满减促销  1:折扣促销  2:返券促销',
  `is_online` int(2) DEFAULT NULL COMMENT '是否上架 0:发布中 1:上架 2:下架',
  `is_hot` int(2) DEFAULT NULL COMMENT '是否热门推荐',
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
  `is_top` int(11) DEFAULT NULL COMMENT 'ÊÇ·ñÖÃ¶¥ 0:²» 1:ÊÇ',
  `is_host` int(11) DEFAULT NULL COMMENT 'ÊÇ·ñÁÐ³ö 0:²»ÏÔÊ¾ 1:ÏÔÊ¾',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `web_resource` */

insert  into `web_resource`(`id`,`user_id`,`name`,`template_id`,`category_id`,`tag_id`,`image_url`,`short_desc`,`long_desc`,`cost`,`price`,`unit`,`weight`,`gift_score`,`stock_num`,`brand_id`,`promotion_type`,`is_online`,`is_hot`,`need_carry`,`remark`,`search_keyword`,`page_title`,`page_keyword`,`page_desc`,`iseff`,`create_time`,`last_update`,`is_show`,`is_top`,`is_host`) values (7,10,'红蟹王',NULL,13,NULL,'/fileupload/Fg_cNErXDhMCDcEprN51ZiBGSLjV1455791428609.gif','222','<p><img src=\"https://img.yzcdn.cn/upload_files/2015/11/23/FvG1TEpsHofNJ21yE8tJW9JarAs-.jpg!730x0.jpg\" style=\"width:100%\" /> &nbsp;&nbsp;</p>\r\n\r\n<p><img src=\"https://img.yzcdn.cn/upload_files/2015/12/01/FjyEeFT6yNC3Esqo1IKEm_b05Abj.jpg!730x0.jpg\" style=\"width:100%\" /></p>\r\n\r\n<p>????????????????????????????????????????????????</p>\r\n\r\n<p>??????<span style=\"font-family:helvetica,sans-serif\">DHA</span>?<span style=\"font-family:helvetica,sans-serif\">EPA</span>???<span style=\"font-family:helvetica,sans-serif\">omega-3</span>?????????????????????????????? ???????????????</p>\r\n\r\n<p><span style=\"font-family:helvetica,sans-serif\">omega-3</span>??????????????????????????<span style=\"font-family:helvetica,sans-serif\">100</span>?????<span style=\"font-family:helvetica,sans-serif\">22.1</span>??</p>\r\n\r\n<p>?????????<span style=\"font-family:helvetica,sans-serif\">A</span>?<span style=\"font-family:helvetica,sans-serif\">B1</span>?<span style=\"font-family:helvetica,sans-serif\">B2</span>?<span style=\"font-family:helvetica,sans-serif\">B3</span>? <span style=\"font-family:helvetica,sans-serif\">B12</span>????<span style=\"font-family:helvetica,sans-serif\">D</span>????<span style=\"font-family:helvetica,sans-serif\">E</span>??????????????????</p>\r\n\r\n<p>????????????????????????????????????</p>\r\n\r\n<p><img src=\"https://img.yzcdn.cn/upload_files/2015/11/23/FubuMokhtYXg2OUMxcOqZUaVU59x.jpg!730x0.jpg\" style=\"width:100%\" /></p>\r\n\r\n<p><img src=\"https://img.yzcdn.cn/upload_files/2015/12/02/Fo2wSFquaFQFoFDgGrE7LAJxrjZy.jpg!730x0.jpg\" style=\"width:100%\" /></p>\r\n\r\n<p><img src=\"https://img.yzcdn.cn/upload_files/2015/11/27/FkfssanSm7CchJP2mldHwE2XicLy.jpg!730x0.jpg\" style=\"width:100%\" /><img src=\"https://img.yzcdn.cn/upload_files/2015/11/27/FgIoNOeY1c6Kcv4DhYI-yrgbjey8.jpg!730x0.jpg\" style=\"width:100%\" /></p>\r\n\r\n<p><img src=\"https://img.yzcdn.cn/upload_files/2015/12/03/FsDeNxzwvrW91HN6wdfrQlaFzdeJ.jpg!730x0.jpg\" style=\"width:100%\" /></p>\r\n',50.00,30.00,'1',NULL,NULL,10,NULL,NULL,0,0,1,NULL,NULL,NULL,NULL,NULL,'1','2015-10-11 22:16:01','2016-02-19 14:26:46',NULL,NULL,NULL),(8,10,'红蟹王',NULL,13,NULL,'/fileupload/Flf7f_-r8pa91RP4ne6b-0ac_ub-1455791446659.gif','多肉新鲜红蟹王',NULL,11.00,11111.08,'1',NULL,NULL,10,NULL,NULL,1,0,1,NULL,NULL,NULL,NULL,NULL,'1','2015-10-11 23:17:42','2016-02-18 18:30:48',NULL,NULL,NULL),(9,10,'老虎虾',5,11,NULL,'/fileupload/FpMAINiYaGaBKm41Owd1F5Vfw2bx1454493962406.gif','马来西亚空运老虎虾',NULL,NULL,NULL,'22','1',NULL,1,NULL,NULL,1,0,1,NULL,NULL,'1','1','1','1','2015-12-06 21:44:17','2016-02-16 13:40:51',NULL,NULL,NULL),(10,10,'鱼子',5,7,NULL,'/fileupload/FiXeAFGvrXXXcwFZWqkVuror2qPF1454493991247.gif','挪威鱼子',NULL,NULL,NULL,'1','1',NULL,1,NULL,NULL,1,0,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:45:27','2016-02-16 13:40:51',NULL,NULL,NULL),(11,10,'生蚝',5,5,NULL,'/fileupload/FsaFK9r-MjvpxyM1hxffClRvbR2K1454494035959.gif','澳洲生蚝王',NULL,NULL,NULL,'1',NULL,NULL,1,NULL,NULL,1,0,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:46:37','2016-02-16 13:40:51',NULL,NULL,NULL),(12,10,'1',5,5,NULL,'/fileupload/01_m1449409582611.jpg','1',NULL,1.00,60.00,'1',NULL,NULL,1,NULL,NULL,1,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:47:23','2016-02-16 13:40:51',NULL,NULL,NULL),(13,10,'11',5,11,NULL,'/fileupload/01_m1449409766681.jpg','1',NULL,1.00,1.00,'1','1',NULL,1,NULL,NULL,1,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:49:47','2016-02-16 13:40:51',NULL,NULL,NULL),(14,10,'1',5,5,NULL,'/fileupload/6975397_dinggai2_thumb1449409918920.jpg','1',NULL,1.00,1.00,'1','1',NULL,1,NULL,NULL,1,1,1,NULL,NULL,NULL,NULL,NULL,'1','2015-12-06 21:52:09','2016-02-16 13:40:51',NULL,NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='资源参数';

/*Data for the table `web_resource_param` */

insert  into `web_resource_param`(`id`,`resource_id`,`category_id`,`param_name`,`param_value`,`root_param_name`,`des`,`rank`,`iseff`,`create_time`,`last_update`) values (46,7,13,'1',NULL,NULL,NULL,1,'1','2016-02-04 14:46:50','2016-02-04 14:46:50'),(47,7,13,'2','2','1',NULL,1,'1','2016-02-04 14:46:50','2016-02-04 14:46:50'),(48,7,13,'3','3','1',NULL,2,'1','2016-02-04 14:46:50','2016-02-04 14:46:50'),(49,7,13,'8',NULL,NULL,NULL,2,'1','2016-02-04 14:46:50','2016-02-04 14:46:50'),(50,7,13,'4','','8',NULL,1,'1','2016-02-04 14:46:50','2016-02-04 14:46:50');

/*Table structure for table `web_resource_price` */

DROP TABLE IF EXISTS `web_resource_price`;

CREATE TABLE `web_resource_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NOT NULL COMMENT '资源id，表web_resource主键',
  `spec_ids` varchar(50) NOT NULL COMMENT '规格ID，对应表web_resource_spec,多个以逗号分隔',
  `cost` float(9,2) DEFAULT '0.00' COMMENT '产品成本价',
  `price` float(9,2) DEFAULT '0.00' COMMENT '产品价格',
  `gift_score` varchar(20) DEFAULT '0' COMMENT '赠送积分',
  `stock_num` int(11) DEFAULT '0' COMMENT '库存',
  `rank` int(3) NOT NULL DEFAULT '0' COMMENT '排序',
  `iseff` char(1) NOT NULL DEFAULT '0' COMMENT '是否生效 0:没生效 1:生效',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='资源价格';

/*Data for the table `web_resource_price` */

insert  into `web_resource_price`(`id`,`resource_id`,`spec_ids`,`cost`,`price`,`gift_score`,`stock_num`,`rank`,`iseff`,`create_time`,`last_update`) values (21,7,'32,30',1000.00,999.00,NULL,1,1,'1','2016-02-04 15:01:26','2016-02-04 15:01:39'),(22,7,'33,30',1000.00,999.00,NULL,1,2,'1','2016-02-04 15:01:26','2016-02-04 15:01:39');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='资源截图';

/*Data for the table `web_resource_screenshot` */

insert  into `web_resource_screenshot`(`id`,`resource_id`,`name`,`image_url`,`short_desc`,`rank`,`iseff`,`create_time`,`last_update`) values (19,7,'1','/fileupload/Fg_cNErXDhMCDcEprN51ZiBGSLjV1454564414070.gif',NULL,1,'1','2015-12-06 22:18:44','2016-02-04 13:40:27'),(20,7,'1','/fileupload/7741d029df953cb1c71251adde3200871454556578016.png',NULL,3,'1','2016-02-04 11:29:40','2016-02-04 13:39:44');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='资源规格';

/*Data for the table `web_resource_spec` */

insert  into `web_resource_spec`(`id`,`resource_id`,`spec`,`root_spec`,`rank`,`iseff`,`create_time`,`last_update`) values (26,8,'1',NULL,1,'1','2016-02-04 10:59:33','2016-02-04 11:00:00'),(27,8,'2','1',1,'1','2016-02-04 10:59:33','2016-02-04 11:00:00'),(28,8,'3','1',2,'1','2016-02-04 10:59:33','2016-02-04 11:00:00'),(29,7,'规格1',NULL,2,'1','2016-02-04 14:49:18','2016-02-04 14:50:58'),(30,7,'2','规格1',1,'1','2016-02-04 14:49:18','2016-02-04 14:50:58'),(31,7,'规格2',NULL,1,'1','2016-02-04 14:50:24','2016-02-04 14:50:58'),(32,7,'3','规格2',1,'1','2016-02-04 14:50:24','2016-02-04 14:50:59'),(33,7,'4','规格2',2,'1','2016-02-04 14:50:40','2016-02-04 14:50:59');

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
