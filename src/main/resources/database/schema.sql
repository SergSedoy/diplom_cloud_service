CREATE TABLE IF NOT EXISTS user
(
    id       int primary key auto_increment,
    login    varchar(255) not null,
    password varchar(255) not null,
    dtbase varchar(255) not null
);

INSERT INTO user (login, password, dtbase) VALUES ('nika@mail.ru', '123', 'nikadb');
INSERT INTO user (login, password, dtbase) VALUES ('max@mail.ru', '456', 'maxdb');
INSERT INTO user (login, password, dtbase) VALUES ('serg@mail.ru', '789', 'sergdb');

CREATE TABLE IF NOT EXISTS nikadb
(
    id          int primary key auto_increment,
    name        varchar(255) not null ,
    size        int check (size > 0),
    upload_date varchar(50),
    content     longblob not null
);

CREATE TABLE IF NOT EXISTS maxdb
(
    id          int primary key auto_increment,
    name        varchar(255) not null ,
    size        int check (size > 0),
    upload_date varchar(50),
    content     longblob not null
);

CREATE TABLE IF NOT EXISTS sergdb
(
    id          int primary key auto_increment,
    name        varchar(255) not null ,
    size        int check (size > 0),
    upload_date varchar(50),
    content     longblob not null
);