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

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 1) AS tmp1)) )),1);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 1) AS tmp1)) )),1);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 2) AS tmp1)) )),2);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 2) AS tmp1)) )),2);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 3) AS tmp1)) )),3);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 3) AS tmp1)) )),3);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 4) AS tmp1)) )),4);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 4) AS tmp1)) )),4);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 5) AS tmp1)) )),5);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 5) AS tmp1)) )),5);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 6) AS tmp1)) )),6);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 6) AS tmp1)) )),6);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 7) AS tmp1)) )),7);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 7) AS tmp1)) )),7);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 8) AS tmp1)) )),8);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 8) AS tmp1)) )),8);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 9) AS tmp1)) )),9);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 9) AS tmp1)) )),9);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 10) AS tmp1)) )),10);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 10) AS tmp1)) )),10);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 11) AS tmp1)) )),11);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 11) AS tmp1)) )),11);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 12) AS tmp1)) )),12);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 12) AS tmp1)) )),12);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 13) AS tmp1)) )),13);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 13) AS tmp1)) )),13);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 14) AS tmp1)) )),14);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 14) AS tmp1)) )),14);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 15) AS tmp1)) )),15);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 15) AS tmp1)) )),15);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 16) AS tmp1)) )),16);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 16) AS tmp1)) )),16);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 17) AS tmp1)) )),17);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 17) AS tmp1)) )),17);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 18) AS tmp1)) )),18);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 18) AS tmp1)) )),18);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 19) AS tmp1)) )),19);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 19) AS tmp1)) )),19);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 20) AS tmp1)) )),20);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 20) AS tmp1)) )),20);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 21) AS tmp1)) )),21);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 21) AS tmp1)) )),21);

INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 22) AS tmp1)) )),22);
INSERT INTO resource_skill(`skill_id`, `resource_id`) VALUES((FLOOR(1 + RAND() * (SELECT MAX(skill_id) FROM `skill` WHERE skill_id NOT IN (SELECT * FROM(SELECT skill_id FROM `resource_skill` WHERE `resource_id` = 22) AS tmp1)) )),22);

INSERT INTO User(email,name,password) VALUES ('jon.rogers@microfocus.com','John','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(1,"ROLE_USER");
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(1,"ROLE_ADMIN");
INSERT INTO User(email,name,password) VALUES ('brdsbrn15@gmail.com','Brodie','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(2,"ROLE_USER");
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(2,"ROLE_ADMIN");
INSERT INTO User(email,name,password) VALUES ('abrody1188@gmail.com','Aaron','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(3,"ROLE_USER");
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(3,"ROLE_ADMIN");
INSERT INTO User(email,name,password) VALUES ('matthewjuliusscott@gmail.com','Matthew','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(4,"ROLE_USER");
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(4,"ROLE_ADMIN");
INSERT INTO User(email,name,password) VALUES ('jaackcarter@hotmail.com','Jack','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(5,"ROLE_USER");
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(5,"ROLE_ADMIN");
INSERT INTO User(email,name,password) VALUES ('user@gmail.com','user','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(6,"ROLE_USER");
INSERT INTO User(email,name,password) VALUES ('admin@gmail.com','admin','$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi');
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(7,"ROLE_USER");
INSERT INTO `User_authoritystrings`(`User_user_id`,`authorityStrings`)VALUES(7,"ROLE_ADMIN");

INSERT INTO User(email, name, password, resource_id) SELECT CONCAT(REPLACE(name, " ", ""), "@gmail.com"), name, '$2a$10$jH./PUyAi1ztDhH5PHjKH.otCTfcqS1IZRtAPmqWNX8DkoN.xQAWi', resource_id FROM resource;

INSERT INTO `testdb`.`notification` (`notification_id`, `createdDate`, `message`, `seen`, `user_id`, `order_col`) SELECT 0, CURDATE(), CONCAT(CONCAT("Welcome ", `user`.name), "! Your account has now been created, you may now start managing resources."), 0, `user`.user_id, 0 FROM `testdb`.`user`;

UPDATE resource AS U1, user AS U2 SET U1.user_id = U2.user_id WHERE U2.resource_id = U1.resource_id;

INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(8, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(9, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(10, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(11, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(12, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(13, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(14, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(15, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(16, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(17, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(18, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(19, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(20, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(21, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(22, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(23, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(24, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(25, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(26, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(27, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(28, "ROLE_USER");
INSERT INTO User_authoritystrings(User_user_id, authorityStrings) VALUES(29, "ROLE_USER");

