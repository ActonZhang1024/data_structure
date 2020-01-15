package pers.zhang.linearList;

/**
 * @author zhang
 * @date 2020/1/14 - 14:28
 */
public class SortedSinglyLinkedList_ex {
    public static Integer[] random(int n)              //返回产生n个随机数的数组，同例2.3
    {
        Integer[] elements = new Integer[n];
        for (int i=0; i<n; i++)
            elements[i] = (int)(Math.random()*100);   //产生随机数
        return elements;
    }

    public static void main(String args[])
    {
/*
        SortedSinglyLinkedList<Integer> list1 = new SortedSinglyLinkedList<Integer>(random(9));
//        list1.insert(-1, -1);                    //覆盖单链表类的方法，抛出异常
        list1.insert(-2);                        //插入指定值结点，调用排序单链表类的方法
        System.out.println("list1: "+list1.toString());
        SortedSinglyLinkedList<Integer> list2=new SortedSinglyLinkedList<Integer>(list1);//深拷贝
        System.out.println("list2: "+list2.toString());
        list1.remove(list1.length()-1);          //删除最后结点，参数类型是int，调用单链表类的方法
        list1.remove(list1.get(0));              //删除首个结点，参数类型是Integer，调用排序单链表类的方法
        list1.remove(new Integer(50));           //删除指定值结点，可能没删除
        System.out.println("list1: "+list1.toString());
        System.out.println("list2: "+list2.toString());


        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<Integer>(random(9));//单链表
        System.out.println("list3: "+list3.toString());
        SortedSinglyLinkedList<Integer> list4=new SortedSinglyLinkedList<Integer>(list3);//由单链表构造排序单链表
        System.out.println("list4: "+list4.toString());*/


        //由单链表list构造排序单链表，直接选择排序
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>(random(7));
        System.out.println("单链表list1: "+list1.toString());
        SortedSinglyLinkedList<Integer> list2 = new SortedSinglyLinkedList<Integer>(list1);
        System.out.println("排序单链表list2: "+list2.toString());
//        list1.set(0, new Integer(list1.get(0).intValue()+100));
//        System.out.println("list1: "+list1.toString());
//        System.out.println("list2: "+list2.toString());
//      list1.merge(list2);                           //归并两条排序单链表
//      System.out.println("归并，list1: "+list1.toString());
//      System.out.println("list2: "+list2.toString());
    }
}
