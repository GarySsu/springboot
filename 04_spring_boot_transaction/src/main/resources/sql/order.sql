CREATE TABLE `order_master` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(50) ,
  `game` varchar(10) ,
  `device` varchar(200) DEFAULT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;