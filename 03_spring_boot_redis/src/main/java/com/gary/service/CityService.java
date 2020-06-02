package com.gary.service;

import com.gary.mapper.CityMapper;
import com.gary.model.CityEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CityMapper cityMapper;

    @Cacheable(value = "city", key="'city_'.concat(#a0)")
    public CityEntity findById(Integer id) {
        logger.info("read row");
        return cityMapper.findById(id);
    }

    @CachePut(value = "city", key="'city_'.concat(#a0)")
    public CityEntity updateById(Integer id,CityEntity cityEntity) {
        logger.info("update row");
        cityEntity.setId(id);
        cityMapper.updateById(cityEntity);
        return cityMapper.findById(id);
    }

    public void insert(CityEntity cityEntity) {
        logger.info("insert row");
        cityMapper.insert(cityEntity);
    }

    @CacheEvict(value = "city", key="'city_'.concat(#a0)")
    public void deleteById(int id) {
        logger.info("delete row");
        cityMapper.deleteById(id);
    }

}
