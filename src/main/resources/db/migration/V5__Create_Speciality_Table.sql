create table if not exists speciality
(
	id bigint not null
		constraint speciality_pkey
			primary key,
	number_of_credits integer not null,
	code varchar(255),
	name varchar(255),
	year_of_adding integer not null
);

alter table speciality owner to postgres;

