create table jwtToken(
    userId UUID NOT NULL PRIMARY KEY,
    token varchar(500) NOT NULL
);