package me.learnings.queue.rabbitmq.impl;


import me.learnings.queue.rabbitmq.RabbitMqConnectionInterface;
import me.learnings.queue.rabbitmq.RabbitMqMessageInterface;
import me.learnings.queue.rabbitmq.RabbitMqProducerInterface;
import org.apache.commons.pool.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 9:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqProducer implements RabbitMqProducerInterface {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqProducer.class);

    @Override
    public boolean push(ObjectPool<RabbitMqConnectionInterface> connectionPool, RabbitMqMessageInterface<String> message) {
        RabbitMqConnectionInterface rabbitMqConnection;

        logger.debug(String.format("message-publish-failure: %s exchange: %s routingKey: %s",message.getMessage().toString(),message.getExchange(),message.getRoutingKey()));

        try{

            rabbitMqConnection = connectionPool.borrowObject();

            try{
                rabbitMqConnection.getChannel()
                        .basicPublish(message.getExchange(),
                                message.getRoutingKey(),
                                null,
                                message.getMessage().toString().getBytes());

            }catch(Exception e){

                logger.error(String.format("message-publish-failure: %s exchange: %s routingKey: %s",message.getMessage().toString(),message.getExchange(),message.getRoutingKey()),e);

                logger.info(String.format("Invalidating rabbitmq connection - active connections: %s idle-connections: %s",connectionPool.getNumActive(),connectionPool.getNumIdle()));
                connectionPool.invalidateObject(rabbitMqConnection);

                rabbitMqConnection = null;

            }finally {

                if(null != rabbitMqConnection){

                    connectionPool.returnObject(rabbitMqConnection);
                    logger.debug(String.format("Returned rabbitmq connection - active connections: %s idle-connections: %s",connectionPool.getNumActive(),connectionPool.getNumIdle()));
                }

            }

        }catch(Exception e){

            logger.error(String.format("message-publish-failure: could not borrow connection from pool message: %s exchange: %s routingKey: %s",message.getMessage().toString(),message.getExchange(),message.getRoutingKey()),e);
            logger.error(String.format("could not borrow connection from pool active connections: %s idle-connections: %s",connectionPool.getNumActive(),connectionPool.getNumIdle()));

        }

        return true;
    }
}
