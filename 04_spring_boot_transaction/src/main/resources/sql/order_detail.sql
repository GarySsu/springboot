CREATE TABLE `order_detail` (
  `order_detail_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL ,
  `numero` varchar(50) ,
  `amount` int(10) ,
  `status` int(1),
  PRIMARY KEY (`order_detail_id`),
  FOREIGN KEY (order_detail_id) REFERENCES `order_master`(order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;