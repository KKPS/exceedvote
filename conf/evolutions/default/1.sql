# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ballot (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  project_id                bigint,
  criterion_id              bigint,
  time                      datetime,
  constraint pk_ballot primary key (id))
;

create table criterion (
  id                        bigint auto_increment not null,
  question                  varchar(255),
  description               varchar(255),
  constraint pk_criterion primary key (id))
;

create table project (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  img_url1                  varchar(255),
  img_url2                  varchar(255),
  img_url3                  varchar(255),
  constraint pk_project primary key (id))
;

create table request (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  role_id                   bigint,
  approve                   tinyint(1) default 0,
  constraint pk_request primary key (id))
;

create table role (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  ballot                    integer,
  constraint pk_role primary key (id))
;

create table rule (
  id                        bigint auto_increment not null,
  start                     datetime,
  finish                    datetime,
  constraint pk_rule primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  name                      varchar(255),
  role_id                   bigint,
  project_id                bigint,
  is_admin                  tinyint(1) default 0,
  first_login               tinyint(1) default 0,
  constraint pk_user primary key (id))
;

alter table ballot add constraint fk_ballot_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_ballot_user_1 on ballot (user_id);
alter table ballot add constraint fk_ballot_project_2 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_ballot_project_2 on ballot (project_id);
alter table ballot add constraint fk_ballot_criterion_3 foreign key (criterion_id) references criterion (id) on delete restrict on update restrict;
create index ix_ballot_criterion_3 on ballot (criterion_id);
alter table request add constraint fk_request_role_4 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_request_role_4 on request (role_id);
alter table user add constraint fk_user_role_5 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_role_5 on user (role_id);
alter table user add constraint fk_user_project_6 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_user_project_6 on user (project_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table ballot;

drop table criterion;

drop table project;

drop table request;

drop table role;

drop table rule;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

