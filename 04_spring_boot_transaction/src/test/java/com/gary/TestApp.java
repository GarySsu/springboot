package com.gary;

import com.gary.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@EnableTransactionManagement
@SpringBootTest(classes = SpringTransactionApp.class)
public class TestApp {

    @Autowired
    private OrderService orderService;

    @Test
    public void addOrderAndRuntimeException(){
        orderService.addOrderAndRuntimeException();
    }

    @Test
    public void addOrderAndSQLException() throws SQLException {
        orderService.addOrderAndSQLException();
    }

}
