ALTER TABLE `user`
ADD `USERTYPE`  varchar(10)
CHARACTER SET utf8 COLLATE utf8_general_ci
DEFAULT '1'
COMMENT '用户类型 ADMIN:系统管理员 | USER:普通用户'
AFTER `PASSWORD`;