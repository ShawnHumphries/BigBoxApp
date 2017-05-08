-- create and select the database
DROP DATABASE IF EXISTS bigboxdb;
CREATE DATABASE bigboxdb;
USE bigboxdb;

-- create the stores table
CREATE TABLE stores (
  ID			INT				PRIMARY KEY  AUTO_INCREMENT,
  DivisionNbr	VARCHAR(3)		NOT NULL,
  StoreNbr		VARCHAR(5)    	NOT NULL,
  Sales			DECIMAL(10,2)	NOT NULL,
  Name			VARCHAR(40)		NOT NULL,
  Address		VARCHAR(120)	NOT NULL,
  City			VARCHAR(20)		NOT NULL,
  State			VARCHAR(2)		NOT NULL,
  Zip			VARCHAR(10)		NOT NULL
);

-- insert some rows into the stores table
INSERT INTO stores VALUES
(1, '001', '00011', '25000.00', 'Mason BigBox', '5711 Fields Ertel Rd', 'Mason', 'OH', '45249'),
(2, '001', '00255', '27500.00', 'Downtown BigBox', '9330 Main St.', 'Cincinnati', 'OH', '45202'),
(3, '001', '00172', '32555.55', 'Goshen BigBox', '6777 Goshen Rd.', 'Goshen', 'OH', '45122'),
(4, '001', '00075', '21425.37', 'Bridgetown BigBox', '3888 Race Rd.', 'Cincinnati', 'OH', '45211'),
(5, '111', '00001', '14432.77', 'Louisville BigBox', '1111 Washington St.', 'Louisville', 'KY', '40206'),
(6, '111', '00044', '17555.11', 'Lawrenceburg BigBox', '8000 Liberty St.', 'Louisville', 'KY', '40204');

-- create a user and grant privileges to that user
GRANT SELECT, INSERT, DELETE, UPDATE
ON bigboxdb.*
TO bigboxdb_user@localhost
IDENTIFIED BY 'sesame';