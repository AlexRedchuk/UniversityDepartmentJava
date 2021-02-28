create table if not exists tutor
(
	id bigint not null
		constraint tutor_pkey
			primary key,
	date_of_birth timestamp,
	degree varchar(255),
	full_name varchar(255),
	position varchar(255),
	salary numeric(19,2),
	tab_number varchar(255)
);

alter table tutor owner to postgres;

