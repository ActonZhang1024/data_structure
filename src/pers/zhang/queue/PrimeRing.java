package pers.zhang.queue;

import pers.zhang.linearList.SeqList;

/**
 * @author zhang
 * @date 2020/1/17 - 13:03
 */
public class PrimeRing {
    public PrimeRing(int n)                                //求1～n素数环
    {
        SeqList<Integer> ring = new SeqList<Integer>(n);   //创建一个顺序表存储素数环
        ring.append(new Integer(1));                       //1添加到素数环中

        SeqQueue<Integer> que = new SeqQueue<Integer>(n);  //创建一个队列que
//        LinkedQueue<Integer> que = new LinkedQueue<Integer>();   //创建一个队列que
        for (int i=2; i<=n; i++)                              //2～n全部入队
            que.enqueue(new Integer(i));
        System.out.println(que.toString());

        int i=0;
        while (!que.isEmpty())
        {
            int k = que.dequeue().intValue();              //出队
            System.out.print("dequeue: "+k+"\t");
            if (isPrime(ring.get(i)+k))                    //判断是否为素数
            {
                i++;
                ring.append(new Integer(k));               //k添加到素数环中
            }
            else
                que.enqueue(new Integer(k));               //k再次入队
            System.out.println("队列: "+que.toString());
        }
        System.out.println("素数环: "+ring.toString());
    }

    public boolean isPrime(int k)                          //判断k是否为素数
    {
        if (k==2)
            return true;
        if (k<2 || k>2 && k%2==0)
            return false;
        int j=(int)Math.sqrt(k);                           //Math.sqrt(k)返回k的平方根值
        if (j%2==0)
            j--;                                           //获得测试范围内的最大奇数
        while (j>2 && k%j!=0)
            j-=2;
        return j<2;
    }
    public static void main(String args[])
    {
        new PrimeRing(8);
    }
}
