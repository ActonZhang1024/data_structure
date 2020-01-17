package pers.zhang.queue;

import pers.zhang.linearList.SortedSinglyLinkedList;

/**
 * @author zhang
 * @date 2020/1/17 - 13:23
 *
 * 优先队列
 */
public class PriorityQueue<T extends Comparable<T>> implements QQueue<T> {

    //使用排序单链表存储队列
    private SortedSinglyLinkedList<T> list;

    //构造空队列
    public PriorityQueue()
    {
        this.list = new SortedSinglyLinkedList<T>();
    }

    //判断队列是否为空
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    //入队
    @Override
    public void enqueue(T x) {
        list.insert(x);
    }

    //出队
    @Override
    public T dequeue() {
        return list.remove(0);
    }

    @Override
    public String toString(){
        return list.toString();
    }
}
