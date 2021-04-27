create table if not exists users
(
	id bigint not null
		constraint users_pkey
			primary key,
	password varchar(255),
	username varchar(255)
);

alter table users owner to postgres;

