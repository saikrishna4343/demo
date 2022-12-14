CREATE TABLE IF NOT EXISTS users(
    user_id varchar(255) PRIMARY KEY,
    first_name varchar(255) NULL,
    last_name varchar(255) NULL,
    email varchar(255) NOT NULL
);