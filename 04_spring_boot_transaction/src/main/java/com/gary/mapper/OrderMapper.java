package com.gary.mapper;

import com.gary.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * insert Order
     * @param order
     * @return
     */
    public void insert(Order order);

}
