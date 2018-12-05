/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.0.96-community-nt : Database - fh-nnsci
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fh-nnsci` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fh-nnsci`;

/*Table structure for table `tb_etermbig` */

DROP TABLE IF EXISTS `tb_etermbig`;

CREATE TABLE `tb_etermbig` (
  `ETERMBIG_ID` varchar(100) NOT NULL,
  `CREATE_BY` varchar(255) default NULL COMMENT '创建人',
  `CREATE_TIME` varchar(255) default NULL COMMENT '创建时间',
  `STATUS` varchar(255) default NULL COMMENT '状态',
  `USERID` varchar(255) default NULL COMMENT '用户ID',
  `OFFICENUM` varchar(255) default NULL COMMENT 'office号',
  `ACCOUNT` varchar(255) default NULL COMMENT '账号',
  `PASSWORD` varchar(255) default NULL COMMENT '密码',
  `IP` varchar(255) default NULL COMMENT 'IP',
  `POST` varchar(255) default NULL COMMENT '端口',
  `TIMELIMIT` varchar(255) default NULL COMMENT '期限',
  `MONEY` varchar(255) default NULL COMMENT '金额',
  `REMARK` varchar(255) default NULL COMMENT '备注',
  `YL1` varchar(255) default NULL COMMENT '预留',
  `YL2` varchar(255) default NULL COMMENT '预留',
  `YL3` varchar(255) default NULL COMMENT '预留',
  `YL4` varchar(255) default NULL COMMENT '预留',
  `YL5` varchar(255) default NULL COMMENT '预留',
  `YL6` varchar(255) default NULL COMMENT '预留',
  `YL7` varchar(255) default NULL COMMENT '预留',
  `YL8` varchar(255) default NULL COMMENT '预留',
  `YL9` varchar(255) default NULL COMMENT '预留',
  `YL10` varchar(255) default NULL COMMENT '预留',
  `YL11` varchar(255) default NULL COMMENT '预留',
  `YL12` varchar(255) default NULL COMMENT '预留',
  `YL13` varchar(255) default NULL COMMENT '预留',
  `YL14` varchar(255) default NULL COMMENT '预留',
  `YL15` varchar(255) default NULL COMMENT '预留',
  `YL16` varchar(255) default NULL COMMENT '预留',
  `YL17` varchar(255) default NULL COMMENT '预留',
  `YL18` varchar(255) default NULL COMMENT '预留',
  `YL19` varchar(255) default NULL COMMENT '预留',
  `YL20` varchar(255) default NULL COMMENT '预留',
  PRIMARY KEY  (`ETERMBIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_etermbig` */

insert  into `tb_etermbig`(`ETERMBIG_ID`,`CREATE_BY`,`CREATE_TIME`,`STATUS`,`USERID`,`OFFICENUM`,`ACCOUNT`,`PASSWORD`,`IP`,`POST`,`TIMELIMIT`,`MONEY`,`REMARK`,`YL1`,`YL2`,`YL3`,`YL4`,`YL5`,`YL6`,`YL7`,`YL8`,`YL9`,`YL10`,`YL11`,`YL12`,`YL13`,`YL14`,`YL15`,`YL16`,`YL17`,`YL18`,`YL19`,`YL20`) values ('15130be354b341cb9581f6a3b5464e14','admin','2018-11-29 15:22:32','1','1','1','1','1','1','1','2018-11-29','1','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_etermsmall` */

DROP TABLE IF EXISTS `tb_etermsmall`;

CREATE TABLE `tb_etermsmall` (
  `ETERMSMALL_ID` varchar(100) NOT NULL,
  `CREATE_BY` varchar(255) default NULL COMMENT '创建人',
  `CREATE_TIME` varchar(255) default NULL COMMENT '创建时间',
  `STATUS` varchar(255) default NULL COMMENT '状态',
  `USERID` varchar(255) default NULL COMMENT '用户ID',
  `ACCOUNT` varchar(255) default NULL COMMENT '账号',
  `PASSWORD` varchar(255) default NULL COMMENT '密码',
  `REMARK` varchar(255) default NULL COMMENT '备注',
  `YL1` varchar(255) default NULL COMMENT '预留',
  `YL2` varchar(255) default NULL COMMENT '预留',
  `YL3` varchar(255) default NULL COMMENT '预留',
  `YL4` varchar(255) default NULL COMMENT '预留',
  `YL5` varchar(255) default NULL COMMENT '预留',
  `YL6` varchar(255) default NULL COMMENT '预留',
  `YL7` varchar(255) default NULL COMMENT '预留',
  `YL8` varchar(255) default NULL COMMENT '预留',
  `YL9` varchar(255) default NULL COMMENT '预留',
  `YL10` varchar(255) default NULL COMMENT '预留',
  `YL11` varchar(255) default NULL COMMENT '预留',
  `YL12` varchar(255) default NULL COMMENT '预留',
  `YL13` varchar(255) default NULL COMMENT '预留',
  `YL14` varchar(255) default NULL COMMENT '预留',
  `YL15` varchar(255) default NULL COMMENT '预留',
  `YL16` varchar(255) default NULL COMMENT '预留',
  `YL17` varchar(255) default NULL COMMENT '预留',
  `YL18` varchar(255) default NULL COMMENT '预留',
  `YL19` varchar(255) default NULL COMMENT '预留',
  `YL20` varchar(255) default NULL COMMENT '预留',
  PRIMARY KEY  (`ETERMSMALL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_etermsmall` */

insert  into `tb_etermsmall`(`ETERMSMALL_ID`,`CREATE_BY`,`CREATE_TIME`,`STATUS`,`USERID`,`ACCOUNT`,`PASSWORD`,`REMARK`,`YL1`,`YL2`,`YL3`,`YL4`,`YL5`,`YL6`,`YL7`,`YL8`,`YL9`,`YL10`,`YL11`,`YL12`,`YL13`,`YL14`,`YL15`,`YL16`,`YL17`,`YL18`,`YL19`,`YL20`) values ('80d5f85ace944ec691e31584e7a5f543','admin','2018-11-29 15:22:42','1','1','1','1','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('f25f848428064e0dabcf1d59658e5ca1','lz','2018-11-29 16:16:18','1','1','1','1','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
