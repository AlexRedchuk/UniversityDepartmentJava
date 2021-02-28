create table if not exists groups
(
	id bigint not null
		constraint groups_pkey
			primary key,
	name varchar(255),
	monitor_id bigint
		constraint fkit2vq6bpdno9cutycdsfhkb59
			references student,
	speciality_id bigint
		constraint fk73ijql5hd085nur62quu3nswx
			references speciality
);

alter table groups owner to postgres;

