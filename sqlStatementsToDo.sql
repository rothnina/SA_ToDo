drop table myUser cascade constraints;
drop table mylist cascade constraints;
drop table userRights cascade constraints;
drop table listEntry cascade constraints purge;

create table myUser (
    myUserId integer primary key, 
    firstName varchar2(20), 
    lastName varchar2(20), 
    birthday date,
    registrationDate date, 
    deregistrationDate date, 
    userName varchar2 (20),
    password varchar2(20),
    unique(userName)
);

create table myList (
    listId integer primary key,
    creator integer, 
    creationDate date,
    listName varchar2(20),
    foreign key (creator) references myuser (myuserid)
);

create table userRights (
    myUser references myUser(myUserId) not null,
    listId references myList (listId) not null,
    right integer,
    primary key (myUser, listId)
);

create table listEntry (
     entryId integer, 
     listId integer, 
     responsible integer, 
     creator integer, 
     creationDate date, 
     endTime date, 
     status integer, 
     toDo varchar2(100), 
     primary key (entryId), 
     foreign key (listId) references myList(listId),
     foreign key (responsible) references myUser(myUserId), 
     foreign key (creator) references myUser(myUserId)
);

select sysdate from dual;

insert into myUser values ( 1, 'Hans', 'Maier', '01.01.1900', '01.01.2023' , null, 'h', 'password');
insert into myUser values ( 2, 'Max', 'Schulze', to_date('1900-02-01', 'yyyy-mm-dd'), to_date('03/2023/01', 'mm/yyyy/dd'), null, 'm', 'password2');
insert into myUser values ( 3, 'Janina', 'Vogt', '10.10.2000', '04.04.2023' , null, 'janina', 'Password3' );
insert into myUser values ( 4, 'Nina', 'Roth', '02.02.1997', '04.05.2023' , null, 'nina', 'PassWord4' );
insert into myUser values ( 5, 'Gorlov','Graf', '01.01.1900', '06.05.2023' , null, 'graf', 'Gorlov' );

insert into myList values ( 100, '1', '01.01.2023', 'Liste 1' );
insert into myList values ( 200, '2', '01.01.2023','Liste 2' );
insert into myList values ( 300, '3', '04.04.2023','Liste 3' );
insert into myList values ( 400, '4', '04.05.2023','Liste 4' );
insert into myList values ( 500, '5', '11.11.2023','Liste 5' );

insert into userRights values ( 1, 100, 1);
insert into userRights values ( 1, 200, 0);
insert into userRights values ( 1, 300, 2);
insert into userRights values ( 2, 300, 2);


insert into userRights (userId, listId, right) values ( 2, 100, 2 );
insert into userRights values ( 2, 300, 1 );

insert into userRights (userId, listId, right) values ( 3, 100, 1 );
insert into userRights values ( 3, 200, 2);
insert into userRights values ( 3, 500, 0);



insert into listEntry (entryId, listId, responsible, creator, creationDate,endTime,status, toDo) values (1, 100, 1, 1, '01.02.2023', '01.08.2023', 1, 'Kochen'); 


commit;

select * from myUser;
select * from myList;

select u.*, l.*
from myUser u, mylist l, userRights ur
where u.myUserId = ur.myuser
and ur.listid = l.listid;

select * from userRights;