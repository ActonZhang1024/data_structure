package pers.zhang.linearList;

/**
 * @author zhang
 * @date 2020/1/14 - 10:23
 * 单链表
 */
public class SinglyLinkedList<T> implements LinearList<T>{
    //头指针，指向单链表的头结点
    public Node<T> head;

    //默认构造，构造空单链表
    public SinglyLinkedList(){
        //创建头结点，data和next均为null
        this.head = new Node<T>();
    }

    //由指定数组中的多个对象构造单链表。采用尾插入构造单链表
    //若element==null，抛出空对象异常；若element.length==0，构造空链表
    public SinglyLinkedList(T[] element){
        this();//先创建空单链表，只有头节点
        Node<T> rear = this.head;//rear指向最后一个节点
        for(int i = 0; i < element.length; i++){//element.length==0,构造空单链表
            rear.next = new Node<T>(element[i], null);//尾插入，创建节点链入rear之后
            rear = rear.next;//rear指向新的链表
        }
    }

    //由指定数组中的多个对象构造单链表，采用尾插入构造单链表，调用insertAfter()方法
//    public SinglyLinkedList(T[] element){
//        this();//创建空单链表，只有头结点
//        Node<T> rear = this.head;
//        for (int i = 0; i < element.length; i++)
//            rear = insertAfter(rear, element[i]);
//    }

    //由指定数组中的多个对象构造单链表，采用头插入构造反序的单链表
//    public SinglyLinkedList(T[] element){
//        this();//创建空单链表，只有头结点
//        for (int i=0; i<element.length; i++)
//            this.head.next = new Node<T>(element[i], this.head.next);
//    }

    //拷贝构造(深拷贝)
    public SinglyLinkedList(SinglyLinkedList<T> list){
        this();//构造空链表
        Node<T> rear = this.head;
        for(Node<T> p = list.head.next; p != null; p = p.next){
            rear.next = new Node<T>(p.data, null);
            rear = rear.next;
        }
    }

    //判断单链表是否为空
    @Override
    public boolean isEmpty() {
        return this.head.next == null;
    }

    //返回单链表长度
    @Override
    public int length() {
        int i = 0;
        Node<T> p = this.head.next;//从单链表第一个节点开始
        while(p != null){//到最后一个
            i++;
            p = p.next;
        }
        return i;
    }

    //返回第i（i>=0）个元素，若i<0或大于表长则返回null，O(n)
    @Override
    public T get(int i) {
        if(i >= 0){
            Node<T> p = this.head.next;
            for(int j = 0; p != null && j < i; j++){
                p = p.next;
            }
            if(p != null)
                return p.data;
        }
        return null;
    }

    //设置第i(i >= 0)个元素值为x。若i<0或大于表长则抛出越界异常；若x=null则不操作。O(n)
    @Override
    public void set(int i, T x) {
        if(x == null)
            return;
        Node<T> p = this.head.next;
        for(int j = 0; p != null && j < i; j++)
            p = p.next;
        if(i >= 0 && p != null)
            p.data = x;
        else
            throw new IndexOutOfBoundsException(i + "");
    }

    //插入第i(i >= 0)个元素值为x。若x==null，不插入。
    //若i<0，插入x作为第0个元素；若i大于表长，插入x作为最后一个元素。O(n)
    @Override
    public void insert(int i, T x) {
        if(x == null)//不能插入空对象
            return;
        Node<T> p = this.head.next;//p指向头结点
        for(int j = 0; p.next != null && i < j; j++)//查找第i个位置的前一个位置，作为插入位置
            p = p.next;
        p.next = new Node<T>(x, p.next);//插入节点
    }


    //将x对象插入在序号为i的节点，若操作成功返回新插入节点；否则返回null。O(n)
//    public Node<T> insert(int i, T x){
//        if(x == null)
//            return null;
//        Node<T> p = this.head.next;
//        for(int j = 0; p.next != null && j < i; j++)
//            p = p.next;
//        Node<T> q = new Node<T>(x, p.next);
//        p.next = q;
//        return q;
//    }


    //插入x作为p节点的后继节点，若操作成功返回新插入节点；否则返回null
    public Node<T> insertAfter(Node<T> p, T x){
        if(x == null || p == null)
            return null;
        Node<T> q = new Node<T>(x, p.next);
        p.next = q;
        return q;
    }

    //在单链表尾部追加节点
    @Override
    public void append(T x) {
        insert(Integer.MAX_VALUE, x);//遍历一次
        insert(this.length(), x);//遍历两次
    }


    //删除第i个节点，返回被删除的对象。若i<0或i>表长，不删除，返回null。O(n)
    @Override
    public T remove(int i) {
        if(i >= 0){
            Node<T> p = this.head.next;
            for(int j = 0; p.next != null && j < i; j++)//定位到待删除节点的前一个节点
                p = p.next;
            if(p.next != null){
                T old = p.next.data;//获得原来对象
                p.next = p.next.next;//删除p的后继节点
                return old;
            }
        }
        return null;
    }

