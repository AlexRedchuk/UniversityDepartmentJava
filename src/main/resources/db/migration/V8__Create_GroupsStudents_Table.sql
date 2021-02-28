create table if not exists groups_students
(
	groups_id bigint not null
		constraint fkhrg9h7qutffg03mof3hlfm5g7
			references groups,
	students_id bigint not null
		constraint uk_rx1gg9e05eb316npu9okdt7u7
			unique
		constraint fketnlctenn46ghutslq4fr1gtu
			references student,
	constraint groups_students_pkey
		primary key (groups_id, students_id)
);

alter table groups_students owner to postgres;

