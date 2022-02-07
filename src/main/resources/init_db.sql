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