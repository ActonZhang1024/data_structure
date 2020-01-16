package pers.zhang.stack;

/**
 * @author zhang
 * @date 2020/1/16 - 14:12
 *
 * 顺序栈，实现栈接口
 */
public class SeqStack<T> implements SStack<T> {

    //存储栈数据元素的Object数组
    private Object element[];

    //栈顶元素下表
    private int top;

    //构造容量为size的栈
    public SeqStack(int size){
        this.element = new Object[Math.abs(size)];
        this.top = -1;
    }

    //默认构造，构造容量为64的空栈
    public SeqStack(){
        this(64);
    }

    //判断栈是否为空，若为空返回true
    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    //元素x入栈，空对象不操作
    @Override
    public void push(T x) {
        if(x == null)
            return;
        if(this.top == element.length - 1){//栈满，需要扩容
            Object[] temp = this.element;
            this.element = new Object[temp.length * 2];//重新申请一个2倍容量的数组
            for(int i = 0; i < temp.length; i++)//复制元素
                this.element[i] = temp[i];
        }
        this.top++;//移动栈顶指针
        this.element[this.top] = x;//入栈
    }

    //出栈，返回栈顶元素，若栈空返回null
    @Override
    public T pop() {
        return this.top == -1 ? null : (T) this.element[this.top--];
    }

    //取出栈顶元素，未出栈，若栈空返回null
    @Override
    public T get() {
        return this.top == -1 ? null : (T) this.element[this.top];
    }

    //返回栈所有元素的描述字符串，形式为“(,)”，算法同顺序表
    @Override
    public String toString(){
        String str = "(";
        if (this.top != -1)
            str += this.element[this.top].toString();
        for (int i= this.top - 1; i >= 0; i--)
            str += ", "+this.element[i].toString();
        return str + ") ";
    }
}
