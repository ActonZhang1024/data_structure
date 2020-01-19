package pers.zhang.array;

/**
 * @author zhang
 * @date 2020/1/19 - 15:12
 */
public class CrossLinkedSparseMatrix_ex {
    public static void main(String args[])
    {
        Triple[] elemsa = {new Triple(0,2,11),new Triple(0,4,17),new Triple(1,1,20),
                new Triple(3,0,19),new Triple(3,5,28),new Triple(4,4,50)};
        CrossLinkedSparseMatrix smata = new CrossLinkedSparseMatrix(5,6,elemsa);
        System.out.print("A "+smata.toString());
        Triple[] elemsb={new Triple(0,2,-11),new Triple(0,4,-17),new Triple(2,3,51),
                new Triple(3,0,10),new Triple(4,5,99),new Triple(1,1,0)};//不存储值为0元素
        CrossLinkedSparseMatrix smatb = new CrossLinkedSparseMatrix(5,6,elemsb);
        System.out.print("B "+smatb.toString());
        CrossLinkedSparseMatrix smatc=smata.plus(smatb);
        System.out.print("C=A+B "+smatc.toString());
        smata.add(smatb);
        System.out.print("A+=B "+smata.toString());
        System.out.println("C.equals(A)？"+smatc.equals(smata));

//        System.out.println("\n//习题5");
//        System.out.println("A转置"+smata.transpose().toString());        //习题5
    }
}
