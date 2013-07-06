package me.learnings.queues.queue.rabbitmq;


import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 02/07/13
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqTest {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqTest.class);


    public static void main(String[] args){


        try {


            RabbitMqConnection.Builder builder = new RabbitMqConnection.Builder()
                    .withHost("localhost")
                    .withPort(5672);

            RabbitMqMessage.Builder<String> builder1 = new RabbitMqMessage.Builder<String>()
                    .withExchange("Test")
                    .withRoutingKey("testKey");


            RabbitMqConnectionFactory rabbitMqConnectionFactory = new RabbitMqConnectionFactory(builder);
            RabbitMqMessageFactory<String> rabbitMqMessageFactory = new RabbitMqMessageFactory<String>(builder1);

            ObjectPool<RabbitMqConnection> rabbitMqConnectionObjectPool = new GenericObjectPool<RabbitMqConnection>(rabbitMqConnectionFactory);
            //TODO set max active


            RabbitMqMessage<String> rabbitMqMessage = rabbitMqMessageFactory.getRabbitMqMessage("hi");


            while(true){

                Scanner inscanner = new Scanner(System.in);
                int i = inscanner.nextInt();

                RabbitMqStaticProducer.push(rabbitMqConnectionObjectPool,rabbitMqMessage);

            }

        } catch (Exception e) {
            logger.error("error in test",e);
        }


    }

}
