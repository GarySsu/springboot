package com.gary.model;

import lombok.Data;

@Data
public class Order {

    private Integer id;
    private String orderNum;
    private String game;
    private String device;
    private String customerName;

}
