package OOP.container;

import java.util.HashSet;

/**
 * Set container
 */
public class Set {
    public static void main(String[] args) {
        HashSet<String> s = new HashSet<>();
        s.add("first");
        s.add("second");
        s.add("third");
        s.add("third");
        for (String str : s) {
            System.out.println(str); // order diff & no duplicate
        }
        System.out.println(s); // ArrayList and Set can both print out without using loop, it implements toString
    }
}
