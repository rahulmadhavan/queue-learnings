package me.learnings.queues.queue.rabbitmq;

import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.pool.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 02/07/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqStaticProducer {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqStaticProducer.class);

    public static boolean push(ObjectPool<RabbitMqConnection> connectionPool, RabbitMqMessage message) {

        RabbitMqConnection rabbitMqConnection;

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
