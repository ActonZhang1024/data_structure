package pers.zhang.stack;

/**
 * @author zhang
 * @date 2020/1/16 - 14:05
 *
 * 栈接口，描述栈的抽象数据类型，泛型参数T表示数据元素的数据类型
 */
public interface SStack<T> {
    //判断栈是否为空
    boolean isEmpty();

    //元素x入栈
    void push(T x);

    //出栈，返回栈顶元素
    T pop();

    //取出栈顶元素，未出栈
    T get();
}
