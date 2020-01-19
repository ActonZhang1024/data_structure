package pers.zhang.array;

/**
 * @author zhang
 * @date 2020/1/19 - 14:26
 *
 * 表示稀疏矩阵非零元素的三元组
 */
public class Triple implements Comparable<Triple>{

    //行号，列号，元素值
    int row, column, value;

    public Triple(int row, int column, int value){
        if (row<0 || column<0)
            throw new IllegalArgumentException("稀疏矩阵元素三元组的行/列序号非正数。");
        this.row = row;
        this.column = column;
        this.value = value;
    }

    //拷贝构造方法，复制一个三元组
    public Triple(Triple elem){
        this(elem.row, elem.column, elem.value);
    }

    //返回三元组描述字符串
    public String toString(){
        return "(" + row + "," + column + "," + value + ")";
    }

    //两个三元组比较是否相等，比较位置和元素值
    public boolean equals(Object obj){
        if (!(obj instanceof Triple))
            return false;
        Triple elem = (Triple)obj;
        return this.row == elem.row && this.column == elem.column && this.value == elem.value;
    }

    //根据三元组位置比较两个三元组的大小，与元素值无关，约定三元组排序次序
    @Override
    public int compareTo(Triple elem){
        if (this.row < elem.row || this.row == elem.row && this.column < elem.column)
            return -1;                                     //当前三元组对象小
        if (this.row == elem.row && this.column == elem.column)
            return 0;                                      //相等，与equals()方法含义不同
        return 1;                                          //当前三元组对象大
    }

    //行的单链表用
    public void add(Triple term){                           //加法，＋=运算符作用
        if (this.compareTo(term)==0)
            this.value += term.value;
        else
            throw new IllegalArgumentException("两项的指数不同，不能相加。");
    }

    //约定删除元素条件
    public boolean removable(){
        return this.value == 0;                              //不存储值为0的元素
    }


    //转置矩阵用
    public Triple toSymmetry(){                            //返回对称位置矩阵元素的三元组
        return new Triple(this.column, this.row, this.value);
    }



}
