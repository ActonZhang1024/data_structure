package pers.zhang.linearList;

/**
 * @author zhang
 * @date 2020/1/15 - 11:21
 *
 * 循环双链表
 */
public class CirDoublyLinkedList<T> implements LinearList<T> {

    //头指针
    public DLinkNode<T> head;

    //默认构造，构造空循环链表
    public CirDoublyLinkedList(){
        this.head = new DLinkNode<T>();
        this.head.pred = head;
        this.head.next = head;
    }

    //由指定数组中的多个对象构造循环双链表，采用尾插入构造循环双链表
    public CirDoublyLinkedList(T[] element){
        this();                                  //创建空循环双链表，只有头结点
        DLinkNode<T> rear = this.head;
        for (int i = 0; i < element.length; i++){
            rear.next = new DLinkNode<T>(element[i], rear, this.head);   //尾插入
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //深拷贝构造方法，复制循环双链表
    public CirDoublyLinkedList(CirDoublyLinkedList<T> list){
        this();                                  //创建空循环双链表，只有头结点
        DLinkNode<T> rear = this.head;
        for (DLinkNode<T> p = list.head.next; p != list.head; p = p.next){
            rear.next = new DLinkNode<T>(p.data, rear, this.head);
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //深拷贝构造方法，由单链表list构造循环双链表
    public CirDoublyLinkedList(SinglyLinkedList<T> list){
        this();
        DLinkNode<T> rear = this.head;
        for (Node<T> p = list.head.next;  p != null;  p = p.next){
            rear.next = new DLinkNode<T>(p.data, rear, this.head);
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //深拷贝构造方法，由循环单链表list构造循环双链表
    public CirDoublyLinkedList(CirSinglyLinkedList<T> list){
        this();
        DLinkNode<T> rear = this.head;
        for (Node<T> p = list.head.next; p != list.head; p = p.next){
            rear.next = new DLinkNode<T>(p.data, rear, this.head);
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //判断循环双链表是否空
    @Override
    public boolean isEmpty() {
        return head.next == head;
    }

    //返回表长
    @Override
    public int length() {
        int i = 0;
        for(DLinkNode<T> p = this.head.next; p != this.head; p = p.next)
            i++;
        return i;
    }

    //返回第i（≥0）个元素，若i<0或大于表长则返回null，O(n)
    @Override
    public T get(int i) {
        if(i >= 0){
            DLinkNode<T> p = this.head.next;
            for(int j = 0; p != null && j < i; j++)
                p = p.next;
            if(p != null)
                return p.data;
        }
        return null;//当i<0或大于表长时
    }

    //设置第i（≥0）个元素值为x。若i<0或大于表长则抛出序号越界异常；若x==null，不操作。O(n)
    @Override
    public void set(int i, T x) {
        if(x == null)
            return;
        DLinkNode<T> p = this.head.next;
        for(int j = 0; p != null && j < i; j++)
            p = p.next;
        if(i >= 0 && p != null)
            p.data = x;
        else
            throw new IndexOutOfBoundsException(i + "");
    }

    //插入第i（≥0）个元素值为x。若x==null，不插入。
    //若i<0，插入x作为第0个元素；若i大于表长，插入x作为最后一个元素。O(n)
    @Override
    public void insert(int i, T x) {
        if(x == null)//不能插入空对象
            return;
        DLinkNode<T> p = this.head;
        for(int j = 0; j < i && p.next != this.head; j++)
            p = p.next;
        DLinkNode<T> q = new DLinkNode<T>(x, p, p.next);//插入在p节点之后，包括头插入，中间插入
        p.pred.next = q;
        p.next = q;
    }

    //在循环双链表最后添加结点，O(1)
    @Override
    public void append(T x) {
        if(x == null)
            return;
        DLinkNode<T> q = new DLinkNode<T>(x, head.pred, head);
        head.pred.next = q;
        head.pred = q;
    }

    //删除第i（≥0）个元素，返回被删除对象。若i<0或i大于表长，不删除，返回null。O(n)
    @Override
    public T remove(int i) {
        if(i >= 0){
            DLinkNode<T> p = this.head.next;
            for(int j = 0; p != head && j < i; j++)
                p = p.next;
            if(p != head){
                T old = p.data;
                p.pred.next = p.next;
                p.next.pred = p.pred;
                return old;
            }
        }
        return null;
        //throw  new IndexOutOfBoundsException(i + "");
    }

    //删除循环双链表所有元素
    @Override
    public void removeAll() {
        this.head.pred = head;
        this.head.next = head;
    }

    //返回循环双链表所有元素的描述字符串，循环双链表遍历算法，O(n)
    @Override
    public String toString(){
        String str = "(";
        for (DLinkNode<T> p = this.head.next;  p != this.head;  p = p.next){
            str += p.data.toString();
            if (p.next != this.head)
                str += ",";
        }
        return str+")";                               //空表返回()
    }

    //比较两条循环双链表是否相等，覆盖Object类的equals(obj)方法
    @Override
    public boolean equals(Object obj){
        if (obj == this)//同一对象
            return true;
        if (!(obj instanceof CirDoublyLinkedList))//不同类对象
            return false;
        DLinkNode<T> p = this.head.next;
        CirDoublyLinkedList<T> list = (CirDoublyLinkedList<T>)obj;
        DLinkNode<T> q = list.head.next;
        while (p!=head && q!=list.head && p.data.equals(q.data)){//逐个比较
            p = p.next;
            q = q.next;
        }
        return p == head && q == list.head;
    }


    //顺序查找关键字为key元素，返回首次出现的元素，若查找不成功返回null
    //key可以只包含关键字数据项，由T类的equals()方法提供比较对象相等的依据
    @Override
    public T search(T key) {
        if(key == null)
            return null;
        for(DLinkNode<T> p = this.head.next; p != this.head; p = p.next)
            if(key.equals(p.data))
                return p.data;
        return null;
    }

    //判断线性表是否包含关键字为key元素
    public boolean contain(T key){
        return this.search(key) != null;
    }

    //输出循环双链表，从后向前沿着前驱域
    public void printPred(){
        System.out.print("从后向前沿着前驱域遍历循环双链表，(");
        for (DLinkNode<T> p = this.head.pred; p != this.head;  p = p.pred)
        {
            System.out.print(p.data.toString());
            if (p.pred != this.head)
                System.out.print(", ");
        }
        System.out.println(")");                           //空表返回()
    }

    /**
     * 以下四个方法提供迭代遍历循环双链表方式
     */
    //返回循环双链表第一个结点（非头结点），O(1)
    public DLinkNode<T> getFirst(){
        if (this.head.next == head)
            return null;
        return this.head.next;
    }

    //返回p的后继结点，O(1)
    public DLinkNode<T> getNext(DLinkNode<T> p){
        if (this.head.next == head || p == null)
            return null;
        return p.next;
    }

    //返回p的前驱结点，O(1)
    public DLinkNode<T> getPred(DLinkNode<T> p){
        if (this.head.next == head || p == null || this.head.next == p)
            return null;
        return p.pred;
    }

    //返回循环双链表最后一个结点，O(1)
    public DLinkNode<T> getLast(){
        if (this.head.pred == head)
            return null;
        return this.head.pred;
    }

    /**
     * 子表操作
     */
    //将list循环双链表中所有结点链接在当前循环双链表之后，并设置list为空
    public void concat(CirDoublyLinkedList<T> list){
        DLinkNode<T> rear = head.pred;
        rear.next = list.head.next;
        list.head.next.pred = rear;
        rear = list.head.pred;
        rear.next = this.head;
        this.head.pred = rear;
        list.head.pred = list.head;
        list.head.next = list.head;
    }

    //返回从第i(≥0)个结点开始、长度为n(≥0)的子表（深拷贝）
    //若i<0或n<0，抛出异常；若i>表长或n=0，返回空链表；若n超长，返回至表尾的子表
    public CirDoublyLinkedList<T> sub(int i, int n){
        if (i < 0)
            throw new IndexOutOfBoundsException(i+"");    //抛出序号越界异常
        if (n < 0)
            throw new IllegalArgumentException(i+"");     //抛出无效参数异常
        DLinkNode<T> p = this.head.next;
        for (int j = 0; j < i && p != this.head; j++)          //寻找子表首结点
            p = p.next;                                    //循环停止时，p指向第i结点。若i>表长，则p==this.head
        CirDoublyLinkedList<T> list = new CirDoublyLinkedList<T>();//空循环双链表
        DLinkNode<T> rear = list.head;                       //复制子表到list链表rear结点之后
        for (int j = 0; p != this.head && j < n; j++, p = p.next){
            rear.next.pred = new DLinkNode<T>(p.data, rear, list.head);
            rear.next = rear.next.pred;
            rear = rear.next;
        }
        return list;                                       //返回对象引用
    }

    //插入子表（深拷贝），将list循环双链表中的所有结点复制插入到当前循环双链表front结点之后
    public void insert(DLinkNode<T> front, CirDoublyLinkedList<T> list){
        for (DLinkNode<T> q = list.head.next;  q != list.head; q = q.next){
            front.next.pred = new DLinkNode<T>(q.data, front, front.next);
            front.next = front.next.pred;
            front = front.next;
        }
    }

    //插入子表（深拷贝），将list循环双链表中的所有结点复制插入到当前循环双链表第i个结点之前
    public void insert(int i, CirDoublyLinkedList<T> list){
        DLinkNode<T> p = this.head;
        for (int j = 0; j < i && p.next != this.head; j++)     //寻找插入位置
            p = p.next;                                    //循环停止时，p指向第i-1结点或最后一个结点
        this.insert(p,list);                               //复制插入list链表到p结点之后
    }

    //将list循环双链表中所有结点复制添加到当前循环双链表最后
    public void append(CirDoublyLinkedList<T> list){
        this.insert(this.head.pred, list);                  //复制插入list链表到链尾结点之后
    }

    //删除从第i（≥0）个结点开始、长度为n（≥1）的子表
    //若i<0或n<0，抛出异常；若i>表长或n=0，不删除；若n超长，删除至表尾的子表
    //算法类似单链表
    public void remove(int i, int n){
        if (i < 0)
            throw new IndexOutOfBoundsException(i+"");    //抛出序号越界异常
        if (n < 0)
            throw new IllegalArgumentException(i+"");     //抛出无效参数异常
        DLinkNode<T> front = this.head.next;
        for (int j = 0; j < i && front != this.head; j++)        //寻找待删除子表首结点的前驱结点
            front = front.next;
        if (front == this.head)                              //i越界，不删除
            return;
        DLinkNode<T> p = front.next;
        for (int j = 0; j < n && p != this.head; j++)          //寻找待删除子表之后的结点
            p = p.next;
        front.next = p;                                    //删除front后继结点至p前驱结点之间的子表
        p.pred = front;
    }


    //返回this单链表首个与patten匹配子表的首结点，查找不成功时返回null
    public DLinkNode<T> index(CirDoublyLinkedList<T> pattern){
        return index(head.next, pattern);
    }

    //返回this单链表从start结点开始首个与pattern匹配子表的首结点，查找不成功时返回null，BF模式匹配
    public DLinkNode<T> index(DLinkNode<T> start, CirDoublyLinkedList<T> pattern){
        if (start == null || pattern.isEmpty())
            return null;
        DLinkNode<T> p = start, q = pattern.head.next;
        while (p != this.head && q != pattern.head){
            if (p.data.equals(q.data)){                     //继续比较下一个结点
                p = p.next;
                q = q.next;
            }else{                                           //回退
                start = start.next;                          //目标表继续从下个子表
                p = start;
                q = pattern.head.next;                       //模式表回退至表头
            }
        }
        if(q == pattern.head)                               //存在匹配的子表
            return start;                                  //返回子表首结点地址
        return null;
    }

    //删除所有与pattern匹配的子表。算法调用index(pattern)查找到再删除。
    public void removeAll(CirDoublyLinkedList<T> pattern){
        System.out.print("将" + this.toString() + "中" + pattern.toString() + "全部删除");
        DLinkNode<T> p = this.index(pattern);      //返回从头结点开始首个与pattern匹配子表的首结点
                                                 //若pattern为空，返回null
        while (p != null){
            DLinkNode<T> q = pattern.head.next;
            while (q != pattern.head){          //删除从p开始与pattern匹配的子表。不能调用remove(i,n)方法，效率低
                p.pred.next = p.next;            //删除p结点，但未释放p结点，p.next和p.pred仍然有效
                p.next.pred = p.pred;
                p = p.next;                        //p指向被删除结点的原后继结点
                q = q.next;
            }
            p = this.index(p,pattern);             //再次匹配，返回从p结点开始首个与pattern匹配子表的首结点
        }
        System.out.println("的结果是" + this.toString());
    }

    //将this单链表中首个与pattern匹配的子表替换为dest子表。
    //算法类似replaceAll(pattern, dest)，只将while改为if，只做一次匹配替换
    public void replaceFirst(CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest){
        System.out.print("将" + this.toString() + "中首个" + pattern.toString() + "替换为" + dest.toString());
        DLinkNode<T> p = this.index(pattern);      //返回从头开始首个与pattern匹配子表的首结点
        if (p != null){                             //匹配成功，进行替换
            DLinkNode<T> q = pattern.head.next;
            while (q != pattern.head){              //删除从p开始与pattern匹配的子表
                p.pred.next = p.next;            //删除p结点，但未释放p结点，p.next和p.pred仍然有效
                p.next.pred = p.pred;
                p = p.next;
                q = q.next;
            }
            p = p.pred;
            q = dest.head.next;
            while (q!=dest.head){                 //将dest中所有结点深拷贝插入到当前单链表p结点之后
                p.next.pred = new DLinkNode<T>(q.data,p,p.next);  //将q结点深拷贝插入到p结点之后
                p.next = p.next.pred;
                p = p.next;
                q = q.next;
            }
            p = p.next;
        }
        System.out.println("的结果是" + this.toString());
    }

    //将this单链表中所有与pattern匹配的子表替换为dest子表。
    //算法调用index(pattern)查找到再删除和插入。
    public void replaceAll(CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest){
        System.out.print("将" + this.toString() + "中" + pattern.toString() + "全部替换为" + dest.toString());
        DLinkNode<T> p = this.index(pattern);      //返回从头开始首个与pattern匹配子表的首结点
        while (p != null){                          //匹配成功，进行一次替换
            DLinkNode<T> q = pattern.head.next;
            while (q != pattern.head)              //删除从p开始与pattern匹配的子表
            {
                p.pred.next = p.next;            //删除p结点，但未释放p结点，p.next和p.pred仍然有效
                p.next.pred = p.pred;
                p = p.next;
                q = q.next;
            }
            p = p.pred;
            q = dest.head.next;
            while (q != dest.head){                 //将dest中所有结点深拷贝插入到当前单链表p结点之后
                p.next.pred = new DLinkNode<T>(q.data,p,p.next);  //将q结点深拷贝插入到p结点之后
                p.next = p.next.pred;
                p = p.next;
                q = q.next;
            }
            p = p.next;
            p = this.index(p,pattern);             //再次匹配，返回从p结点开始首个与pattern匹配子表的首结点
        }
        System.out.println("的结果是" + this.toString());
    }

    //删除所有与pattern匹配的子表。BF模式匹配算法查找到再删除，没有调用index(pattern)。算法同单链表。
//    public void removeAll(CirDoublyLinkedList<T> pattern)
//    {
//        System.out.print("将"+this.toString()+"中"+pattern.toString()+"全部删除");
//        if (pattern.isEmpty())                             //若无此句，则死循环，错误
//            return;
//        DLinkNode<T> start=this.head.next;
//        while (start!=this.head)
//        {
//            DLinkNode<T> p=start, q=pattern.head.next;
//            while (p!=this.head && q!=pattern.head && p.data.equals(q.data)) //一次匹配
//            {   p=p.next;
//                q=q.next;
//            }
//            if (q!=pattern.head)                           //匹配失败，进行下次匹配
//                start=start.next;
//            else                                           //匹配成功，删除该匹配子表
//            {   start.pred.next = p;
//                p.pred = start.pred;
//                start=p;
//            }
//        }
//        System.out.println("的结果是"+this.toString());
//    }


    //将this单链表中所有与pattern匹配的子表替换为dest子表。当dest为空时，相当于删除匹配子表。
    //包含BF模式匹配、删除匹配子表、复制插入子表算法
//    public void replaceAll(CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest)
//    {
//        System.out.print("将"+this.toString()+"中"+pattern.toString()+"全部替换为"+dest.toString()+"的结果是");
//        if (pattern.isEmpty())                             //若无此句，则将dest插入到start结点之后，错误
//            return;
//        DLinkNode<T> start=this.head.next;
//        while (start!=this.head)                           //start指向每次匹配的起始结点
//        {
//            DLinkNode<T> p=start, q=pattern.head.next;
//            while (p!=this.head && q!=pattern.head && p.data.equals(q.data))//一次匹配的多次比较
//            {   p=p.next;
//                q=q.next;
//            }
//            if (q!=pattern.head)                           //匹配失败，进行下次匹配
//                start=start.next;
//            else                                           //匹配成功，替换该匹配子表
//            {   start.pred.next = p;                       //删除该匹配子表
//                p.pred = start.pred;
//                start=p;
//                DLinkNode<T> d=dest.head.next;
//                while (d!=dest.head)                       //将dest剩余结点深拷贝插入到this单链表p之前
//                {   DLinkNode<T> t = new DLinkNode<T>(d.data, p.pred, p);
//                    p.pred.next = t;
//                    p.pred = t;
//                    d = d.next;
//                }
//            }
//        }
//        System.out.println(this.toString());
//    }

    /**
     * 实现迭代器
     */
    public java.util.Iterator<T> iterator(){
        return new DoublyIterator();
    }

    //私有内部类实现迭代器
    private class DoublyIterator implements java.util.Iterator<T>{
        //当前节点
        DLinkNode<T> current = CirDoublyLinkedList.this.head;

        //是否可删除状态
        boolean removable = false;

        @Override
        public boolean hasNext() {
            return this.current.next != CirDoublyLinkedList.this.head;
        }

        @Override
        public T next() {
            if(this.hasNext()){
                this.removable = true;
                this.current = this.current.next;
                return this.current.data;
            }else{
                throw new java.util.NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(this.removable){
                this.current.next.pred = this.current.pred;
                this.current.pred.next = this.current.next;
                this.current = this.current.pred;
                this.removable = false;
            }else{
                throw new java.lang.IllegalStateException();
            }
        }
    }

    public java.util.ListIterator<T> listIterator(){        //返回Java列表迭代器对象
        return new DoublyListIterator(0);
    }
    public java.util.ListIterator<T> listIterator(final int index){        //返回Java列表迭代器对象
        return new DoublyListIterator(index);
    }

    //私有内部类，实现列表迭代器接口
    private class DoublyListIterator extends DoublyIterator implements java.util.ListIterator<T>{

        //当前元素序号
        int succ = 0;

        public DoublyListIterator(int index){
            this.succ = index;
            //        current=CirDoublyLinkedList.this.head;//当前结点
            int i = -1;
            while (i < index && this.hasNext()){
                i++;
                this.current = this.current.next;
            }
            if (index < 0 || !this.hasNext())
                throw new IndexOutOfBoundsException("Index: " + index);
        }

        public boolean hasPrevious(){                       //若有前驱元素，返回true
            return this.current.pred != CirDoublyLinkedList.this.head;
        }

        public T previous(){                                //返回前驱元素
            if (this.hasPrevious()){
                this.current = this.current.pred;
                this.succ--;
                return this.current.data;
            }else{
                throw new java.util.NoSuchElementException();  //抛出无此元素异常
            }
        }

        //返回后继元素序号
        public int nextIndex(){
            return this.succ;
        }

        //返回前驱元素序号
        public int previousIndex(){
            return this.succ-1;
        }

        //将集合当前元素替换为x
        public void set(T x){
            if (x != null && this.current != CirDoublyLinkedList.this.head)
                this.current.data = x;
        }

        //增加元素x，在当前结点之后插入x
        public void add(T x){
            if (x == null)
                return;                                    //不能添加空对象
            DLinkNode<T> q = new DLinkNode<T>(x, this.current, this.current.next);
            this.current.next.pred = q;                    //在当前结点之后插入x
            this.current.next = q;
            this.current = this.current.next;
            this.succ++;                                  //插入结点为当前结点
        }
    }
}
