package pers.zhang.stack;

import pers.zhang.linearList.LinearList;
import pers.zhang.linearList.Node;

/**
 * @author zhang
 * @date 2020/1/16 - 14:28
 *
 * 链栈，实现栈接口
 */
public class LinkedStack<T> implements SStack<T> {

    //栈顶节点，同单链表节点一致
    private Node<T> top;

    //构造空栈
    public LinkedStack(){
        this.top = null;
    }

    //判断栈是否为空，若空返回true
    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    //元素x入栈，空对象不操作
    @Override
    public void push(T x) {
        if(x == null)
            return;
        this.top = new Node<>(x, this.top);//头插入，x节点作为新的栈顶
    }

    //出栈，返回栈顶元素，栈空返回null
    @Override
    public T pop() {
        if(this.top == null)
            return null;
        T temp = this.top.data;//保存栈顶节点元素
        this.top = this.top.next;//删除栈顶节点
        return temp;
    }

    //取出栈顶元素，未出栈，空栈返回null
    @Override
    public T get() {
        return this.top == null ? null : this.top.data;
    }

    //返回栈所有元素的描述字符串，形式为“(,)”。算法同不带头结点的单链表
    public String toString(){
        String str = "(";
        for (Node<T> p = this.top; p != null; p = p.next){
            str += p.data.toString();
            if (p.next != null)
                str += ", ";//不是最后一个结点时后加分隔符
        }
        return str + ")";//空表返回()
    }
}
