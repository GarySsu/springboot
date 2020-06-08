package com.gary.service;

import com.gary.mapper.OrderDetailMapper;
import com.gary.mapper.OrderMapper;
import com.gary.model.Order;
import com.gary.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * no define exception , only do rollback RuntimeException
     * @throws RuntimeException
     */
    @Transactional
    public void addOrderAndRuntimeException() throws RuntimeException{

        Order order = new Order();
        order.setOrderNum("111111");
        order.setGame("Lineage M");
        order.setDevice("web");
        order.setCustomerName("gary");
        int id = orderMapper.insert(order);

        if(id>1){
            throw new RuntimeException("error");
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(id);
        orderDetail.setWeapon("sword");
        orderDetail.setAmount(100);
        orderDetail.setStatus(1);
        orderDetailMapper.insert(orderDetail);
    }

    /**
     * define SQLException to do rollback
     * @throws SQLException
     */
    @Transactional
    public void addOrderAndSQLException() throws SQLException {

        Order order = new Order();
        order.setOrderNum("111111");
        order.setGame("Lineage M");
        order.setDevice("web");
        order.setCustomerName("gary");
        int id = orderMapper.insert(order);

        if(id>1){
            throw new SQLException("error");
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(id);
        orderDetail.setWeapon("sword");
        orderDetail.setAmount(100);
        orderDetail.setStatus(1);
        orderDetailMapper.insert(orderDetail);
    }

}
