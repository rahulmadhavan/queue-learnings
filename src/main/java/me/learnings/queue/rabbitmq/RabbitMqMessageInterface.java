package me.learnings.queue.rabbitmq;


import me.learnings.queue.MessageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 07/07/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RabbitMqMessageInterface<K> extends MessageInterface<K> {

    public String getExchange();

    public String getRoutingKey();

}
