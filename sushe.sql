
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbuilding
-- ----------------------------
DROP TABLE IF EXISTS `tbuilding`;
CREATE TABLE `tbuilding` (
  `buildingId` int(10) NOT NULL AUTO_INCREMENT COMMENT '楼宇id',
  `buildingName` varchar(20) NOT NULL COMMENT '楼宇名称',
  `buildingInfo` text COMMENT '楼宇描述',
  `createDate` date NOT NULL COMMENT '创建日期',
  `createId` int(10) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`buildingId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tdiscipline
-- ----------------------------
DROP TABLE IF EXISTS `tdiscipline`;
CREATE TABLE `tdiscipline` (
  `disciplineId` int(20) NOT NULL AUTO_INCREMENT,
  `buildingId` int(20) NOT NULL,
  `domitoryId` int(20) NOT NULL,
  `disciplineContent` text COMMENT '违规内容',
  `disciplineDate` datetime DEFAULT NULL COMMENT '违规时间',
  `isCriticize` int(1) NOT NULL COMMENT '是否通报批评：1:是，0：否',
  `createDate` datetime NOT NULL,
  `createId` int(20) NOT NULL,
  PRIMARY KEY (`disciplineId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tdomitory
-- ----------------------------
DROP TABLE IF EXISTS `tdomitory`;
CREATE TABLE `tdomitory` (
  `domitoryId` int(20) NOT NULL AUTO_INCREMENT,
  `buildingId` int(20) NOT NULL,
  `domitoryCode` varchar(10) NOT NULL,
  `domitoryType` varchar(2) NOT NULL COMMENT '宿舍类型：4:4人寝，6:6人寝',
  `domitoryStatus` varchar(2) DEFAULT NULL COMMENT '宿舍状态：1：住满，0：有空',
  `domitoryAsset` text,
  `createDate` datetime DEFAULT NULL,
  `createId` int(20) DEFAULT NULL,
  PRIMARY KEY (`domitoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tliveout
-- ----------------------------
DROP TABLE IF EXISTS `tliveout`;
CREATE TABLE `tliveout` (
  `liveoutId` int(20) NOT NULL AUTO_INCREMENT,
  `stuCode` varchar(20) NOT NULL,
  `stuName` varchar(6) NOT NULL,
  `sex` int(2) DEFAULT NULL,
  `liveoutContent` text,
  `liveoutDate` datetime DEFAULT NULL,
  `isCriticize` int(1) NOT NULL COMMENT '是否通告批评； 1：是，0：否',
  `createDate` datetime DEFAULT NULL,
  `createId` int(20) DEFAULT NULL,
  PRIMARY KEY (`liveoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tnotice
-- ----------------------------
DROP TABLE IF EXISTS `tnotice`;
CREATE TABLE `tnotice` (
  `noticeId` int(20) NOT NULL AUTO_INCREMENT,
  `noticeName` varchar(50) NOT NULL,
  `noticeInfo` text,
  `createDate` date DEFAULT NULL,
  `createId` int(20) DEFAULT NULL,
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for trepair
-- ----------------------------
DROP TABLE IF EXISTS `trepair`;
CREATE TABLE `trepair` (
  `repairId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `buildingId` int(20) NOT NULL COMMENT '楼宇id',
  `domitoryId` int(20) NOT NULL COMMENT '宿舍id',
  `repairContent` text COMMENT '报修内容',
  `phone` varchar(15) DEFAULT NULL COMMENT '报修人电话',
  `status` varchar(1) DEFAULT NULL COMMENT '报修状态:0:进行中，1：已完成',
  `createDate` datetime DEFAULT NULL COMMENT '报修创建日期',
  `createId` int(20) DEFAULT NULL COMMENT '报修创建人',
  PRIMARY KEY (`repairId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tstudent
-- ----------------------------
DROP TABLE IF EXISTS `tstudent`;
CREATE TABLE `tstudent` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stuCode` varchar(30) NOT NULL COMMENT '学号',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `sex` decimal(2,0) DEFAULT NULL COMMENT '性别  0:女，1:男',
  `className` varchar(20) NOT NULL COMMENT '班级',
  `buildingId` int(10) DEFAULT NULL COMMENT '楼宇id',
  `domitoryId` int(10) DEFAULT NULL COMMENT '宿舍ID',
  `isStay` decimal(2,0) NOT NULL COMMENT '居住状态；1:居住，0:迁出',
  `phone` varchar(11) DEFAULT NULL COMMENT '学生电话',
  `coachTel` varchar(11) DEFAULT NULL COMMENT '辅导员电话',
  `createDate` date DEFAULT NULL COMMENT '创建日期',
  `createId` decimal(10,0) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`,`stuCode`),
  KEY `stu_build_fk` (`buildingId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tteacher
-- ----------------------------
DROP TABLE IF EXISTS `tteacher`;
CREATE TABLE `tteacher` (
  `teacherId` int(20) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(6) NOT NULL,
  `sex` int(1) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `manageBuiId` int(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `createId` int(20) DEFAULT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tuser
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(12) NOT NULL,
  `code` varchar(30) DEFAULT NULL,
  `role` decimal(2,0) NOT NULL COMMENT '用户角色；1:学生，2:宿舍管理员，3:管理员',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
