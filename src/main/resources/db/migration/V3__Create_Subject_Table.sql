create table if not exists subject
(
	id bigint not null
		constraint subject_pkey
			primary key,
	code integer not null,
	name varchar(255)
);

alter table subject owner to postgres;

