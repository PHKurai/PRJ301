USE master;

CREATE DATABASE workshop1;

USE workshop1;

CREATE TABLE tblUsers (
	Username VARCHAR(50) NOT NULL,
	Name VARCHAR(100) NOT NULL,
	Password VARCHAR(255) NOT NULL,
	Role VARCHAR(20) NOT NULL,
	PRIMARY KEY (Username),
	CONSTRAINT CHK_tblUsers_Role CHECK (Role IN ('Founder', 'Team Member'))
);

CREATE TABLE tblStartupProjects (
	project_id INT NOT NULL,
	project_name VARCHAR(100) NOT NULL,
	Description TEXT,
	Status VARCHAR(20) NOT NULL,
	estimated_launch DATE NOT NULL,
	PRIMARY KEY (project_id),
	CONSTRAINT CHK_tblStartupProjects_Status CHECK (Status IN ('Ideation', 'Development', 'Launch', 'Scaling'))
);

INSERT INTO tblUsers (Username, Name, Password, Role)
VALUES
	('founder01', 'Nguyen Van A', 'securepass123', 'Founder'),
	('founder02', 'Tran Thi B', 'password789', 'Founder'),
	('member01', 'Nguyen Thi C', 'mypassword456', 'Team Member'),
	('member02', 'Pham Minh D', 'pass321', 'Team Member');

INSERT INTO tblStartupProjects(project_id, project_name, Description, Status, estimated_launch)
VALUES
	(1, 'Tech Innovate', 'A startup focused on AI-driven solutions.', 'Ideation', '2025-06-15'),
	(2, 'Eco Ventures', 'A green energy startup.', 'Development', '2025-09-01'),
	(3, 'HealthSync', 'A telemedicine platform for remote healthcare.', 'Launch', '2025-04-20'),
	(4, 'EduNext', 'An EdTech platform with AI-powered learning.', 'Scaling', '2025-11-10');