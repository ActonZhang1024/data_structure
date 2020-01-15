package pers.zhang.linearList;

import java.util.Iterator;

/**
 * @author zhang
 * @date 2020/1/15 - 15:13
 */
public class DoublyLinkedList_ex {

    public static Integer[] random(int n)              //返回产生n个随机数的数组
    {
        Integer[] elements = new Integer[n];
        for (int i=0; i<n; i++)
            elements[i] = (int)(Math.random()*100);   //产生随机数
        return elements;
    }

    public static void main(String args[])
    {
/*	    CirDoublyLinkedList<Integer> list1 = new CirDoublyLinkedList<Integer>(random(5));
        System.out.print("list1: "+list1.toString()+"，");
        list1.printPrevious();
        list1.insert(-1, -1);                              //插入位置容错
        list1.insert(0, 0);
        list1.insert(6, 6);
        list1.insert(100, 100);                            //插入位置容错
        list1.set(3, new Integer((int)(list1.get(3).intValue()+100)));
        System.out.println("插入后: "+list1.toString());
        list1.remove(0);
	    list1.remove(3);
	    list1.remove(100);                                 //序号越界，没删除
        System.out.println("删除后: "+list1.toString());
	    CirDoublyLinkedList<Integer> list2 = new CirDoublyLinkedList<Integer>(list1);//深拷贝
        System.out.println("list2: "+list2.toString());

        //习题2
/*        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<Integer>(random(9));//单链表
        System.out.println("list3: "+list3.toString());
	    CirDoublyLinkedList<Integer> list4 = new CirDoublyLinkedList<Integer>(list3);//由单链表构造循环双链表
        System.out.println("list4: "+list4.toString());
        CirSinglyLinkedList<Integer> list5 = new CirSinglyLinkedList<Integer>(random(9));//循环单链表
        System.out.println("list5: "+list5.toString());
	    CirDoublyLinkedList<Integer> list6 = new CirDoublyLinkedList<Integer>(list5);//由循环单链表构造循环双链表
        System.out.println("list6: "+list6.toString());
        list6.printPrevious();
*/
        //深拷贝与比较相等
        CirDoublyLinkedList<Integer> list1 = new CirDoublyLinkedList<Integer>();   //空表
        System.out.println("list1: "+list1.toString());
        CirDoublyLinkedList<Integer> list2 = new CirDoublyLinkedList<Integer>();   //空表
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        list1 = new CirDoublyLinkedList<Integer>(random(5));
        System.out.println("list1: "+list1.toString());
        list2 = new CirDoublyLinkedList<Integer>(list1);               //拷贝构造方法
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        System.out.println("list1: "+list1.toString());
        list2.set(0, new Integer(list1.get(0).intValue()+100));
        list2.remove(list2.length()-1);
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        //10.2   实现迭代器
        Iterator<Integer> it = list1.iterator();      //获得单链表迭代器对象
        int sum=0;
        while (it.hasNext())
        {
            int value=it.next().intValue();
            sum += value;
            System.out.print(value);
            if (it.hasNext())
                System.out.print("+");
        }
        System.out.println("="+sum);
    }
}
