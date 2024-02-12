package java_basic;

import java.util.Scanner;

/**
 * 基本功：String类的常用基本操作
 */
public class string {
    public static void main(String[] args) {
        System.out.println(2 + "hi" + (3 + 5)); // 2hi8
        // indexOf(), lastIndexOf(), chartAt()
        String s1 = "0123A56389";
        int fromIdx = s1.indexOf('3'); // 第一个3出现是在index 3
        // 从index3+1开始查找第一次出现的3
        System.out.println(s1.indexOf('3', fromIdx + 1));// out:7
        // 一样的效果，从后面查找第一次出现的'3'
        System.out.println(s1.lastIndexOf('3'));// out:7
        System.out.println(s1.indexOf("A56")); // out:4
        System.out.println(s1.startsWith("2"));// false, 应该是0 记住参数是string
        System.out.println(s1.trim()); //两边字符串删掉，不包含中间
        System.out.println(s1.replace('3', 'A'));//所有oldChar换成newChar
        System.out.println(s1.charAt(0));//out:0

        // String.equals() and ==
        String a = new String("a");
        String b = a;
        System.out.println(a == b);

        //逃逸字符
        System.out.println("ab\bv"); // 回退
        System.out.println("a\tv"); // v下一个指标位
        System.out.println("abs\tv");// v下一个指标位

        // Wrapper Class
        char ch;
        System.out.println(ch = Character.toLowerCase('A'));
        char ch2 = 'A';
        System.out.println(Character.toLowerCase(ch2));
        System.out.println(ch2); //copy A, out: A

        System.out.println(Character.isLowerCase('A'));
        System.out.println(Character.isDigit('1'));
        System.out.println(ch);

        String str = "";
        str = str.concat("3");
        System.out.println(str);

        // 2d java_basic.string
        int[][] l3 = {{1, 1}, {2, 3}}; //这样可以
        String[][] lt2 = {
                {"2", "3"},
                {"6", "5"}
        };


        String stri = "xz";
        String stri2 = "xz";
        System.out.println(stri == stri2);
        String substring = stri2.substring(1);
        System.out.println(substring);
        String join = String.join("", "xz0", "xy");
        System.out.println(join);
        int count = solution("xzxzx");
        System.out.println(count);
    }


    /**
     * 问题1 是要计算将字符串s分割成三个非空部分 a, b, 和 c 的方式数，
     * 使得组合 a + b, b + c, 和 c + a 都是不同的字符串。
     * 需要考虑s的所有可能分割，并对满足条件的分割进行计数。
     * @param s
     * @return
     */
    static int solution(String s) {
        int count = 0;
//        确保a, b, 和 c都是非空字符串，这意味着i和j的选择范围是0 < i < j < s.length()
        for (int i = 1; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                //
                String a = s.substring(0, i);
                String b = s.substring(i, j);
                String c = s.substring(j);
//                分开组装免得出错
                String ab = a + b;
                String bc = b + c;
                String ca = c + a;
                if (!ab.equals(bc) && !bc.equals(ca) && !ab.equals(ca)) {
                    count++;
                }
            }
        }
        return count;
    }


}
