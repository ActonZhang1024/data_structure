package pers.zhang.array;

/**
 * @author zhang
 * @date 2020/1/19 - 10:36
 *
 * 矩阵接口
 */
public interface MMatrix {
    //返回矩阵第i行第j列元素值
    int get(int i, int j);

    //设置矩阵第i行第j列元素值
    void set(int i, int j, int value);

    //矩阵相加，this+=mat，各对应元素相加；改变this矩阵
    void add(MMatrix mat);

    //返回this与mat相加后的矩阵，不改变this矩阵
    MMatrix plus(MMatrix mat);

    //返回当前矩阵的转置矩阵
    MMatrix transpose();

    boolean equals(Object obj);
}
