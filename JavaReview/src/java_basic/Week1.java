package java_basic;

import java.util.Scanner;

/**
 * 计算
 */
public class Week1 {
    public static void main(String[] args) {

    }

    public void variable() {
        // 1.2.2变量
        Scanner in = new Scanner(System.in);
        // num 变量
        int num = in.nextInt();
        System.out.println("100块 - " + num + " = " + (100 - num));
    }

    public void assign() {
        // 1.2.3赋值
        int age = 1;
        int price = 0, amount = age;
        final int total = 100; // const
        int money;
        Scanner sc = new Scanner(System.in);
        money = sc.nextInt();
        System.out.println(total + " - " + money + " = " + (total - money));
    }

    void floatDouble() {
        // 1.3.1 浮点数
        double foot;
        float inch;
        Scanner in = new Scanner(System.in);
        foot = in.nextDouble(); //不精确相比于int
        inch = in.nextFloat(); //不精确相比于int
        System.out.println("total: " + ((foot + inch / 12) * 0.3048));
    }

    // 1.3
    //类型转换 只是copy去计算一个新的值，不会改变原来的value
    //当一个运算符只有一个算子的时候，它是单目运算符。
    //(int) 类型转换运算符是一个单目运算符（Unary Operator）。
    // 它只作用于一个算子operand，并将该算子转换为 int 类型。


    /**
     * Practice int float convert
     * 题目内容：
     * 写一个将华氏温度转换成摄氏温度的程序，转换的公式是：
     * °F = (9/5)*°C + 32
     * 其中C表示摄氏温度，F表示华氏温度。
     * 程序的输入是一个整数，表示华氏温度。输出对应的摄氏温度，也是一个整数。
     * 提示，为了把计算结果的浮点数转换成整数，需要使用下面的表达式：
     * (int)x;
     * 其中x是要转换的那个浮点数。
     * 注意：除了题目要求的输出，不能输出任何其他内容，比如输入时的提示，输出时的说明等等都不能。这道题目要求转换后的数字，程序就只能输出这个数字，除此之外任何内容都不能输出。
     * 输入格式:
     * 一个整数。
     * 输出格式：
     * 一个整数。
     * 输入样例：
     * 100
     * 输出样例：
     * 37
     * 时间限制：500ms内存限制：32000kb
     */
    void farenheitToInt() {
        int f;
        float x;
        Scanner in = new Scanner(System.in);
        f = in.nextInt();
        x = ((f - 32) / ((float) 9 / 5));
        System.out.println((int) x);
    }

}