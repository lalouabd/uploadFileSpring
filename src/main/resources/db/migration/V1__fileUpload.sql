CREATE TABLE FileUP(
    id UUID NOT NULL PRIMARY KEY,
    filename varchar(200) NOT NULL,

   path varchar(200) NOT NULL,
    link varchar(200) NOT NULL,

     f_size   DOUBLE PRECISION,
     ownerName varchar(200)
);