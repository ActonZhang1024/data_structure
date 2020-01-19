package pers.zhang.array;

/**
 * @author zhang
 * @date 2020/1/19 - 15:54
 */
public class TriangleMatrix_ex {
    public static void main(String args[])
    {
        int m1[][]={{1,2,3,4},{5,6,7},{8,9},{10}};
        TriangleMatrix mata=new TriangleMatrix(4,true,m1);                    //矩阵对象，初值不足时自动补0，忽略多余元素
        System.out.print("A"+mata.toString());
        TriangleMatrix matb=new TriangleMatrix(4,true);
        matb.set(0,0,1);
        matb.set(0,3,1);
        matb.set(1,1,1);
        matb.set(3,3,1);
        System.out.print("B"+matb.toString());
        TriangleMatrix matc = mata.plus(matb);
        System.out.print("C=A+B"+matc.toString());
        mata.add(matb);
        System.out.print("A+=B"+mata.toString());
        System.out.println("C.equals(A)？"+matc.equals(mata));

        int m2[][]={{1},{2,3},{4,5,6},{7,8,9,10}};
        TriangleMatrix matd=new TriangleMatrix(4,false,m2);      //矩阵对象，初值不足时自动补0，忽略多余元素
        System.out.print("\nD"+matd.toString());
        TriangleMatrix mate=new TriangleMatrix(4,false);
        mate.set(0,0,1);
        mate.set(1,1,1);
        mate.set(3,0,1);
        mate.set(3,3,1);
        System.out.print("E"+mate.toString());
        TriangleMatrix matf = matd.plus(mate);
        System.out.print("F=D+E"+matf.toString());
        matd.add(mate);
        System.out.print("D+=E"+matd.toString());
        System.out.println("F.equals(D)？"+matc.equals(mata));
        System.out.println("B.equals(E)？"+matb.equals(mate)+"\n");

        TriangleMatrix matg = matb.transpose();
        System.out.print("B的转置矩阵G"+matg.toString());
        System.out.println("G.equals(E)？"+matg.equals(mate)+"\n");

        TriangleMatrix math = mate.transpose();
        System.out.print("E的转置矩阵H"+math.toString());
        System.out.println("H.equals(B)？"+math.equals(matb));
    }
}
/*
程序运行结果如下：
A TriangleMatrix（4阶）：
   1   2   3   4
   0   5   6   7
   0   0   8   9
   0   0   0  10
B TriangleMatrix（4阶）：
   1   0   0   1
   0   1   0   0
   0   0   0   0
   0   0   0   1
C=A+B TriangleMatrix（4阶）：
   2   2   3   5
   0   6   6   7
   0   0   8   9
   0   0   0  11
A+=B TriangleMatrix（4阶）：
   2   2   3   5
   0   6   6   7
   0   0   8   9
   0   0   0  11
C.equals(A)？true

D TriangleMatrix（4阶）：
   1   0   0   0
   2   3   0   0
   4   5   6   0
   7   8   9  10
E TriangleMatrix（4阶）：
   1   0   0   0
   0   1   0   0
   0   0   0   0
   1   0   0   1
F=D+E TriangleMatrix（4阶）：
   2   0   0   0
   2   4   0   0
   4   5   6   0
   8   8   9  11
D+=E TriangleMatrix（4阶）：
   2   0   0   0
   2   4   0   0
   4   5   6   0
   8   8   9  11
F.equals(D)？true
B.equals(E)？false

B的转置矩阵G TriangleMatrix（4阶）：
   1   0   0   0
   0   1   0   0
   0   0   0   0
   1   0   0   1
G.equals(E)？true

E的转置矩阵H TriangleMatrix（4阶）：
   1   0   0   1
   0   1   0   0
   0   0   0   0
   0   0   0   1
H.equals(B)？true



*/