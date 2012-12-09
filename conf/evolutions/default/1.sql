# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table criterion (
  id                        bigint not null,
  question                  varchar(255),
  constraint pk_criterion primary key (id))
;

create table project (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_project primary key (id))
;

create table user (
  username                  varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (username))
;

create sequence criterion_seq;

create sequence project_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists criterion;

drop table if exists project;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists criterion_seq;

drop sequence if exists project_seq;

drop sequence if exists user_seq;

