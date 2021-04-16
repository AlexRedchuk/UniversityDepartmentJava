create table if not exists speciality
(
	id bigint not null
		constraint speciality_pkey
			primary key,
	code integer not null,
	name varchar(255),
	number_of_credits integer not null,
	year_of_adding integer not null
);

alter table speciality owner to postgres;

