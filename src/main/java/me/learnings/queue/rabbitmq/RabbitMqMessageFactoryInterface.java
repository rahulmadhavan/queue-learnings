package me.learnings.queue.rabbitmq;


import me.learnings.queue.MessageFactoryInterface;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 07/07/13
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RabbitMqMessageFactoryInterface<K> extends MessageFactoryInterface<K,RabbitMqMessageInterface<K>> {

    public RabbitMqMessageInterface<K> getMessage(K aMessage);

}