    //删除p节点的后继节点，若操作成功返回删除节点；否则返回null。O(1)
    public Node<T> removoAfter(Node<T> p){
        if(p == null || p.next == null)
            return null;
        Node<T> q = p.next;
        p.next = q.next;
        return q;
    }

    //删除所有节点
    @Override
    public void removeAll() {
        this.head.next = null;
    }

    //返回单链表所有元素的描述字符串，形式为“(,)”，覆盖Object类的toString()方法，O(n)
    public String toString(){
        String str = "(";
        for (Node<T> p = this.head.next; p != null; p = p.next)
        {   str += p.data.toString();
            if (p.next != null)
                str += ",";//不是最后一个结点时后加分隔符
        }
        return str + ")";//空表返回()
    }

    //equals方法
    public boolean equals(Object obj){
        if(this == obj)//同一对象返回true
            return true;
        if(!(obj instanceof SinglyLinkedList))//非同类对象返回false
            return false;
        Node<T> p = this.head.next;
        Node<T> q = ((SinglyLinkedList<T>) obj).head.next;
        while(p != null && q != null && p.data.equals(q.data)){//逐个比较
            p = p.next;
            q = q.next;
        }
        return p == null && q == null;
    }

    /*
        以下4个方法提供迭代遍历单链表方式
     */
    //返回单链表第一个元素
    public Node<T> getFirst(){
        return this.head.next;//空表返回null
    }

    //返回p的后继节点。O(1)
    public Node<T> getNext(Node<T> p){
        if(p == null || this.head.next == null)
            return null;
        return p.next;
    }

    //返回p的前驱节点。O(n)
    public Node<T> getPred(Node<T> p){
        if(p == null || this.head.next == p)//p为null，或者p为第一个节点则返回null
            return null;
        Node<T> front = this.head.next;
        while(front != null && front.next != p)
            front = front.next;
        return front;
    }

    //返回单链表的最后一个节点
    public Node<T> getLast(){
        Node<T> p = this.head.next;
        while(p != null && p.next != null)
            p = p.next;
        return p;
    }

    //求子表，返回从第i(i>=0)开始、长度为n(n>=0)的子表：深拷贝
    //若i<0或n<0，抛出异常；若i>表长或n-0，返回空链表；若n超长，返回至表尾的子表
    public SinglyLinkedList<T> sub(int i, int n){
        if(i < 0)
            throw new IndexOutOfBoundsException(i + "");//越界
        if(n < 0)
            throw new IllegalArgumentException(i + "");//无效参数
        Node<T> p = this.head.next;
        for(int j = 0; j < i && p != null; j++)//寻找子表表首节点
            p = p.next;
        SinglyLinkedList<T> list = new SinglyLinkedList<T>();
        Node<T> rear = list.head;//复制子表
        for(int j = 0; p != null && j < n; j++, p = p.next){
            rear.next = new Node<T>(p.data, null);
            rear = rear.next;
        }
        return list;//返回对象引用
    }

    //判断当前单链表是否包含list单链表的所有节点，判断无序的子集
    public boolean contain(SinglyLinkedList<T> list){
        for(Node<T> q = list.head.next; q != null; q = q.next){
            Node<T> p = head.next;
            while(p != null && !p.data.equals(q.data))
                p = p.next;
            if(p == null)
                return false;
        }
        return true;
    }

    //将list单链表中所有节点链接在当前单链表之后，并设置list为空
    public void concat(SinglyLinkedList<T> list){
        Node<T> rear = this.head;
        while(rear.next != null)
            rear = rear.next;//找到最后一个
        rear.next = list.head.next;//接上
        list.head.next = null;
    }

    //插入子表（深拷贝）
    //将list单链表中的所有节点赋复制插入到this单链表front节点之后
    public void insert(Node<T> front, SinglyLinkedList<T> list){
        for(Node<T> q = list.head.next; q != null; q = q.next){
            front.next = new Node<T>(q.data, front.next);
            front = front.next;
        }
    }

    //将list单链表中的所有节点赋值插入到this单链表第i个节点之前
    public void insert(int i, SinglyLinkedList<T> list){
        Node<T> p =  head;
        for(int j = 0; j < i && p.next != null; j++)
            p = p.next;
        this.insert(p, list);
    }

    //将list单链表中所有节点复制添加到当前单链表最后
    public void append(SinglyLinkedList<T> list){
        Node<T> rear = head;
        while(rear.next != null)
            rear = rear.next;
        insert(rear, list);
    }

