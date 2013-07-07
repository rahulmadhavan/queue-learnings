package me.learnings.queue.rabbitmq.impl;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import me.learnings.queue.rabbitmq.RabbitMqConnectionInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqConnection implements RabbitMqConnectionInterface {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqConnection.class);

    private Connection connection;
    private Channel channel;
    private Integer port;
    private String host;

    public Channel getChannel() {
        return channel;
    }

    private RabbitMqConnection(String aHost, Integer aPort, Connection aConnection, Channel aChannel){
        host = aHost;
        port = aPort;
        connection = aConnection;
        channel = aChannel;

    }

    public static class Builder{

        private static Logger logger = LoggerFactory.getLogger(RabbitMqConnection.Builder.class);

        private Integer port;
        private String host;
        private Connection connection;
        private Channel channel;

        public Builder withHost(String aHost){
            host = aHost;
            return this;
        }

        public Builder withPort(Integer aPort){
            port = aPort;
            return this;
        }



        public RabbitMqConnection build(){

            if(connection == null){
                connection = createConnection();
            }

            try {
                channel = connection.createChannel();
            } catch(Exception e){

                logger.info("could not create rabbitmq channel from connection");

                connection = createConnection();
                try {

                    logger.info("creating rabbitmq channel from connection again");

                    channel = connection.createChannel();
                } catch(Exception e1){

                    logger.error("unable to create rabbitmq channel",e1);
                    channel = null;

                }finally {

                    if(channel == null){
                        throw new IllegalStateException("Channel not set");
                    }

                }
            }

            return new RabbitMqConnection(host,port,connection,channel);
        }

        private Connection createConnection(){

            logger.info("creating new rabbitmq connection");

            Connection connection = null;
            if(host == null){
                throw new IllegalStateException("host is not set");
            }
            if(port == null){
                throw new IllegalStateException("port is not set");
            }
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(host);
            connectionFactory.setPort(port);
            try {
                connection = connectionFactory.newConnection();
            } catch(Exception e) {
                logger.error("unable to create rabbitmq connection",e);
                connection = null;
            }finally {
                if(connection == null){
                    throw new IllegalStateException("Channel not set");
                }
            }
            return  connection;
        }

    }



    public void close(){

        try{
            channel.close();
        }catch(Exception e){
            logger.error("close : could not close rabbitmq channel",e);
        }

        try{
            connection.close();
        }catch(Exception e){
            logger.error("close : could not close rabbitmq connection",e);
        }

    }


}

