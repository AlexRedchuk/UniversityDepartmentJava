create table if not exists university_group
(
	id bigint not null
		constraint university_group_pkey
			primary key,
	name varchar(255),
	speciality_id bigint
		constraint fk7hv5octo1g8l93kmj639ilyxp
			references speciality
);

alter table university_group owner to postgres;

