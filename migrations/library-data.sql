CREATE TABLE IF NOT EXISTS book (
                                    isbn VARCHAR(255) PRIMARY KEY,
    author VARCHAR(255),
    copies INT,
    title VARCHAR(255),
    year INT
    );

CREATE TABLE IF NOT EXISTS library_account (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               student_id VARCHAR(255) UNIQUE NOT NULL,
    pin VARCHAR(255)
    );


CREATE TABLE IF NOT EXISTS transaction (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           date_borrowed DATE,
                                           date_returned DATE,
                                           due_date DATE,
                                           student_id VARCHAR(255),
    book_isbn VARCHAR(255),
    CONSTRAINT fk_transaction_book FOREIGN KEY (book_isbn) REFERENCES book (isbn),
    CONSTRAINT fk_transaction_student FOREIGN KEY (student_id) REFERENCES library_account (student_id)
    );


INSERT INTO book (isbn, author, copies, title, year) VALUES ('111111', 'Carl', '4', 'Hello World Book', '2000' );
INSERT INTO book (isbn, author, copies, title, year) VALUES ('222222', 'Jason', '5', 'Java Spring Boot', '2023' );
INSERT INTO book (isbn, author, copies, title, year) VALUES ('333333', 'Harry', '2', 'Python Programming', '2010' );
INSERT INTO book (isbn, author, copies, title, year) VALUES ('444444', 'Daniel', '1', 'Programming Basics', '1990' );
INSERT INTO book (isbn, author, copies, title, year) VALUES ('555555', 'John', '3', 'Web Development', '2000' );
INSERT INTO book (isbn, author, copies, title, year) VALUES ('666666', 'Kat', '4', 'Mobile App', '2008' );

INSERT into library_account(student_id, pin) VALUES ('admin', '000000');
INSERT into library_account(student_id, pin) VALUES ('c0000011', '000000');

INSERT into transaction(date_borrowed, date_returned, due_date, student_id, book_isbn) VALUES ('2025-04-20', NULL, '2025-05-04', 'c0000011', '111111');
INSERT into transaction(date_borrowed, date_returned, due_date, student_id, book_isbn) VALUES ('2025-04-01', NULL, '2025-04-15', 'c0000011', '222222');
INSERT into transaction(date_borrowed, date_returned, due_date, student_id, book_isbn) VALUES ('2025-04-01', '2025-04-10', '2025-04-15', 'c0000011', '333333');
INSERT into transaction(date_borrowed, date_returned, due_date, student_id, book_isbn) VALUES ('2025-04-20', NULL, '2025-05-04', 'c0000011', '666666');


