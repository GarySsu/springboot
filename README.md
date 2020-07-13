## Base on the springboot project, use to quickly build small and medium api or Restful API projects
* Using flyway with Springboot
* Makes use of JWT authentication for securing an exposed REST API
* Use caching with annotations. You can add,delete,query and update information by redis.
* How to use the @Transactional annotation and common pitfalls
* Using Rabbitmq handle asynchronous request
* Follow Telegram API to send message or photo for channel

## Reference Documents: 
* https://www.baeldung.com/database-migrations-with-flyway
* https://www.baeldung.com/spring-boot-security-autoconfiguration
* https://www.baeldung.com/spring-cache-tutoriala
* https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
* https://spring.io/guides/gs/messaging-rabbitmq/
* https://core.telegram.org/bots/api

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

#### Using Rabbitmq handle asynchronous request
    rabbitmq binding queue to exchange
    /**
     * set exchange for consumers
     FanoutExchange: distribute message to blind queue without the concept of routingkey
     HeadersExchange: add attribute key-value mapping
     DirectExchange: distribute message to designated queue according to routingkey
     TopicExchange: multi-keyword mapping
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    /**
     * set queue_one for consumers
     * @return
     */
    @Bean
    public Queue queueEmail() {
        return new Queue("queue_email", true); 
    }

    /**
     * let queue bind with exchange
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueEmail()).to(defaultExchange()).with(RabbitmqConfig.ROUTINGKEY1);
    }    

#### Follow Telegram API to send message of photo for channel
<img src="https://github.com/GarySsu/gary_springboot/blob/master/06_spring_boot_telegram_bot/src/main/resources/photo/screenshot.png" width="450">

### Requirements
#### Redis install by docker
    docker pull redis:6.0.1-alpinec
    docker run -itd --name redis-test -p 6379:6379 redis
    
#### Rabbitmq install by docker
    docker pull rabbitmq:management
    docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
