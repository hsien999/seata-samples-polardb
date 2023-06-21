create schema seata_stock collate utf8mb4_general_ci;

create table stock_tbl
(
    id             int auto_increment
        primary key,
    commodity_code varchar(255)  null,
    count          int default 0 null,
    constraint commodity_code
        unique (commodity_code)
)
    charset = utf8;

create table undo_log
(
    id            bigint auto_increment
        primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   datetime     not null,
    log_modified  datetime     not null,
    ext           varchar(100) null,
    constraint ux_undo_log
        unique (xid, branch_id)
)
    charset = utf8;

insert into stock_tbl (id, commodity_code, count)
values (1, 'product-1', 999999999);

insert into stock_tbl (id, commodity_code, count)
values (2, 'product-2', 0);