-- create and select the database
DROP DATABASE IF EXISTS bigbox2db;
CREATE DATABASE bigbox2db;
USE bigbox2db;

-- create the divisions table
CREATE TABLE divisions (
  ID			INT				PRIMARY KEY  AUTO_INCREMENT,
  DivisionNbr	VARCHAR(3)		NOT NULL,
  Name			VARCHAR(40)		NOT NULL,
  Address		VARCHAR(120)	NOT NULL,
  City			VARCHAR(20)		NOT NULL,
  State			VARCHAR(2)		NOT NULL,
  Zip			VARCHAR(10)		NOT NULL
);

-- insert some rows into the divisions table
INSERT INTO divisions VALUES 
(1, '001', 'Cincinnati Division Office', '2200 Fields Ertel Rd.', 'Mason', 'OH', '45249'),
(2, '111', 'Louisville Division Office', '4000 Liberty St.', 'Louisville', 'KY', '40204');

-- create the stores table
CREATE TABLE stores (
  ID			INT				PRIMARY KEY  AUTO_INCREMENT,
  DivisionID	INT				NOT NULL,
  StoreNbr		VARCHAR(5)    	NOT NULL,
  Name			VARCHAR(40)		NOT NULL,
  Address		VARCHAR(120)	NOT NULL,
  City			VARCHAR(20)		NOT NULL,
  State			VARCHAR(2)		NOT NULL,
  Zip			VARCHAR(10)		NOT NULL,
  Foreign Key (DivisionID) references divisions (ID)
);

-- insert some rows into the stores table
INSERT INTO stores VALUES 
(1, 1, '00011', 'Mason Big Box', '5711 Fields Ertel Rd.', 'Mason', 'OH', '45249'),
(2, 1, '00255', 'Downtown Big Box','9330 Main St.', 'Cincinnati', 'OH', '45249'),
(3, 1, '00172', 'Goshen Big Box','6777 Goshen Rd.', 'Goshen', 'OH', '45249'),
(4, 1, '00075', 'Bridgetown Big Box','3888 Race Rd.', 'Cincinnati', 'OH', '45249'),
(5, 2, '00011', 'Louisville Big Box','1111 Washington St.', 'Louisville', 'KY', '40206'),
(6, 2, '00044', 'Riverfront Big Box','8000 Liberty St.', 'Louisville', 'KY', '40204');

-- create the store_sales table
CREATE TABLE store_sales (
  ID			INT				PRIMARY KEY  AUTO_INCREMENT,
  StoreID		INT		    	NOT NULL,
  Year			INT		    	NOT NULL,
  Week			INT		    	NOT NULL,
  Sales			DECIMAL(10,2)	NOT NULL,
  CONSTRAINT store_year_week unique (StoreID, Year, Week),
  Foreign Key (StoreId) references stores (ID)
  ON DELETE CASCADE
);

-- insert some rows into the Store_Sales table
INSERT INTO store_sales (StoreID, Year, Week, Sales) VALUES
(1, 2017, 2, 28448.81),
(1, 2017, 3, 34933.54),
(1, 2017, 4, 41022.22),
(1, 2017, 5, 27188.33),
(2, 2017, 10, 68332.12),
(2, 2017, 11, 69211.44),
(2, 2017, 12, 82119.15),
(2, 2017, 13, 56281.90),
(2, 2017, 14, 72118.28),
(3, 2017, 21, 45543.27),
(3, 2017, 22, 50921.01),
(3, 2017, 23, 48721.73),
(3, 2017, 24, 46718.77),
(3, 2017, 25, 44832.14),
(3, 2017, 26, 45383.28),
(3, 2017, 27, 47199.08),
(4, 2017, 2, 17281.44),
(4, 2017, 3, 16728.34),
(5, 2017, 11, 133815.39),
(5, 2017, 12, 127882.55),
(5, 2017, 13, 166882.73),
(5, 2017, 14, 154388.00),
(6, 2017, 16, 54883.38),
(6, 2017, 17, 87388.18),
(6, 2017, 18, 60991.67);

-- create a user and grant privileges to that user
GRANT SELECT, INSERT, DELETE, UPDATE
ON bigbox2db.*
TO bb_user@localhost
IDENTIFIED BY 'sesame';