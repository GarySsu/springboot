package com.gary;

import com.gary.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringTransactionApp.class)
public class TestApp {

    @Autowired
    private OrderService orderService;

    @Test
    public void testTransaction(){
        orderService.addOrder();
    }

}
