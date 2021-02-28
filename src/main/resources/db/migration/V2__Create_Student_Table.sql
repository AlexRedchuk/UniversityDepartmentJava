create table if not exists student
(
	id bigint not null
		constraint student_pkey
			primary key,
	full_name varchar(255),
	group_id bigint
		constraint fksflcrdigyrhbqi27vvioiw53q
			references groups
);

alter table student owner to postgres;

