CREATE TABLE `order_master` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(50) ,
  `game` varchar(10) ,
  `device` varchar(200) DEFAULT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_detail` (
  `order_detail_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL ,
  `weapon` varchar(50) ,
  `amount` int(10) ,
  `status` int(1),
  PRIMARY KEY (`order_detail_id`),
  FOREIGN KEY (order_detail_id) REFERENCES `order_master`(order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;