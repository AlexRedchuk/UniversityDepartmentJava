create table if not exists user_roles
(
	user_id bigint not null
		constraint fkhfh9dx7w3ubf1co1vdev94g3f
			references users,
	role_id bigint not null
		constraint fkrhfovtciq1l558cw6udg0h0d3
			references role,
	constraint user_roles_pkey
		primary key (user_id, role_id)
);

alter table user_roles owner to postgres;

