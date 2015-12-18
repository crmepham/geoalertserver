use geoalert;

create table if not exists user(
	userId int(11) not null primary key auto_increment,
	username varchar(20) not null,
	password varchar(20) not null,
	accountCreationDate datetime not null,
	email varchar(50) not null,
	lang varchar(2) not null,
	gender char(1) not null
);