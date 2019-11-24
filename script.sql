drop database if exists MapDB;
create database MapDB;
DROP USER 'MapUser'@'localhost';
create user 'MapUser'@'localhost' identified by 'map';
GRANT CREATE, SELECT, INSERT, DELETE ON MapDB.* TO MapUser@localhost;
use MapDB;
create table playtennis(
    outlook varchar(10),
    temperature float(5,2),
    umidity varchar(10),
    wind varchar(10),
    play varchar(10)
);
insert into playtennis values('sunny',30.3,'high','weak','no');
insert into playtennis values('sunny',30.3,'high','strong','no');
insert into playtennis values('overcast',30.0,'high','weak','yes');
insert into playtennis values('rain',13.0,'high','weak','yes');
insert into playtennis values('rain',0.0,'normal','weak','yes');
insert into playtennis values('rain',0.0,'normal','strong','no');
insert into playtennis values('overcast',0.1,'normal','strong','yes');
insert into playtennis values('sunny',13.0,'high','weak','no');
insert into playtennis values('sunny',0.1,'normal','weak','yes');
insert into playtennis values('rain',12.0,'normal','weak','yes');
insert into playtennis values('sunny',12.5,'normal','strong','yes');
insert into playtennis values('overcast',12.5,'high','strong','yes');
insert into playtennis values('overcast',29.21,'normal','weak','yes');
insert into playtennis values('rain',12.5,'high','strong','no');

create table emptytable(
    attribute1 varchar(10)
);
create table testtable(
    dAttribute varchar(10),
    cAttribute float(5,2)
);
insert into testtable values('value1' , 5.0);
insert into testtable values('value1' , 5.5);
insert into testtable values('value2' , 8.5);