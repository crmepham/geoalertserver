use geoalert;

create table if not exists user(
	userId int(11) not null primary key auto_increment,
	username varchar(20) not null,
	password varchar(200) not null,
	accountCreationDate datetime not null,
	email varchar(50) not null,
	lang varchar(2) not null,
	status varchar(20) not null default 'Inactive',
	contactNumber varchar(50) not null,
	securityQuestion varchar(200) not null,
	securityAnswer varchar(200) not null,
	profileImage longblob,
	gender varchar(6),
	fullName varchar(200),
	dob datetime,
	bloodType varchar(50),
	height varchar(50),
	weight varchar(50),
	clothingTop varchar(50),
	clothingBottom varchar(50),
	clothingShoes varchar(50),
	nextOfKinFullName varchar(200),
	nextofKinRelationship varchar(50),
	nextOfKinContactNumber varchar(50),
	deleted tinyint(1) not null default 0,
	showMap tinyint(1) not null default 1,
	locationId int(11)
)engine=InnoDB;

create table if not exists contact (
	id int(11) not null primary key auto_increment,
	userId int(11) not null,
	contactId int(11) not null,
	accepted tinyint(1) not null default 0,
	deleted tinyint(1) not null default 0
)engine=InnoDB;

create table if not exists location (
	id int(11) not null primary key auto_increment,
	userId int(11),
	latitude varchar(50),
	longitude varchar(50),
	lastUpdated datetime
)engine=InnoDB;

create table if not exists notification (
	id int(11) not null primary key auto_increment,
	userId int(11) not null,
	senderId int(11) not null,
	dateCreated datetime,
	deleted tinyint(1) default 0
)engine=InnoDB;