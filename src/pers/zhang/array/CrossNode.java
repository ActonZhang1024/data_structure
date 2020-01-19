package pers.zhang.array;

/**
 * @author zhang
 * @date 2020/1/19 - 14:58
 *
 * 十字链表节点类
 */
public class CrossNode {
    //数据域表示三元组
    Triple data;

    //right指向行的下一个节点，down指向列的下一个节点
    CrossNode right, down;

    //构造结点，data指定元素，right指向行的下一个结点，down指向列的下一个结点
    public CrossNode(Triple data, CrossNode right, CrossNode down){
        this.data = data;
        this.right = right;
        this.down = down;
    }
}
