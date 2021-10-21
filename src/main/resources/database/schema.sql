CREATE TABLE IF NOT EXISTS datefiles
(
    id          int primary key auto_increment,
    name        varchar(255),
    file_type   varchar(255),
    size        int check (size > 0),
    upload_date varchar(255),
    key_dec     varchar(255)
);

# CREATE TABLE IF NOT EXISTS orders
# (
#     id           int primary key auto_increment,
#     date         varchar(255),
#     customer_id  int NOT NULL,
#     product_name varchar(255),
#     amount       int
# );