create table TB_USER (
	seq NUMBER not null,
	email varchar(255) not null,
	name varchar(255) not null,
	picture varchar(255),
	role varchar(255) not null,
	provider varchar(255) not null,
	primary key (seq)
);

CREATE SEQUENCE TB_USER_SEQ increment by 1 start with 1 nocache nocycle;