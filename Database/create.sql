
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tab_menu
-- ----------------------------
DROP TABLE IF EXISTS `tab_menu`;
CREATE TABLE `tab_menu` (
  `menuId` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `code` varchar(45) DEFAULT NULL COMMENT '菜单编号',
  `pId` bigint(20) DEFAULT NULL COMMENT '菜单父编号',
  `name` varchar(45) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT 'url地址',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `levels` int(4) DEFAULT NULL COMMENT '层级',
  `menuFlag` varchar(45) DEFAULT NULL COMMENT '是否菜单',
  `description` varchar(60) DEFAULT NULL COMMENT '备注',
  `status` varchar(45) DEFAULT NULL COMMENT '菜单状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `createUser` varchar(45) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(45) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_menu
-- ----------------------------
INSERT INTO `tab_menu` VALUES ('1', '2', '0', '系统管理', '', '999', '0', '1', null, '', '2019-04-04 01:34:49', '2019-06-04 15:37:36', '钟义勇', 'admin');
INSERT INTO `tab_menu` VALUES ('2', '1', '1', '菜单管理', 'manageApp.base.controller.menu.Menu', '5', '0', '1', null, '', null, '2019-04-11 17:08:32', '', 'admin');
INSERT INTO `tab_menu` VALUES ('100', '2', '1', '角色管理', 'manageApp.base.controller.role.Role', '2', '0', '1', null, '', '2019-12-05 14:19:00', null, 'admin', null);
INSERT INTO `tab_menu` VALUES ('101', '3', '1', '用户管理', 'manageApp.base.controller.user.User?sysType=1', '3', '0', '1', null, '', '2019-12-05 22:19:27', '2019-12-05 14:23:54', 'admin', 'admin');


DROP TABLE IF EXISTS `tab_relation`;
CREATE TABLE `tab_relation` (
  `relationId` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色 资源 关联表',
  `roleId` bigint(20) NOT NULL COMMENT '角色id',
  `menuId` bigint(20) NOT NULL COMMENT '菜单id',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`relationId`)
) ENGINE=InnoDB AUTO_INCREMENT=513 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_relation
-- ----------------------------
INSERT INTO `tab_relation` VALUES ('1', '1', '1', null);
INSERT INTO `tab_relation` VALUES ('2', '1', '2', null);
INSERT INTO `tab_relation` VALUES ('511', '1', '100', null);
INSERT INTO `tab_relation` VALUES ('512', '1', '101', null);
-- ----------------------------
-- Table structure for tab_role_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `tab_role_user_relation`;
CREATE TABLE `tab_role_user_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色  用户 关联表',
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=365 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_role_user_relation
-- ----------------------------
INSERT INTO `tab_role_user_relation` VALUES ('1', '1', '1', null);
INSERT INTO `tab_role_user_relation` VALUES ('364', '1', '180', null);

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL COMMENT '用户名',
  `alias` varchar(45) DEFAULT NULL COMMENT '用户别名',
  `passWord` varchar(45) DEFAULT NULL COMMENT '密码',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(45) DEFAULT NULL COMMENT '性别',
  `depart` varchar(45) DEFAULT NULL COMMENT '部门',
  `role` int(10) DEFAULT NULL COMMENT '角色',
  `state` int(11) DEFAULT NULL COMMENT '状态 0未启用，1启用',
  `sysType` int(10) DEFAULT NULL COMMENT '系统类型',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间 ',
  `createUser` varchar(45) DEFAULT NULL COMMENT '创建人',
  `modifTime` datetime DEFAULT NULL COMMENT '修改时间',
  `modifUser` varchar(45) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES ('1', 'admin', 'admin', '1234', '', '男', '技术部', '1', '0', '1', '2017-06-08 08:00:00', '', '2017-07-04 08:00:00', '');
INSERT INTO `tab_user` VALUES ('180', 'user', 'user', 'user', '', '', '', '0', '0', '1', '2019-12-05 16:00:00', '', '2019-12-05 16:00:00', '');

-- ----------------------------
-- Table structure for tab_role
-- ----------------------------
DROP TABLE IF EXISTS `tab_role`;
CREATE TABLE `tab_role` (
  `roleId` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(60) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(60) DEFAULT NULL COMMENT '备注',
  `sort` int(4) DEFAULT NULL COMMENT '序号',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createUser` varchar(40) DEFAULT NULL COMMENT '创建人',
  `updateUser` varchar(40) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_role
-- ----------------------------
INSERT INTO `tab_role` VALUES ('1', '超级管理员', '超级管理员', '0', '启动', '2019-04-08 09:25:01', '2019-04-09 15:43:16', '钟义勇', '钟义勇');
