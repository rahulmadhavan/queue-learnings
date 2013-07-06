package me.learnings.queues.queue;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 02/07/13
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public interface QueueProducer<C,K> {

    public boolean push(C connection,K message);

}
