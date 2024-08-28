create table students (
  id       		LONG NOT NULL Primary Key AUTO_INCREMENT,
  number    	LONG NOT NULL UNIQUE,
  name   		VARCHAR(128) NOT NULL 
);

create table courses (
  id       		LONG NOT NULL Primary Key AUTO_INCREMENT,
  studentId   	LONG NOT NULL,	
  name     		VARCHAR(128) NOT NULL,
  grade			LONG NOT NULL
);

alter table courses
  add constraint students_courses_fk foreign key (studentId)
  references students (id);


	
insert into students (number, name)
	values (1234, 'Charlie Brown');
 
insert into students (number, name)
	values (4321, 'Lucy');
 
insert into courses (name, grade, studentId)
values ('Java 1', 83, 1);
 
insert into courses (name, grade, studentId)
values ('Java 3', 92, 2);

insert into courses (name, grade, studentId)
values ('Databases', 78, 2);