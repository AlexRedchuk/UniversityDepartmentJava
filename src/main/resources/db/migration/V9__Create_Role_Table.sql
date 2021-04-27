create table if not exists role
(
	id bigint not null
		constraint role_pkey
			primary key,
	name varchar(255)
);

alter table role owner to postgres;

