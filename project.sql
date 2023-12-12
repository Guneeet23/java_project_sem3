create database hotel_management;

use hotel_management;

create table login
(Name varchar(25),
pass varchar(10),
authority varchar(15)
);

drop table login;

insert into login values("Sanskar", "notty69");

select * from login;

create table Customer
(
 cust_id int primary key,
 Fname varchar(25),
 Lname varchar(25),
 Gender varchar(10),
 Email varchar(25),
PhoneNo bigint, 
 Room_no int , 
 Check_in_date date ,
 No_of_days int,
Deposit int 
);

drop table customer; 

INSERT INTO Customer () 
VALUES
    (1001, 'John','Doe', 'M', 'john.doe@example.com', '1234567890', 101, '02:00:14', 2000),
    (1002, 'Jane','Smith', 'F', 'jane.smith@example.com', '9876543210', 102,'10:00:17', 2500),
    (1003, 'Robert','Johnson', 'M', 'robert.j@example.com', '5555555555', 103, '14:00:00', 1500),
    (1004, 'Alice','Brown', 'F', 'alice.b@example.com', '6666666666', 104, '21:00:12', 3000),
	(1005, 'Michael','Johnson', 'M', 'michael.j@example.com', '7777777777', 105, '07:00:15' , 1750),
    (1006, 'Emily','Wilson', 'F', 'emily.w@example.com',' 8888888888', 106, '06:20:00' , 2200),
    (1007, 'David','Lee', 'M', 'david.lee@example.com', '9999999999', 107, '05:16:28', 2750),
    (1008, 'Sophia','Brown', 'F', 'sophia.b@example.com', '1010101010', 108, '16:18:15' , 3500),
    (1009, 'William','Davis', 'M', 'william.d@example.com', '1111111111', 109, '20:28:12', 3000),
    (1010, 'Olivia','Martin', 'F', 'olivia.m@example.com', '1212121212', 110, '21:00:11', 2000),
    (1011, 'Ethan','Garcia', 'M', 'ethan.g@example.com',' 1313131313', 111,'12:00:10', 2250),
    (1012, 'Ava','Rodriguez', 'F', 'ava.r@example.com', '1414141414', 112, '01:00:00', 2750),
    (1013, 'Liam','Hernandez', 'M', 'liam.h@example.com', '1515151515', 113,'18:16:09', 3200),
    (1014, 'Mia','Martinez', 'F', 'mia.m@example.com', '1616161616', 114, '15:15:00', 2750);
    
    select * from customer;
    delete from customer where  cust_id=null;
    truncate customer;
    
    
create table Bookings
( cust_id int ,
 room_id int,
 check_in_date date, 
 check_out_date date default null
 );
 
 drop table bookings;
 
 create table Roominfo
    (
    room_id int,
     bed_type varchar(25),
     isClean varchar(25) default "Clean",
     price double,
     availability varchar(25) default 'vacant'
     );

drop table roominfo;
select * from roominfo;
desc roominfo;
     
create table EmployeeInfo
 (
 employee_id int primary key,
 first_name varchar(25),
 last_name varchar(25),
 position varchar(25),
 Email varchar(25), 
 PhoneNo varchar(25), 
 date_of_birth date, 
 date_of_hire date,
  salary DOUBLE
 );
 drop table EmployeeInfo;
 
 show tables;
 
 select version();
 
 select * from customer;
 select * from roominfo;
 select * from login;	
 select * from employeeinfo;
 select * from bookings;
 
 insert into login values("archie","hello123","staff"),("guneet","creator123","admin"),("ritika","bye123","staff");
 
 INSERT INTO Roominfo (room_id, bed_type, price)
VALUES 
    (101, 'Single', 100.00),
    (102, 'Double', 130.00),
    (103, 'Double', 120.00),
    (104, 'Single', 110.00),
    (105, 'Single', 95.00),
    (106, 'Double', 150.00);

INSERT INTO EmployeeInfo (employee_id, first_name, last_name, position, Email, PhoneNo, date_of_birth, date_of_hire, salary)
VALUES 
    (1, 'Guneet', 'Singh', 'Manager', 'guneet@example.com', '123-456-7890', '1990-05-15', '2015-09-22', 75000.00),
    (2, 'Ritika', 'Sharma', 'Service Staff', 'ritika@example.com', '987-654-3210', '1993-12-10', '2018-03-14', 60000.00),
    (3, 'Archie', 'Smith', 'Receptionist', 'archie@example.com', '111-222-3333', '1988-08-25', '2017-06-30', 55000.00);

 
 SELECT distinct c.*,b.check_out_date FROM Bookings as b left join customer as c on b.cust_id = c.cust_id;
 
 
 truncate customer;
 truncate bookings;
 truncate employeeinfo;
 truncate login;
 truncate roominfo;