--회원가입 테이블 생성
create table sign_up(
mem_name varchar2(50) not null --회원이름
,mem_id varchar2(200) primary key --회원아이디
,mem_pwd varchar2(50) not null --회원비밀번호
,mem_phone01 varchar2(10) --폰번호
,mem_phone02 varchar2(10) --폰번호
,mem_phone03 varchar2(10) --폰번호
,mem_email_id varchar2(50) --메일 아이디
,mem_email varchar2(100) --메일 도메인
,mem_date date --가입날짜
,mem_state number(38) --가입회원이면 1, 탈퇴회원이면 2
,mem_delcont varchar2(4000) --탈퇴사유
,mem_deldate date --탈퇴날짜
);

select * from sign_up;

