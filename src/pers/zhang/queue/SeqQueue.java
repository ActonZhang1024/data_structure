package pers.zhang.queue;

/**
 * @author zhang
 * @date 2020/1/17 - 11:45
 *
 * 顺序循环队列
 */
public class SeqQueue<T> implements QQueue<T> {

    //存储队列数据的Object数组
    private Object element[];

    //队头和队尾引用
    private int front, rear;

    //构造
    public SeqQueue(int length){
        if(length < 64)
            length = 64;
        this.element = new Object[Math.abs(length)];//设置队列容量最小值
        this.front = this.rear = 0;//构造空队列
    }

    //默认构造
    public SeqQueue(){
        this(64);
    }

    //判断队列是否为空
    @Override
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    //元素x入队，空对象不能入队
    @Override
    public void enqueue(T x) {
        if(x == null)
            return;
        if(this.front == (this.rear + 1) % this.element.length){//队满，扩容
            Object[] temp = this.element;
            this.element = new Object[temp.length * 2];//重新申请一个数组
            int j = 0;
            for(int i = this.front; i != this.rear; i = (i + 1) % temp.length)
                this.element[j++] = temp[i];
        }
        this.element[this.rear] = x;//插入数据
        this.rear = (this.rear + 1) % this.element.length;//移动队尾
    }

    //出队，返回队头元素
    @Override
    public T dequeue() {
        if(isEmpty())//空队列返回null
            return null;
        T temp = (T) this.element[this.front];
        this.front = (this.front + 1) % this.element.length;
        return temp;
    }

    @Override
    public String toString(){                     //返回队列所有元素的描述字符串，形式为“(,)”，按照队列元素次序
        String str = "(";
        if (!isEmpty()){
            str += this.element[this.front].toString();
            int i = (this.front+1) % this.element.length;
            while(i != this.rear){
                str += ", "+this.element[i].toString();
                i = (i + 1) % this.element.length;
            }
        }
        return str + ")";
    }
}
