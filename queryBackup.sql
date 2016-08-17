INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) VALUES ('Wiggins','Bill','1980-11-23 +01:00:00','Loan officer',6500.0,'BOR') RETURNING staff_id;

INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) VALUES ('Potter','Robert','1984-04-19 +02:00:00','Guardian',6000.0,'BOR') RETURNING staff_id;

UPDATE staff SET branch_id = 'DEW' WHERE staff_id = 90026;

INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) VALUES ('Helmer','Sven','2016-01-01 +01:00:00','System Administrator',1.0E8,'DEW') RETURNING staff_id;

DELETE from staff WHERE staff_id = 90027;

INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) VALUES ('Helmer','Sven','1981-02-01 +01:00:00','System Administrator',100000.0,'ERA') RETURNING staff_id;

INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) VALUES ('Pancaldi','Marta','3335-10-03 +02:00:00','Bok',8000.0,'DEW') RETURNING staff_id;

INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) VALUES ('Paisley','Brad','2015-03-01 +01:00:00','Singer',100000.0,'BOR') RETURNING staff_id;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Holmes','Mycroft','1966-10-17 +01:00:00','mholmes@mi6.gov.uk','Diogenes Club, London, UK','ERA') RETURNING user_id;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Watson','John','1971-06-30 +02:00:00',NULL,'221B Baker St, London UK','DEW') RETURNING user_id;

UPDATE library_user SET email = 'jwatson@email.com' WHERE user_id = 20025;

UPDATE staff SET branch_id = 'DEW' WHERE staff_id = 3465;

DELETE from staff WHERE staff_id = 22624;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Holmes','Mycroft','1966-10-17 +01:00:00','mholmes@mi6.gov.uk','Diogenes Club, London, UK','ERA') RETURNING user_id;

UPDATE library_user SET first_name = 'Mycro(so)ft' WHERE user_id = 20028;

DELETE from library_user WHERE user_id = 20028;

INSERT INTO staff (last_name, first_name, date_of_birth, occupation, salary, branch_id) VALUES ('Somebody','Tolove','1999-02-17 +01:00:00','Illbefired',1.0,'BOR') RETURNING staff_id;

UPDATE staff SET salary = 123.0 WHERE staff_id = 90008;

DELETE from staff WHERE staff_id = 90008;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50006;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50006;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50006;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 0;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 0;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 1;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50003;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50003;

UPDATE loan SET return_date = current_date FROM loan_book WHERE loan.loan_id = loan_book.loan_id AND book_id = 70002;

UPDATE loan SET return_date = current_date FROM loan_book WHERE loan.loan_id = loan_book.loan_id AND book_id = 70007;

UPDATE loan SET return_date = current_date WHERE loan_id =50004;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50002;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

UPDATE loan SET return_date = current_date WHERE loan_id =50001;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50005,70002,20002);

UPDATE loan SET return_date = current_date WHERE loan_id =50005;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50005;

UPDATE loan SET return_date = current_date WHERE loan_id =50005;

INSERT INTO loan (user_id) values (20003) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50001;

UPDATE loan SET return_date = current_date WHERE loan_id =50005;

UPDATE loan SET return_date = current_date WHERE loan_id =50003;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

UPDATE loan SET return_date = current_date WHERE loan_id =50003;

UPDATE loan SET return_date = current_date WHERE loan_id =50005;

UPDATE loan SET return_date = current_date WHERE loan_id =50004;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50001;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50001;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

UPDATE loan SET return_date = current_date WHERE loan_id =50003;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

UPDATE loan SET return_date = current_date WHERE loan_id =50003;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50005,70002,20001);

UPDATE loan SET return_date = current_date WHERE loan_id =50003;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50005,70002,20001);

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

UPDATE loan SET return_date = current_date WHERE loan_id =50003;

UPDATE loan SET return_date = return_date + integer '7' WHERE loan_id = 50001;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50004;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

UPDATE loan SET return_date = current_date WHERE loan_id =50004;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50004;

UPDATE loan SET return_date = current_date WHERE loan_id =50003;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

UPDATE loan SET return_date = current_date WHERE loan_id =50002;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50002;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50002;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50003;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50006,70002,20001);

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50002;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50003;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50006;

INSERT INTO loan (user_id) values (20004) RETURNING loan_id;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50005,70002,20002);

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50002;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days';

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50003;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days';

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days';

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50002;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50002;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50002;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50005;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50002;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50003;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50001;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50001;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50002;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50003;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50005,70003,20001);

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50006,70004,20001);

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50006,70006,20001);

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

INSERT INTO loan (user_id) values (20004) RETURNING loan_id;

INSERT INTO loan (user_id) values (20004) RETURNING loan_id;

INSERT INTO loan (user_id) values (20004) RETURNING loan_id;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50005;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50006;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50011,70003,20001);

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50011,70006,20001);

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50011,70004,20001);

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50011;

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('The golden compass','Salani',9788812941837,'EF','Paperback','ERA') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Pullman','Philip','writer') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70008,10008);

UPDATE book SET title = 'The Golden Compass' WHERE book_id = 70008;

UPDATE book SET ISBN = '9788828571234' WHERE book_id = 70008;

UPDATE book SET branch_id = 'BOR' WHERE book_id = 70008;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50004;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Holmes','Mycroft','1966-10-17 +01:00:00','mholmes@mi6.gov.uk','Diogenes Club, London, UK','ERA') RETURNING user_id;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Watson','John','1971-09-08 +02:00:00','jhwatson@gmail.uk','221B Baker St, London, UK','BOR') RETURNING user_id;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('White','Walter','1959-09-07 +01:00:00','breakingbad@mail.org','308 Negra Arroyo Lane, Albuquerque','DEW') RETURNING user_id;

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('The Golden Compass','Yearling',9788804004184,'EF','Paperback','PAN') RETURNING book_id;

