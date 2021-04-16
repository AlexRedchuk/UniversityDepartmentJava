create table if not exists subject_plan
(
	id bigint not null
		constraint subject_plan_pkey
			primary key,
	type varchar(255),
	year integer not null,
	subject_id bigint
		constraint fk5qm6e4tx4i7jr80mfbl0lr27x
			references subject,
	tutor_id bigint
		constraint fk711fk6s6x1ofrg7ajlwcauc3t
			references tutor,
	group_id bigint
		constraint fknwgq7m7w7o9j642tw3x38nc09
			references university_group
);

alter table subject_plan owner to postgres;

