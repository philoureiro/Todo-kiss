CREATE DATABASE todokiss;

USE todokiss;

CREATE TABLE status(
status_id TINYINT PRIMARY KEY AUTO_INCREMENT,
status VARCHAR(30) NOT NULL
);

CREATE TABLE categories(
category_id TINYINT PRIMARY KEY AUTO_INCREMENT,
category VARCHAR(30) NOT NULL
);

CREATE TABLE tasks(
id INT PRIMARY KEY AUTO_INCREMENT,
description VARCHAR(60) NOT NULL,
sub_description VARCHAR(150) NOT NULL,
statusId TINYINT NOT NULL,
max_date DATE NOT NULL,
categoryId TINYINT NOT NULL,

FOREIGN KEY(statusId) REFERENCES status(status_id),
FOREIGN KEY(categoryId) REFERENCES categories(category_id)

);
