package com.gary.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.gary.model.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    /**
     * query user's detail
     * @param username
     * @return
     */
    public UserEntity findByUsername(String username);

}
