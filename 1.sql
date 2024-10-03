-- 创建数据库
create database verification;

-- 使用数据库
use verification;

-- 用户表
create table user (
    id int unsigned primary key auto_increment comment 'ID',
    username varchar(20) not null unique comment '用户名',
    password varchar(32) comment '密码',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) comment '用户表';

-- 应用表
create table app(
    id int unsigned primary key auto_increment comment 'ID',
    name varchar(32) not null comment '应用名称',
    create_user int unsigned not null comment '创建人ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    constraint create_userOn_user_id foreign key (create_user) references user(id) 
    ON DELETE CASCADE -- 外键约束，级联删除
);

-- 密钥表
create table keytable(
    id int unsigned primary key auto_increment comment 'ID',
    content varchar(100) not null comment '卡密',
    appId int unsigned not null comment '应用ID',
    create_time datetime not null comment '创建时间',
    maturity_time datetime not null comment '到期时间',
    constraint appIdOn_app_id foreign key (appId) references app(id) 
    ON DELETE CASCADE -- 外键约束，级联删除
);
