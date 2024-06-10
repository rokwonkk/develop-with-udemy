CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
	(1,'lee','rokwon','rokwon@rokwonkk.com'),
	(2,'lee','taerin','taerin@rokwonkk.com'),
	(3,'lee','taebin','taebin@rokwonkk.com'),
	(4,'kim','su','su@rokwonkk.com'),
	(5,'lee','sol','sol@rokwonkk.com');

