package pers.zhang.genList;

/**
 * @author zhang
 * @date 2020/1/20 - 10:57
 *
 * 广义表抽象数据结构
 */
public interface GGenList<T> {
    //判断广义表是否为空
    boolean isEmpty();

    //返回广义表长度
    int length();

    //返回广义表的深度
    int depth();

    //插入原子x作为第i个元素
    GenListNode<T> insert(int i, T x);

    //插入子表作为第i个元素
    GenListNode<T> insert(int i, GenList<T> glist);


}
