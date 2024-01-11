package java_basic;

/**
 * 判断
 */
public class Week2 {
    public static void main(String[] args) {
        // 1-优先级
        int a = /* 赋值运算符 < 所有的关系运算的的优先级 < 算数运算符 */ 3;
        boolean b = (a < 2 + 3);
        System.out.println(b);

        // == != 的优先级 < 其它的关系运算符
        // 连续运算是从左到右进行的
        System.out.println(5 > 3 == 6 > 4);

        // ==
        System.out.println(5 == 5.0);
        System.out.println(5.0 == 5.0);
        double c = 0.8;
        double d = 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1;
        System.out.println(c == d);
        System.out.println("c = " + c + " d =" + d + "可以看到这里有误差");

    }
}
