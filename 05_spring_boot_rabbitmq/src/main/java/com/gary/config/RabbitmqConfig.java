package com.gary.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class RabbitmqConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private Environment env;

    /** exchange name */
    public static final String EXCHANGE = "my-mq-exchange";
    /** queue key */
    public static final String ROUTINGKEY1 = "queue_email";
    /** queue key */
    public static final String ROUTINGKEY2 = "queue_short_message";

    /**
     * rabbitmq setting
     * @return
     */
    @Bean
    public CachingConnectionFactory connectionFactory() {
        env.getProperty("spring.rabbitmq.username");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("spring.rabbitmq.ip"),
                Integer.valueOf(env.getProperty("spring.rabbitmq.port")));
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username"));
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password"));
        connectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.virtual-host"));
        connectionFactory.setPublisherConfirms(Boolean.valueOf(env.getProperty("spring.rabbitmq.publisher-confirms")));
        return connectionFactory;
    }

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

    /**
     * set queue_one1 for consumers
     * @return
     */
    @Bean
    public Queue queueShortMessage() {
        return new Queue("queue_short_message", true);
    }

    /**
     * let queue bind with exchange
     * @return
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queueShortMessage()).to(defaultExchange()).with(RabbitmqConfig.ROUTINGKEY2);
    }

    /**
     * receive listening message, this listening will receive queue's message about queue_email
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queueEmail());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
                byte[] body = message.getBody();
                logger.info("receive email message : "  +new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });
        return container;
    }

    /**
     * receive listening message, this listening will receive queue's message about queue_short_message
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer2() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queueShortMessage());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
                byte[] body = message.getBody();
                logger.info("receive short message : "  +new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });
        return container;
    }

}
