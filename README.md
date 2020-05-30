# Base on the springboot project, use to quickly build small and medium api or Restful API projects
* Use caching with annotations. You can add,delete,query and updare information by redis.
* Makes use of JWT authentication for securing an exposed REST API

## Reference Documents: 
* https://www.baeldung.com/spring-cache-tutoriala
* https://www.baeldung.com/spring-boot-security-autoconfiguration

### Here is a quick teaser of an application :
#### Using Spring Data Redis in Springboot
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

#### Using JWT authentication in Springboot
    {
      "status": 200,
      "msg": "Login Success",
      "data": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTA4MjEwNTIsInVzZXJuYW1lIjoiZ2FyeSJ9.6hHez-JqknYoUlB9NFDhRLQP1ysN93kWWqbcxfA4GPA"
    }

## Requirements
    
