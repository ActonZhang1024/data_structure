package pers.zhang.string;

/**
 * @author zhang
 * @date 2020/1/18 - 13:59
 */
public class MyString_ex {
    public static void main(String args[])
    {
        //1.  String类声明
/*   	    MyString s1 = new MyString();                 //构造一个空串
        MyString s2 = new MyString("xyz");            //以java.lang.String字符串常量构造串对象
        char[] letters={'a','b','c','d'};             //字符数组，只能在声明时赋值，不能赋值为"abcd"
        MyString s3 = new MyString(letters);          //以字符数组构造串对象
        letters[0]='y';                               //数组元素改了，对串没影响
        MyString s4 = new MyString(s3);               //拷贝构造方法
        System.out.println("s1=\""+s1.toString()+"\"");
        System.out.println("s2=\""+s2.toString()+"\"");
        System.out.println("s3=\""+s3.toString()+"\"");
        System.out.println("s4=\""+s4.toString()+"\"");
        MyString s5=s2;                               //对象引用赋值
        System.out.println("\""+s5.toString()+"\"==\""+s2.toString()+"\"? "+(s5==s2));
        System.out.println("\""+s3.toString()+"\"==\""+s4.toString()+"\"? "+(s3==s4));
        System.out.println("\""+s3.toString()+"\".equals(\""+s4.toString()+"\")? "+s3.equals(s4));
        System.out.println("\""+s3.toString()+"\".compareTo(\""+s4.toString()+"\")? "+s3.compareTo(s4));
        System.out.println("\""+s2.toString()+"\".equals(\""+s3.toString()+"\")? "+s2.equals(s3));
        System.out.println("\""+s2.toString()+"\".compareTo(\""+s3.toString()+"\")? "+s2.compareTo(s3));

    	//2.  连接串
        MyString s1=new MyString("abcd"), s2=new MyString("xyz");
        System.out.println("s1.concat(s2)=\""+s1.concat(s2).toString()+"\""); //s1.concat(s2)="abcdxyz"

    	//3.  求子串
        MyString s=new MyString("abcdefgh");
        System.out.println(s.substring(2,5).toString());                //cde


//        MyString target=new MyString("ababdabcd");         //目标串
//        MyString pattern=new MyString("abc");              //模式串
//        MyString target=new MyString("aabaaa"), pattern=new MyString("aab");   //最好情况，
//        MyString target=new MyString("aaaaa"), pattern=new MyString("aab");     //最坏情况，匹配不成功，
        MyString target=new MyString("aaaab"), pattern=new MyString("aab");     //最坏情况，匹配成功
        System.out.println("\""+target+"\".indexOf(\""+pattern+"\")="+target.indexOf(pattern));
        System.out.println("BF.count="+target.count);

        MyString target=new MyString("ababdabcdabcabc"); //目标串，
        MyString pattern=new MyString("abc");              //模式串
        MyString replacement=new MyString("xy");           //替换串
*/
        MyString target=new MyString("aaaa");              //目标串，
        MyString pattern=new MyString("a");                //模式串
        MyString replacement=new MyString("ab");           //替换串

        System.out.println("\""+target+"\".replaceFirst(\""+pattern+"\", \""+replacement+"\")="+
                target.replaceFirst(pattern,replacement));
        System.out.println("\""+target+"\".replaceAll(\""+pattern+"\", \""+replacement+"\")="+
                target.replaceAll(pattern,replacement));

/*
        System.out.println("\""+s3.toString()+"\".startsWith(\""+s2.toString()+"\")? "+s3.startsWith(s6));
        System.out.println("\""+s3.toString()+"\".endsWith(\""+s2.toString()+"\")? "+s3.endsWith(s2));
        System.out.println("\""+s2.toString()+"\".equalsIgnoreCase(\"XYZ\")? "+
            s2.equalsIgnoreCase(new MyString("XYZ")));
        System.out.println("\""+s4.toString()+"\".compareToIgnoreCase(\"ABCDEF\")? "+
            s4.compareToIgnoreCase(new MyString("ABCDEF")));
*/
    }
}
/*
程序运行结果如下：
s1=""
s2="xyz"
s3="abcd"
s4="abcd"
"xyz"=="xyz"? true
"abcd"=="abcd"? false
"abcd".equals("abcd")? true
"abcd".compareTo("abcd")? 0
"xyz".equals("abcd")? false
"xyz".compareTo("abcd")? 23
"abcdxyz".startsWith("xyz")? true
"abcdxyz".endsWith("xyz")? true
"xyz".equalsIgnoreCase("XYZ")? true
"abcd".compareToIgnoreCase("ABCDEF")? -2

朴素的模式匹配（Brute-Force）算法
"ababdabcd".indexOf_BF("abc")=5
BF.count=12

"aabaaa".indexOf("aab")=0                        //最好情况，
BF.count=3

"aaaaaa".indexOf("aab")=-1                       //最坏情况
BF.count=14                                      //比较m*(n-m+1)次，O(n*m)

"aaaaa".indexOf("aab")=-1                        //最坏情况，匹配不成功
BF.count=11
"aaaab".indexOf("aab")=2                         //最坏情况，匹配成功
BF.count=9

//替换子串
"ababdabcdabcabc".replaceFirst("abc", "xy")=ababdxydabcabc
"ababdabcdabcabc".replaceAll("abc", "xy")=ababdxydxyxy

//替换子串
"aaaa".replaceFirst("a", "ab")=abaaa
"aaaa".replaceAll("a", "ab")=abababab

 **/