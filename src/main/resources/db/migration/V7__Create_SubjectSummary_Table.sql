create table if not exists subject_summary
(
	id bigint not null
		constraint subject_summary_pkey
			primary key,
	mark integer not null,
	semester integer not null,
	year integer not null,
	student_id bigint
		constraint fkkhscgqk056bslue1mkyvk6iqa
			references student,
	subject_id bigint
		constraint fkdv0yr9hyb64598pkmp33hmpx6
			references subject,
	tutor_id bigint
		constraint fkntsid8fqnq78qbs28xgv0i422
			references tutor
);

alter table subject_summary owner to postgres;

