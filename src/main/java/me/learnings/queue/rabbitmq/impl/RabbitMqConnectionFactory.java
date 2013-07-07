package me.learnings.queue.rabbitmq.impl;

import me.learnings.queue.rabbitmq.RabbitMqConnectionInterface;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqConnectionFactory extends BasePoolableObjectFactory<RabbitMqConnectionInterface> {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqConnectionFactory.class);

    private RabbitMqConnection.Builder builder;

    public RabbitMqConnectionFactory(RabbitMqConnection.Builder aBuilder){
        builder = aBuilder;
    }

    public RabbitMqConnectionInterface createRabbitMqConnection(){
        return builder.build();
    }

    @Override
    public RabbitMqConnectionInterface makeObject() throws Exception {
        logger.info("makeObject called");
        return createRabbitMqConnection();
    }

    @Override
    public void destroyObject(RabbitMqConnectionInterface rabbitMqConnection){
        logger.info("destroyObject called");
        rabbitMqConnection.close();
    }

}

