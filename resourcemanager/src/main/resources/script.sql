DROP DATABASE IF EXISTS testDB;
CREATE DATABASE testDB;
USE testDB;

CREATE TABLE `person` (
  `person_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL DEFAULT '',
  `last_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `skill` (
  `skill_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `person_skill` (
  `person_id` int(11) unsigned,
  `skill_id` int(11) unsigned,
  `order_col` int(11) unsigned,
  PRIMARY KEY (`person_id`,`skill_id`),
  FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE,
  FOREIGN KEY (skill_id) REFERENCES skill(skill_id) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO skill VALUES(0, 'Java');
INSERT INTO skill VALUES(0, 'MySQL');
INSERT INTO skill VALUES(0, 'C#');
INSERT INTO skill VALUES(0, 'C++');
INSERT INTO skill VALUES(0, 'Python');
INSERT INTO skill VALUES(0, 'Ruby');
INSERT INTO skill VALUES(0, 'HTML');
INSERT INTO skill VALUES(0, 'CSS');
INSERT INTO skill VALUES(0, 'PHP');
INSERT INTO skill VALUES(0, 'JavaScript');
