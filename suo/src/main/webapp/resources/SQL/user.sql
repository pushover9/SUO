--사용자 계정

create table user_account(
 user_no number(38)
 ,user_id varchar2(30) primary key --아이디
 ,user_pw varchar2(200) not null -- 비번 암호화
 ,user_name varchar2(20) not null -- 관리자 이름
 ,user_email01 varchar2(200) --이메일
 ,user_email02 varchar2(200) --이메일2
 ,user_phone01 varchar2(10) not null --전번1
 ,user_phone02 varchar2(10) not null --전번2
 ,user_phone03 varchar2(10) not null --전번3
 ,user_date date --등록 날짜
);

drop table user_account;


select * from user_account;

select * from user_account where user_name='fdas' and user_email01='fdsa' and user_email02='@dreamwiz.com';
update user_account set user_pw='fdsa' where user_id='user01';



create sequence user_acc_seq
start with 1
increment by 1
nocache;