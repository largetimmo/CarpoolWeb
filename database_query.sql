create table BLOCKED_IP
(
	ipaddr varchar(15) not null
		primary key
)
;

create table BOOKED_CARPOOL
(
	booking_ref int not null auto_increment
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
	id int not null auto_increment
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
foreign key (id) references carpooweb.CARPOOL (id)
	on update cascade on delete cascade
;

create table SUPPORTCITY
(
	city varchar(30) not null
		primary key
)
;

create table USER_REG
(
	uid int not null auto_increment
		primary key,
	username varchar(30) not null,
	password varchar(32) not null,
	nickname varchar(30) not null,
	email varchar(32) not null,
	cell char(12) not null
)
;

alter table BOOKED_CARPOOL
	add constraint BOOKED_CARPOOL_USER_REG_uid_fk
foreign key (uid) references carpooweb.USER_REG (uid)
	on update cascade on delete cascade
;

alter table CARPOOL
	add constraint CARPOOL_USER_REG_uid_fk
foreign key (uid) references carpooweb.USER_REG (uid)
	on update cascade on delete cascade
;

create table VEHICLE_OWNER
(
	uid int not null auto_increment
		primary key,
	userlevel int default '0' not null,
	vehicle varchar(30) not null,
	exp int default '0' not null,
	constraint VEHICLE_OWNER_USER_REG_uid_fk
	foreign key (uid) references carpooweb.USER_REG (uid)
		on update cascade on delete cascade
)
;

