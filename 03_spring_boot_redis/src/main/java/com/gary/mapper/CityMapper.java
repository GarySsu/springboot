package com.gary.mapper;

import com.gary.model.CityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CityMapper {

    /**
     * query city
     * @param id
     * @return
     */
    public CityEntity findById(Integer id);

    /**
     * update city by id
     * @param cityEntity
     * @return
     */
    public void updateById(CityEntity cityEntity);

    /**
     * insert city
     * @param cityEntity
     * @return
     */
    public void insert(CityEntity cityEntity);

    /**
     * delete city by id
     * @param id
     * @return
     */
    public void deleteById(int id);

}
