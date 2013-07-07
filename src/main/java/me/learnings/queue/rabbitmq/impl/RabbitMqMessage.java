package me.learnings.queue.rabbitmq.impl;


import me.learnings.queue.rabbitmq.RabbitMqMessageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMqMessage<K> implements RabbitMqMessageInterface<K> {

    private String exchange;
    private String routingKey;
    private K message;


    private RabbitMqMessage(String aExchange, String aRoutingKey, K aMessage){
        exchange = aExchange;
        routingKey = aRoutingKey;
        message = aMessage;
    }

    public static class Builder<K>{

        private String exchange;
        private String routingKey;
        private K message;

        public Builder<K> withExchange(String aExchange){
            this.exchange = aExchange;
            return this;
        }

        public Builder<K> withRoutingKey(String aRoutingKey){
            this.routingKey = aRoutingKey;
            return this;
        }

        public Builder<K> withMessage(K aMessage){
            this.message = aMessage;
            return this;
        }

        public RabbitMqMessage<K> build(){
            if(exchange == null){
                throw new IllegalStateException(String.format("exchange is not set"));
            }
            if(routingKey == null){
                throw new IllegalStateException(String.format("routingKey is not set"));
            }
            if(message == null){
                throw new IllegalStateException(String.format("message is not set"));
            }

            return new RabbitMqMessage(exchange,routingKey,message);
        }

    }


    public String getExchange() {
        return exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public K getMessage() {
        return message;
    }
}