    //删除从第i（≥0）个结点开始、长度为n（≥1）的子表
    //若i<0或n<0，抛出异常；若i>表长或n=0，不删除；若n超长，删除至表尾的子表
    public void remove(int i, int n){
        if(i < 0)
            throw new IndexOutOfBoundsException(i + "");    //抛出序号越界异常
        if(n < 0)
            throw new IllegalArgumentException(i + "");     //抛出无效参数异常
        Node<T> front = head;
        for (int j = 0; front != null && j < i; j++)//寻找待删除子表首结点的前驱结点
            front = front.next;
        if (front == null)//i越界，不删除
            return;
        Node<T> p = front.next;
        for (int j = 0; p != null && j < n; j++)//寻找待删除子表之后的结点
            p = p.next;
        front.next = p;//删除front后继结点至p前驱结点之间的子表
    }

    //返回this单链表首次出现的与pattern匹配的子表首结点，
    //查找不成功、当前或pattern单链表为空时，返回null
    public Node<T> index(SinglyLinkedList<T> pattern){
        return index(head.next, pattern);
    }

    //返回this单链表从start结点开始首次出现的与pattern匹配的子表首结点。
    //BF模式匹配。逐个结点比较，一重循环。
    public Node<T> index(Node<T> start, SinglyLinkedList<T> pattern){
        if (pattern.isEmpty())                             //若无此句，则返回start，错误
            return null;
        Node<T> p = start;
        Node<T> q = pattern.head.next;
        while (p != null && q != null)
        {
            if (p.data.equals(q.data))                     //继续比较下一个结点
            {   p = p.next;
                q = q.next;
            }
            else                                           //一次匹配失败
            {   start = start.next;                          //this继续匹配下个子表
                p = start;
                q = pattern.head.next;                       //pattern回退至表头
            }
        }
        if (q == null)                                       //匹配成功
            return start;                                  //返回匹配子表首结点
        return null;                                       //this空或全部匹配失败
    }

    //BF模式匹配。逐个子表匹配，二重循环。
//    public Node<T> index(Node<T> start, SinglyLinkedList<T> pattern){
//        if (pattern.isEmpty())                             //若无此句，则返回start，错误
//            return null;
//        while (start!=null)
//        {
//            Node<T> p=start, q=pattern.head.next;
//            while (p!=null && q!=null && p.data.equals(q.data))//继续比较下一个结点
//            {
//                p=p.next;
//                q=q.next;
//            }
//            if (q==null)                                   //匹配成功
//                return start;                              //返回匹配子表首结点
//            start=start.next;
//        }
//        return null;                                       //this空或全部匹配失败
//    }

    //删除所有与pattern匹配的子表，BF模式匹配查找到再删除
    public void removeAll(SinglyLinkedList<T> pattern){
        System.out.print("将" + this.toString() + "中" + pattern.toString() + "全部删除");
        if (pattern.isEmpty())                             //若无此句，则死循环，错误
            return;
        Node<T> start = this.head.next, front = this.head;
        while (start != null){
            Node<T> p = start, q = pattern.head.next;
            while (p != null && q != null && p.data.equals(q.data)) {//一次匹配
                p = p.next;
                q = q.next;
            }
            if (q != null){                                   //匹配失败，进行下次匹配
                front = start;
                start = start.next;
            }else{                                           //匹配成功，删除该匹配子表
                front.next = p;
                start = p;
            }
        }
        System.out.println("的结果是" + this.toString());
    }

    //将this单链表中所有与pattern匹配的子表替换为dest子表。当dest为空时，相当于删除匹配子表。
    //包含BF模式匹配、删除匹配子表、复制插入子表算法
    public void replaceAll(SinglyLinkedList<T> pattern, SinglyLinkedList<T> dest){
        System.out.print("将" + this.toString() + "中" + pattern.toString() + "全部替换为" + dest.toString());
        if (pattern.isEmpty())                             //若无此句，则将dest插入到start结点之后，错误
            return;
        Node<T> start = this.head.next, front = this.head;     //front是start的前驱结点
        while (start != null){                        //start指向每次匹配的起始结点
            Node<T> p = start, q = pattern.head.next;
            while (p != null && q != null && p.data.equals(q.data)){//一次匹配的多次比较
                p = p.next;
                q = q.next;
            }
            if (q != null){                                //匹配失败，进行下次匹配
                front = start;
                start = start.next;
            }else{                                           //匹配成功，替换该匹配子表
                front.next = p;
                start = p;
                Node<T> d = dest.head.next;
                while (d != null){                        //将dest剩余结点深拷贝插入到this单链表front之后p之前
                    front.next = new Node<T>(d.data, p);
                    front = front.next;
                    d = d.next;
                }
            }
        }
        System.out.println("的结果是" + this.toString());
    }

