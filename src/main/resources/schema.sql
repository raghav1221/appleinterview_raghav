drop table if exists URL;
create table URL(id int auto_increment primary key, actualUrl varchar(50) NOT NULL, shortUrl varchar(50) NOT NULL,createdDate date,expirationDate date);