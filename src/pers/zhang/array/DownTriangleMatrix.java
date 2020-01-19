package pers.zhang.array;

/**
 * @author zhang
 * @date 2020/1/19 - 13:34
 *
 * 下三角矩阵线性压缩存储
 */
public class DownTriangleMatrix {

    //下三角矩阵的阶数
    private int rows;

    //存储矩阵元素的一维数组
    private int element[];

    //构造rows阶下三角矩阵
    public DownTriangleMatrix(int rows){
        if(rows <= 0)
            throw new IllegalArgumentException("矩阵行数非正数：" + rows);
        this.rows = rows;
        //rows阶下三角矩阵需要存储rows*(rows+1)/2个元素
        this.element = new int[rows * (rows + 1) / 2];
    }

    //构造rows阶下三角矩阵，初值由mat提供，mat按行主序顺序存储rows阶下三角矩阵元素
    public DownTriangleMatrix(int rows, int mat[]){
        this(rows);
        int n = element.length <= mat.length ? element.length : mat.length;
        for (int i = 0; i < n; i++)//mat元素不足时补0，忽略多余元素
            this.element[i] = mat[i];
    }

    //深拷贝
    public DownTriangleMatrix(DownTriangleMatrix mat){
        this(mat.rows, mat.element);
    }

    //返回矩阵第i行第j列元素值，O(1)
    public int get(int i, int j)      {
        if (i < 0 || i >= rows || j < 0 || j >= rows)
            throw new IndexOutOfBoundsException("矩阵元素的行或列序号越界");
        return i < j ? 0 : element[i * (i + 1) / 2 + j];//按线性压缩存储地址寻找矩阵元素
    }

    //设置矩阵第i行第j列元素值为value，O(1)
    public void set(int i, int j, int value){
        if (i < 0 || i >= rows || j < 0 || j >= rows)
            throw new IndexOutOfBoundsException("矩阵元素的行或列序号越界");
        this.element[i * (i + 1) / 2 + j] = value;
    }

    //返回下三角矩阵所有元素的描述字符串，行主序遍历
    public String toString(){
        String str =" 下三角矩阵" + this.getClass().getName() + "（" + this.rows + "阶）：\n";
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.rows; j++)
                str += String.format("%4d", this.get(i,j));
            str += "\n";
        }
        return str;
    }

    //当前下三角矩阵与mat矩阵相加，this+=mat，各对应元素相加，改变当前矩阵
    public void add(DownTriangleMatrix mat){
        if (this.rows != mat.rows)
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");
        for (int i = 0; i < this.element.length; i++)
            this.element[i] += mat.element[i];
    }

    //返回当前矩阵与mat相加后的矩阵，=this+mat，各对应元素相加，不改变当前矩阵
    public DownTriangleMatrix plus(DownTriangleMatrix mat){
        DownTriangleMatrix matc = new DownTriangleMatrix(this);//深拷贝
        matc.add(mat);
        return matc;                                       //返回对象引用
    }

    //比较两个同阶矩阵是否相等
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof DownTriangleMatrix))
            return false;
        DownTriangleMatrix mat=(DownTriangleMatrix)obj;
        if (this.rows != mat.rows)
            return false;
        for (int i = 0; i < this.element.length; i++)
            if (this.element[i] != mat.element[i])           //比较对应元素是否相等
                return false;
        return true;
    }
}
