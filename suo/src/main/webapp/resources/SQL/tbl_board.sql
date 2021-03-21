--도혁이형이 준걸로 다시!
create table tbl_board(
 bno number(38) primary key
 ,writer varchar2(100) not null
 ,title varchar2(200) not null
 ,content varchar2(4000)
 ,viewcnt varchar2(4000) default 0
 ,regdate date --등록날짜
 ,reply_cnt number(38) default 0
 ,category varchar2(100) not null
);

drop table tbl_board;

select * from tbl_board order by bno desc;

create sequence bno_seq
start with 1
increment by 1
nocache;

drop sequence bno_seq;

select count(*) from tbl_board;


select * from tbl_board



create table reply(
 rno number(38) primary key --댓글번호
 ,bno number(38) default 0 --게시판 번호값만 저장. 외래키 제약조건이 추가 설정됨. tbl_board7테이블의 bno컬럼을 주인키로 가리키고 있다.
 ,replyer varchar2(20) not null--댓글 작성자
 ,replytext varchar2(4000) not null--댓글 내용
 ,regdate date -- 등록 날짜
 ,updatedate date --수정날짜
);

select * from reply;


alter table reply add constraint reply_bno_fk foreign key(bno) references tbl_board(bno);


create sequence rno_seq
start with 1
increment by 1
nocache;

CREATE TABLE tbl_board_file (
file_num VARCHAR2(150) NOT NULL primary key,
o_name varchar2(150) not null,
file_name varchar2(150) not null,
file_size int not null,
bno number(38) default 0
);


create sequence file_seq
start with 1
increment by 1
nocache;

-- 게시글 첨부파일 테이블 참조키 설정 
ALTER TABLE tbl_board_file ADD CONSTRAINT file_bno_fk FOREIGN KEY (bno) REFERENCES tbl_board(bno); 

select * from tbl_board_file























create table board(
writer varchar2(20)--작성자
,title varchar2(20) --제목
,cont varchar2(2000) --내용
);


insert into board values('양서준','하이하이','게시판연습입니다.');
insert into board values('김성훈','안녕하세요','치킨사주세요');
insert into board values('김주환','누누재밌다','누누드라이브');
insert into board values('이민우','세라핀','세라핀 무시하지마');
insert into board values('조재우','이렐리아','이렐장인이다.');

select * from board;


--방명록 다시 만들어 보자
create table board2(
b_no number(38) primary key --번호
,b_name varchar2(30) not null --글쓴이
,b_title varchar2(200) not null --제목
,b_cont varchar2(4000) --내용
,b_date date	--등록날짜
);

select * from board2;

--board2시퀀스 생성
create sequence b_no_seq
start with 1
increment by 1
nocache;

drop table board2;
drop sequence b_no_seq;




