package pers.zhang.linearList;

import java.util.Arrays;

/**
 * @author zhang
 * @date 2020/1/13 - 16:45
 *
 * 顺序表，线性表的顺序表示
 */
public class SeqList<T> implements LinearList<T> {

    //对象数组
    private Object[] element;

    //顺序表长度
    private int len;

    //构造方法，创建容量为size的顺序表
    public SeqList(int size){
        //若size<0，抛出负数组长度异常NegativeArraySizeException
        this.element = new Object[size];
        this.len = 0;
    }

    //默认构造方法，创建一个容量为64的顺序表
    public SeqList(){
        this(64);
    }

    //顺序表拷贝
    //浅拷贝构造
//    public SeqList(SeqList<T> list){
//        this.len = list.len;
//        this.element = list.element;//数组为引用赋值，两个变量共享一个数组，错误！
//    }

    //深拷贝构造
    public SeqList(SeqList<T> list){
        if(list == null)//若list为空，抛出异常
            throw new NullPointerException();
        this.len = list.len;
        this.element = new Object[list.element.length];//申请一个数组，大小为传入list的数组大小
        for(int i = 0; i < list.len; i++)//复制数组元素，O(n)
            this.element[i] = list.element[i];
        //this.element[i] = new T(list.element[i]); 语法错误，因为Java没有提供默认拷贝构造方法
        //this.element[i] = new Object(list.element[i]); 语法错，因为Object没有提供拷贝构造方法，且构造方法不能继承

    }

    //深拷贝构造，参数数组指定顺序表初值
    public SeqList(T[] element){
        this.len = element.length;
        this.element = new Object[element.length];//申请一个数组，大小为数组长度
        for(int i = 0; i < element.length; i++)
            this.element[i] = element[i];
    }

    //判断顺序表是否为空，若空返回true
    @Override
    public boolean isEmpty() {
        return this.len == 0;
    }

    //返回顺序表的长度
    @Override
    public int length() {
        return this.len;
    }

    //返回第i（i>=0）个元素。若i<0或大于表长则返回null
    @Override
    public T get(int i) {
        if(i >= 0 && i < this.len)
            return (T)this.element[i];//需要强转为T类型，否则编译出错
        return null;
    }

    //设置第i（i>=0）个元素的值为x。若i<0或大于表长则抛出序号越界异常；若x==null，不操作
    @Override
    public void set(int i, T x) {
        if(x == null)//不能设置空对象
            return;
        if(i >= 0 && i < this.len)
            this.element[i] = x;
        else
            throw  new IndexOutOfBoundsException(i + "");//抛出越界异常
    }

    //返回顺序表所有元素的描述字符串，形式为“（，）”，覆盖Object类的toString()方法
    @Override
    public String toString() {
        String str = "(";
        if(this.len > 0)
            str += this.element[0].toString();
        for(int i = 1; i < this.len; i++)
            str += ", " + this.element[i].toString();
        return str +") ";//空表返回（）
    }

    //顺序表的插入操作
    //插入第i（i>=0）个元素值为x。若x==null，不插入
    //若i<0，插入 x作为第0个元素；若i大于表长，插入x作为最后一个元素。
    @Override
    public void insert(int i, T x) {
        if(x == null)
            return;
        if(this.len == element.length){//若数组已满，则先扩容
            Object[] temp = this.element;//temp引用element数组
            this.element = new Object[temp.length * 2];//重新申请一个容量*2的数组
            for(int j = 0; i < temp.length; j++)//复制数组元素
                this.element[j] = temp[j];
        }
        //下标容错
        if(i < 0)
            i = 0;
        if(i > this.len)
            i = this.len;
        //元素后移
        for(int j = this.len - 1; j >= i; j--)
            this.element[j + 1] = this.element[j];
        this.element[i] = x;
        this.len++;
    }

    //在顺序表尾部追加数据
    @Override
    public void append(T x) {
        this.insert(this.len, x);
    }

