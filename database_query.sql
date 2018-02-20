create table BLOCKED_IP
(
	ipaddr varchar(15) not null
		primary key
)
;

create table BOOKED_CARPOOL
(
	booking_ref int auto_increment
		primary key,
	uid int not null,
	id int not null,
	seat int not null
)
;

create index carpool_book_carpool_id_fk
	on BOOKED_CARPOOL (id)
;

create index carpool_book_user_reg_info_uid_fk
	on BOOKED_CARPOOL (uid)
;

create table CARPOOL
(
	id int auto_increment
		primary key,
	uid int not null,
	date datetime not null,
	price int not null,
	capacity int not null,
	departure varchar(30) not null,
	destination varchar(30) not null,
	remainseat int not null
)
;

create index carpool_user_reg_info_uid_fk
	on CARPOOL (uid)
;

alter table BOOKED_CARPOOL
	add constraint BOOKED_CARPOOL_CARPOOL_id_fk
foreign key (id) references CARPOOL (id)
	on update cascade on delete cascade
;

create table MESSAGE
(
	M_ID int auto_increment
		primary key,
	sender_uid int null,
	receiver_uid int null,
	message text null,
	`read` char default '0' null,
	Carpool_ID int null,
	replied char default '0' null,
	constraint MESSAGE_BOOKED_CARPOOL_booking_ref_fk
	foreign key (Carpool_ID) references BOOKED_CARPOOL (booking_ref)
)
;

create index receiver_uid
	on MESSAGE (receiver_uid)
;

create index sender_uid
	on MESSAGE (sender_uid)
;

create index MESSAGE_BOOKED_CARPOOL_booking_ref_fk
	on MESSAGE (Carpool_ID)
;

create table SUPPORTCITY
(
	city varchar(30) not null
		primary key
)
;

create table USER_REG
(
	uid int auto_increment
		primary key,
	username varchar(30) not null,
	password varchar(32) not null,
	nickname varchar(30) not null,
	email varchar(32) not null,
	cell char(12) not null,
	constraint USER_REG_nickname_uindex
	unique (nickname)
)
;

alter table BOOKED_CARPOOL
	add constraint BOOKED_CARPOOL_USER_REG_uid_fk
foreign key (uid) references USER_REG (uid)
	on update cascade on delete cascade
;

alter table CARPOOL
	add constraint CARPOOL_USER_REG_uid_fk
foreign key (uid) references USER_REG (uid)
	on update cascade on delete cascade
;

alter table MESSAGE
	add constraint message_ibfk_1
foreign key (sender_uid) references USER_REG (uid)
;

alter table MESSAGE
	add constraint message_ibfk_2
foreign key (receiver_uid) references USER_REG (uid)
;

create table VEHICLE_OWNER
(
	uid int null,
	userlevel int default '0' not null,
	vehicletype varchar(30) not null,
	exp int default '0' not null,
	platenum varchar(10) not null
		primary key,
	constraint VEHICLE_OWNER_USER_REG_uid_fk
	foreign key (uid) references USER_REG (uid)
		on update cascade on delete cascade
)
;

create index VEHICLE_OWNER_USER_REG_uid_fk
	on VEHICLE_OWNER (uid)
;

