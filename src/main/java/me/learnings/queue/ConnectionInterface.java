package me.learnings.queue;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 06/07/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ConnectionInterface<K>{

    K getChannel();

    void close();

}
