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

insert into myUser values ( 1, 'Hans', 'Maier', to_date('1900-02-01', 'yyyy-mm-dd'), to_date('03/2023/01', 'mm/yyyy/dd') , null, 'h', 'password');
insert into myUser values ( 2, 'Max', 'Schulze', to_date('1900-02-01', 'yyyy-mm-dd'), to_date('03/2023/01', 'mm/yyyy/dd'), null, 'm', 'password2');
insert into myUser values ( 3, 'Janina', 'Vogt', to_date('2000-10-10', 'yyyy-mm-dd'), to_date('04/2023/04', 'mm/yyyy/dd') , null, 'janina', 'Password3' );
insert into myUser values ( 4, 'Nina', 'Roth', to_date('1997-02-02', 'yyyy-mm-dd'), to_date('04/2023/05', 'mm/yyyy/dd') , null, 'nina', 'PassWord4' );
insert into myUser values ( 5, 'Gorlov','Graf', to_date('1900-02-01', 'yyyy-mm-dd'), to_date('06/2023/05', 'mm/yyyy/dd'), null, 'graf', 'Gorlov' );

insert into myList values ( 100, '1', to_date('2023-01-01', 'yyyy-mm-dd'), 'Liste 1' );
insert into myList values ( 200, '2', to_date('2023-02-01', 'yyyy-mm-dd'),'Liste 2' );
insert into myList values ( 300, '3', to_date('2023-04-04', 'yyyy-mm-dd'),'Liste 3' );
insert into myList values ( 400, '4', to_date('2023-04-05', 'yyyy-mm-dd'),'Liste 4' );
insert into myList values ( 500, '5', to_date('2023-11-11', 'yyyy-mm-dd'),'Liste 5' );
insert into myList values ( 600, '4', to_date('2023-04-05', 'yyyy-mm-dd'),'Liste 6' );
insert into myList values ( 700, '4', to_date('2023-04-05', 'yyyy-mm-dd'),'Liste 7' );
insert into myList values ( 800, '4', to_date('2023-04-05', 'yyyy-mm-dd'),'Liste 8' );


insert into userRights values ( 1, 100, 1);
insert into userRights values ( 1, 200, 0);
insert into userRights values ( 1, 300, 2);
insert into userRights values ( 2, 300, 2);


insert into userRights (userId, listId, right) values ( 2, 100, 2 );
insert into userRights values ( 2, 300, 1 );

insert into userRights (userId, listId, right) values ( 3, 100, 1 );
insert into userRights values ( 3, 200, 2);
insert into userRights values ( 3, 500, 0);



insert into listEntry (entryId, listId, responsible, creator, creationDate,endTime,status, toDo) values (1, 400, 4, 1, to_date('2023-02-01', 'yyyy-mm-dd'), to_date('2023-08-01', 'yyyy-mm-dd'), 1, 'Lernen'); 
insert into listEntry (entryId, listId, responsible, creator, creationDate,endTime,status, toDo) values (2, 400, 4, 1, to_date('2023-02-01', 'yyyy-mm-dd'), to_date('2023-08-01', 'yyyy-mm-dd'), 1, 'Prüfung scheiben'); 
insert into listEntry (entryId, listId, responsible, creator, creationDate,endTime,status, toDo) values (3, 100, 1, 4, to_date('2023-02-01', 'yyyy-mm-dd'), to_date('2023-08-01', 'yyyy-mm-dd'), 1, 'Programmieren'); 
insert into listEntry (entryId, listId, responsible, creator, creationDate,endTime,status, toDo) values (4, 100, 1, 4, to_date('2023-02-01', 'yyyy-mm-dd'), to_date('2023-08-01', 'yyyy-mm-dd'), 1, 'Abgabe'); 
insert into listEntry (entryId, listId, responsible, creator, creationDate,endTime,status, toDo) values (5, 100, 1, 1, to_date('2023-02-01', 'yyyy-mm-dd'), to_date('2023-08-01', 'yyyy-mm-dd'), 1, 'Präsentation vorbereiten'); 


commit;

select * from myUser;
select * from myList;

select u.*, l.*
from myUser u, mylist l, userRights ur
where u.myUserId = ur.myuser
and ur.listid = l.listid;

select * from userRights;

select * from myUser; 
select * from myList; 
