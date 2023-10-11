drop table if exists user_details cascade;
drop table if exists post;

create table user_details
(
    id    int not null auto_increment,
    name  varchar(255),
    birth_date    date,
     primary key (id)
) engine = InnoDB;

create table post
    (
       id    int not null auto_increment primary key,
       description varchar(255),
      user_id int not null unique
     ) engine = InnoDB;


