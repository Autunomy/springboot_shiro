create database shiro;
use shiro;

drop table if exists user;

create table user
(
    id       int primary key auto_increment comment '主键id',
    username varchar(40) comment '用户名',
    password varchar(40) comment '密码 加密之后的',
    salt     varchar(40) comment '给密码加的盐'

) comment '用户表';

drop table if exists role;

create table role
(
    id   int primary key auto_increment comment '主键id',
    name varchar(80) comment '角色信息'
) comment '角色表';

drop table if exists pers;

create table pers
(
    id   int primary key auto_increment comment '主键id',
    name varchar(80) comment '角色',
    url  varchar(255) comment '权限url'
) comment '权限表';

drop table if exists user_role;

create table user_role
(
    id     int primary key auto_increment,
    userid int,
    roleid int
) comment '用户角色表';

drop table if exists role_pers;

create table role_pers(
                          id int primary key auto_increment,
                          roleid int,
                          persid int
) comment '角色权限标签'

