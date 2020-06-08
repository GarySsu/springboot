package com.gary.mapper;

import com.gary.model.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderDetailMapper {

    /**
     * insert order detail
     * @param orderDetail
     * @return
     */
    public int insert(OrderDetail orderDetail);

}
