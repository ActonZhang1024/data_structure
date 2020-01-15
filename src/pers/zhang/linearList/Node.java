package pers.zhang.linearList;


/**
 * @author zhang
 * @date 2020/1/14 - 10:17
 * 单链表节点类
 */
public class Node<T> {
    //数据域，保存数据元素
    public T data;

    //地址域，引用后继节点
    public Node<T> next;

    public Node(){
        this(null,null);
    }

    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }

    public String toString(){
        return this.data.toString();
    }

    public boolean equals(Object obj){
        return obj == this || obj instanceof Node && this.data.equals(((Node<T>)obj).data);
    }

}
