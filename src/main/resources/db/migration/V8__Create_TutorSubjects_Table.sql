create table if not exists tutor_subjects
(
	tutor_id bigint not null
		constraint fkrbwej4ik99xyo11357qqwucco
			references tutor,
	subjects_id bigint not null
		constraint fk3wpswxifhr35whtb2nf3eo4p9
			references subject,
	subject_id bigint not null
		constraint fk2rgvjsbsp6aqto0jj07peljlc
			references subject,
	constraint tutor_subjects_pkey
		primary key (subject_id, tutor_id)
);

alter table tutor_subjects owner to postgres;

