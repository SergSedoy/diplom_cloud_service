CREATE TABLE IF NOT EXISTS datefiles
(
    id          int primary key auto_increment,
    name        varchar(255),
    file_type   varchar(255),
    size        int check (size > 0),
    upload_date varchar(255)
);

CREATE TABLE IF NOT EXISTS user
(
    id       int primary key auto_increment,
    login    varchar(255) not null,
    password varchar(255) not null
);

insert into user (login, password) VALUES ('qid@mail.ru', '123');
insert into user (login, password) VALUES ('wid@mail.ru', '456');