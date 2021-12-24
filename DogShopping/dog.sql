select * from tab;

create table  dog(
id number primary key,
kind varchar2(12) not null,
price number not null,
image varchar2(20) not null,
country varchar2(12) not null,
height number,
weight number,
content varchar2(400),
readcount number
);

create sequence dog_seq;

insert into dog values(dog_seq.nextval, '푸들', 4000, 'pu.jpg', '프랑스', 10, 20, '까칠함', 0);
insert into dog values(dog_seq.nextval, '불도그', 4000, 'bul.jpg', '독일', 10, 20, '강인함', 0);
insert into dog values(dog_seq.nextval, '진돗개', 4000, 'jin.jpg', '대한민국', 10, 20, '충직함', 0);
insert into dog values(dog_seq.nextval, '시바견', 4000, 'siba.jpg', '일본', 10, 20, '예민함', 0);
insert into dog values(dog_seq.nextval, '리트리버', 4000, 'golden.jpg', '영국', 10, 20, '순함', 0);

select * from dog;