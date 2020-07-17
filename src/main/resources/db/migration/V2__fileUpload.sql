CREATE TABLE Person(
    id UUID NOT NULL ,
    name varchar(200) NOT NULL,
    email varchar(200) NOT NULL PRIMARY KEY,
     dob DATE NOT NULL,
     password varchar(300) NOT NULL,
     image_path varchar(300) NOT NULL

);