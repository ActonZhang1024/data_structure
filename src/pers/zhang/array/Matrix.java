package pers.zhang.array;

/**
 * @author zhang
 * @date 2020/1/19 - 10:42
 *
 * 矩阵类
 */
public class Matrix {
    //存储矩阵元素的二维数组
    private int element[][];

    //构造m*n零矩阵
    public Matrix(int m, int n){
        this.element = new int[m][n];//数组元素初值为0
    }

    //构造n*n矩阵
    public Matrix(int n){
        this(n, n);
    }

    //构造m*n矩阵，由mat提供元素
    public Matrix(int m, int n, int mat[][]){
        this(m, n);
        for(int i = 0; i < mat.length && i < m; i++)
            for(int j = 0; j < mat[i].length && j < n; j++)
                this.element[i][j] = mat[i][j];
    }

    //深拷贝构造
    public Matrix(Matrix mat){
        this(mat.element.length, mat.element[0].length, mat.element);
    }

    //返回矩阵第i行第j列元素值，O(1)
    public int get(int i, int j){
        return this.element[i][j];//若i、j下标越界，Java将抛出数组下标越界异常ArrayIndexOutOfBoundsException
    }

    //设置矩阵第i行第j列的元素
    public void set(int i, int j, int value){
        this.element[i][j] = value;
    }

    //返回矩阵所有元素的描述字符串，行主序遍历
    public String toString(){
        String str = " 矩阵" + this.getClass().getName() + "（" + this.element.length+"×"+this.element[0].length+"）：\n";
        for (int i = 0; i < this.element.length; i++){
            for (int j = 0; j < this.element[i].length; j++)
                str += String.format("%4d", this.element[i][j]);
            str += "\n";
        }
        return str;
    }

    //当前矩阵与mat矩阵相加，this += mat，各对应元素相加；改变当前矩阵
    public void add(Matrix mat){
        if(this.element.length != mat.element.length || this.element[0].length != mat.element[0].length)
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");
        for(int i = 0; i < this.element.length; i++)
            for(int j = 0; j < this.element[i].length; j++)
                this.element[i][j] += mat.element[i][j];
    }

    //返回当前矩阵与mat相加后的矩阵，不改变当前矩阵，=this+mat，各对应元素相加
    public Matrix plus(Matrix mat){
        Matrix matc = new Matrix(this);                      //深拷贝
        matc.add(mat);
        return matc;                                       //返回对象引用
    }

    //比较两个同阶矩阵是否相等
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Matrix))
            return false;
        Matrix mat = (Matrix)obj;
        if (this.element.length != mat.element.length || this.element[0].length != mat.element[0].length)
            return false;
        for (int i = 0; i < this.element.length; i++)
            for (int j = 0; j < this.element[i].length; j++)
                if (this.element[i][j]!=mat.element[i][j]) //比较对应元素是否相等
                    return false;
        return true;
    }

    //返回当前矩阵的转置矩阵
    public Matrix transpose(){
        Matrix trans = new Matrix(this.element[0].length, this.element.length);//构造零矩阵
        for (int i = 0; i < this.element.length; i++)
            for (int j = 0; j < this.element[i].length; j++)
                trans.element[j][i]=this.element[i][j];
        return trans;                                      //返回对象引用
    }

    //判断当前矩阵是否为上三角矩阵
    public boolean isUpTriangular(){
        if(this.element.length != this.element[0].length)
            return false;
        for(int i = 0; i < this.element.length; i++)
            for(int j = 0; j < i; j++)
                if(this.element[i][j] != 0)//下三角元素应为0
                    return false;
        return true;
    }

    //判断当前矩阵是否为下三角矩阵
    public boolean isDownTriangular(){
        if(this.element.length != this.element[0].length)
            return false;
        for (int i = 0; i < this.element.length; i++)
            for (int j = i+1; j < this.element.length; j++)
                if (this.element[i][j] != 0)//上三角元素应为0
                    return false;
        return true;
    }

    //判断当前矩阵是否为对称矩阵
    public boolean isSymmetric(){
        if (this.element.length != this.element[0].length)
            return false;
        for (int i = 0; i < this.element.length; i++)
            for (int j = 0; j < this.element[i].length; j++)
                if (this.element[i][j] != this.element[j][i])
                    return false;
        return true;
    }
}
