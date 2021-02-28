create table if not exists subject
(
	id bigint not null
		constraint subject_pkey
			primary key,
	code varchar(255),
	name varchar(255)
);

alter table subject owner to postgres;

