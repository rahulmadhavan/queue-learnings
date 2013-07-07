package me.learnings.queue;

import org.apache.commons.pool.ObjectPool;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QueueProducer<P,K extends ConnectionInterface<P> ,Q extends MessageInterface>{

    public boolean push(ObjectPool<K> connectionPool, Q message);
}
