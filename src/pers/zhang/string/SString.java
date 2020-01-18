package pers.zhang.string;

/**
 * @author zhang
 * @date 2020/1/18 - 13:19
 *
 * 串的抽象数据类型
 */
public interface SString {

    //返回字符串的长度
    int length();

    //返回第i（i>=0）个字符
    char charAt(int i);

    //返回当前串与str串连接生成的新串
    SString concat(SString str);

    //返回串中字符序号从begin到end-1的子串
    SString substring(int begin, int end);

    //返回pattern串在主串中的第一次匹配位置
    int indexOf(SString pattern);
}
