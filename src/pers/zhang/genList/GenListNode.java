package pers.zhang.genList;

/**
 * @author zhang
 * @date 2020/1/20 - 11:03
 *
 * 广义表双链表示的节点类
 */
public class GenListNode<T> {
    //数据域
    public T data;

    //地址域，指向子表
    public GenList<T> child;

    //地址域，指向后继节点
    public GenListNode<T> next;

    //构造节点，data指定元素，child指向子表，next指向后继节点
    public GenListNode(T data, GenList<T> child, GenListNode<T> next){
        this.data = data;
        this.child = child;
        this.next = next;
    }

    public GenListNode(T data){
        this(data, null, null);
    }

    public GenListNode(){
        this(null, null, null);
    }
}