    //顺序查找关键字为key元素，返回首次出现的元素，若查找不成功返回null
    //key可以只包含关键字数据项，由T类的equals()方法提供比较对象相等的依据
    @Override
    public T search(T key) {
        if(key == null)
            return null;
        for(Node<T> p = this.head.next; p != null; p = p.next)
            if(p.data.equals(key))
                return p.data;
        return null;
    }

    //判断线性表是否包含关键字为key元素
    public boolean contain(T key){
        return this.search(key) != null;
    }

    //删除首次出现的值为x的结点，若没找到指定结点则不删除。O(n)
    public void remove(T x){
        if(x == null)
            return;
        Node<T> front = this.head;
        Node<T> p = front.next;
        while(p != null && !p.data.equals(x)){
            front = p;
            p = p.next;
        }
        if(p != null)
            front.next = p.next;//头删除、中间/尾删除
    }

    //删除所有值为x的结点
    public void removeAll(T x){
        if (x != null)
        {
            Node<T> front = this.head, p = front.next;
            while (p != null) {
                if (p.data.equals(x)) {
                    front.next = p.next;                   //删除p结点
                    p = front.next;
                } else {
                    front = p;
                    p = p.next;
                }
            }
        }
    }

    //将首次出现的元素x替换为y，O(n)
    public void replace(T x, T y){
        if (x != null && y != null) {
            for (Node<T> p = this.head.next; p != null; p = p.next)
                if (p.data.equals(x)) {
                    p.data = y;
                    return;
                }
        }
    }

    //将所有值为x元素替换为y，O(n)
    public void replaceAll(T x, T y){
        if (x != null && y != null)
            for (Node<T> p = this.head.next;  p != null;  p = p.next)
                if (p.data.equals(x))
                    p.data = y;
    }


    /**
     * 实现迭代器
     */
    public java.util.Iterator<T> iterator(){
        return new SinglyIterator();
    }

    private class SinglyIterator implements java.util.Iterator<T>{
        //当前节点，初值为外部类单链表头结点
        Node<T> current = SinglyLinkedList.this.head;

        //当前结点的前驱结点
        Node<T> front = null;


        @Override
        public boolean hasNext() {
            return this.current != null && this.current.next != null;
        }

        @Override
        public T next() {
            if(this.hasNext()){
                this.front = this.current;
                this.current = this.current.next;
                return this.current.data;
            }else{
                throw new java.util.NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(this.front != null){
                this.front.next = this.current.next;//删除当前节点
                this.current = this.front;
                this.front = null;//设置不能连续删除
            }else{
                throw new java.lang.IllegalStateException();
            }
        }
    }



    /**
     * 递归方法
     */
    /*    public String toString()                               //返回单链表描述字符串
    {
        return "("+ this.toString(this.head.next) +")";
    }
    public String toString(Node<T> p)                      //返回从p结点开始的子表描述字符串，递归方法
    {
         if (p==null)
             return "";
         String str=p.data.toString();
         if (p.next!=null)
             str+=", ";
         return str+this.toString(p.next);                 //递归调用
    }

    public SinglyLinkedList(T[] element)                  //由指定数组中的多个对象构造单链表
    {
        this();                                            //创建空单链表，只有头结点
        this.head.next = create(element,0);
    }
    private Node<T> create(T[] element, int i)            //由指定数组构造单链表，递归方法
    {
        Node<T> p=null;
        if (i<element.length)
        {
            p = new Node<T>(element[i], null);
            p.next = create(element, i+1);
        }
        return p;
    }

    public SinglyLinkedList(SinglyLinkedList<T> list)      //深拷贝，以单链表list构造新的单链表
    {
        this();
        this.head.next = copy(list.head.next);
    }
    private Node<T> copy(Node<T> p)                        //复制单链表，递归方法
    {
        Node<T> q=null;
        if (p!=null)
        {
            q = new Node<T>(p.data, null);
            q.next = copy(p.next);
        }
        return q;
    }

    public int length()                                    //返回单链表长度
    {
        return length(this.head.next);
    }
    public int length(Node<T> p)                           //返回从p结点开始的单链表长度，递归方法
    {
        if (p==null)
            return 0;
        return 1+length(p.next);                           //递归调用
    }

    public boolean equals(Object obj)                      //比较两条单链表是否相等
    {
        if (obj == this)
            return true;
        if (obj instanceof SinglyLinkedList)
        {
            SinglyLinkedList<T> list = (SinglyLinkedList<T>)obj;
            return equals(this.head.next, list.head.next);
        }
        return false;
    }
    private boolean equals(Node<T> p, Node<T> q)           //比较两条单链表是否相等，递归方法
    {
        return p==null && q==null ||
               p!=null && q!=null && p.data.equals(q.data) && equals(p.next, q.next);
    }
    */
}
