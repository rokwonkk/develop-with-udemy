USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('rok','{noop}test123',1),
('su','{noop}test123',1),
('rin','{noop}test123',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('rok','ROLE_EMPLOYEE'),
('su','ROLE_EMPLOYEE'),
('su','ROLE_MANAGER'),
('rin','ROLE_EMPLOYEE'),
('rin','ROLE_MANAGER'),
('rin','ROLE_ADMIN');


