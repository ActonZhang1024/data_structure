package pers.zhang.string;

/**
 * @author zhang
 * @date 2020/1/18 - 13:23
 *
 * 串的实现
 */
public class MyString implements Comparable<MyString>, java.io.Serializable{

    //字符数组，私有最终变量，只能赋值一次
    private final char[] value;

    //构造一个空串
    public MyString() {
        this.value = new char[0];
    }

    //由字符串常量构造串对象
    public MyString(String original){
        this.value = original.toCharArray();
    }

    //以value数组中从begin开始的count个字符构造串对象
    public MyString(char[] value, int begin, int count){
        this.value = new char[count];
        for(int i = begin; i < begin + count; i++)
            this.value[i] = value[i];
    }

    //以value数组中字符构造串对象
    public MyString(char[] value){
        this(value, 0, value.length);
    }

    //拷贝构造
    public MyString(MyString str){
        this(str.value);
    }

    //比较当前串与str串的大小，返回两者差值，实现Comparable接口
    @Override
    public int compareTo(MyString str) {
        for(int i = 0; i < this.value.length && i < str.value.length; i++)
            if(this.value[i] != str.value[i])
                return this.value[i] - str.value[i];//返回两串第一个不同字符的差值
        return this.value.length - str.value.length;//前缀子串，返回两串长度的差值
    }

    //返回字符串长度
    public int length() {
        return this.value.length;
    }

    //返回第i（i>=0）个字符
    public char charAt(int i) {
        if(i < 0 || i >= this.value.length)
            throw new StringIndexOutOfBoundsException(i);
        return this.value[i];
    }

    //返回当前串与指定串str连接生成的新串，不改变当前串
    public MyString concat(MyString str) {
        if(str.length() == 0)//连接空串，返回当前串
            return this;
        char[] buffer = new char[this.value.length + str.length()];
        int i;
        for(i = 0; i < this.value.length; i++)
            buffer[i] = this.value[i];
        for(int j = 0; j < str.value.length; j++)
            buffer[i + j] = str.value[j];
        return new MyString(buffer);
    }

    //返回串中序号从begin至end-1的子串
    public MyString substring(int begin, int end) {
        if(begin < 0)
            begin = 0;
        if(end > this.value.length)
            end = this.value.length;
        if(begin == 0 && end == this.value.length)
            return this;
        if(begin > end)
            throw new StringIndexOutOfBoundsException(end - begin);
        char[] buffer = new char[end - begin];
        for (int i = 0; i < buffer.length; i++)
            buffer[i] = this.value[i + begin];
        return new MyString(buffer);
    }

    //返回串中序号从begin至串尾的子串
    public MyString substring(int begin){
        return substring(begin, this.value.length);
    }


    @Override
    public String toString(){
        return new String(this.value);
    }

    //比较当前串是否与obj引用的串相等 ，覆盖Object类的equals(obj)方法
    public boolean equals(Object obj){
        if (this == obj)//同一对象
            return true;
        if (obj instanceof MyString)//同类对象
        {
            MyString str = (MyString)obj;
            if (this.value.length == str.value.length){//比较长度
                for (int i=0; i<this.value.length; i++)//逐个比较
                    if (this.value[i]!=str.value[i])
                        return false;
                return true;
            }
        }
        return false;
    }

    //返回ch字符在当前串中首次出现的序号
    public int indexOf(char ch){
        for (int i = 0; i < this.value.length; i++)
            if (this.value[i] == ch)
                return i;
        return -1;
    }

    //串的模式匹配，Brute-Force算法
    //返回模式串pattern在当前串this（目标串target）中从begin开始的首次匹配位置，匹配失败时返回-1
    int count=0;//记载比较次数
    public int indexOf(MyString pattern, int begin){
        if (pattern.length() > 0 && this.length() >= pattern.length()){//当目标串比模式串长时进行比较
            int i = begin, j = 0;                              //i、j分别为目标串和模式串当前字符的下标
            count = 0;
            while (i < this.length()){
                if (this.charAt(i) == pattern.charAt(j)){     //若当前两字符相等，则继续比较后续字符
                    i++;
                    j++;
                }else{                                       //否则i、j回溯，进行下一次匹配
                    i = i - j + 1;                               //目标串下标i退回到下一个待匹配子串首字符
                    j = 0;                                   //模式串下标j退回到0
                }
                count++;
                if (j == pattern.length())                   //一次匹配结束，匹配成功
                    return i-j;                            //返回匹配的子串序号
            }
        }
        return -1;                                         //匹配失败时返回-1
    }

