package com.gary.service;

import com.gary.mapper.OrderDetailMapper;
import com.gary.mapper.OrderMapper;
import com.gary.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Transactional
    public void addOrder(){

        try {
            Order order = new Order();
            order.setOrderNum("111111");
            order.setGame("Lineage M");
            order.setDevice("web");
            order.setCustomerName("gary");

            orderMapper.insert(order);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


}
