package pers.zhang.queue;

/**
 * @author zhang
 * @date 2020/1/17 - 13:26
 *
 * 模拟线程调度
 */
public class Process implements Comparable<Process> {
    private String name;                                   //进程名
    private int priority;                                  //优先级

    public Process(String name, int priority)
    {
        this.name = name;
        this.priority = priority;
    }
    public String toString()
    {
        return "("+this.name+","+this.priority+")";
    }

    @Override
    public int compareTo(Process p)                        //比较两个进程的大小，约定进程排队次序的规则
    {
        return this.priority - p.priority;
    }
}

class Process_ex
{
    public static void main(String args[])
    {
        Process process[]={new Process("A",4),new Process("B",3),new Process("C",5),
                new Process("D",4),new Process("E",10),new Process("F",1)};
        PriorityQueue<Process> que = new PriorityQueue<Process>();   //创建一个优先队列
        new PriorityQueue<Process>();
        System.out.print("入队进程：");
        for (int i=0; i<process.length; i++)
        {
            System.out.println(process[i]);
            que.enqueue(process[i]);                                 //进程入队
            System.out.print(process[i]+" ");
        }
        System.out.print("\n出队进程：");
        while (!que.isEmpty())
            System.out.print(que.dequeue().toString()+" ");          //出队
        System.out.println();
    }
}

/*
程序运行结果如下：
入队进程：(A,4) (B,3) (C,5) (D,4) (E,10) (F,1)
出队进程：(F,1) (B,3) (A,4) (D,4) (C,5) (E,10)

*/