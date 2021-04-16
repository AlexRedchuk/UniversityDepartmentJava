create table if not exists student
(
	id bigint not null
		constraint student_pkey
			primary key,
	full_name varchar(255),
	university_group_id bigint
		constraint fk7bc8li2bhld0bd1cq2krdwms6
			references university_group
);

alter table student owner to postgres;

