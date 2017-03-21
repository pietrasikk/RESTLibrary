INSERT INTO USER (USER_ID, USER_LOGIN, USER_NAME, USER_SURNAME, USER_ROLE) VALUES (1l, 'Test_Login_1', 'Test_Name_1', 'Test_Surname_1', 'CUSTOMER');

INSERT INTO BOOK (BOOK_ID, TITLE, AUTHOR_1, BOOK_TYPE, LANGUAGE, PAGES, RELEASE_YEAR, PUBLISHING_HOUSE, ISBN, COPIES, GENRE_TYPE) VALUES (1l, 'Test_title_1', 'Test_author_1', 'BOOK', 'EN', 150, 1960, 'P_H', 'ISBN', 11, 'BIOGRAPHY');
INSERT INTO BOOK (BOOK_ID, TITLE, AUTHOR_1, BOOK_TYPE, LANGUAGE, PAGES, RELEASE_YEAR, PUBLISHING_HOUSE, ISBN, COPIES, GENRE_TYPE) VALUES (2l, 'Test_title_2', 'Test_author_2', 'BOOK', 'EN', 120, 1970, 'P_H', 'ISBN', 2, 'FICTION');
INSERT INTO BOOK (BOOK_ID, TITLE, AUTHOR_1, BOOK_TYPE, LANGUAGE, PAGES, RELEASE_YEAR, PUBLISHING_HOUSE, ISBN, COPIES, GENRE_TYPE) VALUES (3l, 'Test_title_3', 'Test_author_3', 'BOOK', 'EN', 110, 1940, 'P_H', 'ISBN', 5, 'FICTION');
INSERT INTO BOOK (BOOK_ID, TITLE, AUTHOR_1, BOOK_TYPE, LANGUAGE, PAGES, RELEASE_YEAR, PUBLISHING_HOUSE, ISBN, COPIES, GENRE_TYPE) VALUES (4l, 'Test_title_4', 'Test_author_4', 'BOOK', 'EN', 160, 1945, 'P_H', 'ISBN', 5, 'FICTION');

INSERT INTO RENTAL_RECORD (RENTAL_RECORD_ID, BOOK_ID, CUSTOMER_ID, RENTAL_RECORD_STATUS) VALUES (1l, 1l, 1l, 'RENTED');
INSERT INTO RENTAL_RECORD (RENTAL_RECORD_ID, BOOK_ID, CUSTOMER_ID, RENTAL_RECORD_STATUS) VALUES (2l, 2l, 1l, 'RENTED');