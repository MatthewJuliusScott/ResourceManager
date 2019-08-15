INSERT INTO skill(`name`) VALUES('Java');
INSERT INTO skill(`name`) VALUES('MySQL');
INSERT INTO skill(`name`) VALUES('C#');
INSERT INTO skill(`name`) VALUES('C++');
INSERT INTO skill(`name`) VALUES('Python');
INSERT INTO skill(`name`) VALUES('Ruby');
INSERT INTO skill(`name`) VALUES('HTML');
INSERT INTO skill(`name`) VALUES('CSS');
INSERT INTO skill(`name`) VALUES('PHP');
INSERT INTO skill(`name`) VALUES('JavaScript');

INSERT INTO resource(`name`,`hours`) VALUES('Ernest Hemingway', 40);
INSERT INTO resource(`name`,`hours`) VALUES('Leo Tolstoy', 40);
INSERT INTO resource(`name`,`hours`) VALUES('Jane Austen', 15);
INSERT INTO resource(`name`,`hours`) VALUES('Charles Dickens', 40);
INSERT INTO resource(`name`,`hours`) VALUES('William Shakespeare', 40);
INSERT INTO resource(`name`,`hours`) VALUES('F. Scott Fitzgerald', 40);
INSERT INTO resource(`name`,`hours`) VALUES('Stephen King', 40);
INSERT INTO resource(`name`,`hours`) VALUES('Mark Twain', 40);
INSERT INTO resource(`name`,`hours`) VALUES('William Faulkner', 40);
INSERT INTO resource(`name`,`hours`) VALUES('Virginia Woolf', 40);
INSERT INTO resource(`name`,`hours`) VALUES('James Joyce', 40);
INSERT INTO resource(`name`,`hours`) VALUES('Vladamir Nabokov', 40);
INSERT INTO resource(`name`,`hours`) VALUES('George Orwell', 40);
INSERT INTO resource(`name`,`hours`) VALUES('J. R. R Tolkien', 30);
INSERT INTO resource(`name`,`hours`) VALUES('Fyodor Dostoyevsky', 40);
INSERT INTO resource(`name`,`hours`) VALUES('J. D. Salinger', 42);
INSERT INTO resource(`name`,`hours`) VALUES('Franz Kafka', 40);
INSERT INTO resource(`name`,`hours`) VALUES('C. S. Lewis', 20);
INSERT INTO resource(`name`,`hours`) VALUES('Toni Morrison', 40);
INSERT INTO resource(`name`,`hours`) VALUES('J. K. Rowling', 20);
INSERT INTO resource(`name`,`hours`) VALUES('Herman Melville', 40);
INSERT INTO resource(`name`,`hours`) VALUES('John Steinbeck', 40);

INSERT INTO project(`name`) VALUES('Manhattan Project');
INSERT INTO project(`name`) VALUES('Operation Anthropoid');
INSERT INTO project(`name`) VALUES('Project Newcastle');
INSERT INTO project(`name`) VALUES('Bon Echo');
INSERT INTO project(`name`) VALUES('Incident of 57th Street');
INSERT INTO project(`name`) VALUES('Revolution');

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 1) AS tmp1)) )),1,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 1 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 1) AS tmp1)) )),1,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 1 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 2) AS tmp1)) )),2,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 2 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 2) AS tmp1)) )),2,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 2 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 3) AS tmp1)) )),3,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 3 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 3) AS tmp1)) )),3,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 3 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 4) AS tmp1)) )),4,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 4 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 4) AS tmp1)) )),4,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 4 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 5) AS tmp1)) )),5,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 5 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 5) AS tmp1)) )),5,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 5 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 6) AS tmp1)) )),6,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 6 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 6) AS tmp1)) )),6,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 6 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 7) AS tmp1)) )),7,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 7 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 7) AS tmp1)) )),7,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 7 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 8) AS tmp1)) )),8,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 8 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 8) AS tmp1)) )),8,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 8 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 9) AS tmp1)) )),9,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 9 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 9) AS tmp1)) )),9,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 9 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 10) AS tmp1)) )),10,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 10 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 10) AS tmp1)) )),10,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 10 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 11) AS tmp1)) )),11,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 11 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 11) AS tmp1)) )),11,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 11 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 12) AS tmp1)) )),12,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 12 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 12) AS tmp1)) )),12,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 12 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 13) AS tmp1)) )),13,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 13 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 13) AS tmp1)) )),13,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 13 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 14) AS tmp1)) )),14,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 14 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 14) AS tmp1)) )),14,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 14 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 15) AS tmp1)) )),15,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 15 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 15) AS tmp1)) )),15,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 15 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 16) AS tmp1)) )),16,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 16 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 16) AS tmp1)) )),16,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 16 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 17) AS tmp1)) )),17,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 17 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 17) AS tmp1)) )),17,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 17 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 18) AS tmp1)) )),18,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 18 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 18) AS tmp1)) )),18,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 18 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 19) AS tmp1)) )),19,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 19 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 19) AS tmp1)) )),19,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 19 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 20) AS tmp1)) )),20,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 20 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 20) AS tmp1)) )),20,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 20 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 21) AS tmp1)) )),21,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 21 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 21) AS tmp1)) )),21,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 21 ) AS tmp));

INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 22) AS tmp1)) )),22,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 22 ) AS tmp));
INSERT INTO resource_skill(`skill_id`, `resource_id`, `order_col`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 22) AS tmp1)) )),22,(SELECT * FROM (SELECT IFNULL(MAX(order_col) + 1, 0) FROM `resource_skill` WHERE `resource_id` = 22 ) AS tmp));

INSERT INTO userdetails(email,name,password) VALUES ('jon.rogers@microfocus.com','John','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(1,"ROLE_USER");
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(1,"ROLE_ADMIN");
INSERT INTO userdetails(email,name,password) VALUES ('brdsbrn15@gmail.com','Brodie','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(2,"ROLE_USER");
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(2,"ROLE_ADMIN");
INSERT INTO userdetails(email,name,password) VALUES ('abrody1188@gmail.com','Aaron','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(3,"ROLE_USER");
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(3,"ROLE_ADMIN");
INSERT INTO userdetails(email,name,password) VALUES ('matthewjuliusscott@gmail.com','Matthew','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(4,"ROLE_USER");
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(4,"ROLE_ADMIN");
INSERT INTO userdetails(email,name,password) VALUES ('jaackcarter@hotmail.com','Jack','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(5,"ROLE_USER");
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(5,"ROLE_ADMIN");
INSERT INTO userdetails(email,name,password) VALUES ('user@gmail.com','user','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(6,"ROLE_USER");
INSERT INTO `userdetails_authoritystrings`(`UserDetails_id`,`authorityStrings`)VALUES(6,"ROLE_ADMIN");

