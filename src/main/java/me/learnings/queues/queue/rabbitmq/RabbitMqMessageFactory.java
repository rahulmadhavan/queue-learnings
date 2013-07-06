package me.learnings.queues.queue.rabbitmq;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 02/07/13
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqMessageFactory<K>{

    private RabbitMqMessage.Builder<K> builder;

    public RabbitMqMessageFactory(RabbitMqMessage.Builder<K> aBuilder){
        builder = aBuilder;
    }

    public RabbitMqMessage<K> getRabbitMqMessage(K aMessage){
        return builder.withMessage(aMessage).build();
    }


}
