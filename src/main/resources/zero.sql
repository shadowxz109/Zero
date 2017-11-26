CREATE DATABASE IF NOT EXISTS zero CHARACTER SET UTF8;
USE zero;

CREATE TABLE IF NOT EXISTS user(
id INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
user_name VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
password VARCHAR(50) NOT NULL COMMENT '密码',
email VARCHAR(30) NOT NULL COMMENT '邮箱',
sex INT(1)  NOT NULL COMMENT '性别0:男,1:女',
self_introduce VARCHAR(100) DEFAULT NULL COMMENT '自我介绍',
state INT(1) NOT NULL DEFAULT '1' COMMENT '用户状态 0:小黑屋1:正常',
register_time DATETIME NOT NULL COMMENT '注册时间',
massage_number INT(10) DEFAULT '0' COMMENT '未读消息',
image_url VARCHAR(50) NOT NULL DEFAULT '111' COMMENT '头像url',
active INT(1) NOT NULL DEFAULT '0' COMMENT '邮箱激活状态 0:未激活 1:已激活',
validateCode VARCHAR(50) NOT NULL COMMENT '邮箱激活码',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS admin(
id INT NOT NULL AUTO_INCREMENT COMMENT '管理员id',
user_name VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
password VARCHAR(30) NOT NULL COMMENT '密码',
state  INT(1) NOT NULL DEFAULT '0' COMMENT '身份 0：普通管理员，1：超级管理员',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS post(
id INT NOT NULL AUTO_INCREMENT COMMENT '帖子id',
section_id INT(6) NOT NULL COMMENT '所在板块id',
user_id INT NOT NULL COMMENT '发表用户id',
title VARCHAR(100) NOT NULL COMMENT '帖子题目',
content TEXT DEFAULT NULL COMMENT '帖子内容',
pulish_time DATETIME NOT NULL COMMENT '发表时间',
reply_time DATETIME NOT NULL COMMENT '最近回复时间',
read_number INT NOT NULL DEFAULT '0' COMMENT '浏览数',
reply_number INT NOT NULL DEFAULT '0' COMMENT '回复数',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;


CREATE TABLE IF NOT EXISTS section(
id INT(6) NOT NULL AUTO_INCREMENT COMMENT '板块id',
section_name VARCHAR(10) NOT NULL COMMENT '板块名称',
introduce VARCHAR(100) NOT NULL COMMENT '版面描述',
post_number INT DEFAULT '0' COMMENT '帖子数',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

INSERT INTO section (section_name,introduce,post_number) VALUES ('全部','NullPointerException',0);
INSERT INTO section (section_name,introduce,post_number) VALUES ('技术','nobody can read',0);
INSERT INTO section (section_name,introduce,post_number) VALUES ('创意','nobody can read',0);
INSERT INTO section (section_name,introduce,post_number) VALUES ('酷工作','nobody can read',0);
INSERT INTO section (section_name,introduce,post_number) VALUES ('交易','nobody can read',0);
INSERT INTO section (section_name,introduce,post_number) VALUES ('交友','nobody can read',0);


CREATE TABLE IF NOT EXISTS reply(
id INT NOT NULL AUTO_INCREMENT COMMENT '回复id',
post_id INT NOT NULL COMMENT '帖子id',
user_id INT NOT NULL COMMENT '回复者id',
content TEXT NOT NULL COMMENT '回复内容',
reply_time DATETIME NOT NULL COMMENT '回复时间',
state INT(1) NOT NULL COMMENT '发帖者查看 0：为查看，1：已查看',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS dialogue(
id INT NOT NULL  AUTO_INCREMENT COMMENT '对话id',
reply_id INT NOT NULL COMMENT '回复节点id',
send_user_id INT NOT NULL COMMENT '发送者id',
receive_user_id  INT NOT NULL COMMENT '接收者id',
content TEXT NOT NULL COMMENT '对话内容',
reply_read INT(1) DEFAULT '0' COMMENT '回复者查看 0:未查看，1：已查看',
receive_read INT(1) DEFAULT '0' COMMENT '接收者查看 0：未查看,1：已查看',
send_time DATETIME NOT NULL COMMENT'发送时间',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;



