package inner_class;

public class InnerClass {
    public static void main(String[] args) {

    }

}


/**
 * 1. 局部内部类（有类名）
 * 定义在外部类的局部位置
 */
class OuterClass01 { // outer class
    private int n = 1; // private int

    private void m2() {
        private int a = 0;
        public int b = 2;
    } // private method

    void m1() { // a method
        public class Inner01 { // local inner class, can be final, no 修饰符
            private int w = 0;

            void f1() {
                public int o = 0;
                System.out.println("n = " + n); // private int
                m2(); // private method
            }
        }
        new Inner01().f1(); // ok, 直接new对象
    }

    { // OK inner class domain in a block
        final class Inner02 { // local inner class, can be final, no 修饰符
            private int w = 0;

            void f1() {
                public int o = 0;
                private int w = 0;
                System.out.println("n = " + n); // private int
                m2(); // private method
            }
        }
        new Inner02().f1(); // ok, 直接new对象
    }

    Inne02() // no 只在block 内
}

/**
 * 2.匿名内部类
 */
class OuterClass02 {
    public void method() { // 匿名内部类要写在方法里
        // compile name : IA
        IA ia = new IA() { //AnonymousClass 只用一次
            @Override
            public void smile() { // 把接口方法实现了

            }
        };
        ia.smile(); // 而且可以调用了，还不用专门写一个类
        // running name : OuterClass02$1
        System.out.println(ia.getClass());
    }

    int a = 0;
    int ok = 0;

    { //AnonymousClass 只用一次
        // 匿名内部类要写在block里
        a = 1;
        ok = 2;
    }
}

interface IA {
    public void smile();
}