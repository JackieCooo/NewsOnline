create table news_users
(
    usid  int auto_increment
        primary key,
    uname varchar(20) not null,
    upwd  varchar(20) not null
);

INSERT INTO news_online.news_users (usid, uname, upwd) VALUES (1, 'admin', 'admin');
INSERT INTO news_online.news_users (usid, uname, upwd) VALUES (3, 'zhang', '0000');
INSERT INTO news_online.news_users (usid, uname, upwd) VALUES (4, 'mike', '0000');
INSERT INTO news_online.news_users (usid, uname, upwd) VALUES (5, 'jack', '999');
INSERT INTO news_online.news_users (usid, uname, upwd) VALUES (10, 'danny', '32333');
