package me.learnings.queue.rabbitmq.impl;


import me.learnings.queue.rabbitmq.RabbitMqMessageFactoryInterface;
import me.learnings.queue.rabbitmq.RabbitMqMessageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqMessageFactory<K> implements RabbitMqMessageFactoryInterface<K> {

    private RabbitMqMessage.Builder<K> builder;

    public RabbitMqMessageFactory(RabbitMqMessage.Builder<K> aBuilder){
        builder = aBuilder;
    }

    public RabbitMqMessageInterface<K> getMessage(K aMessage){
        return builder.withMessage(aMessage).build();
    }


}

