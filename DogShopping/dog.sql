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

insert into dog values(dog_seq.nextval, 'Ǫ��', 4000, 'pu.jpg', '������', 10, 20, '��ĥ��', 0);
insert into dog values(dog_seq.nextval, '�ҵ���', 4000, 'bul.jpg', '����', 10, 20, '������', 0);
insert into dog values(dog_seq.nextval, '������', 4000, 'jin.jpg', '���ѹα�', 10, 20, '������', 0);
insert into dog values(dog_seq.nextval, '�ùٰ�', 4000, 'siba.jpg', '�Ϻ�', 10, 20, '������', 0);
insert into dog values(dog_seq.nextval, '��Ʈ����', 4000, 'golden.jpg', '����', 10, 20, '����', 0);

select * from dog;