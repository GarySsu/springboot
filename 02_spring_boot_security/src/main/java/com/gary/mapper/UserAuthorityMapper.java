package com.gary.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.gary.model.UserAuthorityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserAuthorityMapper {

    /**
     * query user's authority
     * @param id
     * @return
     */
    public List<UserAuthorityEntity> findByid(Integer id);

}
