package pers.zhang.linearList;

/**
 * @author zhang
 * @date 2020/1/14 - 14:15
 */
public class SinglyLinkedList_average {
    public static Integer[] random(int n)              //返回产生n个随机数的数组
    {
        Integer[] elements = new Integer[n];
        for (int i=0; i<n; i++)
            elements[i] = new Integer((int)(Math.random()*100)); //产生一个0～100之间的随机数
        return elements;
    }

    public static double averageAll(SinglyLinkedList<Integer> list)   //求所有元素的平均值
    {
        if (list.isEmpty())
            throw new IllegalArgumentException("不能对空单链表计算平均值。"); //抛出无效参数异常
        int sum=0, i=0;
        Node<Integer> p=list.head.next;         //要求head权限必须是public 或p=list.getFirst();
        while (p!=null)
        {
            sum += p.data.intValue();
            p=list.getNext(p);
            i++;
        }
        return (double)sum/i;                    //避免了除数为0错误
    }

    //去掉最高分和最低分，再求平均值，O(n)
    public static double averageExceptMaxMin(SinglyLinkedList<Integer> list)
    {
        if (list.isEmpty())
            throw new IllegalArgumentException("不能对空单链表计算平均值。"); //抛出无效参数异常
        int sum=0, i=0, max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
        Node<Integer> p=list.head.next;               //要求head权限必须是public
        while (p!=null)
        {
            int value = p.data.intValue();
            sum += value;
            if (value>max)
                max = value;
            if (value<min)
                min = value;
            p = p.next;
            i++;
        }
        if (i==1 || i==2)
            return (double)sum/i;                     //返回两个元素的平均值，避免了除数为0错误
        return (double)(sum-max-min)/(i-2);           //返回去掉最高分和最低分后的平均值
    }
    public static void main(String args[])
    {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();//random(10));
        System.out.println("list: "+list.toString());
        System.out.println("averageAll: "+averageAll(list));
        System.out.println("averageExceptMaxMin: "+averageExceptMaxMin(list));
    }
}
