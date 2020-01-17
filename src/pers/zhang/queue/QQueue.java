package pers.zhang.queue;

/**
 * @author zhang
 * @date 2020/1/17 - 11:35
 *
 * 队列的接口
 */
public interface QQueue<T> {

    //判断队列是否为空
    boolean isEmpty();

    //元素x入队
    void enqueue(T x);

    //出队，返回队头元素
    T dequeue();
}
