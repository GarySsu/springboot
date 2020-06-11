# Base on the springboot project, use to quickly build small and medium api or Restful API projects
* Makes use of JWT authentication for securing an exposed REST API
* Use caching with annotations. You can add,delete,query and update information by redis.
* how to use the @Transactional annotation and common pitfalls

## Reference Documents: 
* https://www.baeldung.com/spring-boot-security-autoconfiguration
* https://www.baeldung.com/spring-cache-tutoriala
* https://www.baeldung.com/transaction-configuration-with-jpa-and-spring

### Here is a quick teaser of an application :
#### Using JWT authentication in Springboot
    {
      "status": 200,
      "msg": "Login Success",
      "data": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTA4MjEwNTIsInVzZXJuYW1lIjoiZ2FyeSJ9.6hHez-JqknYoUlB9NFDhRLQP1ysN93kWWqbcxfA4GPA"
    }

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

#### Using Transactional tag to open transaction and define error to do rollback
    /**
     * no define exception , only do rollback RuntimeException
     * @throws RuntimeException
     */
    @Transactional
    public void addOrderAndRuntimeException() throws RuntimeException{
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new RuntimeException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

    /**
     * define SQLException to do rollback
     * @throws SQLException
     */
    @Transactional
    public void addOrderAndSQLException() throws SQLException {
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new SQLException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

    /**
     * Re-create a new transaction, if there is currently a transaction,
     *  postpone the current transaction
     * @throws RuntimeException
     */
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void addOrderRequired() throws RuntimeException {
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new RuntimeException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

    /**
     * If there is currently a transaction, then join the transaction;
     * if there isn't currently  transaction, then continue to run in a non-transactional way
     * @throws RuntimeException
     */
    @Transactional(propagation= Propagation.SUPPORTS)
    public void addOrderSupports() throws RuntimeException {
        int id = orderMapper.insert(processOrderObject());
        if(id>1){
            throw new RuntimeException("error");
        }
        orderDetailMapper.insert(processOrderDetailObject(id));
    }

## Requirements
    
