package me.learnings.queue.rabbitmq;


import com.rabbitmq.client.Channel;
import me.learnings.queue.ConnectionInterface;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 07/07/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RabbitMqConnectionInterface extends ConnectionInterface<Channel> {

    public Channel getChannel();

    public void close();


}
