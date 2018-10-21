# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table message (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  mail                          varchar(255),
  message                       varchar(255),
  postdate                      timestamp not null,
  constraint pk_message primary key (id)
);


# --- !Downs

drop table if exists message;

