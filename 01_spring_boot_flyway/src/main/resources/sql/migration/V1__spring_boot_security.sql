#CREATE DATABASE IF NOT EXISTS spring_boot_example CHARACTER SET latin1 COLLATE latin1_swedish_ci;

CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `lastLoginTime` date DEFAULT NULL,
  `enabled` int(5) DEFAULT NULL,
  `accountNonExpired` int(5) DEFAULT NULL,
  `accountNonLocked` int(5) DEFAULT NULL,
  `credentialsNonExpired` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_authority` (
  `id` int(10) NOT NULL,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;