INSERT INTO book_author (book_id, author_id) VALUES (70009,10008);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('Matilda','Puffin Books',9788819412353,'EF','Hardcover','BOR') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Dahl','Roald','writer') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70010,10009);

INSERT INTO author (last_name, first_name, role) VALUES ('Blake','Quentin','illustrator') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70010,10010);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('Introduction to Algorithms','MIT Press',9788812841935,'004.319','Harcover','DEW') RETURNING book_id;

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('rfebtd','',1234567892342,'','','BOR') RETURNING book_id;

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('The BFG','Puffin Books',9788812345678,'EF','Paperback','BOR') RETURNING book_id;

INSERT INTO book_author (book_id, author_id) VALUES (70013,10009);

INSERT INTO book_author (book_id, author_id) VALUES (70013,10010);

UPDATE book SET title = 'The BFG' WHERE book_id = 70001;

UPDATE book SET title = 'Le due torri' WHERE book_id = 70001;

UPDATE book SET title = 'The Silmarillion' WHERE book_id = 70001;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan (user_id) values (20002) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50014,70010,20002);

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50014,70013,20002);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('The Golden Compass','Yearling',9788812741342,'EF','Paperback','DEW') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Pullman','Philip','writer') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70014,10011);

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50001;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50001;

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50001;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Morini','Elisabetta','1959-03-12 +01:00:00','bettymorini@yahoo.it','Via B. Corti 3, Reggio Emilia, IT','BOR') RETURNING user_id;

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('The Fellowship of the Ring','Bompiani',9788812941228,'EF','Paperback','DEW') RETURNING book_id;

INSERT INTO book_author (book_id, author_id) VALUES (70015,10001);

INSERT INTO loan (user_id) values (20001) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50015,70015,20001);

INSERT INTO loan (user_id) values (20013) RETURNING loan_id;

INSERT INTO loan (user_id) values (20013) RETURNING loan_id;

INSERT INTO loan (user_id) values (20013) RETURNING loan_id;

INSERT INTO loan (user_id) values (20013) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50019,70014,20013);

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50019;

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('The Da Vinci Code','Nord',9788824231145,'EF','Hardcover','PAN') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Brown','Dan','Writer') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70016,10012);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('Angels and Demons','Nord',9788812345678,'EF','paperback','PAN') RETURNING book_id;

INSERT INTO book_author (book_id, author_id) VALUES (70017,10012);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('Random Title','Publisher',9788811234441,'IF','paperback','BOR') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Brown','Christopher','Writer') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70018,10013);

INSERT INTO loan (user_id) values (20003) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50020,70014,20003);

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50020;

UPDATE library_user SET branch_id = 'BOR' WHERE user_id = 20001;

UPDATE book SET title = 'Der Herr der Ringe - Die Gefährten' WHERE book_id = 70004;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50015;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50015;

UPDATE loan SET return_timestamp = return_timestamp + interval '7 days' where loan_id =50015;

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('Divina Commedia - Inferno','Rizzoli',9788813284125,'800.347.1','Hardcover','ERA') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Alighieri','Dante','writer') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70019,10014);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('Decameron','Mondadori',9788814151353,'800.214.4','Hardcover','ERA') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Boccaccio','Giovanni','Writer') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70020,10015);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('James and the Giant Peach','Puffin Books',9788814851733,'EF','hardcover','DEW') RETURNING book_id;

INSERT INTO book_author (book_id, author_id) VALUES (70021,10009);

INSERT INTO book_author (book_id, author_id) VALUES (70021,10010);

INSERT INTO book (title, publisher, ISBN, collocation, format, branch_id) VALUES ('Introduction to Algorithms 3rd edition','MIT press',9788812361819,'001.316','Hardcover','PAN') RETURNING book_id;

INSERT INTO author (last_name, first_name, role) VALUES ('Cormen','Thomas','author') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70022,10016);

INSERT INTO author (last_name, first_name, role) VALUES ('Leiserson','Charles','author') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70022,10017);

INSERT INTO author (last_name, first_name, role) VALUES ('Rivest','Ronald','author') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70022,10018);

INSERT INTO author (last_name, first_name, role) VALUES ('Stein','Clifford','author') RETURNING author_id;

INSERT INTO book_author (book_id, author_id) VALUES (70022,10019);

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Pancaldi','Umberto','1959-04-04 +01:00:00','upancaldi@gmail.com','Via Vincenzi 1, Reggio Emilia, IT','DEW') RETURNING user_id;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Morini','Lucia','1995-02-08 +01:00:00','lucymor@mail.it','Via Paglia 5, Reggio Emilia, IT','BOR') RETURNING user_id;

INSERT INTO library_user (last_name, first_name, date_of_birth, email, address, branch_id) VALUES ('Helmer','Sven','2016-06-14 +02:00:00','shelmer@unibz.it','Pzza Università 1','DEW') RETURNING user_id;

UPDATE library_user SET address = 'Piazza Domenicani 3' WHERE user_id = 20016;

UPDATE library_user SET branch_id = 'BOR' WHERE user_id = 20016;

INSERT INTO loan (user_id) values (20016) RETURNING loan_id;

INSERT INTO loan (user_id) values (20016) RETURNING loan_id;

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50022,70021,20016);

INSERT INTO loan_book (loan_id, book_id, user_id) VALUES (50022,70022,20016);

UPDATE loan SET return_timestamp = current_timestamp WHERE loan_id =50022;

