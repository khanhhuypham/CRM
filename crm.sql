CREATE DATABASE IF NOT EXISTS crm;

USE crm;

CREATE TABLE IF NOT EXISTS role (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
    primary key(id)
);

CREATE TABLE IF NOT EXISTS user (
	id 				int auto_increment,
    email			varchar(100) not null unique,
    password		varchar(255) not null,
    name 			varchar(100) not null,
    address	 		varchar(255),
    phone			varchar(50),
    role_id			int,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS project (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
    start_date		date,
    end_date		date,
    owner			int,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS project_user (
	project_id		int,
    user_id			int,
    join_date		date,
    role_description varchar(255),
    primary key(project_id, user_id)
);

CREATE TABLE IF NOT EXISTS status (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
    primary key(id)
);

CREATE TABLE IF NOT EXISTS task (
	id 				int auto_increment,
    name 			varchar(100) not null,
    description 	varchar(255),
	start_date		date,
    end_date		date,
    project_id		int,
    user_id			int,
    status_id		int,
    primary key(id)
);

ALTER TABLE user
	ADD CONSTRAINT FK_USER_ROLE
		FOREIGN KEY (role_id) REFERENCES role(id); 
        
ALTER TABLE project
	ADD CONSTRAINT FK_PROJECT_USER_OWNER
		FOREIGN KEY (owner) REFERENCES user(id); 
	
ALTER TABLE project_user
	ADD CONSTRAINT FK_PROJECT_USER_PROJECT_USER_LIST
		FOREIGN KEY (project_id) REFERENCES project(id),
	ADD CONSTRAINT FK_PROJECT_USER_USER_JOIN_LIST
		FOREIGN KEY (user_id) REFERENCES user(id);

ALTER TABLE task
	ADD CONSTRAINT FK_TASK_USER
		FOREIGN KEY (user_id) REFERENCES user(id),
	ADD CONSTRAINT FK_TASK_PROJECT
		FOREIGN KEY (project_id) REFERENCES project(id),
	ADD CONSTRAINT FK_TASK_STATUS
		FOREIGN KEY (status_id) REFERENCES status(id);
        
INSERT INTO role(id,name,description)
VALUES
('1','ADMIN','Quản Lý dự án'),
('2','LEADER','Quán lý nhân sự'),
('3','STAFF','Nhân sự triển khai dự án');
INSERT INTO user(id,email,password,name,address,phone,role_id)
VALUES
('1','a@gmail.com','1234','Nguyễn Văn A','TP.HCM','0123456789','1'),
('2','b@gmail.com','1234','Nguyễn Văn B','TP.HCM','0123456789','2'),
('3','c@gmail.com','1234','Nguyễn Văn C','TP.HCM','0123456789','3'),
('4','d@gmail.com','1234','Nguyễn Văn D','TP.HCM','0123456789','2'),
('5','e@gmail.com','1234','Nguyễn Văn E','TP.HCM','0123456789','2'),
('6','f@gmail.com','1234','Nguyễn Văn F','TP.HCM','0123456789','3'),
('7','g@gmail.com','1234','Nguyễn Văn G','TP.HCM','0123456789','3'),
('8','h@gmail.com','1234','Nguyễn Văn H','TP.HCM','0123456789','3'),
('9','i@gmail.com','1234','Nguyễn Văn I','TP.HCM','0123456789','3'),
('10','k@gmail.com','1234','Nguyễn Văn K','TP.HCM','0123456789','3');
INSERT INTO project(id,name,description,start_date,end_date,owner)
VALUES
('1','Dự Án Web Thương Mại','Budget: 1000$','2021-07-01','2021-12-31','1'),
('2','Dự Án App Game','Budget: 1000$','2021-07-01','2021-12-31','2'),
('3','Dự Án Web Game','Budget: 1000$','2021-07-01','2021-12-31','3');
INSERT INTO project_user(project_id,user_id,join_date,role_description)
VALUES
/*Dự án 1*/
('1','1','2021-07-01','Quản Lý dự án'),
('1','2','2021-07-01','Quán lý nhân sự'),
('1','3','2021-07-01','Nhân sự triển khai dự án'),
('1','10','2021-07-01','Nhân sự triển khai dự án'),
/* Dự án 2*/
('2','4','2021-07-01','Quán lý nhân sự'),
('2','6','2021-07-01','Nhân sự triển khai dự án'),
('2','7','2021-07-01','Nhân sự triển khai dự án'),
('2','8','2021-07-01','Nhân sự triển khai dự án'),
('2','9','2021-07-01','Nhân sự triển khai dự án'),
/* Dự án 3*/
('3','5','2021-07-01','Quán lý nhân sự');
INSERT INTO status(id,name,description)
VALUES
('1','Đã hoàn thành','Khách hàng đã thanh toán'),
('2','Chưa hoàn thành','Khách hàng chưa thanh toán'),
('3','Tạm hoãn','Khách Hàng hủy hợp đồng');
INSERT INTO task(id,name,description,start_date,end_date,project_id,user_id,status_id)
VALUES
/* Dự án 1*/
('1','Quản lý dự án','Điều phối nguồn lực','2021-07-01','2021-12-31','1','1','1'),
('2','Quản lý nhân sự','Điều phối nguồn lực','2021-07-01','2021-12-31','1','2','1'),
('3','Code CRM ','Sử dụng Sping Boot','2021-07-01','2021-12-31','1','3','1'),
('4','Code CRM','Sử dụng Sping Boot','2021-07-01','2021-12-31','1','10','1'),
/* Dự án 2 */
('5','Quản lý dự án','Điều phối nguồn lực','2021-07-01','2021-12-31','3','4','2'),
('6','Code CRM ','Sử dụng Sping Boot','2021-07-01','2021-12-31','3','6','2'),
('7','Code CRM ','Sử dụng Sping Boot','2021-07-01','2021-12-31','3','7','2'),
('8','Code CRM ','Sử dụng Sping Boot','2021-07-01','2021-12-31','3','8','2'),
('9','Code CRM ','Sử dụng Sping Boot','2021-07-01','2021-12-31','3','9','2'),
/*Dự án 3*/
('10','Quản lý dự án','Điều phối nguồn lực','2021-07-01','2021-12-31','3','5','2');