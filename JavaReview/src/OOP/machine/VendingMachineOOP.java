package OOP.machine;

import OOP.display.Show; //只要一个类不在你这个类的包，你要用的话就要import
// 也就是包的管理机制，包之间的.是包的层级关系链路: import java.util.Scanner(这个才是类名);

public class VendingMachineOOP { // public关键字 - public type name should == its file name
    // 当一个编译单元里面，只能有一个public：class display.Show
    private int price = 80; // private针对class，当前class所有对象可以访问
    int balance; //member variable will be automatically given 0
    int total;
    private static int step = 1; // static step shared by all objects

    void showPrompt() {
        System.out.println("Welcome");
    }

    void insertMoney(int amount) {
        balance = balance + amount;
    }

    void showBalance() {
        System.out.println(this.balance);
    }

    void getFood() {
        if (balance >= price) {
            System.out.println(balance);
        }

    }

    static void sharedFunc(){
        //        total++; 注意：static的函数不能用non-static field, 只能操作static变量
        step++; // 操作static field 就可以
    }

    static void sharedFunc2(){
        sharedFunc();
    }

    public static void main(String[] args) {
        VendingMachineOOP vm = new VendingMachineOOP();
        vm.showPrompt();
//        vm.insertMoney(100);
        vm.showBalance();
        Show show = new Show();
        show.show();

//        total++; 注意：static的函数不能用non-static field, 只能操作static变量

        System.out.println("------------");

        VendingMachineOOP v = new VendingMachineOOP();
        v.step = 1;
        System.out.println(v.step);
        VendingMachineOOP.step = 2;
        System.out.println(VendingMachineOOP.step);
        v.sharedFunc();
        VendingMachineOOP.sharedFunc();

        System.out.println("-------------");
        VendingMachineOOP vm1 = new VendingMachineOOP();
        vm1.total += 10;
        vm1.step = 100; // step shared by all objects
        VendingMachineOOP vm2 = new VendingMachineOOP();
        System.out.println(vm2.step);
        System.out.println(vm2.total);
    }
}

class ExampleClass {
    // 静态成员变量
    static int staticCounter = 0; // 实例共享

    // 实例成员变量
    int instanceCounter = 0; //实例单独副本

    public ExampleClass() {
        // 每创建一个对象实例，静态成员和实例成员的计数器都会增加
        staticCounter++;    // 对所有实例共享
        instanceCounter++;  // 只属于当前实例
    }

    public static void main(String[] args) {
        ExampleClass obj1 = new ExampleClass();
        ExampleClass obj2 = new ExampleClass();

        //共享会叠加
        System.out.println("Static Counter: " + ExampleClass.staticCounter); // 输出 2
        //副本不会
        System.out.println("Obj1 Instance Counter: " + obj1.instanceCounter); // 输出 1
        System.out.println("Obj2 Instance Counter: " + obj2.instanceCounter); // 输出 1
    }
}


