drop table if exits cat CASCADE;

create table cat
(
		id integer PRIMARY KEY AUTO_INCREMENT,
		age integer not null,
		breed varchar(255),
		cutie boolean not null,
		full_name varchar(255),
		colouring varchar(255)
);