    //返回模式串pattern在当前串中的首次匹配位置，匹配失败时返回-1
    public int indexOf(MyString pattern){
        return this.indexOf(pattern, 0);
    }

    //返回将当前串中首个与pattern匹配的子串替换成replacement的字符串
    public MyString replaceFirst(MyString pattern, MyString replacement){
        int i = this.indexOf(pattern,0);                     //返回匹配子串的序号，从0开始查找
        if (i == -1)
            return this;                                   //匹配失败时返回当前串
        return this.substring(0,i).concat(replacement).concat(this.substring(i+pattern.length()));//连接3个串
    }

    //返回将当前串中所有与pattern匹配的子串全部替换成replacement的字符串
    public MyString replaceAll(MyString pattern, MyString replacement){
        MyString temp = new MyString(this);                //拷贝构造方法，复制当前串
        int i = this.indexOf(pattern,0);
        while (i != -1){
            temp = temp.substring(0,i).concat(replacement).concat(temp.substring(i+pattern.length()));
            i=temp.indexOf(pattern, i+replacement.length());//从下一个字符开始再次查找匹配子串
//            i=temp.indexOf(pattern, i+1);                  	//错
        }
        return temp;
    }

    //将串中的大写字母全部转换成对应的小写字母
    public MyString toLowerCase(){
        char temp[] = new char[this.value.length];
        for (int i = 0; i < this.value.length; i++)
            if (this.value[i] >= 'A' && this.value[i] <= 'Z')  //大写字母
                temp[i] = (char)(this.value[i] + 'a' - 'A');     //转换成对应小写字母
        return new MyString(temp);
    }

    //将串中的小写字母全部转换成对应的大写字母
    public MyString toUpperCase(){
        char temp[] = new char[this.value.length];
        for (int i = 0; i < this.value.length; i++)
            if (this.value[i] >= 'a' && this.value[i] <= 'z')  //小写字母
                temp[i] = (char)(this.value[i] - ('a'-'A'));   //转换成对应大写字母
        return new MyString(temp);
    }

    //返回字符数组
    public char[] toCharArray(){
        char[] temp = new char[this.value.length];
        for (int i = 0; i < temp.length; i++)                  //复制数组
            temp[i] = this.value[i];
        return temp;
    }

    //判断prefix是否前缀子串
    public boolean startsWith(MyString prefix){
        if (this.value.length < prefix.value.length)
            return false;
        for (int i = 0; i < prefix.value.length; i++)
            if (this.value[i] != prefix.value[i])
                return false;
        return true;
    }

    //判断suffix是否后缀子串
    public boolean endsWith(MyString suffix){
        int j = suffix.value.length-1;
        for (int i = this.value.length - 1; i >= 0 && j >= 0; i--,j--)
            if (this.value[i] != suffix.value[j])
                return false;
        return j == -1;
    }

    //比较当前串与str串是否相等，忽略字母大小写
    public boolean equalsIgnoreCase(MyString str){
        if (this == str)
            return true;
        if (this.value.length == str.value.length){
            for (int i = 0; i < this.value.length; i++){
                int c1 = this.value[i];
                if (c1 >= 'a' && c1 <= 'z')
                    c1 -= 'a'-'A';
                int c2 = str.value[i];
                if (c2 >= 'a' && c2 <= 'z')
                    c2 -= 'a'-'A';
                if (c1 != c2)
                    return false;
            }
            return true;
        }
        return false;
    }

    //比较两个串大小，忽略字母大小写
    public int compareToIgnoreCase(MyString str){
        for (int i = 0; i < this.value.length && i < str.value.length; i++){
            int c1 = this.value[i];
            if (c1 >= 'a' && c1 <= 'z')
                c1 -= 'a'-'A';
            int c2 = str.value[i];
            if (c2 >= 'a' && c2 <= 'z')
                c2 -= 'a'-'A';
            if (c1 != c2)
                return this.value[i] - str.value[i];
        }
        return this.value.length - str.value.length;
    }

    //返回删除所有空格后的字符串
    public MyString trim(){
        char temp[] = new char[this.value.length];
        int j = 0;
        for (int i = 0; i < this.value.length; i++)
            if (this.value[i] != ' ')
                temp[j++] = this.value[i];
        return new MyString(temp,0,j);                     //以temp数组中从0开始的j个字符构造串对象
    }
}
