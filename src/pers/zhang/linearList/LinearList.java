package pers.zhang.linearList;

/**
 * @author zhang
 * @date 2020/1/13 - 16:28
 * 线性表接口，泛型参数T表示数据元素的数据类型
 */
public interface LinearList<T> {
    //判断线性表是否为空
    boolean isEmpty();

    //返回线性表长度
    int length();

    //返回第i(i>=0)个元素
    T get(int i);

    //设置第i个元素值为x
    void set(int i, T x);

    //插入x作为第i个元素
    void insert(int i, T x);

    //在线性表最后插入x元素
    void append(T x);

    //删除第i个元素并返回被删除的对象
    T remove(int i);

    //删除线性表所有元素
    void removeAll();

    //查找，返回首次出现的关键字为key的元素
    T search(T key);
}
