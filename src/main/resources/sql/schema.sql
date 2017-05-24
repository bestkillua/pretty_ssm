
-- 需要 MySQL 5.6.5以上的版本
USE pretty_ssm;

-- 用户表
CREATE TABLE _user(
`user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
`user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
`user_phone` BIGINT NOT NULL COMMENT '手机号',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (`user_id`),
KEY `idx_user_phone`(`user_phone`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 插入初始数据
INSERT INTO 
	_user(user_name, user_phone)
VALUES
	('最佳损友', 18768128888),
	('陈奕迅', 18968129999);


	