    //顺序表的删除操作
    //删除第i(i>=0)个元素，返回被删除的对象。若i<0或i大于表长，不删除，返回null
    @Override
    public T remove(int i) {
        if(this.len == 0 || i < 0 || i >= this.len)
            return null;
            //throw new IndexOutOfBoundsException(i + "");抛出越界异常
        T old = (T) this.element[i];
        for(int j = i; j < this.len - 1; j++)//元素前移，平均移动len/2
            this.element[j] = this.element[j + 1];
        this.element[this.len - 1] = null;//最后一个设为null
        this.len--;
        return old;
    }

    //删除所有
    @Override
    public void removeAll() {
        this.len = 0;
    }

    //顺序表比较equals方法
    //比较两个顺序表是否相等
    @Override
    public boolean equals(Object obj) {
        if(this == obj)//同一对象返回true
            return true;
        if(obj instanceof SeqList){//子类对象
            SeqList<T> list = (SeqList<T>) obj;
            if(this.length() == list.length()){//长度相等
                for(int i = 0; i < this.length(); i++)//比较每个元素
                    if(!(this.get(i).equals(list.get(i))))
                        return false;
                    return true;
            }
        }
        return false;
    }

    //顺序查找关键字为key元素，返回首次出现的元素，若查找不成功返回-1
    //key可以只包含关键字数据项，由T类的equals()方法提供比较对象相等的依据
    public int indexOf(T key){
        if (key != null)
            for (int i = 0; i < this.len; i++)
                if (this.element[i].equals(key))//对象采用equals()方法比较是否相等
                    return i;
        return -1;//空表、key为空对象或未找到时
    }

    //查找，返回首次出现的关键字为key元素
    @Override
    public T search(T key) {
        int find = this.indexOf(key);
        return find == -1 ? null : (T)this.element[find];
    }

    //判断线性表是否包含关键字为key元素
    public boolean contain(T key){
        return this.indexOf(key) >= 0;//顺序查找结果大于0，则存在该元素
    }

    //删除首次出现的关键字为key元素
    public void remove(T key){
        this.remove(this.indexOf(key));
    }

    //返回元素key最后出现位置，若未找到返回-1
    public int lastIndexOf(T key){
        if(key != null){
            for(int i = 0; i < this.len - 1; i++)
                if(this.element[i].equals(key))
                    return i;
        }
        return -1;
    }

    //删除所有关键字为key元素
    public void removeAll(T key){
        if (key != null)
        {
            int i = 0;
            while (i < this.len)
                if (this.element[i].equals(key))
                    this.remove(i);//删除元素，this.len减1，i不变
                else i++;
        }
    }

    //将首次出现的元素x替换为y，O(n)
    public void replace(T x, T y){
        if (x != null && y != null)
        {
            int i = this.indexOf(x);//查找x首次出现位置
            if (i != -1)
                this.element[i] = y;
        }
    }

    //将所有元素x替换为y
    public void replaceAll(T x, T y){
        if (x != null && y != null)
            for (int i= 0; i < this.len; i++)
                if (x.equals(this.element[i]))
                    this.element[i] = y;
    }

    //获取迭代器
    public java.util.Iterator<T> iterator(){
        return new SeqIterator();
    }

    //私有内部类，实现迭代器接口
    private class SeqIterator implements java.util.Iterator<T>{

        int index = -1;//当前元素序号
        int succ = 0;//后继元素序号

        //若有后继元素，返回true
        @Override
        public boolean hasNext() {
            return this.succ < SeqList.this.len;//SeqList.this.len是外部类当前实例的成员变量
        }

        //返回后继元素，若没有后继元素，返回null
        @Override
        public T next() {
            T value = SeqList.this.get(this.succ);//调用外部类SeqList当前实例的成员方法
            if(value != null){
                this.index = this.succ++;
                return value;
            }
            throw new java.util.NoSuchElementException();//抛出无此元素异常
        }

        //删除迭代器对象表示的集合当前元素
        @Override
        public void remove() {
            if(this.index >= 0 && this.index < SeqList.this.len){
                SeqList.this.remove(this.index);//调用外部类当前实例的成员方法
                SeqList.this.len--;//删除第index个元素，长度SeqList.this.len-1
                if(this.succ > 0)//(this.index < this.succ)
                    this.succ--;
                this.index = -1;//设置不能连续删除
            }else{
                throw new java.lang.IllegalStateException();//抛出无效状态异常
            }
        }
    }
}
