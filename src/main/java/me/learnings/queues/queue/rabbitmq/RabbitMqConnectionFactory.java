package me.learnings.queues.queue.rabbitmq;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 02/07/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqConnectionFactory extends BasePoolableObjectFactory<RabbitMqConnection> {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqConnectionFactory.class);

    private RabbitMqConnection.Builder builder;

    public RabbitMqConnectionFactory(RabbitMqConnection.Builder aBuilder){
        builder = aBuilder;
    }

    public RabbitMqConnection createRabbitMqConnection(){
        return builder.build();
    }

    @Override
    public RabbitMqConnection makeObject() throws Exception {
        logger.info("makeObject called");
        return createRabbitMqConnection();
    }

    @Override
    public void destroyObject(RabbitMqConnection rabbitMqConnection){
        logger.info("destroyObject called");
        rabbitMqConnection.close();
    }

}
