create table IF NOT EXISTS client_seq
(
    num_compte bigint not null primary key,
    solde bigint,
    nom varchar(200) not null
);