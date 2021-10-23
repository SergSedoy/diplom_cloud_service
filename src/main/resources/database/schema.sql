CREATE TABLE IF NOT EXISTS datefiles
(
    id          int primary key auto_increment,
    name        varchar(255),
    file_type   varchar(255),
    size        int check (size > 0),
    upload_date varchar(255),
    key_dec     varchar(255)
);

CREATE TABLE IF NOT EXISTS users
(
    id       int primary key auto_increment,
    login    varchar(255) not null,
    password varchar(255) not null,
    token    varchar(255)
);

insert into users (id, login, password, token) VALUES (1, 'qid@mail.ru', '123', null);
insert into users (id, login, password, token) VALUES (1, 'wid@mail.ru', '123', null);