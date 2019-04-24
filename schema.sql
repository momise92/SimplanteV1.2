create table category (category_id bigint not null auto_increment, name varchar(50), primary key (category_id)) engine=InnoDB
create table comment (comment_id bigint not null auto_increment, body TEXT, create_date datetime not null, post_id bigint, update_date datetime, user_id bigint, primary key (comment_id)) engine=InnoDB
create table post (post_id bigint not null auto_increment, body TEXT not null, create_date datetime, last_modified datetime, status bit not null, title varchar(100) not null, user_id bigint, vote integer not null, category_id bigint, primary key (post_id)) engine=InnoDB
create table role_app (role_id bigint not null auto_increment, roles varchar(255), primary key (role_id)) engine=InnoDB
create table user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id)) engine=InnoDB
create table user_app (user_id bigint not null auto_increment, email varchar(255) not null, first_name varchar(255), is_active bit not null, last_name varchar(255), password varchar(255) not null, register_date datetime not null, username varchar(20) not null, primary key (user_id)) engine=InnoDB
alter table category add constraint UK_46ccwnsi9409t36lurvtyljak unique (name)
alter table post add constraint UK_2jm25hjrq6iv4w8y1dhi0d9p4 unique (title)
alter table role_app add constraint UK_kinj6b9sauukhm9ry2dmiual7 unique (roles)
alter table user_app add constraint UK_mk1xvwget6ponrb1elcqe09uv unique (email)
alter table user_app add constraint UK_65kue06vu2g78mxpdfke453e5 unique (username)
alter table comment add constraint FKs1slvnkuemjsq2kj4h3vhx7i1 foreign key (post_id) references post (post_id)
alter table comment add constraint FKdu9ddkdwq630pm7cdcj6j0ef2 foreign key (user_id) references user_app (user_id)
alter table post add constraint FKg6l1ydp1pwkmyj166teiuov1b foreign key (category_id) references category (category_id)
alter table post add constraint FKhqkpqjriftn3y0b045ul5v4pw foreign key (user_id) references user_app (user_id)
alter table user_role add constraint FK9goe837qcyvq72p9w9yut2x9f foreign key (role_id) references role_app (role_id)
alter table user_role add constraint FKou9cjiokv9u8w3bg4vjeu3n4g foreign key (user_id) references user_app (user_id)
