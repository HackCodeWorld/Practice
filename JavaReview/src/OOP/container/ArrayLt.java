package OOP.container;

import java.util.ArrayList;

public class ArrayLt {
    public static void main(String[] args) {
        ArrayList<Integer> lt = new ArrayList(10);
//        System.out.println(lt.get(0)); // list container只有放进去才会真实size改变，直接取会indexOutOfBounds
        // remove add ...  bsaic CRUD operations

        // Object array, each element is the the object's manager, 不是对象本身

        // for each
        // primitive type
        int[] arr = new int[10];
        for (int k : arr) {
            // a copy of element k
            System.out.println(k);
        }
        // but for wrapper/obj element不同
        Value[] a = new Value[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Value();
            a[i].set(i);
        }
        for (Value v : a){
            System.out.println(v.get()); // 0 - 9
            v.set(0);
        }
        for (Value v : a){
            System.out.println(v.get()); // 0 - 9
        }
    }
}

class Value {
    private int i;
    public void set(int i) {
        this.i = i;
    }
    public int get() {
        return i;
    }
}
