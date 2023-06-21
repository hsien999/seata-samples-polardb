create database seata_order with owner xxx;

create table order_tbl
(
    id             serial
        constraint pk_order_tbl
            primary key,
    user_id        varchar,
    commodity_code varchar,
    count          integer default 0,
    money          integer default 0
);

alter table order_tbl
    owner to xxx;

create table undo_log
(
    id            serial
        constraint pk_undo_log
            primary key,
    branch_id     bigint       not null,
    xid           varchar(128) not null,
    context       varchar(128) not null,
    rollback_info bytea        not null,
    log_status    integer      not null,
    log_created   timestamp(0) not null,
    log_modified  timestamp(0) not null,
    constraint ux_undo_log
        unique (xid, branch_id)
);

comment on table undo_log is 'AT transaction mode undo table';

comment on column undo_log.branch_id is 'branch transaction id';

comment on column undo_log.xid is 'global transaction id';

comment on column undo_log.context is 'undo_log context,such as serialization';

comment on column undo_log.rollback_info is 'rollback info';

comment on column undo_log.log_status is '0:normal status,1:defense status';

comment on column undo_log.log_created is 'create datetime';

comment on column undo_log.log_modified is 'modify datetime';

alter table undo_log
    owner to xxx;

create index ix_log_created
    on undo_log (log_created);