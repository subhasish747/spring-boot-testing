CREATE SCHEMA IF NOT EXISTS dev ;

CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);
