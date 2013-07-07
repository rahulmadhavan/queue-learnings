package me.learnings.queue.rabbitmq.impl;

import me.learnings.queue.MessageFactoryInterface;
import me.learnings.queue.QueueProducer;
import me.learnings.queue.rabbitmq.RabbitMqConnectionInterface;
import me.learnings.queue.rabbitmq.RabbitMqMessageFactoryInterface;
import me.learnings.queue.rabbitmq.RabbitMqMessageInterface;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 07/07/13
 * Time: 1:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqTester{



    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code
        System.out.println("@BeforeClass - oneTimeSetUp");


    }

    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
        System.out.println("@AfterClass - oneTimeTearDown");




    }

    @Test
    public void setUpRabbitMqAndInsertItem(){

        RabbitMqConnection.Builder connectionBuilder = new RabbitMqConnection.Builder().withHost("localhost").withPort(5672);
        PoolableObjectFactory poolableObjectFactory = new RabbitMqConnectionFactory(connectionBuilder);
        ObjectPool<RabbitMqConnectionInterface> objectPool = new GenericObjectPool<RabbitMqConnectionInterface>(poolableObjectFactory);

        RabbitMqMessage.Builder<String> builder = new RabbitMqMessage.Builder<String>().withExchange("Test").withRoutingKey("testKey");
        RabbitMqMessageFactoryInterface<String> rabbitMqMessageFactoryInterface = new RabbitMqMessageFactory<String>(builder);

        QueueProducer queueProducer = new RabbitMqProducer();

        boolean result = queueProducer.push(objectPool,rabbitMqMessageFactoryInterface.getMessage("hello"));

        assertTrue(result);

    }

}
