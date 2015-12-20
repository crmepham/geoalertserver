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
	gender char(1),
	fullName varchar(200),
	dob datetime,
	bloodType varchar(50),
	height varchar(20),
	weight varchar(20),
	clothingTop varchar(20),
	clothingBottom varchar(20),
	clothingShoes varchar(20),
	nextOfKinFullName varchar(200),
	nextofKinRelationship varchar(50),
	nextOfKinContactNumber varchar(50)
);