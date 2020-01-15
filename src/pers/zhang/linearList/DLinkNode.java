package pers.zhang.linearList;

/**
 * @author zhang
 * @date 2020/1/15 - 11:06
 * 双链表节点类
 */
public class DLinkNode<T> {
    //数据元素
    public T data;

    //前驱节点
    public DLinkNode<T> pred;

    //后继节点
    public DLinkNode<T> next;

    //带参构造
    public DLinkNode(T data, DLinkNode<T> pred, DLinkNode<T> next){
        this.data = data;
        this.pred = pred;
        this.next = next;
    }

    public DLinkNode(){
        this(null, null, null);
    }

    public DLinkNode(T data){
        this(data, null, null);
    }

    //toString()
    public String toString(){
        return this.data.toString();
    }
}
