create table if not exists student
(
	id bigint not null
		constraint student_pkey
			primary key,
	full_name varchar(255),
	group_id bigint
		constraint fkrmvq3iao097xuicmamd6l83ja
			references university_group
);

alter table student owner to postgres;

