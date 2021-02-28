create table if not exists subject_plan
(
	id bigint not null
		constraint subject_plan_pkey
			primary key,
	type integer,
	year integer not null,
	group_id bigint
		constraint fktajnwgeq706ptjes27cjhvva9
			references groups,
	subject_summary_id bigint
		constraint fk46cg8xsh0fhc8labt6w740dl5
			references subject_summary,
	tutor_id bigint
		constraint fk711fk6s6x1ofrg7ajlwcauc3t
			references tutor
);

alter table subject_plan owner to postgres;

