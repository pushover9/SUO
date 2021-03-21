drop SEQUENCE chatID_seq;
drop table chat;

create table chat(
chatID INT primary key,
chatName varchar2(30),
chatContent varchar2(200),
chatTime date

);

create SEQUENCE chatID_seq
start with 1
INCREMENT by 1
NOCACHE;


select chatID_SEQ.NEXTVAL FROM DUAL;

SELECT * FROM CHAT;