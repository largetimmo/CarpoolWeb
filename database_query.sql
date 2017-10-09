create table carpool
(
	id int auto_increment
		primary key,
	uid int not null,
	date date not null,
	price int not null,
	capacity int not null,
	departure varchar(30) not null,
	destination varchar(30) not null
)
;

create table carpool_book
(
	booking_ref int auto_increment
		primary key,
	uid int not null,
	id int not null
)
;

create table city
(
	city varchar(30) not null
		primary key
)
;

create table user_carpool_info
(
	uid int auto_increment
		primary key,
	username varchar(30) not null,
	userlevel int not null,
	vehicle varchar(30) not null,
	exp int not null
)
;

create table user_reg_info
(
	uid int not null
		primary key,
	username varchar(30) not null,
	password varchar(32) not null,
	nickname varchar(30) not null
)
;

