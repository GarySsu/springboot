package com.gary.service;

import com.gary.mapper.OrderDetailMapper;
import com.gary.mapper.OrderMapper;
import com.gary.model.Order;
import com.gary.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new RuntimeException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

    /**
     * define SQLException to do rollback
     * @throws SQLException
     */
    @Transactional
    public void addOrderAndSQLException() throws SQLException {
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new SQLException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

    /**
     * Re-create a new transaction, if there is currently a transaction,
     *  postpone the current transaction
     * @throws RuntimeException
     */
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void addOrderRequired() throws RuntimeException {
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new RuntimeException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

    /**
     * If there is currently a transaction, then join the transaction;
     * if there isn't currently  transaction, then continue to run in a non-transactional way
     * @throws RuntimeException
     */
    @Transactional(propagation= Propagation.SUPPORTS)
    public void addOrderSupports() throws RuntimeException {
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new RuntimeException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

    private Order processOrderObject(){
        Order order = new Order();
        order.setOrderNum("111111");
        order.setGame("Lineage M");
        order.setDevice("web");
        order.setCustomerName("gary");

        return order;
    }

    private OrderDetail processOrderDetailObject(int id){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(id);
        orderDetail.setWeapon("sword");
        orderDetail.setAmount(100);
        orderDetail.setStatus(1);

        return orderDetail;
    }

}
