package pers.zhang.queue;

import pers.zhang.linearList.Node;

/**
 * @author zhang
 * @date 2020/1/17 - 12:48
 *
 * 链式队列
 */
public class LinkedQueue<T> implements QQueue<T> {

    //头节点和尾节点引用
    private Node<T> front, rear;

    //构造空队列
    public LinkedQueue(){
        this.front = this.rear = null;
    }

    //判断队列是否为空
    @Override
    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    //元素x入队，空对象不操作
    @Override
    public void enqueue(T x) {
        if(x == null)
            return;
        Node<T> q = new Node<T>(x, null);
        if(this.front == null)
            this.front = q;//空队插入
        else
            this.rear.next = q;//插入队尾
        this.rear = q;
    }

    //出队，返回队头元素
    @Override
    public T dequeue() {
        if(isEmpty())
            return null;
        T temp = this.front.data;
        this.front = this.front.next;//删除队头节点
        if(this.front == null)
            this.rear = null;
        return temp;
    }

    //返回队列所有元素的描述字符串，形式为“(,)”
    @Override
    public String toString(){//算法同不带头结点的单链表
        String str = "(";
        for (Node<T> p=this.front;  p!=null;  p=p.next){
            str += p.data.toString();
            if (p.next != null)
                str += ", ";                               //不是最后一个结点时后加分隔符
        }
        return str + ")";                                    //空表返回()
    }
}
