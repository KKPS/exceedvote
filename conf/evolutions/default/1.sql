# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ballot (
  id                        bigint auto_increment not null,
  user_username             varchar(255),
  project_id                bigint,
  criterion_id              bigint,
  constraint pk_ballot primary key (id))
;

create table criterion (
  id                        bigint auto_increment not null,
  question                  varchar(255),
  constraint pk_criterion primary key (id))
;

create table project (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_project primary key (id))
;

create table role (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  ballot                    integer,
  constraint pk_role primary key (id))
;

create table user (
  username                  varchar(255) not null,
  password                  varchar(255),
  name                      varchar(255),
  role_id                   bigint,
  is_admin                  tinyint(1) default 0,
  first_login               tinyint(1) default 0,
  constraint pk_user primary key (username))
;

alter table ballot add constraint fk_ballot_user_1 foreign key (user_username) references user (username) on delete restrict on update restrict;
create index ix_ballot_user_1 on ballot (user_username);
alter table ballot add constraint fk_ballot_project_2 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_ballot_project_2 on ballot (project_id);
alter table ballot add constraint fk_ballot_criterion_3 foreign key (criterion_id) references criterion (id) on delete restrict on update restrict;
create index ix_ballot_criterion_3 on ballot (criterion_id);
alter table user add constraint fk_user_role_4 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_user_role_4 on user (role_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table ballot;

drop table criterion;

drop table project;

drop table role;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

