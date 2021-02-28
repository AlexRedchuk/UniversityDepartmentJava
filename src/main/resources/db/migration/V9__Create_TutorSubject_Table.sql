create table if not exists tutor_subject
(
	subject_id bigint not null
		constraint fkc8um4d7iopbq72enywi84a1f7
			references subject,
	tutors_id bigint not null
		constraint fk7hdydp0ax13xtsggsn3u34v2o
			references tutor,
	constraint tutor_subject_pkey
		primary key (subject_id, tutors_id)
);

alter table tutor_subject owner to postgres;

