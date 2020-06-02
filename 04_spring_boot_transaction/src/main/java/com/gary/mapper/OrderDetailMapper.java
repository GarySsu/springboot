package com.gary.mapper;

import com.gary.model.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderDetailMapper {

    /**
     * insert OrderDetail
     * @param orderDetail
     * @return
     */
    public void insert(OrderDetail orderDetail);

}
