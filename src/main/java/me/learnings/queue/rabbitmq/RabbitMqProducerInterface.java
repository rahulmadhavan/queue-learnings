package me.learnings.queue.rabbitmq;


import com.rabbitmq.client.Channel;
import me.learnings.queue.QueueProducer;
import me.learnings.queue.rabbitmq.RabbitMqMessageInterface;
import org.apache.commons.pool.ObjectPool;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 07/07/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RabbitMqProducerInterface extends QueueProducer<Channel, RabbitMqConnectionInterface, RabbitMqMessageInterface<String>> {


    public boolean push(ObjectPool<RabbitMqConnectionInterface> connectionPool, RabbitMqMessageInterface<String> message);

}
