CREATE TABLE author(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(1000),
    last_name  VARCHAR(1000),
    email      VARCHAR(1000)
);

CREATE TABLE book(
    id SERIAL PRIMARY KEY,
    name VARCHAR(1000),
    count_pages INT,
    author_id INT,
    FOREIGN KEY (author_id)
        REFERENCES author
);

CREATE TABLE journal(
    id SERIAL PRIMARY KEY,
    name VARCHAR(1000),
    count_pages INT,
    number INT,
    publication_year INT
);

ALTER TABLE author
ADD UNIQUE (email);

ALTER TABLE book DROP COLUMN author_id;

CREATE TABLE book_author(
    book_author_id SERIAL PRIMARY KEY,
    book_id BIGINT REFERENCES book(id),
    author_id BIGINT REFERENCES author(id),
    UNIQUE (book_id, author_id)
);

ALTER TABLE book
ADD UNIQUE (name);

ALTER TABLE book_author
ALTER COLUMN book_id TYPE INT;

ALTER TABLE book_author
ALTER COLUMN author_id TYPE INT;

CREATE TABLE users
(
    id         serial,
    first_name VARCHAR(1000) NOT NULL,
    last_name  VARCHAR(1000) NOT NULL,
    email      VARCHAR(1000),
    user_role  VARCHAR(100),
    status     VARCHAR(1000),
    password   VARCHAR(1000 char) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(email)
);
