DROP DATABASE IF EXISTS hospital_management;
CREATE DATABASE hospital_management;

/* Stores All Patients */
DROP TABLE IF EXISTS waitroom;
CREATE TABLE waitroom (
   patient_id INT    NOT NULL AUTO_INCREMENT,
   patient_first_name VARCHAR(255) NOT NULL,
   patient_last_name VARCHAR(320) NOT NULL,
   patient_identification INT(100)   NOT NULL,
   PRIMARY KEY (patient_id);
);

/* Stores ALL Patients who entered Testing Room */
DROP TABLE IF EXISTS testing_room;
CREATE TABLE testing (
   id     INT         NOT NULL AUTO_INCREMENT,
   patient_id INT    NOT NULL AUTO_INCREMENT,
   patient_first_name VARCHAR(255) NOT NULL,
   patient_last_name VARCHAR(320) NOT NULL,
   PRIMARY KEY (patient_id)
   PRIMARY KEY (id)
);

/* Stores ALL Patients who entered Emergency Room */
DROP TABLE IF EXISTS emergency_room;
CREATE TABLE emergency_room (
    user_id INT    NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(320) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name Varchar(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

/* Stores ALL Patients who entered Checkup Room */
DROP TABLE IF EXISTS checkup_room;
CREATE TABLE checkup_room (
    user_id INT    NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(320) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name Varchar(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);