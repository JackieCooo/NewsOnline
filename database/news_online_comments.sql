create table comments
(
    cid      int auto_increment
        primary key,
    cnid     int           not null,
    ccontent varchar(3000) not null,
    cdate    datetime      null,
    cip      varchar(100)  not null,
    cauthor  varchar(100)  not null
);

INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (22, 82, '评论一下 我是admin', '2016-09-30 11:02:57', '127.0.0.1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (23, 81, '低碳生活', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (26, 86, '隔岸观火吃鸡腿~！', '2016-09-30 11:02:57', '127.0.0.1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (32, 73, '无聊！！', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (33, 108, '很饱啊', '2016-09-30 11:02:57', '127.0.0.1', '妈妈');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (52, 108, '我郁闷啊！！', '2016-09-30 11:02:57', '127.0.0.1', '那只蛇');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (53, 108, '虫子，你妈妈叫你回家吃饭', '2016-09-30 11:02:57', '127.0.0.1', '打酱油的');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (54, 108, '别回来了，都吃完了', '2016-09-30 11:02:57', '127.0.0.1', '那只虫子');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (110, 79, '支持祖国！！', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (111, 79, '非常支持您', '2016-09-30 11:02:57', '127.0.0.1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (112, 79, '非常支持您', '2016-09-30 11:02:57', '127.0.0.1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (113, 79, '非常支持您', '2016-09-30 11:02:57', '127.0.0.1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (114, 73, 'Hello!您好！', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (115, 77, '坚决打击“两 抢', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (131, 73, '很好!', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (151, 165, '是的，是很懒', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (152, 165, '很好', '2016-09-30 11:02:57', '127.0.0.1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (160, 69, '很好！', '2016-09-30 11:02:57', '127.0.0.1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (161, 165, '呵呵。。。。。', '2016-09-30 11:02:57', '0:0:0:0:0:0:0:1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (162, 79, 'very good', '2016-09-30 11:02:57', '0:0:0:0:0:0:0:1', 'admin');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (163, 165, 'Ok,好', '2016-09-30 11:02:57', '0:0:0:0:0:0:0:1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (164, 165, 'very good', '2016-09-30 11:02:57', '0:0:0:0:0:0:0:1', '这家伙很懒什么也没留下');
INSERT INTO news_online.comments (cid, cnid, ccontent, cdate, cip, cauthor) VALUES (165, 165, 'very good.', '2016-09-30 11:02:57', '0:0:0:0:0:0:0:1', '这家伙很懒什么也没留下');
