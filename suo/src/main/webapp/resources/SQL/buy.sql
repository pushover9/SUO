--회원테이블
create table tbl_member20 (
    userId      varchar2(50)    not null,
    userPass    varchar2(100)   not null,
    userName    varchar2(30)    not null,
    userPhon    varchar2(20)    not null,
    userAddr1   varchar2(20)    null,
    userAddr2   varchar2(50)    null,
    userAddr3   varchar2(50)    null,
    regiDate    date            default sysdate,
    verify      number          default 0,
    primary key(userId)
);

update TBL_MEMBER20 set verify=9 where userId='admin';
insert into tbl_member20 (userId,userPass,userName,userPhon,verify)
values('ysj','$2a$10$jZKQlK.03WhIlebbU.nTBO/2XEllqkOWRDhRSc5/J9cWDlZKIo6pO','양서준','01012341234',9);

select * from tbl_member20;

 ysj    $2a$10$jZKQlK.03WhIlebbU.nTBO/2XEllqkOWRDhRSc5/J9cWDlZKIo6pO 양서준      01012341234 NULL      NULL      NULL      2021-03-18 10:25:00.0      0



delete from tbl_member20;


create table tbl_goods (
    gdsNum       number          not null,
    gdsName      varchar2(50)    not null,
    cateCode     varchar2(30)    not null,
    gdsPrice     number          not null,
    gdsStock     number          null,
    gdsDes       varchar(500)    null,
    gdsImg       varchar(200)    null,
    gdsDate      date            default sysdate,
    primary key(gdsNum)  
);

alter table tbl_goods add (gdsThumbImg varchar(200));

create sequence tbl_goods_seq
start with 1
increment by 1
nocache;

--카테고리
create table goods_category (
    cateName     varchar2(20)    not null,
    cateCode     varchar2(30)    not null,
    cateCodeRef  varchar2(30)    null,
    primary key(cateCode),
    foreign key(cateCodeRef) references goods_category(cateCode)
);



select level, cateName, cateCode, cateCodeRef from goods_category
    start with cateCodeRef is null connect by prior cateCode = cateCodeRef;  
    
insert into goods_category (cateName, cateCode) values ('롤 굿즈', '100');
insert into goods_category (cateName, cateCode, cateCodeRef) values ('피규어', '101', '100');
insert into goods_category (cateName, cateCode, cateCodeRef) values ('인형', '102', '100');
insert into goods_category (cateName, cateCode, cateCodeRef) values ('악세사리', '103', '100');
insert into goods_category (cateName, cateCode, cateCodeRef) values ('스태츄', '104', '100');

insert into goods_category (cateName, cateCode) values ('옷', '200');
insert into goods_category (cateName, cateCode, cateCodeRef) values ('티셔츠', '201', '200');
insert into goods_category (cateName, cateCode, cateCodeRef) values ('후드티와 자켓', '202', '200');

insert into goods_category (cateName, cateCode) values ('서적', '300');

insert into goods_category (cateName, cateCode) values ('보드게임', '400');




alter table tbl_goods add
    constraint fk_goods_category
    foreign key (cateCode)
        references goods_category(cateCode);


create table tbl_reply20 (
    gdsNum      number          not null,
    userId      varchar2(50)    not null,
    repNum      number          not null,
    repCon      varchar2(2000)  not null,
    repDate     date            default sysdate,
    primary key(gdsNum, repNum) 
);



create sequence tbl_reply_seq20
start with 1
increment by 1
nocache;


alter table tbl_reply20
    add constraint tbl_reply_gdsNum foreign key(gdsNum)
    references tbl_goods(gdsNum);
   
alter table tbl_reply20
    add constraint tbl_reply_userId foreign key(userId)
    references tbl_member20(userId);


--카트테이블

create table tbl_cart (
    cartNum     number          not null,
    userId      varchar2(50)    not null,
    gdsNum      number          not null,
    cartStock   number          not null,
    addDate     date            default sysdate,
    primary key(cartNum, userId) 
);
 
create sequence tbl_cart_seq
start with 1
increment by 1
nocache;

 alter table tbl_cart
    add constraint tbl_cart_userId foreign key(userId)
    references tbl_member20(userId);

alter table tbl_cart
    add constraint tbl_cart_gdsNum foreign key(gdsNum)
    references tbl_goods(gdsNum);

--주문테이블

create table tbl_order (
    orderId     varchar2(150) not null,
    userId      varchar2(150) not null,
    orderRec    varchar2(150) not null,
    userAddr1   varchar2(120) not null,
    userAddr2   varchar2(150) not null,
    userAddr3   varchar2(150) ,
    orderPhon   varchar2(130) not null,
    amount      number       not null,
    orderDate   Date         default sysdate,   
    primary key(orderId)
);

delete from tbl_order;

select * from tbl_order;

alter table tbl_order
    add(
        delivery    varchar2(20)    default '배송준비'
    );

--주문상세 테이블

create table tbl_order_details (
    orderDetailsNum number       not null,
    orderId         varchar2(50) not null,
    gdsNum          number          not null,
    cartStock       number          not null,
    primary key(orderDetailsNum)
);

create sequence tbl_order_details_seq
start with 1
increment by 1
nocache;
