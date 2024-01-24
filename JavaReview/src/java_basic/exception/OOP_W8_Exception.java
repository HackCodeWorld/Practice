package java_basic.exception;

import java.util.Scanner;

public class OOP_W8_Exception {
    public static void f() {
        int[] a = new int[10];
        a[10] = 10; // exception
        System.out.println("hello");
    }
    public static void g() {
        f();
    }
    public static void h() {
        int i = 10;
        if (i < 100) { // not a function, it's a block, 退出外层判断是不是try
            g();
        }
    }
    public static void k() {
        try {
            h(); // 是try
        } catch (NullPointerException e) { //catch不匹配
            System.out.println("k() caught an exception");
            // 不throw的话就是这里已经处理了异常，main不会接收到 IndexOutOfBounds异常了
        }
    }
    public static void main(String[] args) {
        try {
            k(); // 是try
        }catch (ArrayIndexOutOfBoundsException e){ //catch匹配
            System.out.println("Caught");
            System.out.println(e.getMessage());
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println("main");
    }
}
