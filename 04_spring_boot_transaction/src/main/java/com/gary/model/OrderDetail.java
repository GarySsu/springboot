package com.gary.model;

import lombok.Data;

@Data
public class OrderDetail {

    private Integer id;
    private Integer orderId;
    private String weapon;
    private Integer amount;
    private Integer status;

}
