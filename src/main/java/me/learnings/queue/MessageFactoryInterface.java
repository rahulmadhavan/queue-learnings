package me.learnings.queue;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MessageFactoryInterface<K ,Q extends MessageInterface<K>>{

    Q getMessage(K aMessage);
}
