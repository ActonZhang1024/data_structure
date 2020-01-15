package pers.zhang.linearList;

/**
 * @author zhang
 * @date 2020/1/14 - 15:16
 *
 * 循环单链表
 */
public class CirSinglyLinkedList<T>{
    //头指针，指向循环单链表的头结点
    public Node<T> head;

    //默认构造
    public CirSinglyLinkedList(){
        this.head = new Node<T>();
        this.head.next = this.head;
    }

    //由element数组中的多个对象构造单链表。采用尾插入构造单链表
    public CirSinglyLinkedList(T[] element){
        this();                                            //创建空单链表，只有头结点
        Node<T> rear = this.head;                            //rear指向单链表最后一个结点
        for (int i = 0; i < element.length; i++)               //若element==null，抛出空对象异常
        {                                                  //若element.length==0，构造空链表
            rear.next = new Node<T>(element[i], this.head);  //尾插入，创建结点链入rear结点之后
            rear = rear.next;                              //rear指向新的链尾结点
        }
    }

    //以下方法对两条循环单链表进行操作
    public CirSinglyLinkedList(CirSinglyLinkedList<T> list)//循环单链表深拷贝构造方法
    {
        this();                                            //创建空循环单链表，只有头结点
        Node<T> rear = this.head;
        for (Node<T> p=list.head.next;  p!=list.head;  p=p.next)
        {
            rear.next = new Node<T>(p.data, this.head);
            rear = rear.next;
        }
//      rear.next = this.head;
    }
    public CirSinglyLinkedList(SinglyLinkedList<T> list)   //深拷贝，以单链表list构造循环单链表
    {
        this();                                            //创建空循环单链表，只有头结点
        Node<T> rear = this.head;
        for (Node<T> p=list.head.next;  p!=list.head;  p=p.next)
        {
            rear.next = new Node<T>(p.data, this.head);
            rear = rear.next;
        }
    }

    //判断循环单链表是否空
    public boolean isEmpty() {
        return this.head.next == this.head;
    }

    //返回循环单链表长度，单链表遍历算法
    public int length() {
        int i = 0;
        for(Node<T> p = this.head.next; p != this.head; p = p.next)
            i++;
        return i;
    }

    //返回第i（≥0）个元素，若i<0或大于表长则返回null，O(n)
    public T get(int i) {
        if(i >= 0){
            Node<T> p = this.head.next;
            for(int j = 0; j < i && p != this.head; j++)
                p = p.next;
            if(p != this.head)
                return p.data;
        }
        return null;
    }

    //设置第i（≥0）个元素值为x。若i<0或大于表长则抛出序号越界异常；若x==null，不操作。O(n)
    public void set(int i, T x) {
        if(x == null)//不能设置空对象
            return;
        Node<T> p = this.head.next;
        for(int j = 0; p != this.head && j < i; j++)
            p = p.next;
        if(i >= 0 && p != this.head)
            p.data = x;
        else
            throw new IndexOutOfBoundsException(i + "");
    }


    public String toString(){                          //返回循环单链表所有元素的描述字符串
        String str="(";
        Node<T> p = this.head.next;
        while (p!=this.head)                          //遍历单链表的循环条件改变了
        {
            str += p.data.toString();
            if (p!=this.head)
                str += ", ";                          //不是最后一个结点时后加分隔符
            p = p.next;
        }
        return str+")";                               //空表返回()
    }
}
